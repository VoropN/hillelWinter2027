package hw_14;

import com.fileLogger.app.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Program {
    private static final String configPath = Files.isRegularFile(Path.of("config.txt"))
        ? "."
        : String.join(File.separator, "hw", "hw_14");

    public static void main(String[] args) throws IOException {
        runInfoMode();
        runDebugMode();
    }

    static void runInfoMode() throws IOException {
        String dir = String.join(File.separator, configPath, "logs", "info_log");
        File file = new File(dir);
        FileLoggerConfiguration config = new FileLoggerConfiguration(file, LoggingLevel.INFO, SizeUnit.KB.toBytes(50), FileFormat.TXT);
        FileLogger fileLogger = new FileLogger(config);
        for (int i = 0; i < 1000; i++) {
            // should do nothing as debug not available in info mode
            fileLogger.debug("some debug message " + i);
        }
        for (int i = 0; i < 1000; i++) {
            fileLogger.info("some message " + i);
        }

    }

    static void runDebugMode() throws IOException {
        String configFile = String.join(File.separator, configPath, "config.txt");
        FileLoggerConfiguration config = new FileLoggerConfigurationLoader(configFile).load();
        FileLogger fileLogger = new FileLogger(config);
        for (int i = 0; i < 10; i++) {
            fileLogger.info("some info message " + i);
        }
        for (int i = 0; i < 1000; i++) {
            fileLogger.debug("some message " + i);
        }
    }
}
