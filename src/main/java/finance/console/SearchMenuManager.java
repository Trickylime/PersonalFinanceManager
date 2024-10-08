package finance.console;

import finance.transaction.TransactionManager;

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
    }
}
