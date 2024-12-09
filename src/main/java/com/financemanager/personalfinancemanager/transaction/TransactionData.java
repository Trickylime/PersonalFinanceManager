package com.financemanager.personalfinancemanager.transaction;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TransactionData {

    private static TransactionData instance = new TransactionData();
    private ObservableList<TransactionItem> transactions = FXCollections.observableArrayList();
    private ObservableList<TransactionItem> incomeTransactions = FXCollections.observableArrayList();
    private ObservableList<TransactionItem> expenseTransactions = FXCollections.observableArrayList();

    public static TransactionData getInstance() {
        return instance;
    }

    public void add(TransactionItem transaction) {
        transactions.add(transaction);
    }

    public ObservableList<TransactionItem> getIncomeTransactions() {
        incomeTransactions = new FilteredList<TransactionItem>(transactions, TransactionItem::getType);
        return incomeTransactions;
    }

    public ObservableList<TransactionItem> getExpenseTransactions() {
        expenseTransactions = new FilteredList<TransactionItem>(transactions, t -> !t.getType());
        return expenseTransactions;
    }

    public void deleteTransaction(TransactionItem item) {
        transactions.remove(item);
    }

    public void load() throws IOException {
        final FileReader reader = new FileReader(DATABASE_FILE);
        if(!reader.ready())
            return;

        var read = OBJECT_MAPPER.readValue(reader,  new TypeReference<List<TransactionItem>>(){});
        transactions.addAll(read);
    }

    public void save() throws IOException {
        final String transactionsToJson = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(transactions);

        final BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE));
        writer.write(transactionsToJson);
        writer.flush();
        writer.close();
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());
    private static final String DATABASE_FILE = "transactions.json";

}
