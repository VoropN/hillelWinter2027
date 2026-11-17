package com.fileLogger.app;

public enum FileFormat {
    TXT("txt"),
    JSON("json");

    private final String format;

    FileFormat(String format) {
        this.format = format;
    }

    public String getExtension() {
        return "." + format;
    }
}
