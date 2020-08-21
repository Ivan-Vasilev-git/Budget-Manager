import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class FileProcessor {
  public static List<String> readFromFile(String filePath) {
    List<String> lines;
    try {
      lines =
          Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return lines;
  }

  public static void saveInFile(String filePath, Map<String, PurchasesList> purchasesByCategory,
                                double balance) {
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write(balance + "\n");
      for (String key : purchasesByCategory.keySet()) {
        for (Purchase purchase : purchasesByCategory.get(key).getPurchases()) {
          writer.write(key + "," + purchase.getName() + "," + purchase.getPrice() + '\n');
        }
      }
      System.out.println("\nPurchases were saved!\n");
    } catch (
        IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
