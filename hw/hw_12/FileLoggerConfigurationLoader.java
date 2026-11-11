package hw_12;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// 5. * Створити клас FileLoggerConfigurationLoader. Об'єкт даного типу за допомогою методу load, завантажуватиме конфігурацію для логування із зазначеного файлу. Метод повертає об'єкт типу FileLoggerConfiguration.
public class FileLoggerConfigurationLoader {
    private String configPath;

    public FileLoggerConfigurationLoader(String configPath) {
        this.configPath = configPath;
    }

    public FileLoggerConfiguration load() {
        return loadConfig(configPath);
    }

    public FileLoggerConfiguration load(String path) {
        return loadConfig(path);
    }

    private FileLoggerConfiguration loadConfig(String path) {
        Map<ConfigKeys, String> map = parseConfig(path);
        LoggingLevel logLevel = LoggingLevel.valueOf(requireValue(map, ConfigKeys.LEVEL));
        String configuredMaxSize = requireValue(map, ConfigKeys.MAX_SIZE);
        SizeUnit unit = SizeUnit.fromSuffix(configuredMaxSize);
        long maxSize = unit.toBytes(configuredMaxSize);
        FileFormat fileFormat = FileFormat.valueOf(requireValue(map, ConfigKeys.FORMAT));
        File file = new File(requireValue(map, ConfigKeys.FILE));

        return new FileLoggerConfiguration(file, logLevel, maxSize, fileFormat);
    }

    private String requireValue(Map<ConfigKeys, String> map, ConfigKeys key) {
        String value = map.get(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing configuration value for " + key);
        }
        return value;
    }

    private Map<ConfigKeys, String> parseConfig(String path) {
        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException("Configuration file path must not be blank");
        }

        Map<ConfigKeys, String> map = new HashMap<>();
        try (FileReader fileReader = new FileReader(path);
             BufferedReader reader = new BufferedReader(fileReader)
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(":", 2);
                if (parts.length != 2) continue;
                ConfigKeys key = ConfigKeys.fromKey(parts[0].trim());
                map.put(key, parts[1].trim());
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read logger configuration: " + path, e);
        }
        return map;
    }
}
