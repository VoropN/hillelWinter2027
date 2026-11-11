package hw_12.file_logger;

import hw_12.LogFormat;

public enum FileFormat implements LogFormat {
    TXT("txt"),
    JSON("json");

    private final String format;

    FileFormat(String format) {
        this.format = format;
    }

    public String getExtension() {
        return "." + format;
    }

    @Override
    public String getFormat() {
        return format;
    }
}
