public class Product {
    private String name;
    private double price;
    private int stockLevel;

    // Constructor to initialize product properties
    public Product(String name, double price, int stockLevel) {
        this.name = name;
        this.price = price;
        this.stockLevel = stockLevel;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Getter for stock level
    public int getStockLevel() {
        return stockLevel;
    }

    // Method to update stock level
    public void updateStock(int quantity) {
        this.stockLevel += quantity;
    }

    // Method to display product details
    public String getProductDetails() {
        return String.format("Name: %s | Price: $%.2f | Stock: %d", name, price, stockLevel);
    }
}
