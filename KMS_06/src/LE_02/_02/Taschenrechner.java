package LE_02._02;

import java.util.Scanner;

public class Taschenrechner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first number: ");
        double num1 = sc.nextDouble();

        System.out.println("Enter operator (+, -, *, /): ");
        String op = sc.next();

        System.out.println("Enter second number: ");
        double num2 = sc.nextDouble();

        sc.close();

        double result = 0;

        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Error: Division by zero is not allowed.");
                    return;
                }
                break;
            default:
                System.out.println("Error: Unknown operator.");
                return;
        }
        System.out.println("Result of: " + num1 + " " + op + " " + num2 + " = " + result);
    }
}