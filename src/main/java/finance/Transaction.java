package finance;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Transaction {

    private final LocalDateTime date;
    private final double amount;
    private final String category;
    private final String type;

    @Override
    public String toString() {
        return "Transaction {\n" +
                "  date='" + date + "',\n" +
                "  amount=" + amount + ",\n" +
                "  category='" + category + "',\n" +
                "  type='" + type + "',\n" +
                '}';
    }

}
