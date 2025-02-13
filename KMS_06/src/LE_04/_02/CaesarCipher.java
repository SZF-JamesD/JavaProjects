package LE_04._02;

import java.util.Scanner;

public class CaesarCipher {
    public static char[] encrypt(char[] input, int shift) {
        int length = input.length;


        char[] result = new char[length];

        for (int i = 0; i < length; i++) {
            char currentChar = input[i];

            if ((currentChar >= 'A' && currentChar <= 'Z') || (currentChar >= 'a' && currentChar <= 'z')) {
                char base = (currentChar >= 'A' && currentChar <= 'Z') ? 'A' : 'a';
                char shiftedChar = (char) (((currentChar - base + shift) % 26 + 26) % 26 + base);
                result[i] = shiftedChar;
            } else {
                result[i] = currentChar;
            }
        }

        return result;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a string to encrypt: ");
        char[] input = sc.nextLine().toCharArray();

        int shift = 0;
        while (true) {
            System.out.println("Enter the shift value (integer): ");
            if (sc.hasNextInt()) {
                shift = sc.nextInt();
                sc.nextLine();
                break;
            } else {
                System.out.println("Invalid input, please enter an integer.");
                sc.nextLine();
            }
        }

        char[] encryptedChars = encrypt(input, shift);
        System.out.println("Encrypted string: ");
        for (char c : encryptedChars) {
            System.out.print(c);
        }
        System.out.println();

        sc.close();
    }
}