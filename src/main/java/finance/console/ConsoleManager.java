package finance.console;

import finance.transaction.TransactionManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleManager {

    private final Scanner scanner = new Scanner(System.in);
    private final TransactionMenuManager transactionMenu;
    private final SearchMenuManager searchMenu;
    private final ReportMenuManager reportMenu;

    public ConsoleManager(TransactionManager transactionManager) {
        this.transactionMenu = new TransactionMenuManager(transactionManager, scanner);
        this.searchMenu = new SearchMenuManager(transactionManager, transactionMenu, scanner);
        this.reportMenu = new ReportMenuManager(transactionManager, scanner);
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
                    case 1 -> transactionMenu.addTransactionsMenu();
                    case 2 -> searchMenu.searchTransactionsMenu();
                    case 3 -> reportMenu.showReportsMenu();
                    case 4 -> saveAndQuit = true;
                    default -> System.out.println("Invalid Input please enter a number 1-4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number 1-4");
                scanner.next();  // Clear the invalid input from the scanner
            }
        }
    }
}
