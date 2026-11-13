package findFileDuplicate.base;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;
import java.util.zip.CRC32;

public class Program1 {
    private static final long MAX_SIZE = 10L * 1024 * 1024;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static long lastTimeReported = 0;

    public static void main(String[] args) throws IOException {
        Path root = getScanPath();
        Map<Long, List<Path>> filesBySize = groupBySize(root);
        long filesToCheck = filesBySize.values().stream().mapToLong(List::size).sum();
        System.out.printf("Checking content of %d candidate files...%n", filesToCheck);
        Map<FileSignature, List<Path>> duplicates = groupByContent(filesBySize, filesToCheck);
        duplicates.values().forEach(paths -> {
            System.out.println("----------------------");
            paths.forEach(System.out::println);
        });
    }

    private static Map<FileSignature, List<Path>> groupByContent(
        Map<Long, List<Path>> filesBySize, long filesToCheck) throws IOException {
        Map<FileSignature, List<Path>> duplicates = new HashMap<>();
        long checked = 0;
        for (Map.Entry<Long, List<Path>> entry : filesBySize.entrySet()) {
            for (Path path : entry.getValue()) {
                FileSignature signature = new FileSignature(entry.getKey(), crc32(path));
                duplicates.computeIfAbsent(signature, ignored -> new ArrayList<>()).add(path);
                reportUpdate(++checked, filesToCheck);
            }
        }

        if (filesToCheck > 0) {
            System.out.println();
        }
        duplicates.values().removeIf(paths -> paths.size() < 2);
        return duplicates;
    }

    public static void reportUpdate(long checked, long filesToCheck) {
        long now = System.nanoTime();
        if (now - lastTimeReported >= 1000_000_000L) {
            lastTimeReported = now;
            System.out.printf("\rChecked %d/%d files", checked, filesToCheck);
            System.out.flush();
        }
    }

    private static Map<Long, List<Path>> groupBySize(Path root) throws IOException {
        Map<Long, List<Path>> filesBySize = new HashMap<>();
        System.out.println("Scanning files...");
        try (Stream<Path> paths = Files.walk(root)) {
            var iterator = paths.iterator();
            while (iterator.hasNext()) {
                Path path = iterator.next();
                if (Files.isRegularFile(path)) {
                    long size = Files.size(path);
                    if (size <= MAX_SIZE) {
                        filesBySize.computeIfAbsent(size, ignored -> new ArrayList<>()).add(path);
                    }
                }
            }
        }
        filesBySize.values().removeIf(paths -> paths.size() < 2);
        return filesBySize;
    }

    private static long crc32(Path path) throws IOException {
        CRC32 crc = new CRC32();
        byte[] buffer = new byte[8192];
        try (InputStream input = Files.newInputStream(path)) {
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                crc.update(buffer, 0, bytesRead);
            }
        }
        return crc.getValue();
    }

    private static Path getScanPath() {
        System.out.println("Enter directory path:");
        while (SCANNER.hasNextLine()) {
            Path path = Path.of(SCANNER.nextLine().strip());
            if (Files.isDirectory(path)) {
                return path;
            }
            System.out.println("Path is not a directory. Try again:");
        }
        throw new IllegalArgumentException("No valid directory path was provided.");
    }

    private record FileSignature(long size, long crc32) {
    }
}
