package pp.javastart.budget;
import java.util.Scanner;

public class BudgetApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Aplikacja do zarządzania budżetem. Wybierz opcje: ");
            System.out.println("1. Dodawanie");
            System.out.println("2. Usuwanie");
            System.out.println("3. Aktualizacja");
            System.out.println("4. Wyszukanie typu transakcji");
            System.out.println("0. Koniec");

            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    BudgetSave.main(new String[0]);
                    break;
                case "2":
                    BudgetDelete.main(new String[0]);
                    break;
                case "3":
                    BudgetUpdate.main(new String[0]);
                    break;
                case "4":
                    BudgetSearchType.main(new String[0]);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Błędny wybór!");
            }
        }
    }
}
