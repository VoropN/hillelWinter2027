package hw_12.file_logger;

import hw_12.LoggerConfiguration;
import hw_12.LoggingLevel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 3. Створити клас FileLoggerConfiguration. Клас представляє конфігурацію для логування. Складається з властивостей: файл, куди буде записуватися інформація, поточний рівень логування, максимальний розмір файлу (в байтах), формат для запису в файл.
public class FileLoggerConfiguration implements LoggerConfiguration<Long> {
    private final File file;
    private final DateTimeFormatter suffixFormat = DateTimeFormatter.ofPattern("_dd.MM.yyyy-HH:mm:ss");
    private final LoggingLevel logLevel;
    private final long maxSize;
    private final FileFormat format;
    private String path;

    public FileLoggerConfiguration(File file, LoggingLevel logLevel, long maxSize, FileFormat format) {
        this.file = file;
        this.logLevel = logLevel;
        this.maxSize = maxSize;
        this.format = format;
    }

    public File getOutput() {
        if (path == null) {
            path = getBasePath() + format.getExtension();
            File parent = file.getAbsoluteFile().getParentFile();
            if (parent != null) parent.mkdirs();
        }
        return new File(path);
    }

    public LoggingLevel getLogLevel() {
        return logLevel;
    }

    public Long getMaxSize() {
        return maxSize;
    }

    public FileFormat getFormat() {
        return format;
    }

    // 6. ** При досягненні максимального розміру файлу або його перевищенні, створювати новий (додатковий) файл для зберігання . Ім'я кожного нового файлу має містити дату створення.
    public void createLogFile() throws IOException {
        String timestamp = LocalDateTime.now().format(suffixFormat);
        File parent = file.getAbsoluteFile().getParentFile();
        if (parent != null) Files.createDirectories(parent.toPath());
        int sequence = 0;
        while (true) {
            String collisionSuffix = sequence == 0 ? "" : "_" + sequence;
            File log = new File(getBasePath() + timestamp + collisionSuffix + format.getExtension());
            if (log.createNewFile()) {
                path = log.getPath();
                return;
            }
            sequence++;
        }
    }

    private String getBasePath() {
        String basePath = file.getPath();
        String extension = format.getExtension();
        return basePath.endsWith(extension)
                ? basePath.substring(0, basePath.length() - extension.length())
                : basePath;
    }
}
