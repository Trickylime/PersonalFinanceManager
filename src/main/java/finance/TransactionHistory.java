package finance;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
public class TransactionHistory {

    @Getter
    private List<Transaction> transactions = new LinkedList<>();

    /*
        TODO:
         Add transactions
         return a list of transactions
         Search for specific transactions
         Return sorted list of transactions (highest to lowest, by category)
         Calculate and Return OUTGOING and INCOME values
     */

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    public void deleteTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public List<Transaction> findCategoryTransactions(String category) {

        return transactions.stream()
                .filter(transaction -> transaction.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    public List<Transaction> findTypeTransactions(String type) {

        return transactions.stream()
                .filter(transaction -> transaction.getType().equalsIgnoreCase(type))
                .toList();
    }

    public List<Transaction> findRecurringTransactions(boolean bool) {

        return transactions.stream()
                .filter(transaction -> transaction.isRecurring() == bool)
                .toList();
    }






}
