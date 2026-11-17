package com.fileLogger.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileLoggerConfigurationLoaderTest {
    @TempDir
    Path tempDir;

    @Test
    void loadReadsConfigurationFromFile() throws IOException {
        Path configFile = tempDir.resolve("logger.txt");
        Files.writeString(configFile, """
            FILE: application.txt
            LEVEL: DEBUG
            MAX-SIZE: 5 KB
            FORMAT: TXT
            """);

        FileLoggerConfiguration configuration =
            new FileLoggerConfigurationLoader(configFile.toString()).load();

        assertEquals("application.txt", configuration.getFile().getName());
        assertEquals(LoggingLevel.DEBUG, configuration.getLogLevel());
        assertEquals(5L * 1024, configuration.getMaxSize());
        assertEquals(FileFormat.TXT, configuration.getFormat());
    }

    @Test
    void loadRequiresConfigPath() {
        assertThrows(IllegalArgumentException.class,
            () -> new FileLoggerConfigurationLoader(" ").load());
    }

    @Test
    void loadRequiresAllValues() throws IOException {
        Path configFile = tempDir.resolve("logger.txt");
        Files.writeString(configFile, "FILE: application.txt");

        assertThrows(IllegalArgumentException.class,
            () -> new FileLoggerConfigurationLoader(configFile.toString()).load());
    }

    @Test
    void loadReportsMissingFile() {
        assertThrows(UncheckedIOException.class,
            () -> new FileLoggerConfigurationLoader(tempDir.resolve("missing.txt").toString()).load());
    }
}
