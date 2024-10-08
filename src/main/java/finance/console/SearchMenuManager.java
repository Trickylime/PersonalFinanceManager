package finance.console;

import finance.transaction.Transaction;
import finance.transaction.TransactionManager;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SearchMenuManager {

    private final TransactionManager transactionManager;
    private final TransactionMenuManager transactionMenuManager;
    private final Scanner scanner;

    public SearchMenuManager(TransactionManager transactionManager,
                             TransactionMenuManager transactionMenuManager,
                             Scanner scanner) {
        this.transactionManager = transactionManager;
        this.transactionMenuManager = transactionMenuManager;
        this.scanner = scanner;
    }

    public void searchTransactionsMenu() {

        boolean searching = true;
        while (searching) {

            System.out.println("--------------------Search Transactions--------------------");
            System.out.print("""
                    Search For Transaction(s) By:
                    1. Date
                    2. Amount
                    3. Category
                    4. Type
                    5. Recurring
                    6. Main Menu
                    Please select what you would like to do:
                    """);

            try {
                int input = scanner.nextInt();
                scanner.nextLine();  // Consume the newline

                switch (input) {
                    case 1 -> searchByDate();
                    case 2 -> searchByAmount();
                    case 3 -> searchByCategory();
                    case 4 -> searchByType();
                    case 5 -> searchByRecurring();
                    case 6 -> searching = false;
                    default -> System.out.println("Invalid Input please enter a number 1-6");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number 1-6");
                scanner.next();  // Clear the invalid input from the scanner
            }
        }

    }

    private void searchByDate() {

        System.out.println("--------------------Search By Date--------------------");

        LocalDate date = transactionMenuManager.getDateInput();
        List<Transaction> transactionList = transactionManager.findDateTransactions(date);

        if (!transactionList.isEmpty()) {
            System.out.println(transactionList);
        } else {
            System.out.println("No Transactions Found With That Date");
        }
    }

    private void searchByAmount() {
        System.out.println("--------------------Search By Greater Or Less Than Amount--------------------");

        double amount = transactionMenuManager.getAmountInput();

        System.out.println("Are you searching for amounts, greater(T) or less(F) than amount input");
        boolean greater = scanner.nextBoolean();

        List<Transaction> transactionList = transactionManager.findGreaterLessThanTransactions(amount, greater);

        if (!transactionList.isEmpty()) {
            System.out.println(transactionList);
        } else {
            String greaterOrLess = greater ? "Greater " : "Less ";
            System.out.println("No Transactions Found " + greaterOrLess + "Than That Amount");
        }
    }

    private void searchByCategory() {
    }

    private void searchByType() {
    }

    private void searchByRecurring() {
    }
}
