package pp.javastart.budget;

import java.time.LocalDate;
import java.util.Scanner;

public class BudgetUpdate {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id transakcji, którą chcesz zaktualizować");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Podaj typ: wydatek/przychód");
        String type;
        type = scanner.nextLine();
        while (!(type.equals("wydatek") || type.equals("przychód"))) {
            System.out.println("Zły typ: Poprawne wartości to wydatek lub przychód");
            type = scanner.nextLine();
        }
        System.out.println("Podaj opis");
        String description = scanner.nextLine();

        System.out.println("Podaj kwote transakcji");
        int amount = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Podaj date w formacie yyyy-mm-dd");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        Transaction transaction = new Transaction(id, type, description, amount, date);

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.update(transaction);
        scanner.close();
    }
}
