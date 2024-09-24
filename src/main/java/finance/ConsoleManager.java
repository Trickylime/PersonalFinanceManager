package finance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

        LocalDate date;
        double amount;
        String category;
        String type;
        boolean recurring;

        System.out.println("Add Transaction: ");

        System.out.println("Please enter a date for your transaction ie: YYYY/MM/DD (leave blank for current date");
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

        System.out.println("Please enter a category for your input");
        while (true) {
            String userCategoryInput = scanner.nextLine();
            
        }
    }

    private LocalDate validateDateInput(String userDateInput) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        try {
            return LocalDate.parse(userDateInput, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please try again using the correct format YYYY/MM/DD");
            return null;
        }
    }

    private void searchTransactionsMenu() {

    }

    private void showReportsMenu() {

    }
}
