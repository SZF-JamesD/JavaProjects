package LE_06._03;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class DataValidation {
    public static void main(String[] args) {

        try {
            Random rand = new Random();

            int[] transmittedData = generateRandArray(rand.nextInt(50));
            System.out.println("Transmitted data: " + Arrays.toString(transmittedData));

            int[] sequence = getVerificationSequence();

            int[] completeData = appendVerificationSequence(transmittedData, sequence);

            boolean isSequenceValid = containsSequence(completeData, sequence);

            if (isSequenceValid) {
                System.out.println("\nVerification sequence found." + Arrays.toString(sequence));
            } else {
                System.out.println("\nVerification sequence not found.");
            }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }


    public static int[] generateRandArray(int length) {
        Random rand = new Random();
        int[] array = new int[length];
        try{
            for (int i = 0; i < length; i++) {
                array[i] = rand.nextInt(10);
            }
        } catch (Exception e){
            System.out.println("Error generating random array: " + e.getMessage());
        }
        return array;
    }


    public static int[] getVerificationSequence() {
        Scanner sc = new Scanner(System.in);
        int[] sequence = new int[4];
        while (true) {
            System.out.println("Please enter your 4 number validation sequence (space-seperated e.g. 1 2 3 4): ");
            try{
                String[] inputs = sc.nextLine().split(" ");
                if (inputs.length != 4) {
                    throw new IllegalArgumentException("Please enter exactly 4 integers.");
                }

                for (int i = 0; i < 4; i++) {
                    sequence[i] = Integer.parseInt(inputs[i]);
                }

                break;
            } catch (Exception e) {
                System.out.println("Invalid input. " + e.getMessage());
            }
        }
        return sequence;
    }

    public static int[] appendVerificationSequence(int[] transmittedData, int[] sequence) {
        int[] newSequence = new int[sequence.length + transmittedData.length];
        System.arraycopy(transmittedData, 0, newSequence, 0, transmittedData.length);
        System.arraycopy(sequence, 0, newSequence, transmittedData.length, sequence.length);

        return newSequence;
    }

    public static boolean containsSequence(int[] completeData, int[] sequence) {
        try {
            int verCheck[] = Arrays.copyOfRange(completeData, completeData.length - 4, completeData.length);
            boolean areEqual = Arrays.equals(verCheck, sequence);
            if (areEqual) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error during sequence check: " + e.getMessage());
            return false;
        }
    }
}


