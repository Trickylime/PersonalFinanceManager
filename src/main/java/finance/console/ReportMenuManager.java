package finance.console;

import finance.transaction.TransactionManager;

import java.util.Scanner;

public class ReportMenuManager {

    private final TransactionManager transactionManager;
    private final Scanner scanner;

    public ReportMenuManager(TransactionManager transactionManager, Scanner scanner) {
        this.transactionManager = transactionManager;
        this.scanner = scanner;
    }

    public void showReportsMenu() {
    }
}
