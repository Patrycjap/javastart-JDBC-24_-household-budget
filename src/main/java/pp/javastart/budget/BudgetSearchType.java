package pp.javastart.budget;

import java.util.List;
import java.util.Scanner;

public class BudgetSearchType {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj typ transakcji, którą chcesz wyświetlić: wydatek lub przychód");

        TransactionType type;
        type = TransactionType.getByName(scanner.nextLine());
        while (!TransactionType.SPEND.equals(type) && !TransactionType.INCOME.equals(type)) {
            System.out.println("Zła nazwa wydatku: poprawne wartości to 'wydatek' lub 'przychód'");
            type = TransactionType.getByName(scanner.nextLine());
        }
        TransactionDao transactionDao = new TransactionDao();
        List<Transaction> transaction = transactionDao.searchByType(String.valueOf(type));
        System.out.println("Poniżej znajdują się transakcje dla typu: " + type);
        System.out.println(transaction);
    }
}
