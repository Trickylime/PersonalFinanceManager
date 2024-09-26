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

        while(true) {
            System.out.print("""
                    Main Menu:
                    1. Add Transaction
                    2. Find Transaction(s)
                    3. View Reports
                    Please select what you would like to do:
                    """);

            try {
                int input = scanner.nextInt();

                switch(input) {
                    case 1 -> addTransactionsMenu();
                    case 2 -> searchTransactionsMenu();
                    case 3 -> showReportsMenu();
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

        LocalDate date;
        double amount;
        String category;
        String type = "";
        boolean recurring;

        System.out.println("Add Transaction: ");

        System.out.println("Please enter a date for your transaction ie: YYYY/MM/DD (leave blank for current date)");
        while (true) {
            String userDateInput = scanner.nextLine();

            if (userDateInput.isEmpty()) {
                date = LocalDate.now(); //Use current date if input is empty
                break;
            } else {
                date = validateDateInput(userDateInput);
                if (date != null) break; //Exit loop if date is valid
            }
        }

        System.out.println("Please enter a category for your transaction...");
        category = scanner.nextLine().toUpperCase();

        System.out.println("Please enter the transaction amount...");
        while (true) {
            try {
                amount = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input please input a numerical value ie: 10.99");
                scanner.next();  // Clear the invalid input from the scanner
            }
        }

        System.out.print("Is this transaction... \n 1. Income \n 2. Expense \n");
        while (true) {
            try {
                int userTypeInput = scanner.nextInt();
                if (userTypeInput == 1) {
                    type = "INCOME";
                    break;
                }
                if (userTypeInput == 2) {
                    type = "OUTGOING";
                    break;
                }
                else System.out.println("Invalid Input please select 1 for Income or 2 for Expense");

            } catch (InputMismatchException e) {
                System.out.println("Invalid Input please select 1 for Income or 2 for Expense");
                scanner.next();  // Clear the invalid input from the scanner
            }
        }

        System.out.println("Does this transaction happen every month? (Y / N)");
        while (true) {
            String recurringUserInput = scanner.nextLine().toUpperCase();
            if (recurringUserInput.equals("Y") || recurringUserInput.equals("YES")) {
                recurring = true;
                break;
            } else if (recurringUserInput.equals("N") || recurringUserInput.equals("NO")) {
                recurring = false;
                break;
            } else System.out.println("Invalid Input please enter Yes or No");
        }

        Transaction transaction = Transaction.builder()
                    .amount(amount)
                    .category(category)
                    .date(date)
                    .type(type)
                    .recurring(recurring)
                    .build();
        System.out.println(transaction);

        System.out.println("Is this transaction correct? (Y/N)");
        while (true) {
            String userInput = scanner.nextLine().toUpperCase();
            if (userInput.equals("Y") || userInput.equals("YES")) {
                transactionManager.addTransaction(transaction);
                System.out.println("Transaction Added!");
                break;
            } else if (userInput.equals("N") || userInput.equals("NO")) break;
            else System.out.println("Invalid Input please enter Yes or No");
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

    public void searchTransactionsMenu() {

    }

    public void showReportsMenu() {

    }
}
