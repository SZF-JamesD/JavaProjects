package LE_09._03.account;

public class Account implements Comparable<Account>{
    private int accountNumber;
    private double balance;
    private String accountType;

    public Account(int accountNumber, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public int compareTo(Account o) {
        return Integer.compare(this.accountNumber, o.accountNumber);
    }

    @Override
    public String toString() {
        return String.format("\nAccount Number: %d\nBalance: €%.2f\nAccount Type: %s\n", getAccountNumber(), getBalance(), getAccountType());
    }
}