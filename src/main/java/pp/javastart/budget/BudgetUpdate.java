package pp.javastart.budget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BudgetUpdate {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id transakcji, którą chcesz zaktualizować");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Podaj typ: 'WYDATEK' lub 'PRZYCHÓD'");
        TransactionType type;
        type = TransactionType.valueOf(scanner.nextLine());
        while (!(type.equals(TransactionType.SPEND)) && !(type.equals(TransactionType.INCOME))) {
            System.out.println("Zły typ: Poprawne wartości to 'WYDATEK' lub 'PRZYCHÓD'");
            type = TransactionType.valueOf(scanner.nextLine());
        }
        System.out.println("Podaj opis");
        String description = scanner.nextLine();

        System.out.println("Podaj kwote transakcji");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.println("Podaj date w formacie yyyy-mm-dd");
        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date;
        date = scanner.nextLine();

        LocalDate formatedDate = LocalDate.parse(date, dateFormater);
        Transaction transaction = new Transaction(id, type, description, amount, formatedDate);
        TransactionDao transactionDao = new TransactionDao();
        transactionDao.update(transaction);
    }
}
