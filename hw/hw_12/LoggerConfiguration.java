package hw_12;

public interface LoggerConfiguration<T extends Number> {
    LoggingLevel getLogLevel();

    T getMaxSize();

    LogFormat getFormat();
}
