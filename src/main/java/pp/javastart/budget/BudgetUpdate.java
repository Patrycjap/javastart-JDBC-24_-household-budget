package pp.javastart.budget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class BudgetUpdate {

    public static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id transakcji, którą chcesz zaktualizować");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Podaj typ: wydatek/przychód");
        TransactionType type;
        type = TransactionType.getByName(scanner.nextLine());
        while (!TransactionType.SPEND.equals(type) && !TransactionType.INCOME.equals(type)) {
            System.out.println("Zła nazwa wydatku: poprawne wartości to 'wydatek' lub 'przychód'");
            type = TransactionType.getByName(scanner.nextLine());
        }
        System.out.println("Podaj opis");
        String description = scanner.nextLine();

        System.out.println("Podaj kwote transakcji");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();

        LocalDate formatedDate = null;
        while (formatedDate == null) {
            System.out.println("Podaj date w formacie yyyy-mm-dd");
            try {
                String date = scanner.nextLine();
                formatedDate = LocalDate.parse(date, DATE_FORMATER);
            } catch (DateTimeParseException e) {
                System.out.println("Data nieprawidłowa");
            }
        }
        Transaction transaction = new Transaction(id, type, description, amount, formatedDate);
        TransactionDao transactionDao = new TransactionDao();
        transactionDao.update(transaction);
        System.out.println("Zaktualizowano transakcje o numerze id:" + id);
    }
}