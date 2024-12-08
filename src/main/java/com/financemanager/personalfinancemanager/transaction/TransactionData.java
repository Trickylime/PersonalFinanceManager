package com.financemanager.personalfinancemanager.transaction;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TransactionData {

    private static TransactionData instance = new TransactionData();
    public ObservableList<TransactionItem> transactions = FXCollections.observableArrayList();

    public static TransactionData getInstance() {
        return instance;
    }

    public void add(final TransactionItem transaction) {
        transactions.add(transaction);
    }

    public ObservableList<TransactionItem> getTransactions() {
        return transactions;
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

        System.out.println(transactionsToJson);

        final BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE));
        writer.write(transactionsToJson);
        writer.flush();
        writer.close();
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());
    private static final String DATABASE_FILE = "transactions.json";
}
