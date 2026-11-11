package hw_12.stdout_logger;

import hw_12.LoggerConfigurationLoader;
import hw_12.LoggingLevel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Map;

public class StdoutLoggerConfigurationLoader implements LoggerConfigurationLoader {
    private final String configPath;

    public StdoutLoggerConfigurationLoader(String configPath) {
        this.configPath = configPath;
    }

    public StdoutLoggerConfiguration load() {
        return loadConfig(configPath);
    }

    public StdoutLoggerConfiguration load(String path) {
        return loadConfig(path);
    }

    private StdoutLoggerConfiguration loadConfig(String path) {
        Map<ConfigKeys, String> map = parseConfig(path);
        LoggingLevel logLevel = LoggingLevel.valueOf(requireValue(map, ConfigKeys.LEVEL));
        int maxSize;
        try {
            maxSize = Integer.parseInt(requireValue(map, ConfigKeys.MAX_SIZE));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("MAX-SIZE must be a positive integer", e);
        }
        StdoutFormat format = StdoutFormat.valueOf(requireValue(map, ConfigKeys.FORMAT));

        return new StdoutLoggerConfiguration(logLevel, maxSize, format);
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
