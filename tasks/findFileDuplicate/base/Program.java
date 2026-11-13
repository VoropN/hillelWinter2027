package findFileDuplicate.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.LongConsumer;
import java.util.zip.CRC32;
import java.util.stream.Stream;

public class Program {
    private static final long BYTES_PER_MEGABYTE = 1024L * 1024;
    private static final long PROGRESS_INTERVAL_NANOS = 500_000_000L;

    record FileSignature(long size, long crc32) {}

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Path> roots = getScanPaths(args, scanner);
            long maxSizeBytes = getMaxSizeBytes(scanner);
            System.out.println(findDuplicates(roots, maxSizeBytes, System.err));
        }
    }

    static Map<FileSignature, List<String>> findDuplicates(
            List<Path> roots, long maxSizeBytes, PrintStream progressOutput) throws IOException {
        if (maxSizeBytes < 0) {
            throw new IllegalArgumentException("Maximum file size cannot be negative.");
        }

        Map<Long, List<Path>> filesBySize = new HashMap<>();
        Set<Path> scannedFiles = new HashSet<>();
        long visitedEntries = 0;
        long eligibleFiles = 0;
        long lastProgressTime = System.nanoTime();

        progressOutput.println("Scanning directories...");
        for (Path root : roots) {
            if (!Files.isDirectory(root)) {
                throw new IllegalArgumentException("Not a directory: " + root);
            }

            try (Stream<Path> paths = Files.walk(root)) {
                var iterator = paths.iterator();
                while (iterator.hasNext()) {
                    Path path = iterator.next();
                    visitedEntries++;
                    if (!Files.readAttributes(path, BasicFileAttributes.class).isRegularFile()) {
                        lastProgressTime = reportScanProgress(
                                progressOutput, visitedEntries, eligibleFiles, lastProgressTime, false
                        );
                        continue;
                    }

                    Path normalizedPath = path.toAbsolutePath().normalize();
                    if (scannedFiles.add(normalizedPath)) {
                        long size = Files.size(normalizedPath);
                        if (size <= maxSizeBytes) {
                            filesBySize.computeIfAbsent(size, ignored -> new ArrayList<>()).add(normalizedPath);
                            eligibleFiles++;
                        }
                    }
                    lastProgressTime = reportScanProgress(
                            progressOutput, visitedEntries, eligibleFiles, lastProgressTime, false
                    );
                }
            }
        }
        reportScanProgress(progressOutput, visitedEntries, eligibleFiles, lastProgressTime, true);

        long filesToHash = filesBySize.values().stream()
                .filter(paths -> paths.size() > 1)
                .mapToLong(List::size)
                .sum();
        Map<FileSignature, List<String>> duplicates = new LinkedHashMap<>();
        if (filesToHash == 0) {
            progressOutput.println("No files need content comparison.");
            return duplicates;
        }

        progressOutput.println("Comparing file contents...");
        long hashedFiles = 0;
        lastProgressTime = System.nanoTime();
        for (Map.Entry<Long, List<Path>> sizeEntry : filesBySize.entrySet()) {
            if (sizeEntry.getValue().size() < 2) {
                continue;
            }

            Map<FileSignature, List<String>> filesBySignature = new LinkedHashMap<>();
            for (Path path : sizeEntry.getValue()) {
                long fileSize = sizeEntry.getKey();
                long[] progressTime = {lastProgressTime};
                long completedBeforeCurrent = hashedFiles;
                FileSignature signature = new FileSignature(
                        fileSize,
                        crc32(path, bytes -> {
                            progressTime[0] = reportHashProgress(
                                    progressOutput, completedBeforeCurrent + 1, filesToHash, path,
                                    bytes, fileSize, progressTime[0], false
                            );
                        })
                );
                filesBySignature.computeIfAbsent(signature, ignored -> new ArrayList<>()).add(path.toString());
                hashedFiles++;
                lastProgressTime = reportHashProgress(
                        progressOutput, hashedFiles, filesToHash, path, fileSize, fileSize,
                        progressTime[0], hashedFiles == filesToHash
                );
            }

            filesBySignature.forEach((signature, matchingPaths) -> {
                if (matchingPaths.size() > 1) {
                    matchingPaths.sort(Comparator.naturalOrder());
                    duplicates.put(signature, matchingPaths);
                }
            });
        }

        return duplicates;
    }

    private static long reportScanProgress(
            PrintStream output, long entries, long files, long lastUpdate, boolean force) {
        long now = System.nanoTime();
        if (force || now - lastUpdate >= PROGRESS_INTERVAL_NANOS) {
            output.printf("Scanning: %d entries visited, %d files within the size limit.%n", entries, files);
            output.flush();
            return now;
        }
        return lastUpdate;
    }

    private static long reportHashProgress(
            PrintStream output, long completed, long total, Path currentFile, long bytesRead,
            long fileSize, long lastUpdate, boolean force) {
        long now = System.nanoTime();
        if (force || now - lastUpdate >= PROGRESS_INTERVAL_NANOS) {
            int filePercent = fileSize == 0 ? 100 : (int) (bytesRead * 100.0 / fileSize);
            output.printf(
                    "Hashing file %d/%d: %s (%d%%).%n",
                    completed, total, currentFile.getFileName(), filePercent
            );
            output.flush();
            return now;
        }
        return lastUpdate;
    }

    private static long getMaxSizeBytes(Scanner scanner) {
        System.out.println("Maximum file size in MB (0 = no limit):");
        if (!scanner.hasNextLine()) {
            throw new IllegalArgumentException("No maximum file size was provided.");
        }

        String input = scanner.nextLine().strip();
        try {
            long maxSizeMegabytes = Long.parseLong(input);
            if (maxSizeMegabytes < 0) {
                throw new IllegalArgumentException("Maximum file size cannot be negative.");
            }
            return maxSizeMegabytes == 0
                    ? Long.MAX_VALUE
                    : Math.multiplyExact(maxSizeMegabytes, BYTES_PER_MEGABYTE);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Maximum file size must be a whole number of megabytes.", e);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Maximum file size is too large.", e);
        }
    }

    private static long crc32(Path path, LongConsumer progress) throws IOException {
        CRC32 crc32 = new CRC32();
        byte[] buffer = new byte[8192];
        long bytesReadTotal = 0;
        try (InputStream input = Files.newInputStream(path)) {
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                crc32.update(buffer, 0, bytesRead);
                bytesReadTotal += bytesRead;
                progress.accept(bytesReadTotal);
            }
        }
        return crc32.getValue();
    }

    private static List<Path> getScanPaths(String[] args, Scanner scanner) {
        List<Path> roots = new ArrayList<>();
        if (args.length > 0) {
            for (String arg : args) {
                roots.add(Path.of(arg));
            }
            return roots;
        }

        System.out.println("Enter directory path:");
        if (scanner.hasNextLine()) {
            roots.add(Path.of(scanner.nextLine().strip()));
        }
        if (roots.isEmpty()) {
            throw new IllegalArgumentException("No directory path was provided.");
        }
        return roots;
    }
}
