package finance.transaction;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Transaction {

    private final LocalDate date;
    private final double amount;
    private final String category;
    private final String type;
    private final boolean recurring;

    @Override
    public String toString() {
        return "Transaction {\n" +
                "  date='" + date + "',\n" +
                "  amount=" + amount + ",\n" +
                "  category='" + category + "',\n" +
                "  type='" + type + "',\n" +
                "  recurring='" + recurring + "'\n" +
                '}';
    }

}
