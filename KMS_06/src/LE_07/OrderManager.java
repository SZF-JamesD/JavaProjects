package LE_07;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class OrderManager {
    public static void calculateOrderDuration() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter order date (DD-MM-YYYY): ");
            LocalDate orderDate = LocalDate.parse(sc.next(), TimeUtils.DATE_FORMAT);

            System.out.print("Enter delivery date (DD-MM-YYYY): ");
            LocalDate deliveryDate = LocalDate.parse(sc.next(), TimeUtils.DATE_FORMAT);

            if (deliveryDate.isBefore(orderDate)) {
                throw new IllegalArgumentException("Delivery date cannot be before order date.");
            }

            long daysBetween = ChronoUnit.DAYS.between(orderDate, deliveryDate);

            System.out.println("Days between " + orderDate.format(TimeUtils.DATE_FORMAT) + " and " + deliveryDate.format(TimeUtils.DATE_FORMAT) + " are " + daysBetween);

    } catch (DateTimeParseException e) {
        System.out.println("❌ Invalid date format! Please enter a valid date (DD-MM-YYYY)!");
    } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}