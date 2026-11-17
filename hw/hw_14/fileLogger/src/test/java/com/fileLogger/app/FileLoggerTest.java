package com.fileLogger.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileLoggerTest {
    @TempDir
    private Path tempDir;

    @Mock
    private FileLoggerConfiguration config;

    @InjectMocks
    private FileLogger logger;

    @Test
    void debug() throws IOException {
        String timestamp = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Path filePath = tempDir.resolve("text.txt");
        String message = "Hello world!";
        when(config.getLogLevel()).thenReturn(LoggingLevel.DEBUG);
        when(config.getFormat()).thenReturn(FileFormat.TXT);
        when(config.getMaxSize()).thenReturn(3000L);
        when(config.getFile()).thenReturn(filePath.toFile());
        logger.debug(message);
        assertEquals(Files.readString(filePath), "[%s][DEBUG] Повідомлення: [%s]\n".formatted(timestamp, message));
    }

    @Test
    void debugWithInfo() throws IOException {
        String timestamp = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Path filePath = tempDir.resolve("text.txt");
        String message = "Hello world!";
        when(config.getLogLevel()).thenReturn(LoggingLevel.DEBUG);
        when(config.getFormat()).thenReturn(FileFormat.TXT);
        when(config.getMaxSize()).thenReturn(3000L);
        when(config.getFile()).thenReturn(filePath.toFile());
        logger.info(message);
        assertEquals(Files.readString(filePath),
            "[%s][INFO] Повідомлення: [%s]\n".formatted(timestamp, message));
    }

    @Test
    void info() throws IOException {
        String timestamp = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Path filePath = tempDir.resolve("text.txt");
        String message = "Hello world!";
        when(config.getLogLevel()).thenReturn(LoggingLevel.INFO);
        when(config.getFormat()).thenReturn(FileFormat.TXT);
        when(config.getMaxSize()).thenReturn(3000L);
        when(config.getFile()).thenReturn(filePath.toFile());
        logger.info(message);
        assertEquals(Files.readString(filePath),
            "[%s][INFO] Повідомлення: [%s]\n".formatted(timestamp, message));
    }

    @Test
    void infoWithDebug() throws IOException {
        String message = "Hello world!";
        when(config.getLogLevel()).thenReturn(LoggingLevel.INFO);
        logger.debug(message);

        verify(config, never()).getFormat();
        verify(config, never()).getMaxSize();
        verify(config, never()).getFile();
    }
}