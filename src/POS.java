import java.util.ArrayList;

public class POS {
    private ArrayList<Product> cart;
    private Inventory inventory;

    public POS(Inventory inventory) {
        this.cart = new ArrayList<>();
        this.inventory = inventory;
    }

    public boolean addToCart(String productName, int quantity) {
        Product product = inventory.findProduct(productName);
        if (product != null && product.getStockLevel() >= quantity) {
            cart.add(new Product(product.getName(), product.getPrice(), quantity));
            product.updateStock(-quantity); // Reduce stock in inventory
            return true;
        }
        return false; // Insufficient stock or invalid product
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Product product : cart) {
            total += product.getPrice() * product.getStockLevel();
        }
        return total;
    }

    public void processTransaction() {
        cart.clear();
    }

    public String displayCart() {
        if (cart.isEmpty()) return "Cart is empty.";
        StringBuilder builder = new StringBuilder();
        for (Product product : cart) {
            builder.append(product.getProductDetails()).append("\n");
        }
        return builder.toString();
    }
}
