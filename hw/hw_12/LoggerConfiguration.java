package hw_12;

public interface LoggerConfiguration {
    LoggingLevel getLogLevel();

    long getMaxSize();

    LogFormat getFormat();
}
