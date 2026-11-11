package hw_12.stdout_logger;

import hw_12.Logger;
import hw_12.LoggingLevel;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StdoutLogger implements Logger {
    private static final DateTimeFormatter dateFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final StdoutLoggerConfiguration config;

    public StdoutLogger(StdoutLoggerConfiguration config) {
        this.config = config;
    }

    @Override
    public void debug(String messageLine) throws IOException {
        if (config.getLogLevel() == LoggingLevel.DEBUG) {
            print(messageLine, LoggingLevel.DEBUG);
        }
    }

    @Override
    public void info(String messageLine) throws IOException {
        print(messageLine, LoggingLevel.INFO);
    }

    private void print(String messageLine, LoggingLevel level) {
        String timestamp = LocalDateTime.now().format(dateFormat);
        System.out.printf("%s[%s][%s] Повідомлення: [%s]%s%n",
                config.getFormat().getFormat(),
                timestamp,
                level,
                messageLine,
                StdoutFormat.RESET.getFormat());
    }
}
