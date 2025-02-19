package LE_09._03.management;

import LE_09._03.account.Account;
import LE_09._03.utils.Calculations;

import java.util.*;

public class AccountManager implements Calculations {
    private List<Account> accounts = new ArrayList<>();

    public void createAccount(Scanner sc) {
        while (true) {
            int accNo = 1;
            boolean found = false;

            while (!found) {
                found = true;

                for (Account acc : accounts) {
                    if (acc.getAccountNumber() == accNo) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    break;
                }
                accNo++;
            }

                    String accType = null;
                    double accBalance;


                    System.out.println("Select account type from the following options: ");
                    System.out.println("1: Checking\n2: Savings\n3: Business\n4: Exit");
                    System.out.print("Enter option: ");
                    try {
                        int option = sc.nextInt();
                        switch (option) {
                            case 1 -> accType = "Checking";
                            case 2 -> accType = "Savings";
                            case 3 -> accType = "Business";
                            case 4 -> {
                                System.out.print("Returning to main menu.");
                                return;
                            }
                            default -> {
                                System.out.println("Invalid option, please try again.\n");
                                continue;
                            }

                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: please enter an integer!\n");
                        sc.nextLine();
                        continue;
                    }

                    while (true) {
                        System.out.print("Enter starting balance: ");
                        if (!sc.hasNextDouble()) {
                            System.out.println("Invalid input, please enter a decimal number! (e.g. 10.00).");
                            sc.next();
                            continue;
                        }
                        accBalance = sc.nextDouble();
                        break;
                    }

                    Account acc = new Account(accNo, accBalance, accType);
                    accounts.add(acc);
                    System.out.println(accType + " account created.");
                    Collections.sort(accounts);
                }
            }


    public void deleteAccount(Scanner sc) {
        while (true) {
            System.out.println("Enter account number to delete or -1 to exit: ");
            try {
                int accNo = sc.nextInt();
                if (!(accNo == -1)) {
                    Account accountToDelete = accounts.stream().filter(a -> a.getAccountNumber() == accNo).findFirst().orElse(null);

                    if (accountToDelete != null) {
                        accounts.remove(accountToDelete);
                        System.out.println("Account " + accNo + " has been removed.");
                    } else {
                        System.out.println("Account does not exist! Please try again..");
                        sc.nextLine();
                        continue;
                    }
                }
                return;
            } catch (InputMismatchException e) {
                System.out.println("Error: please enter an integer!\n");
                sc.nextLine();
            }
        }

    }

    public void displayAccountBalance(Scanner sc) {
        while (true) {
            System.out.println("Enter account number to display the balance or -1 to exit: ");
            try {
                int accNo = sc.nextInt();
                if (!(accNo == -1)) {
                    Account accountToDisplay = accounts.stream().filter(a -> a.getAccountNumber() == accNo).findFirst().orElse(null);
                    if (accountToDisplay != null) {
                        System.out.println("Account balance for " + accNo + ": " + accountToDisplay.getBalance());
                    } else {
                        System.out.println("Account does not exist! Please try again.");
                        sc.nextLine();
                    }
                }
                return;
            } catch (InputMismatchException e) {
                System.out.println("Error: please enter an integer!\n");
                sc.nextLine();}
        }
    }

    public void makeWithdrawal(Scanner sc){
        while (true) {
            System.out.println("Enter account number to withdraw from or -1 to exit: ");
            try {
                int accNo = sc.nextInt();
                if (!(accNo == -1)) {
                    Account accountToWithdraw = accounts.stream().filter(a -> a.getAccountNumber() == accNo).findFirst().orElse(null);
                    if (accountToWithdraw != null) {
                        sc.nextLine();
                        System.out.println("How much would you like to withdraw from " + accNo + "?");
                        if (!sc.hasNextDouble()) {
                            System.out.println("Invalid input, please enter a decimal number! (e.g. 10.00).");
                            sc.next();
                            continue;
                        }
                        double withdrawAmount = sc.nextDouble();
                        if (withdrawAmount > accountToWithdraw.getBalance()) {
                            System.out.println("Insufficient funds!");
                            sc.nextLine();
                        } else {
                            System.out.println("Withdraw successful!");
                            System.out.println("New balance:" + withdraw(accountToWithdraw.getBalance(), withdrawAmount));
                            accountToWithdraw.setBalance(withdraw(accountToWithdraw.getBalance(), withdrawAmount));
                        }
                    } else {
                        System.out.println("Account does not exist! Please try again.");
                        sc.nextLine();
                    }
                }
                return;
            } catch (InputMismatchException e) {
                System.out.println("Error: please enter a decimal number!\n");
                sc.nextLine();}
        }
    }

    public void makeDeposit(Scanner sc) {
        while (true) {
            System.out.println("Enter account number to deposit to or -1 to exit: ");
            try {
                int accNo = sc.nextInt();
                if (!(accNo == -1)) {
                    Account accountToDeposit = accounts.stream().filter(a -> a.getAccountNumber() == accNo).findFirst().orElse(null);
                    if (accountToDeposit != null) {
                        sc.nextLine();
                        System.out.println("How much would you like to deposit to " + accNo + "?");
                        if (!sc.hasNextDouble()) {
                            System.out.println("Invalid input, please enter a decimal number! (e.g. 10.00).");
                            sc.next();
                            continue;
                        }
                        double depositAmount = sc.nextDouble();
                        System.out.println("Deposit successful!");
                        System.out.println("New balance:" + deposit(accountToDeposit.getBalance(), depositAmount));
                        accountToDeposit.setBalance(deposit(accountToDeposit.getBalance(), depositAmount));

                    } else {
                        System.out.println("Account does not exist! Please try again.");
                        sc.nextLine();
                    }
                }
                return;
            } catch (InputMismatchException e) {
                System.out.println("Error: please enter a decimal number!\n");
                sc.nextLine();
            }
        }
    }

    @Override
    public double withdraw(double balance, double amount) {
        return balance - amount;
    }

    @Override
    public double deposit(double balance, double amount) {
        return balance + amount;
    }

    public void displayAccount(Scanner sc) {
        while (true) {
            System.out.println("Enter account number to display the details or -1 to exit: ");
            try {
                int accNo = sc.nextInt();
                if (!(accNo == -1)) {
                    Account accountToDisplay = accounts.stream().filter(a -> a.getAccountNumber() == accNo).findFirst().orElse(null);
                    if (accountToDisplay != null) {
                        System.out.println(accountToDisplay);
                    } else {
                        System.out.println("Account does not exist! Please try again.");
                        sc.nextLine();
                    }
                }
                return;
            } catch (InputMismatchException e) {
                System.out.println("Error: please enter an integer!\n");
                sc.nextLine();
            }
        }
    }

    public void displayAllAccounts() {
        for (Account acc : accounts) {
            System.out.println(acc.toString());
        }
    }
}




