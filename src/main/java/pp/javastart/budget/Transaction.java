package pp.javastart.budget;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private Long id;
    private TransactionType type;
    private String description;
    private BigDecimal amount;
    private LocalDate date;

    public Transaction(Long id, TransactionType type, String description, BigDecimal amount, LocalDate date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(TransactionType type, String description, BigDecimal amount, LocalDate date) {
        this(null, type, description, amount, date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}