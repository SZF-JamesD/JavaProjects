package LE_03_01;

import java.util.Scanner;

public class Validation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //product name
        String name;
        while (true){
            System.out.print("Enter product name: ");
            name =  sc.nextLine().trim();
            if (name.matches("^[\\p{L}0-9\\s\\p{Punct}]+$")) {
                break;
            } else {
                System.out.println("Invalid product name.");
            }
        }

        //quantity
        int quantity;
        while (true) {
            System.out.print("Enter quantity (whole number only): ");
            if (sc.hasNextInt()) {
                quantity = sc.nextInt();
                if (quantity > 0) {
                    break;
                } else {
                    System.out.println("Error: Quantity must be a positive number.");
                }
            } else {
                System.out.println("Error: Quantity must be a whole number.");
                sc.next();
            }
        }

        //price
        double price;
        while (true) {
            System.out.print("Enter price (decimal number): ");
            if (sc.hasNextDouble()) {
                price = sc.nextDouble();
                if (price > 0) {
                    break;
                } else{
                    System.out.println("Error: Price must be a positive number.");
                }
            } else {
                System.out.println("Error: Price must be a decimal number.");
                sc.next();
            }
        }

        System.out.print("\nProduct Information:");
        System.out.println("Product name: " + name);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: €" + price);

        sc.close();
    }
}