package finance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleManager {

    private final Scanner scanner = new Scanner(System.in);
    private final TransactionManager transactionManager;

    public ConsoleManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void mainMenu() {

        boolean saveAndQuit = false;
        while (!saveAndQuit) {
            System.out.println("-".repeat(30));
            System.out.print("""
                    Main Menu:
                    1. Add Transaction
                    2. Find Transaction(s)
                    3. View Reports
                    4. Quit
                    Please select what you would like to do:
                    """);

            try {
                int input = scanner.nextInt();

                switch (input) {
                    case 1 -> addTransactionsMenu();
                    case 2 -> searchTransactionsMenu();
                    case 3 -> showReportsMenu();
                    case 4 -> saveAndQuit = true;
                    default -> System.out.println("Invalid Input please enter a number 1-3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number 1-3");
                scanner.next();  // Clear the invalid input from the scanner
            }
        }
    }

    public void addTransactionsMenu() {

        // Consume any leftover newline character
        scanner.nextLine();


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

    private LocalDate getDateInput() {
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

    public void searchTransactionsMenu() {


    }

    public void showReportsMenu() {

    }
}
