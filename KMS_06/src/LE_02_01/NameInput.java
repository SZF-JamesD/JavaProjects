package LE_02_01;

import java.util.Scanner;

public class NameInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your first name: ");
        String firstName = sc.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = sc.nextLine();

        System.out.println("Your full name is: " + firstName + " " + lastName);

        sc.close();
    }
}