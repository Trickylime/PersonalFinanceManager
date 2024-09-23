package finance;

import java.time.LocalDateTime;

public class PersonalFinanceManager {


    public static void main(String[] args) {

        TransactionManager transactionManager = new TransactionManager();

        final Transaction transaction = Transaction.builder()
                .amount(1000)
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


        transactionManager.addTransaction(transaction);
        transactionManager.addTransaction(transaction2);
        transactionManager.addTransaction(transaction3);
        transactionManager.addTransaction(transaction4);

        System.out.println("-".repeat(30));
        System.out.println(transactionManager.findCategoryTransactions("Gym Membership"));
        System.out.println("-".repeat(30));
        System.out.println(transactionManager.findCategoryTransactions("phone"));
        System.out.println("-".repeat(30));
        System.out.println(transactionManager.findTypeTransactions("income"));
        System.out.println("-".repeat(30));
        System.out.println(transactionManager.findTypeTransactions("outgoing"));
        System.out.println("-".repeat(30));
        System.out.println(transactionManager.getTransactions());
        System.out.println("-".repeat(30));
        System.out.println(transactionManager.findRecurringTransactions(false));
        System.out.println("-".repeat(30));
        System.out.println(transactionManager.findRecurringTransactions(true));

        transactionManager.deleteTransaction(transaction);
        transactionManager.deleteTransaction(transaction2);
        transactionManager.deleteTransaction(transaction3);

        System.out.println("-".repeat(30));
        System.out.println(transactionManager.getTransactions());

        transactionManager.addTransaction(transaction);
        transactionManager.addTransaction(transaction2);
        transactionManager.addTransaction(transaction3);

        System.out.println("-".repeat(30));
        System.out.println("Income total = " + transactionManager.incomeTotal());
        System.out.println("Outgoing total = " + transactionManager.outgoingTotal());

    }
}
