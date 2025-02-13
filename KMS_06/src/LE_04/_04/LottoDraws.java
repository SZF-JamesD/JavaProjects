package LE_04._04;

import java.util.Scanner;
import java.util.Random;
// 6 from 1-45
public class LottoDraws {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int amount;
        int[] numbers_drawn = new int[45];
        while (true) {
            System.out.println("Enter the number of lotto draws:");
            if (sc.hasNextInt()) {
                amount = sc.nextInt();
                sc.nextLine();
                break;
            } else {
                System.out.println("Error: Please enter an integer number.");
                sc.nextLine();
            }
        }
        int i = 0;

        while (i < amount) {
            int j = 0;
            i++;
            while (j < 6) {
                numbers_drawn[rand.nextInt(45)]++;
                j++;
            }
        }

        for (int n = 0; n < numbers_drawn.length; n++) {
            System.out.printf("%d: %d - %.2f%%\n", n+1, numbers_drawn[n], (numbers_drawn[n] / (double)(amount*6)) * 100 );
        }
        }
    }
