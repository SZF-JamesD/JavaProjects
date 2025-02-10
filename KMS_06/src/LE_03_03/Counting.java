package LE_03_03;

import java.util.Scanner;

public class Counting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start, end, step;
        while (true) {
            System.out.print("Enter a start int, end int, and the step size: ");
            if (sc.hasNextInt()) {
                start = sc.nextInt();
                if (sc.hasNextInt()) {
                    end = sc.nextInt();
                    if (sc.hasNextInt()) {
                        step = sc.nextInt();
                        break;
                    }
                }
            } else {
                System.out.println("Please enter 3 integers.");
            }
        }
        System.out.format("Numbers between %d and %d in steps of %d\n", start, end, step);
        if (start > end) {//start bigger than end, - steps if i < end
            for (int i = start; i >= end; i -= step) {
                System.out.println(i);
            }
        } else {
            for (int i = start; i <= end; i += step) {
                System.out.println(i);
            }
        }
        sc.close();
    }
}