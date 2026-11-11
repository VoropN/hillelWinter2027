package hw_12.file_logger;

public enum ConfigKeys {
    FILE,
    LEVEL,
    MAX_SIZE,
    FORMAT;

    public static ConfigKeys fromKey(String key) {
        return ConfigKeys.valueOf(key.replace("-", "_").toUpperCase());
    }
}
