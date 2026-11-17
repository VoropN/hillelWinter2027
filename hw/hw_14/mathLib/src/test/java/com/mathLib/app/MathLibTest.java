package com.mathLib.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("MathLib validator test")
class MathLibTest {

    @Test
    void add() {
        Assertions.assertEquals(3, MathLib.add(1, 2));
    }

    @Test
    @DisplayName("Division: regular")
    void div() {
        Assertions.assertEquals(4, MathLib.div(8, 2));
    }

    @Test
    @DisplayName("Division: on zero")
    void div_zero_throws() {
        Assertions.assertThrows(ArithmeticException.class, () -> MathLib.div(2, 0));
    }

    @ParameterizedTest(name = "abs({0}) = {1}")
    @CsvSource({
        "-2, 2",
        "-1, 1",
        "0, 0",
        "3, 3",
        "5, 5"
    })
    void abs(int input, int expect) {
        Assertions.assertEquals(expect, MathLib.abs(input));
    }

    @Test
    void sqrt() {
        Assertions.assertEquals(Math.sqrt(24), MathLib.sqrt(24));
    }

    @Test
    void pow() {
        Assertions.assertEquals(Math.pow(10.1, 12.2), MathLib.pow(10.1, 12.2));
    }
}