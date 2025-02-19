package LE_09._03.main;

import LE_09._03.management.AccountManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManagementApp {
    public static void main(String[] args) {
        AccountManager accManager = new AccountManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try{
                System.out.println("\nWelcome to the Account Management System\nPlease make a selection: ");
                System.out.println("1: Add an Account\n2: Delete an account\n3: Make a Withdrawal\n4: Make a Deposit\n5: Display Info of an Account\n6: Display Info of all Accounts\n7: Exit");
                System.out.print("\nEnter your selection: ");
                int selection = sc.nextInt();
                switch (selection) {
                    case 1 -> {
                        sc.nextLine();
                        accManager.createAccount(sc);
                    }
                    case 2 -> {
                        sc.nextLine();
                        accManager.deleteAccount(sc);
                    }
                    case 3 -> {
                        sc.nextLine();
                        accManager.makeWithdrawal(sc);
                    }
                    case 4 -> {
                        sc.nextLine();
                        accManager.makeDeposit(sc);
                    }
                    case 5 -> {
                        sc.nextLine();
                        accManager.displayAccount(sc);
                    }
                    case 6 -> {
                        sc.nextLine();
                        accManager.displayAllAccounts();
                    }
                    case 7 -> {
                        System.out.println("\nThank you for using our Account Management System!");
                        return;
                    }
                    default -> {
                        System.out.println("\nPlease enter a valid selection!");
                    }
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid input, please enter a valid selection");
                sc.next();
            }
        }
    }
}
