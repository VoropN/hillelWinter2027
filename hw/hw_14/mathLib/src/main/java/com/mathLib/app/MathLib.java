package com.mathLib.app;


public class MathLib {
    public static int add(int a, int b) {
        return a + b;
    }

    public static int div(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Dividing on 0 not allowed!");
        }
        return a / b;
    }

    public static int abs(int a) {
        return a > 0 ? a : a * -1;
    }

    public static double sqrt(int a) {
        return Math.sqrt(a);
    }

    public static double pow(double a, double b) {
        return Math.pow(a, b);
    }
}
