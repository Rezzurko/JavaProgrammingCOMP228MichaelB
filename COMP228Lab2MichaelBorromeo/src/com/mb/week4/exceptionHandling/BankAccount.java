package com.mb.week4.exceptionHandling;

/**
 * @author michael_borromeo	
 * @date 2026-10-01
 */

public class BankAccount {
	
	private String accountNumber;
    private String name;
    private double balance;

    public BankAccount(String accountNumber, String name, double initialBalance) {

        if (accountNumber.length() != 9) {
            throw new IllegalArgumentException("Account number must contain exactly 9 digits");
        }
        
        if (accountNumber == null) {
            throw new IllegalArgumentException("Account number can not be blank");
        }

        if (name == null) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }

        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }

        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0.");
        }

        balance += amount;

        System.out.printf("Successfully deposited $%.2f. Current balance: $%.2f%n", amount, balance);
    }

    public void withdrawal(double amount) throws InsufficientFundsException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Withdrawal amount must be greater than 0.");
        }

        if (amount > balance) {
            throw new InsufficientFundsException(String.format("Insufficient funds. Your current balance is $%.2f.", balance));
        }

        balance -= amount;

        System.out.printf("Successfully withdrew $%.2f. Current balance: $%.2f%n", amount, balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void displayInfo() {
        System.out.println("-------------------------------");
        System.out.println("Name: " + name);
        System.out.println("Account Number: " + accountNumber);
        System.out.printf("Balance: $%.2f%n", balance);
        System.out.println("-------------------------------");
    }
}

