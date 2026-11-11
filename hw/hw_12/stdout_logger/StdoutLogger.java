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
        int start = 0;
        while (start < messageLine.length()) {
            if (start > 0) {
                System.out.println("---Continue previous message---");
            }
            String messageCount = start > 0 ? "(" + start / config.getMaxSize() + ")" : "";
            int end = Math.min(messageLine.length(), start + config.getMaxSize());
            System.out.printf("%s[%s][%s] Повідомлення%s: [%s]%s%n",
                    config.getFormat().getFormat(),
                    timestamp,
                    level,
                    messageCount,
                    messageLine.substring(start, end),
                    StdoutFormat.RESET.getFormat());
            start = end;
        }
    }
}
