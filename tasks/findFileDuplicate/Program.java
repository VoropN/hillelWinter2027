package findFileDuplicate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;
import java.util.zip.CRC32;

public class Program {
    private static final Scanner sc = new Scanner(System.in);

    static void main(String[] args) {
        File root = getScanPath();
        long maxSize = 100L * 1024 * 1024; // 100 MB
        Map<Long, List<Path>> longListMap = groupBySize(root.toPath(), maxSize);
        Map<Long, List<Path>> crc32ListMap = groupByCRC(longListMap);
        for (List<Path> list : crc32ListMap.values()) {
            System.out.println("---------------------");
            for (Path path : list) {
                System.out.println(path);
            }
        }
    }

    private static Map<Long, List<Path>> groupByCRC(Map<Long, List<Path>> longListMap) {
        Map<Long, List<Path>> crc32ListMap = new HashMap<>();
        longListMap.values().forEach(list -> list.forEach(path -> {
            CRC32 crc = new CRC32();
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(path.toUri()));
                crc.update(bytes);
                crc32ListMap.computeIfAbsent(crc.getValue(), _ -> new ArrayList<>()).add(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        crc32ListMap.entrySet().removeIf(entry -> entry.getValue().size() == 1);
        return crc32ListMap;
    }

    private static Map<Long, List<Path>> groupBySize(Path root, long maxSize) {
        Map<Long, List<Path>> longListMap = new HashMap<>();
        if (root == null) return longListMap;
        try (Stream<Path> paths = Files.walk(root)) {
            paths.forEach(path -> {
                try {
                    if (Files.isDirectory(path)) return;
                    long size = Files.size(path);
                    if (size > maxSize) {
                        System.out.println("File was skipped: " + path);
                        return;
                    }
                    longListMap.computeIfAbsent(size, _ -> new ArrayList<>()).add(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        longListMap.entrySet().removeIf(entry -> entry.getValue().size() == 1);
        return longListMap;
    }

    private static File getScanPath() {
        System.out.println("Enter directory path: ");
        while (sc.hasNextLine()) {
            String path = sc.nextLine();
            File file = new File(path.strip());
            if (file.exists() && file.isDirectory()) {
                System.out.println("Root: " + file.getAbsoluteFile());
                return file;
            }
        }
        return null;
    }
}
