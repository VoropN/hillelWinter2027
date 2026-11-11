package hw_12;

import hw_12.file_logger.*;
import hw_12.stdout_logger.StdoutFormat;
import hw_12.stdout_logger.StdoutLogger;
import hw_12.stdout_logger.StdoutLoggerConfiguration;
import hw_12.stdout_logger.StdoutLoggerConfigurationLoader;

import java.io.File;
import java.io.IOException;

public class Program {
    static void main(String[] args) throws IOException {
        runStoutInfoMode();
        runStoutDebugMode();
        runDebugMode();
    }

    static void runStoutInfoMode() throws IOException {
        StdoutLoggerConfiguration config = new StdoutLoggerConfiguration(LoggingLevel.INFO, 100, StdoutFormat.GREEN);
        Logger logger = new StdoutLogger(config);
        logger.info("some info message");
    }

    static void runStoutDebugMode() throws IOException {
        String configPath = String.join(File.separator, "hw", "hw_12", "stdout_logger", "config.txt");
        StdoutLoggerConfigurationLoader loader = new StdoutLoggerConfigurationLoader(configPath);
        Logger logger = new StdoutLogger(loader.load());
        logger.debug("some debug message");
        logger.debug("some debug message: but long" + "-longgg".repeat(100));
    }

    static void runInfoMode() throws IOException {
        String dir = String.join(File.separator, "hw", "hw_12", "file_logger", "logs", "info_log");
        File file = new File(dir);
        FileLoggerConfiguration config = new FileLoggerConfiguration(file, LoggingLevel.INFO, SizeUnit.KB.toBytes(50), FileFormat.TXT);
        Logger fileLogger = new FileLogger(config);
        for (int i = 0; i < 1000; i++) {
            // should do nothing as debug not available in info mode
            fileLogger.debug("some debug message " + i);
        }
        for (int i = 0; i < 1000; i++) {
            fileLogger.info("some message " + i);
        }

    }

    static void runDebugMode() throws IOException {
        String configPath = String.join(File.separator, "hw", "hw_12", "file_logger", "config.txt");
        FileLoggerConfiguration config = new FileLoggerConfigurationLoader(configPath).load();
        Logger fileLogger = new FileLogger(config);
        for (int i = 0; i < 10; i++) {
            fileLogger.info("some info message " + i);
        }
        for (int i = 0; i < 1000; i++) {
            fileLogger.debug("some message " + i);
        }
    }
}
