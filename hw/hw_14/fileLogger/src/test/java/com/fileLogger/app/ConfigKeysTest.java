package com.fileLogger.app;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ConfigKeysTest {
    @ParameterizedTest
    @EnumSource(ConfigKeys.class)
    @DisplayName("Should convert enum keys")
    void fromKey(ConfigKeys input) {
        Assertions.assertEquals(input, ConfigKeys.fromKey(input.toString()));
    }

    @Test
    @DisplayName("Should replace '-' with '_'")
    void from_string_to_max_size_key() {
        Assertions.assertEquals(ConfigKeys.MAX_SIZE, ConfigKeys.fromKey("MAX-SIZE"));
    }
}