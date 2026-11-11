package hw_12;

import java.io.IOException;

public interface Logger {
    void debug(String messageLine) throws IOException;

    void info(String messageLine) throws IOException;
}
