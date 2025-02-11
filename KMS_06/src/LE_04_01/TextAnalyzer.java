package LE_04_01;

import java.util.Scanner;

public class TextAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter text to analyze: ");
        String text = sc.nextLine();

        int vowels = 0, consonants = 0, punctuation = 0, spaces = 0;

        text = text.toLowerCase();

        String vowelChars = "aeiou";

        for (char ch: text.toCharArray()) {
            if (Character.isLetter(ch)) {
                if (vowelChars.indexOf(ch) != -1) {
                    vowels++;
                } else {
                    consonants++;
                }
            } else if (Character.isWhitespace(ch)) {
                spaces++;
            } else if (Character.isDigit(ch)) {
            } else {
                punctuation++;
            }
        }

        System.out.println("Total Length: " + text.length());
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
        System.out.println("Punctuation: " + punctuation);
        System.out.println("Spaces: " + spaces);

        sc.close();
    }
}