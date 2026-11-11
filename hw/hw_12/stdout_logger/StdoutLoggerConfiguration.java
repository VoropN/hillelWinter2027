package hw_12.stdout_logger;

import hw_12.LoggerConfiguration;
import hw_12.LoggingLevel;

public class StdoutLoggerConfiguration implements LoggerConfiguration {
    private final LoggingLevel logLevel;
    private final long maxSize;
    private final StdoutFormat format;

    public StdoutLoggerConfiguration(LoggingLevel logLevel, long maxSize, StdoutFormat format) {
        this.logLevel = logLevel;
        this.maxSize = maxSize;
        this.format = format;
    }

    @Override
    public LoggingLevel getLogLevel() {
        return logLevel;
    }

    @Override
    public long getMaxSize() {
        return maxSize;
    }

    @Override
    public StdoutFormat getFormat() {
        return format;
    }

}
