package finance;

import java.time.LocalDateTime;

public class PersonalFinanceManager {


    public static void main(String[] args) {

        TransactionHistory transactionHistory = new TransactionHistory();

        final Transaction transaction = Transaction.builder()
                .amount(10)
                .category("Internet")
                .date(LocalDateTime.now())
                .type("INCOME")
                .build();

        final Transaction transaction2 = Transaction.builder()
                .amount(99.99)
                .category("Gym Membership")
                .date(LocalDateTime.now())
                .type("OUTGOING")
                .build();
        final Transaction transaction3 = Transaction.builder()
                .amount(99.99)
                .category("Phone")
                .date(LocalDateTime.now())
                .type("OUTGOING")
                .build();
        final Transaction transaction4 = Transaction.builder()
                .amount(99.99)
                .category("Gym Membership")
                .date(LocalDateTime.now())
                .type("OUTGOING")
                .build();


        transactionHistory.addTransaction(transaction);
        transactionHistory.addTransaction(transaction2);
        transactionHistory.addTransaction(transaction3);
        transactionHistory.addTransaction(transaction4);

        System.out.println(transactionHistory.findCategoryTransactions("Gym Membership"));
        System.out.println(transactionHistory.findCategoryTransactions("phone"));
    }
}
