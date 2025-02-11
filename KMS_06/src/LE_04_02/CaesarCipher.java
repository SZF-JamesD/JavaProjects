package LE_04_02;

import java.util.Scanner;

public class CaesarCipher {
    public static String encrypt(String input, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';

                char shiftedChar = (char) (((currentChar - base + shift) % 26 + 26) % 26 + base);
                result.append(shiftedChar);
            } else {
                result.append(currentChar);
            }
        }
    return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a string to encrypt: ");
        String input = sc.nextLine();

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

        String encryptedString = encrypt(input, shift);
        System.out.println("Encrypted string: " + encryptedString);

        sc.close();
    }
}