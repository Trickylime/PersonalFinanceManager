package finance.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import finance.Transaction;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class DatabaseManager {
    public List<Transaction> transactions = new LinkedList<>();

    public static void main(final String... args) throws Exception
    {
        final DatabaseManager databaseManager = new DatabaseManager();

        databaseManager.load();

        final Transaction transaction = Transaction.builder()
                .amount(99.99)
                .category("Food")
                .date(LocalDateTime.now())
                .type("OUTGOING")
                .build();

        System.out.println(transaction);
        databaseManager.add(transaction);
    }

    public void add(final Transaction transaction) throws IOException
    {
        transactions.add(transaction);
        save();
    }

    public void load() throws IOException {
        final FileReader reader = new FileReader(DATABASE_FILE);
        if(!reader.ready())
            return;
        transactions = OBJECT_MAPPER.readValue(reader,  new TypeReference<List<Transaction>>(){});
    }

    public void save() throws IOException
    {
        final String transactionsToJson = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(transactions);

        System.out.println(transactionsToJson);

        final BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE));
        writer.write(transactionsToJson);
        writer.flush();
        writer.close();
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());
    private static final String DATABASE_FILE = "C:/Users/jackm/Desktop/transactions.json";
}
