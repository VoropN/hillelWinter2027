package com.fileLogger.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileLoggerConfigurationTest {
    @TempDir
    Path tempDir;

    @Test
    void getFileUsesConfiguredFormatExtension() {
        Path basePath = tempDir.resolve("application");
        FileLoggerConfiguration configuration =
            new FileLoggerConfiguration(basePath.toFile(), LoggingLevel.DEBUG, 1024, FileFormat.JSON);

        assertEquals(basePath.resolveSibling("application.json").toString(),
            configuration.getFile().toPath().toString());
    }

    @Test
    void createLogFileCreatesTimestampedFile() throws IOException {
        Path basePath = tempDir.resolve("application");
        FileLoggerConfiguration configuration =
            new FileLoggerConfiguration(basePath.toFile(), LoggingLevel.DEBUG, 1024, FileFormat.TXT);

        configuration.createLogFile();

        assertTrue(configuration.getFile().isFile());
        assertTrue(configuration.getFile().getName().matches(
            "application_.*\\.txt"
        ));
    }
}
