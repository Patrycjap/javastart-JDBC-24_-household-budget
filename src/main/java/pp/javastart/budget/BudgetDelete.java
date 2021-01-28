package pp.javastart.budget;

import java.util.Scanner;

public class BudgetDelete {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id transakcji, którą chcesz usunąć");
        long id = scanner.nextLong();
        scanner.nextLine();
        TransactionDao transactionDao = new TransactionDao();
        transactionDao.deleteById(id);
        System.out.println("Usunięto transakcje o nr id: " + id);
        scanner.close();
    }
}
