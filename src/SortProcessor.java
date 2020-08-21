import java.util.*;

public class SortProcessor {
  private BudgetManager manager;

  public SortProcessor(BudgetManager manager) {
    this.manager = manager;
  }

  private List<Purchase> sortAllPurchases() {
    List<Purchase> purchases = new ArrayList<>();
    if (!manager.isEmpty()) {
      for (String key : manager.getPurchasesByCategory().keySet()) {
        purchases.addAll(manager.getPurchasesByCategory().get(key).getPurchases());
      }
      Collections.sort(purchases);
    }
    return purchases;
  }

  public void printAllSortedPurchases() {
    List<Purchase> purchases = sortAllPurchases();
    if (purchases.isEmpty() || purchases == null) {
      System.out.println("\nPurchase list is empty!\n");
      return;
    }
    System.out.println("\nAll:");
    for (Purchase p : purchases) {
      System.out.println(p);
    }
    System.out.println("Total sum: $"
        + String.format(java.util.Locale.US, "%.2f", manager.getTotalSum()));
  }

  private Map<String, PurchasesList> sortByType() {
    Map<String, PurchasesList> sorted = new LinkedHashMap<>();
    manager.getPurchasesByCategory().entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue())
        .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
    return sorted;
  }

  public void printSortedByCategory() {
    Map<String, PurchasesList> sortedMap = sortByType();
    System.out.println("\nTypes:");
    for (String key : sortedMap.keySet()) {
      System.out.println(key + " - $" + String.format(java.util.Locale.US, "%.2f",
          sortedMap.get(key).getTotalSum()));
    }
    System.out.println("Total sum: $"
        + String.format(java.util.Locale.US, "%.2f", manager.getTotalSum()));
  }

  private List<Purchase> sortCertainType(String key) {
    List<Purchase> list = new ArrayList<>();
    if (manager.getPurchasesByCategory().containsKey(key)) {
      list.addAll(manager.getPurchasesByCategory().get(key).getPurchases());
      Collections.sort(list);
      return list;
    }
    return list;
  }

  public void printSortInCertainCategory(String key) {
    List<Purchase> purchases = sortCertainType(key);
    if (purchases.isEmpty() || purchases == null) {
      System.out.println("\nPurchase list is empty!\n");
      return;
    }
    System.out.println("");
    System.out.println(key + ":");
    for (Purchase p : purchases) {
      System.out.println(p);
    }
    double totalSum = manager.getPurchasesByCategory().get(key).getTotalSum();
    System.out.println("Total sum: $"
        + String.format(java.util.Locale.US, "%.2f", totalSum));
  }
}
