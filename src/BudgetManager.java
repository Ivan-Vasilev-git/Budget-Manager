import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BudgetManager {

  private double balance;
  private double totalSum;
  private Scanner scanner;
  private Map<String, PurchasesList> purchasesByCategory;

  public BudgetManager(Scanner scanner) {
    balance = 0;
    totalSum = 0;
    this.scanner = scanner;
    purchasesByCategory = new HashMap<>();
  }


  public void addBalance() {
    double input;
    while (true) {
      System.out.println("\nEnter income: ");
      try {
        input = Double.parseDouble(scanner.nextLine());
        if (input >= 0) {
          balance += input;
          System.out.println("Income was added!\n");
          break;
        }
        System.out.println("Enter positive value.\n");
      } catch (NumberFormatException e) {
        System.out.println("Invalid input.\n");
      }
    }
  }

  public void addPurchaseByCategory(String key, String name, double price) {
    if (!purchasesByCategory.containsKey(key)) {
      purchasesByCategory.put(key, new PurchasesList());
    }
    totalSum += price;
    balance = balance - price < 0 ? 0 : balance - price;
    purchasesByCategory.get(key).addPurchase(name, price);
  }

  public void addPurchaseByCategoryWithoutBalance(String key, String name, double price) {
    if (!purchasesByCategory.containsKey(key)) {
      purchasesByCategory.put(key, new PurchasesList());
    }
    totalSum += price;
    purchasesByCategory.get(key).addPurchase(name, price);
  }

  public void showPurchasesByCategory(String key, boolean printTotalSum) {
    System.out.println(key + ":");
    PurchasesList purchasesList = purchasesByCategory.get(key);
    if (purchasesByCategory.isEmpty() || purchasesList == null) {
      System.out.println("Purchase list is empty\n");
      return;
    }
    if (purchasesByCategory.containsKey(key)) {
      showPurchases(purchasesByCategory.get(key).getPurchases());
    }
    if (printTotalSum) {
      System.out.println("Total sum: $" + String.format(java.util.Locale.US, "%.2f",
          purchasesList.getTotalSum()) + "\n");
    }
  }

  public void showPurchasesByCategory(String key) {
    showPurchasesByCategory(key, true);
  }

  public void showPurchasesByCategory() {
    for (String key : purchasesByCategory.keySet()) {
      showPurchasesByCategory(key, false);
    }
    System.out.println("Total sum: $" + String.format(java.util.Locale.US, "%.2f", totalSum) + "\n");
  }

  private void showPurchases(List<Purchase> purchaseList) {
    if (purchaseList.isEmpty()) {
      System.out.println("Purchase list is empty\n");
      return;
    }
    for (Purchase p : purchaseList) {
      System.out.println(p);
    }
  }

  public void printBalance() {
    System.out.println("\nBalance: $" + String.format(java.util.Locale.US, "%.2f", balance) + "\n");
  }

  public Map<String, PurchasesList> getPurchasesByCategory() {
    Map<String, PurchasesList> result = new HashMap<>();
    result.putAll(purchasesByCategory);
    return result;
  }

  public void setPurchasesByCategory(Map<String, PurchasesList> purchasesByCategory) {
    Map<String, PurchasesList> result = new HashMap<>();
    result.putAll(purchasesByCategory);
    this.purchasesByCategory = result;
  }

  public boolean isEmpty() {
    return purchasesByCategory.isEmpty();
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public double getTotalSum() {
    return totalSum;
  }
}
