package finance;

import java.time.LocalDateTime;

public class PersonalFinanceManager {


    public static void main(String[] args) {

        TransactionHistory transactionHistory = new TransactionHistory();

        final Transaction transaction = Transaction.builder()
                .amount(10)
                .category("Wages")
                .date(LocalDateTime.now())
                .type("INCOME")
                .recurring(true)
                .build();

        final Transaction transaction2 = Transaction.builder()
                .amount(99.99)
                .category("Gym Membership")
                .date(LocalDateTime.now())
                .type("OUTGOING")
                .recurring(true)
                .build();
        final Transaction transaction3 = Transaction.builder()
                .amount(99.99)
                .category("Phone")
                .date(LocalDateTime.now())
                .type("OUTGOING")
                .recurring(true)
                .build();
        final Transaction transaction4 = Transaction.builder()
                .amount(99.99)
                .category("Food Shopping")
                .date(LocalDateTime.now())
                .type("OUTGOING")
                .recurring(false)
                .build();


        transactionHistory.addTransaction(transaction);
        transactionHistory.addTransaction(transaction2);
        transactionHistory.addTransaction(transaction3);
        transactionHistory.addTransaction(transaction4);

        System.out.println("-".repeat(30));
        System.out.println(transactionHistory.findCategoryTransactions("Gym Membership"));
        System.out.println("-".repeat(30));
        System.out.println(transactionHistory.findCategoryTransactions("phone"));
        System.out.println("-".repeat(30));
        System.out.println(transactionHistory.findTypeTransactions("income"));
        System.out.println("-".repeat(30));
        System.out.println(transactionHistory.findTypeTransactions("outgoing"));
        System.out.println("-".repeat(30));
        System.out.println(transactionHistory.getTransactions());
        System.out.println("-".repeat(30));
        System.out.println(transactionHistory.findRecurringTransactions(false));
        System.out.println("-".repeat(30));
        System.out.println(transactionHistory.findRecurringTransactions(true));

        transactionHistory.deleteTransaction(transaction);
        transactionHistory.deleteTransaction(transaction2);
        transactionHistory.deleteTransaction(transaction3);

        System.out.println("-".repeat(30));
        System.out.println(transactionHistory.getTransactions());

    }
}
