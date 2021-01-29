package pp.javastart.budget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BudgetSave {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj typ: wydatek/przychód");
        String type;
        type = scanner.nextLine();
        while (!(type.equals("wydatek") || type.equals("przychód"))) {
            System.out.println("Zła nazwa wydatku: poprawne wartości to 'wydatek' lub 'przychód'");
            type = scanner.nextLine();
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

        Transaction transaction = new Transaction(type, description, amount, formatedDate);
        TransactionDao transactionDao = new TransactionDao();
        transactionDao.insert(transaction);
        scanner.close();
    }
}
