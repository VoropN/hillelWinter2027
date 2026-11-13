package findFileDuplicate.base;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ProgramTest {
    public static void main(String[] args) throws IOException {
        Path root = Files.createTempDirectory("duplicate-finder-test");
        try {
            Path firstDirectory = Files.createDirectories(root.resolve("first"));
            Path secondDirectory = Files.createDirectories(root.resolve("second"));
            Path thirdDirectory = Files.createDirectories(root.resolve("third"));
            Path fourthDirectory = Files.createDirectories(root.resolve("fourth"));

            Path firstDuplicate = Files.writeString(firstDirectory.resolve("duplicate.txt"), "identical");
            Path renamedDuplicate = Files.writeString(secondDirectory.resolve("copy.bin"), "identical");
            Files.writeString(thirdDirectory.resolve("duplicate.txt"), "different");
            Files.writeString(fourthDirectory.resolve("duplicate.txt"), "different size");
            Files.writeString(firstDirectory.resolve("unique.txt"), "unique content");

            ByteArrayOutputStream progressBytes = new ByteArrayOutputStream();
            Map<Program.FileSignature, List<String>> actual;
            try (PrintStream progress = new PrintStream(progressBytes)) {
                actual = Program.findDuplicates(List.of(root), Long.MAX_VALUE, progress);
            }
            List<String> expectedPaths = Stream.of(firstDuplicate, renamedDuplicate)
                    .map(path -> path.toAbsolutePath().normalize().toString())
                    .sorted()
                    .toList();

            if (actual.size() != 1 || !actual.values().iterator().next().equals(expectedPaths)) {
                throw new AssertionError("Unexpected duplicate results: " + actual);
            }
            String progress = progressBytes.toString();
            if (!progress.contains("Scanning directories")
                    || !progress.contains("Scanning:")
                    || !progress.contains("Hashing file")) {
                throw new AssertionError("Progress output was incomplete: " + progress);
            }

            Map<Program.FileSignature, List<String>> multipleRootsResult =
                    findDuplicates(List.of(firstDirectory, secondDirectory), Long.MAX_VALUE);
            if (multipleRootsResult.size() != 1
                    || !multipleRootsResult.values().iterator().next().equals(expectedPaths)) {
                throw new AssertionError("Unexpected results for multiple roots: " + multipleRootsResult);
            }

            Map<Program.FileSignature, List<String>> limitedResult = findDuplicates(List.of(root), 9);
            if (limitedResult.size() != 1
                    || !limitedResult.values().iterator().next().equals(expectedPaths)) {
                throw new AssertionError("Unexpected results at the inclusive size limit: " + limitedResult);
            }

            Map<Program.FileSignature, List<String>> belowLimitResult = findDuplicates(List.of(root), 8);
            if (!belowLimitResult.isEmpty()) {
                throw new AssertionError("Files above the size limit were not excluded: " + belowLimitResult);
            }

            System.out.println("ProgramTest passed.");
        } finally {
            try (var paths = Files.walk(root)) {
                for (Path path : paths.sorted(java.util.Comparator.reverseOrder()).toList()) {
                    Files.deleteIfExists(path);
                }
            }
        }
    }

    private static Map<Program.FileSignature, List<String>> findDuplicates(List<Path> roots, long maxSizeBytes)
            throws IOException {
        try (PrintStream progress = new PrintStream(OutputStream.nullOutputStream())) {
            return Program.findDuplicates(roots, maxSizeBytes, progress);
        }
    }
}
