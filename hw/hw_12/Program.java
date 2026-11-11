package hw_12;

import java.io.File;
import java.io.IOException;

public class Program {
    static void main(String[] args) throws IOException {
        runInfoMode();
        runDebugMode();
    }

    static void runInfoMode() throws IOException {
        String dir = String.join(File.separator, "hw", "hw_12", "logs", "info_log");
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
        String configPath = String.join(File.separator, "hw", "hw_12", "config.txt");
        FileLoggerConfiguration config = new FileLoggerConfigurationLoader(configPath).load();
        FileLogger fileLogger = new FileLogger(config);
        for (int i = 0; i < 10; i++) {
            fileLogger.info("some info message " + i);
        }
        for (int i = 0; i < 1000; i++) {
            fileLogger.debug("some message " + i);
        }
    }
}
