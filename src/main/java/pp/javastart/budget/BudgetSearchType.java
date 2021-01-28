package pp.javastart.budget;

import java.util.List;
import java.util.Scanner;

public class BudgetSearchType {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj typ transakcji, którą chcesz wyświetlić: wydatek lub przychód");

        String type;
        type = scanner.nextLine();
        while (!(type.equals("wydatek") || type.equals("przychód"))) {
            System.out.println("Zły typ: Poprawne wartości to wydatek lub przychód");
            type = scanner.nextLine();
        }
        TransactionDao transactionDao = new TransactionDao();
        List<Transaction> transaction = transactionDao.searchByType(type);
        System.out.println("Poniżej znajdują się transakcje dla typu: " + type);
        System.out.println(transaction);
        scanner.close();
    }
}
