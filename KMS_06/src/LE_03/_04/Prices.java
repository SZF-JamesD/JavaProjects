package LE_03._04;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Prices {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double lower, upper, step;
        List<Double> prices = new ArrayList<>();

        while (true) {
            System.out.println("Enter the lower and upper price limits: ");
            if (sc.hasNextDouble()) {
                lower = sc.nextDouble();
                if (sc.hasNextDouble()) {
                    upper = sc.nextDouble();
                    break;
                }
            } else {
                System.out.println("Error: Please enter valid prices.");
            }
        }

        step = (upper - lower) / 10;
        for (int i = 0; i <= 10; i++) {
            prices.add(lower + (i * step));
        }
        System.out.printf("%-15s%-10s\n", "Amount", "Single Price");

        System.out.printf("%-15s", "");
        for (double price : prices) {
            System.out.printf("%10.2f", price);
        }
        System.out.println();


        for (int quantity = 10; quantity <= 100; quantity += 10) {
            System.out.printf("%-15d", quantity);
            for (double price : prices) {
                System.out.printf("%10.2f", quantity * price);
            }
            System.out.println();
        }
        sc.close();
    }//amount x price. list of prices and amounts, 2d array?
}