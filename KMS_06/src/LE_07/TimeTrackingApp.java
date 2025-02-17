package LE_07;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TimeTrackingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nSelect an option: ");
                System.out.println("1. Calculate Work Hours");
                System.out.println("2. Calculate Order Processing Time");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> WorkHoursManager.calculateWorkHours();
                    case 2 -> OrderManager.calculateOrderDuration();
                    case 3 -> {
                        System.out.println("Exiting");
                        return;
                    }
                    default -> System.out.println("❌ Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Please enter a valid option (1, 2, or 3).");
                sc.nextLine();

            }
        }
    }
}