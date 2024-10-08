package finance.console;

import finance.transaction.Transaction;
import finance.transaction.TransactionManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TransactionMenuManager {

    private final TransactionManager transactionManager;
    private final Scanner scanner;

    public TransactionMenuManager(TransactionManager transactionManager, Scanner scanner) {
        this.transactionManager = transactionManager;
        this.scanner = scanner;
    }

    public void addTransactionsMenu() {

        scanner.nextLine();  // Consume the newline

        System.out.println("--------------------Add Transaction--------------------");

        LocalDate date = getDateInput();
        double amount = getAmountInput();
        String category = getCategoryInput();
        String type = getTransactionType();
        boolean recurring = isRecurringTransaction();

        Transaction transaction = Transaction.builder()
                .amount(amount)
                .category(category)
                .date(date)
                .type(type)
                .recurring(recurring)
                .build();

        System.out.println(transaction);

        if (confirmTransaction()) {
            transactionManager.addTransaction(transaction);
            System.out.println("Transaction Added!");
        } else {
            System.out.println("Transaction Deleted.");
        }
    }

    public LocalDate getDateInput() {
        LocalDate date;
        System.out.println("Please enter a date for your transaction ie: YYYY/MM/DD (leave blank for current date)");
        while (true) {
            String userDateInput = scanner.nextLine();
            if (userDateInput.isEmpty()) {
                return LocalDate.now(); // Use current date if input is empty
            } else {
                date = validateDateInput(userDateInput);
                if (date != null) return date; // Return valid date
            }
        }
    }

    public LocalDate validateDateInput(String userDateInput) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        try {
            return LocalDate.parse(userDateInput, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please try again using the correct format YYYY/MM/DD");
            return null;
        }
    }

    private double getAmountInput() {
        double amount;
        System.out.println("Please enter the transaction amount...");
        while (true) {
            try {
                amount = scanner.nextDouble();
                scanner.nextLine();  // Consume the newline
                return amount;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input please input a numerical value ie: 10.99");
                scanner.next();  // Clear the invalid input from the scanner
            }
        }
    }

    private String getCategoryInput() {
        System.out.println("Please enter a category for your transaction...");
        return scanner.nextLine().toUpperCase();
    }

    private String getTransactionType() {
        String type = "";
        System.out.print("Is this transaction... \n 1. Income \n 2. Expense \n");
        while (true) {
            try {
                int userTypeInput = scanner.nextInt();
                scanner.nextLine();  // Consume the newline
                if (userTypeInput == 1) {
                    return "INCOME";
                }
                if (userTypeInput == 2) {
                    return "OUTGOING";
                }
                System.out.println("Invalid Input please select 1 for Income or 2 for Expense");
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input please select 1 for Income or 2 for Expense");
                scanner.next();  // Clear the invalid input from the scanner
            }
        }
    }

    private boolean isRecurringTransaction() {
        System.out.println("Does this transaction happen every month? (Y / N)");
        while (true) {
            String recurringUserInput = scanner.nextLine().toUpperCase();
            if (recurringUserInput.equals("Y") || recurringUserInput.equals("YES")) {
                return true;
            } else if (recurringUserInput.equals("N") || recurringUserInput.equals("NO")) {
                return false;
            } else {
                System.out.println("Invalid Input please enter Yes or No");
            }
        }
    }

    private boolean confirmTransaction() {
        System.out.println("Is this transaction correct? (Y/N)");
        while (true) {
            String userInput = scanner.nextLine().toUpperCase();
            if (userInput.equals("Y") || userInput.equals("YES")) {
                return true;
            } else if (userInput.equals("N") || userInput.equals("NO")) {
                return false;
            } else {
                System.out.println("Invalid Input please enter Yes or No");
            }
        }
    }
}
