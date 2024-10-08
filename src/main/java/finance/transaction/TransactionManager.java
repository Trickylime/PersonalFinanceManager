package finance.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
public class TransactionManager {

    @Getter
    private List<Transaction> transactions = new LinkedList<>();

    /*
        TODO:
         Return sorted list of transactions (highest to lowest, by category)
     */

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    public void deleteTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public List<Transaction> findDateTransactions(LocalDate date) {

        return transactions.stream()
                .filter(transaction -> transaction.getDate().equals(date))
                .toList();
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

    public double incomeTotal() {

        List<Transaction> incomeTransactions = findTypeTransactions("income");

        return incomeTransactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double outgoingTotal() {

        List<Transaction> incomeTransactions = findTypeTransactions("outgoing");

        return incomeTransactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }






}
