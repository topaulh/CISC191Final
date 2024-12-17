import java.util.ArrayList;

public class Report {

    public String generateInventoryReport(Inventory inventory) {
        if (inventory == null) return "No inventory data available.";
        return inventory.displayInventory();
    }

    public String generateSalesReport(ArrayList<Product> salesHistory) {
        if (salesHistory == null || salesHistory.isEmpty()) {
            return "No sales have been made yet.";
        }
        StringBuilder builder = new StringBuilder("Sales Report:\n");
        for (Product product : salesHistory) {
            builder.append(product.getProductDetails()).append("\n");
        }
        return builder.toString();
    }
}
