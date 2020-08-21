public class Purchase implements Comparable<Purchase> {
  private String name;
  private double price;

  public Purchase(String name, double price) {
    this.name = name;
    this.price = price;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name + " $" + String.format(java.util.Locale.US, "%.2f", price);
  }

  @Override
  public int compareTo(Purchase o) {
    Double obj1 = this.price;
    Double obj2 = o.price;
    return obj2.compareTo(obj1);
  }
}
