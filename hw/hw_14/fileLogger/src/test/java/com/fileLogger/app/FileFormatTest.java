package com.fileLogger.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileFormatTest {

    @Test
    void getExtension() {
        Assertions.assertEquals(".txt", FileFormat.TXT.getExtension());
        Assertions.assertEquals(".json", FileFormat.JSON.getExtension());
    }
}