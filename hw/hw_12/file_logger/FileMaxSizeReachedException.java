package hw_12.file_logger;

import java.io.File;

// При досягненні максимального розміру файлу або його перевищення, викинути виняток FileMaxSizeReachedException з повідомленням максимального і поточного розміру файлу, шляхи до файлу.
public class FileMaxSizeReachedException extends RuntimeException {
    public FileMaxSizeReachedException(File file, long maxSize, long currentSize, long attemptedSize) {
        super("""
                        File max size reached: max allowed size is [%d] bytes; \
                        current size is [%d] bytes and writing this message would make it [%d] bytes. \
                        File: [%s].
                        """.formatted(
                        maxSize,
                        currentSize,
                        attemptedSize,
                        file.getAbsolutePath()
                )
        );
    }
}
