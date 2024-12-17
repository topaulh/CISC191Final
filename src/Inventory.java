import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products;

    public Inventory() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        Product existingProduct = findProduct(product.getName());
        if (existingProduct != null) {
            existingProduct.updateStock(product.getStockLevel());
        } else {
            products.add(product);
        }
    }

    public void removeProduct(String productName) {
        Product product = findProduct(productName);
        if (product != null) {
            products.remove(product);
        }
    }

    public Product findProduct(String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) { // Fixed typo
                return product;
            }
        }
        return null;
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public String displayInventory() {
        if (products.isEmpty()) {
            return "Inventory is empty.";
        }
        StringBuilder builder = new StringBuilder();
        for (Product product : products) {
            builder.append(product.getProductDetails()).append("\n");
        }
        return builder.toString();
    }
}


