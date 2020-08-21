import java.util.ArrayList;
import java.util.List;

public class PurchasesList implements Comparable<PurchasesList> {

  private double totalSum = 0;
  private List<Purchase> purchases;

  public PurchasesList() {
    purchases = new ArrayList<>();
  }

  public List<Purchase> getPurchases() {
    return List.copyOf(purchases);
  }

  public void addPurchase(String name, double price) {
    purchases.add(new Purchase(name, price));
    totalSum += price;
  }

  public void addPurchase(Purchase purchase) {
    purchases.add(purchase);
    totalSum += purchase.getPrice();
  }

  public double getTotalSum() {
    return totalSum;
  }

  public int getNumberOfPurchases() {
    return purchases.size();
  }


  @Override
  public int compareTo(PurchasesList o) {
    Double obj1 = this.getTotalSum();
    Double obj2 = o.getTotalSum();

    return obj2.compareTo(obj1);
  }
}
