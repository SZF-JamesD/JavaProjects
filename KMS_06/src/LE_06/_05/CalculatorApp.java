package LE_06._05;

import java.util.Scanner;

public class CalculatorApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        String num1 = sc.next();

        System.out.print("Enter an operator (+, -, *, /): ");
        char operator = sc.next().charAt(0);

        System.out.print("Enter the second number: ");
        String num2 = sc.next();

        if (num1.contains(".") || num2.contains(".")) {
            double a = Double.parseDouble(num1);
            double b = Double.parseDouble(num2);
            System.out.println("Result: " + Calculator.calculate(a, b, operator));
        } else {
            int a = Integer.parseInt(num1);
            int b = Integer.parseInt(num2);
            System.out.println("Result: " + Calculator.calculate(a, b, operator));
        }

        sc.close();
    }
}