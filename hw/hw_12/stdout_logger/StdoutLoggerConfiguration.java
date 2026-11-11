package hw_12.stdout_logger;

import hw_12.LoggerConfiguration;
import hw_12.LoggingLevel;

public class StdoutLoggerConfiguration implements LoggerConfiguration<Integer> {
    private final LoggingLevel logLevel;
    private final Integer maxSize;
    private final StdoutFormat format;

    public StdoutLoggerConfiguration(LoggingLevel logLevel, Integer maxSize, StdoutFormat format) {
        if (maxSize == null || maxSize <= 0) {
            throw new IllegalArgumentException("Maximum stdout message size must be greater than zero");
        }
        this.logLevel = logLevel;
        this.maxSize = maxSize;
        this.format = format;
    }

    @Override
    public LoggingLevel getLogLevel() {
        return logLevel;
    }

    @Override
    public Integer getMaxSize() {
        return maxSize;
    }

    @Override
    public StdoutFormat getFormat() {
        return format;
    }

}
