package pp.javastart.budget;

import java.util.Arrays;

public enum TransactionType {
    INCOME("przychód"),
    SPEND("wydatek");

    private String name;

    TransactionType(String name) {
        this.name = name;

    }

    public static TransactionType getByName(String name) {
        return Arrays.stream(values())
                .filter(transactionType -> transactionType.name.equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}