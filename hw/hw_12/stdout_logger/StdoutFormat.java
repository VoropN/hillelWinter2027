package hw_12.stdout_logger;

import hw_12.LogFormat;

public enum StdoutFormat implements LogFormat {
    RESET("\u001B[0m"),
    BLUE("\u001B[34m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    RED("\u001B[31m");

    private final String format;

    StdoutFormat(String format) {
        this.format = format;
    }

    @Override
    public String getFormat() {
        return format;
    }
}
