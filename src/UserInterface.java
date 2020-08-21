import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {
  private Scanner scanner;
  private BudgetManager manager;
  private SortProcessor sortProcessor;

  public UserInterface() {
    scanner = new Scanner(System.in);
    manager = new BudgetManager(scanner);
    sortProcessor = new SortProcessor(manager);
  }

  public void startApp() {
    while (true) {
      mainMenu();
      String input = scanner.nextLine().trim();
      switch (input) {
        case "0":
          System.out.println("\nBye!");
          scanner.close();
          return;
        case "1":
          manager.addBalance();
          break;
        case "2":
          System.out.println("");
          startTypeOfPurchaseMenu();
          break;
        case "3":
          System.out.println("");
          startListOfPurchasesMenu();
          break;
        case "4":
          manager.printBalance();
          break;
        case "5":
          savePurchasesInFile();
          break;
        case "6":
          loadPurchasesFromFile();
          break;
        case "7":
          startAnalyzeMenu();
          break;
        default:
          System.out.println("\nUnknown action.\n");
          break;
      }
    }
  }

  private void mainMenu() {
    System.out.println("Choose your action:\n"
        + "1) Add income\n"
        + "2) Add purchase\n"
        + "3) Show list of purchases\n"
        + "4) Balance\n"
        + "5) Save\n"
        + "6) Load\n"
        + "7) Analyze (Sort)\n"
        + "0) Exit");
  }

  private void typeOfPurchaseMenu() {
    System.out.println("Choose the type of purchase\n"
        + "1) Food\n"
        + "2) Clothes\n"
        + "3) Entertainment\n"
        + "4) Other\n"
        + "5) Back");
  }

  private void listOfPurchasesMenu() {
    System.out.println("Choose the type of purchase\n"
        + "1) Food\n"
        + "2) Clothes\n"
        + "3) Entertainment\n"
        + "4) Other\n"
        + "5) All\n"
        + "6) Back");
  }

  private void analyzeMenu() {
    System.out.println("\nHow do you want to sort?\n"
        + "1) Sort all purchases\n"
        + "2) Sort by type\n"
        + "3) Sort certain type\n"
        + "4) Back");
  }

  private void startTypeOfPurchaseMenu() {
    while (true) {
      typeOfPurchaseMenu();
      String input = scanner.nextLine().trim();
      switch (input) {
        case "1":
          addPurchase("Food");
          break;
        case "2":
          addPurchase("Clothes");
          break;
        case "3":
          addPurchase("Entertainment");
          break;
        case "4":
          addPurchase("Other");
          break;
        case "5":
          System.out.println("");
          return;
        default:
          System.out.println("\nUnknown action.\n");
          break;
      }
    }
  }

  private void startListOfPurchasesMenu() {
    if (manager.isEmpty()) {
      System.out.println("Purchase list is empty!\n");
      return;
    }
    while (true) {
      listOfPurchasesMenu();
      String input = scanner.nextLine().trim();
      switch (input) {
        case "1":
          System.out.println("");
          manager.showPurchasesByCategory("Food");
          break;
        case "2":
          System.out.println("");
          manager.showPurchasesByCategory("Clothes");
          break;
        case "3":
          System.out.println("");
          manager.showPurchasesByCategory("Entertainment");
          break;
        case "4":
          System.out.println("");
          manager.showPurchasesByCategory("Other");
          break;
        case "5":
          System.out.println("");
          manager.showPurchasesByCategory();
          break;
        case "6":
          System.out.println("");
          return;
        default:
          System.out.println("\nUnknown action.\n");
          break;
      }
    }
  }

  private void startAnalyzeMenu() {
    while (true) {
      analyzeMenu();
      String input = scanner.nextLine();
      switch (input) {
        case "1":
          sortProcessor.printAllSortedPurchases();
          break;
        case "2":
          sortProcessor.printSortedByCategory();
          break;
        case "3":
          startSortByCertainTypeMenu();
          break;
        case "4":
          System.out.println("");
          return;
        default:
          System.out.println("\nUnknown action.\n");
          break;
      }
    }
  }

  private void startSortByCertainTypeMenu() {
    while (true) {
      System.out.println("");
      typeOfPurchaseMenu();
      String input = scanner.nextLine().trim();
      switch (input) {
        case "1":
          sortProcessor.printSortInCertainCategory("Food");
          return;
        case "2":
          sortProcessor.printSortInCertainCategory("Clothes");
          return;
        case "3":
          sortProcessor.printSortInCertainCategory("Entertainment");
          return;
        case "4":
          sortProcessor.printSortInCertainCategory("Other");
          return;
        case "5":
          System.out.println("");
          return;
        default:
          System.out.println("\nUnknown action.\n");
          break;
      }
    }
  }

  private void addPurchase(String key) {
    System.out.println("\nEnter purchase name: ");
    String name = scanner.nextLine().trim();
    double price;
    while (true) {
      System.out.println("Enter its price: ");
      try {
        price = Double.parseDouble(scanner.nextLine());
        if (price >= 0) {
          System.out.println("Purchase was added!\n");
          break;
        }
        System.out.println("Enter positive value.\n");
      } catch (NumberFormatException e) {
        System.out.println("Invalid input.\n");
      }
    }
    manager.addPurchaseByCategory(key, name, price);
  }

  private void savePurchasesInFile() {
    FileProcessor.saveInFile("purchases.txt", manager.getPurchasesByCategory(), manager.getBalance());
  }

  private void loadPurchasesFromFile() {
    List<String> listOfPurchases = FileProcessor.readFromFile("purchases.txt");
    Map<String, PurchasesList> emptyPurchasesList = new HashMap<>();
    manager.setPurchasesByCategory(emptyPurchasesList);
    manager.setBalance(Double.parseDouble(listOfPurchases.get(0)));
    for (int i = 1; i < listOfPurchases.size(); i++) {
      String[] purchase = listOfPurchases.get(i).split(",");
      manager.addPurchaseByCategoryWithoutBalance(purchase[0], purchase[1], Double.parseDouble(purchase[2]));
    }
    System.out.println("\nPurchases were loaded!\n");
  }
}
