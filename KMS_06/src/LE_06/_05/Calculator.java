package LE_06._05;

import java.util.Scanner;

public class Calculator {
    public static int calculate(int a, int b, char operator) {
        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> (b != 0) ? a / b : 0;
            default -> throw new ArithmeticException("Invalid operator.");
        };
    }

    public static double calculate(double a, double b, char operator) {
        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> (b != 0) ? a / b : 0.0;
            default -> throw new ArithmeticException("Invalid operator.");
        };
    }
}