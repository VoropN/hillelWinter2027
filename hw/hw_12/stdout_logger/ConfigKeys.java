package hw_12.stdout_logger;

import java.util.Locale;

public enum ConfigKeys {
    LEVEL,
    MAX_SIZE,
    FORMAT;

    public static ConfigKeys fromKey(String key) {
        return valueOf(key.replace("-", "_").toUpperCase(Locale.ROOT));
    }
}
