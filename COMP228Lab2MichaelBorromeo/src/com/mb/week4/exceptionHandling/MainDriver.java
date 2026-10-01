package com.mb.week4.exceptionHandling;

/**
 * @author michael_borromeo	
 * @date 2026-10-01
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class MainDriver {

	public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankAccount[] accounts = new BankAccount[3];

        System.out.println("===================================");
        System.out.println("\tWelcome To The Bank");
        System.out.println("===================================");

        for (int i = 0; i < accounts.length; i++) {

            while (true) {

                try {

                    System.out.println("\nEnter information for account " + (i + 1));

                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter account number (9 digits): ");
                    String accountNumber = sc.nextLine();

                    System.out.print("Enter initial balance: $");
                    double balance = sc.nextDouble();

                    sc.nextLine();

                    accounts[i] = new BankAccount(accountNumber, name, balance);

                    System.out.println("Account created successfully!");

                    break;

                } catch (InputMismatchException e) {

                    System.out.println("Invalid input. Please enter a valid number.");

                    sc.nextLine();

                } catch (IllegalArgumentException e) {

                    System.out.println("Error: " + e.getMessage());

                    System.out.println(
                            "Please enter the account information again.");
                }
            }
        }


        boolean running = true;

        while (running) {

            System.out.println("\n===================================");
            System.out.println("\tBank Menu");
            System.out.println("===================================");
            System.out.println("1. View Accounts");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.println("===================================");

            System.out.print("What would you like to do? ");

            try {

                int option = sc.nextInt();
                sc.nextLine();

                switch (option) {

                    case 1:

                        System.out.println("\nAccount Information:");

                        for (BankAccount account : accounts) {
                        	account.displayInfo();
                        }
                        
                        System.out.println("Click ENTER to go back");
                        sc.nextLine();
                        
                        break;

                    case 2:

                        try {

                            System.out.println("\nWhich account would you like to access?");

                            for (int i = 0; i < accounts.length; i++) {
                                System.out.println((i + 1) + ". " + accounts[i].getName() + " - Account " + accounts[i].getAccountNumber());
                            }

                            System.out.print("Enter your choice: ");
                            int accountChoice = sc.nextInt();

                            if (accountChoice < 1 || accountChoice > accounts.length) {

                                throw new IllegalArgumentException("Please select an account between 1 and 3.");
                            }
                            

                            BankAccount selectedAccount =
                                    accounts[accountChoice - 1];

                            System.out.print("Enter deposit amount: $");
                            double amount = sc.nextDouble();

                            selectedAccount.deposit(amount);

                        } catch (InputMismatchException e) {

                            System.out.println("Invalid input. Please enter a valid number.");


                        } catch (IllegalArgumentException e) {

                            System.out.println("Error: " + e.getMessage());

                        } finally {

                            System.out.println("Transaction has been processed.");
                        }
                        
                        break;

                    case 3:

                        try {

                            System.out.println("\nWhich account would you like to access?");

                            for (int i = 0; i < accounts.length; i++) {
                                System.out.println( (i + 1) + ". " + accounts[i].getName() + " - Account " + accounts[i].getAccountNumber());
                            }

                            System.out.print("Enter your choice: ");
                            int accountChoice = sc.nextInt();

                            if (accountChoice < 1 || accountChoice > accounts.length) {
                                throw new IllegalArgumentException("Please select an account between 1 and 3.");
                            }

                            BankAccount selectedAccount =
                                    accounts[accountChoice - 1];

                            System.out.print("Enter withdrawal amount: $");
                            double amount = sc.nextDouble();

                            selectedAccount.withdrawal(amount);

                        } catch (InputMismatchException e) {

                            System.out.println("Invalid input. Please enter a valid number.");

                            sc.nextLine();

                        } catch (InsufficientFundsException e) {

                            System.out.println("Withdrawal Error: " + e.getMessage());

                        } catch (IllegalArgumentException e) {

                            System.out.println("Error: " + e.getMessage());

                        } finally {

                            System.out.println("Transaction has been processed.");
                        }


                        break;

                    case 4:

                        running = false;

                        System.out.println("\nThank you for using the bank system!");

                        break;

                    default:

                    System.out.println("Invalid option. Please select 1, 2, 3, or 4.");

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid input. Please enter a number.");

            }
        }

        sc.close();
    }
}


