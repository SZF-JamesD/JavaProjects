package LE_03_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int year;
        while (true) {
            System.out.print("Enter a year (YYYY): ");
            if (sc.hasNextInt()) {
                year = sc.nextInt();
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    System.out.format("%d is a Leap Year", year);
                } else {
                    System.out.format("%d is not a Leap Year", year);
                }
                break;
            } else {
                System.out.println("Invalid input, please enter a full number in the YYYY format.");
            }
        }

        int startYear, endYear;
        while (true) {
            System.out.println("Enter two years (YYYY YYYY): ");
            if (sc.hasNextInt()) {
                startYear = sc.nextInt();
                if (sc.hasNextInt()) {
                    endYear = sc.nextInt();
                    if (startYear < endYear) {
                        break;
                    } else {
                        System.out.format("Second year must be greater than the first year.");
                    }
                }
            } else {
                System.out.println("Invalid input, please enter full numbers in the YYYY format.");
            }

        }

        List<Integer> leapYears = new ArrayList<>();
        for (int i = startYear; i <= endYear; i++) {
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                leapYears.add(i);
            }
        }

        if (leapYears.isEmpty()) {
            System.out.format("No leap years found between %d and %d.", startYear, endYear);
        } else {
            System.out.format("Leap years between %d and %d:\n", startYear, endYear);
            for (int i : leapYears) {
                System.out.println(i);
            }
        }

        sc.close();
    }
}