package LE_06._01;

import java.util.Random;

public class MinMaxArrays {
    public static void main(String[] args) {
        int rows = 10;
        int cols = 10;

        int[][] salesMatrix = new int[rows][cols];

        Random rand = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                salesMatrix[i][j] = rand.nextInt(1000);
            }
        }

        System.out.println("Sales Matrix (10x10):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(salesMatrix[i][j] + "\t");
            }
            System.out.println();
        }
        int min = findMin(salesMatrix);
        int max = findMax(salesMatrix);

        System.out.println("\nMinimum: " + min);
        System.out.println("Maximum: " + max);
    }

    private static int findMin(int[][] salesMatrix) {
        int min = salesMatrix[0][0];

        for (int i = 0; i < salesMatrix.length; i++) {
            for (int j = 0; j < salesMatrix[i].length; j++) {
                if (salesMatrix[i][j] < min) {
                    min = salesMatrix[i][j];
                }
            }
        }
        return min;
    }

    private static int findMax(int[][] salesMatrix) {
        int max = salesMatrix[0][0];
        for (int i = 0; i < salesMatrix.length; i++) {
            for (int j = 0; j < salesMatrix.length; j++) {
                if (salesMatrix[i][j] > max) {
                    max = salesMatrix[i][j];
                }
            }
        }
        return max;
    }
}