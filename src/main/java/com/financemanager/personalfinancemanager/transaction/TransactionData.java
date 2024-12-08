package com.financemanager.personalfinancemanager.transaction;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TransactionData {

    private static TransactionData instance = new TransactionData();
    public List<Transaction> transactions = new LinkedList<>();

    public static TransactionData getInstance() {
        return instance;
    }

    public void add(final Transaction transaction) throws IOException {
        transactions.add(transaction);
        save();
    }

    public void load() throws IOException {
        final FileReader reader = new FileReader(DATABASE_FILE);
        if(!reader.ready())
            return;
        transactions = OBJECT_MAPPER.readValue(reader,  new TypeReference<List<Transaction>>(){});
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
