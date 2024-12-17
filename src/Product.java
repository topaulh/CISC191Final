public class Product {
    private String name;
    private double price;
    private int stockLevel;

    public Product(String name, double price, int stockLevel) {
        this.name = name;
        this.price = price;
        this.stockLevel = stockLevel;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void updateStock(int quantity) {
        this.stockLevel += quantity;
    }

    public String getProductDetails() {
        return String.format("Name: %s | Price: $%.2f | Stock: %d", name, price, stockLevel);
    }
}
