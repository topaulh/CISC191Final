import java.util.ArrayList;

public class Report {

    // Generate a report for the current inventory
    public String generateInventoryReport(Inventory inventory) {
        return "Inventory Report:\n" + inventory.displayInventory();
    }

    // Generate a sales report based on sales history
    public String generateSalesReport(ArrayList<Product> salesHistory) {
        if (salesHistory.isEmpty()) {
            return "No sales have been made yet.";
        }

        StringBuilder builder = new StringBuilder("Sales Report:\n");
        for (Product product : salesHistory) {
            builder.append(product.getProductDetails()).append("\n");
        }
        return builder.toString();
    }
}
