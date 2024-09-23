package finance;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleManager {

    private final Scanner scanner = new Scanner(System.in);
    private TransactionManager transactionManager;

    private void mainMenu() {

        while(true) {
            System.out.println("""
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

    private void addTransactionsMenu() {

        String dateInput;
        double amountInput;
        String categoryInput;
        String typeInput;
        boolean recurringInput;

        System.out.println("Add Transaction: ");
        while (true) {
            System.out.println("Please enter a date for your transaction ie: ##/##/#### (leave blank for current date");
            dateInput = scanner.nextLine();
        }
    }

    private void searchTransactionsMenu() {

    }

    private void showReportsMenu() {

    }
}
