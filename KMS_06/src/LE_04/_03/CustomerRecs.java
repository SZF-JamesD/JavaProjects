package LE_04._03;

import java.util.Scanner;
import java.util.Random;

public class CustomerRecs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int n;
        int[] results;
        int bad = 0, okay = 0, good = 0;

        while (true) {
            System.out.println("Enter the number of reviews you require: ");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                results = new int[n];
                break;
            } else{
                System.out.println("Error: Please enter a positive integer.");
            }
        }
        for (int i = 0; i < n; i++) {
            results[i] = rand.nextInt(3)+1;
        }

        for (int i : results) {
            switch (i) {
                case 1:
                    bad += 1;
                    break;
                case 2:
                    okay += 1;
                    break;
                case 3:
                    good += 1;
            }
        }
        System.out.println("Results: ");
        System.out.println("Total votes: " + n);
        System.out.printf("Bad: %d - %.2f%%\n", bad, (bad / (double)n) * 100);
        System.out.printf("Okay: %d - %.2f%%\n", okay, (okay / (double)n) * 100);
        System.out.printf("Good: %d - %.2f%%\n", good, (good / (double)n) * 100);

        double overallRating = ((bad) + (2 * okay) + (3 * good)) / (double) n;
        System.out.printf("Overall Rating: %.2f / 3",overallRating);


    }
}