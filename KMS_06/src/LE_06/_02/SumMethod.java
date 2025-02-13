package LE_06._02;

import java.util.Scanner;

public class SumMethod {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            int[] sales;
            while (true) {
                System.out.println("How many sales figures would you like to enter?: ");
                if (sc.hasNextInt()) {
                    int a = sc.nextInt();
                    sales = new int[a];

                    for (int i = 0; i < a; i++) {
                        System.out.println("Enter sales figure " + (i+1) + ": ");
                        if (sc.hasNextInt()) {
                            sales[i] = sc.nextInt();
                        } else {
                            System.out.println("❌ Invalid input! Please enter an integer value");
                            sc.nextLine();
                            i--;
                        }
                    }
                    break;

                } else{
                    System.out.println("❌ Invalid input! Please enter an integer");
                    sc.nextLine();
                }
            }
            sumSales(sales);

        }catch (Exception e) {
            System.out.println("❌ Failed to initialize scanner: " + e.getMessage());}
    }

    public static void sumSales(int... numbers) {
        int sum = 0;

        for (int number : numbers) {
            sum += number;
        }

        System.out.println("\nThe sum of the sales figures is: " + sum);
        System.out.println("The number of sales figures entered is " + numbers.length);
    }
}