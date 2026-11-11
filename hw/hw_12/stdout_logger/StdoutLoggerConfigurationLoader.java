package hw_12.stdout_logger;

import hw_12.LoggerConfiguration;
import hw_12.LoggerConfigurationLoader;

public class StdoutLoggerConfigurationLoader implements LoggerConfigurationLoader {
    private final StdoutLoggerConfiguration config;

    public StdoutLoggerConfigurationLoader(StdoutLoggerConfiguration config) {
        this.config = config;
    }

    @Override
    public LoggerConfiguration load() {
        return config;
    }
}
