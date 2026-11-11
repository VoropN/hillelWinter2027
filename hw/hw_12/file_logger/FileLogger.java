package hw_12.file_logger;

import hw_12.Logger;
import hw_12.LoggingLevel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// 1. Створити клас FileLogger. Клас здійснюватиме логування (протоколування) інформації в зазначений файл на підставі конфігураційного об'єкта.
public class FileLogger implements Logger {
    private final FileLoggerConfiguration config;

    public FileLogger(FileLoggerConfiguration config) {
        this.config = config;
    }

    // 3. У класі FileLogger. Створити методи debug та info, які як параметр приймають рядок-повідомлення. Метод повинен виконувати запис у вказаний у конфігурації файл у встановленому форматі для запису з конфігурації.
    public void debug(String messageLine) throws IOException {
        print(messageLine, LoggingLevel.DEBUG);
    }

    public void info(String messageLine) throws IOException {
        print(messageLine, LoggingLevel.INFO);
    }

    // Тобто, якщо активовано рівень DEBUG, то його також включається INFO, але не навпаки.
    private void print(String messageLine, LoggingLevel logLevel) throws IOException {
        if (config.getLogLevel() == LoggingLevel.INFO && logLevel == LoggingLevel.DEBUG) {
            return;
        }

        String message = getFormattedMessage(messageLine, logLevel);
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        File file = config.getOutput();
        long maxSize = config.getMaxSize();
        long currentSize = file.exists() ? Files.size(file.toPath()) : 0;

        try {
            if (messageBytes.length > maxSize - currentSize) {
                throw new FileMaxSizeReachedException(file, maxSize, currentSize, currentSize + messageBytes.length);
            }
        } catch (FileMaxSizeReachedException e) {
            System.err.println(e.getMessage().trim());
            if (messageBytes.length > maxSize) return;

            String previousPath = file.getAbsolutePath();
            config.createLogFile();
            file = config.getOutput();
            System.err.printf("""
                    Log file rotated
                      - from %s
                      - to   %s
                    """, previousPath, file.getAbsolutePath());
        }

        Files.write(file.toPath(), messageBytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    private String getFormattedMessage(String messageLine, LoggingLevel logLevel) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(dateFormat);

        return switch (config.getFormat()) {
            // Формат запису: [ПОТОЧНИЙ_ЧАС][DEBUG] Повідомлення: [СТРОКА-ПОВІДОМЛЕННЯ]
            case TXT -> "[%s][%s] Повідомлення: [%s]%n".formatted(timestamp, logLevel, messageLine);
            case JSON -> """
                    {"timestamp":"%s","level":"%s","message":"%s"}%n""".formatted(timestamp, logLevel, messageLine);
            default -> throw new UnsupportedOperationException(config.getFormat() + " log output is not supported");
        };
    }
}
