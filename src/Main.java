import javax.swing.*;
import java.awt.*;

public class Main {
    private Inventory inventory = new Inventory();
    private POS pos = new POS(inventory);
    private JTextArea inventoryArea;
    private JTextArea cartArea;

    public Main() {
        setupDummyData();
        createGUI();
    }

    private void setupDummyData() {
        inventory.addProduct(new Product("Laptop", 999.99, 10));
        inventory.addProduct(new Product("Phone", 599.99, 20));
        inventory.addProduct(new Product("Headphones", 49.99, 50));
    }

    private void createGUI() {
        JFrame frame = new JFrame("Retail Store POS System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Inventory Area
        inventoryArea = new JTextArea(inventory.displayInventory());
        inventoryArea.setEditable(false);
        JButton refreshInventory = new JButton("Refresh Inventory");
        refreshInventory.addActionListener(e -> inventoryArea.setText(inventory.displayInventory()));

        JPanel inventoryPanel = new JPanel(new BorderLayout());
        inventoryPanel.add(new JScrollPane(inventoryArea), BorderLayout.CENTER);
        inventoryPanel.add(refreshInventory, BorderLayout.SOUTH);

        // Cart Area
        cartArea = new JTextArea("Cart is empty.");
        cartArea.setEditable(false);
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            double total = pos.calculateTotal();
            JOptionPane.showMessageDialog(frame, "Total: $" + total);
            pos.processTransaction();
            cartArea.setText("Cart is empty.");
            inventoryArea.setText(inventory.displayInventory());
        });

        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.add(new JScrollPane(cartArea), BorderLayout.CENTER);
        cartPanel.add(checkoutButton, BorderLayout.SOUTH);

        // Add to Cart Panel
        JPanel addToCartPanel = new JPanel(new GridLayout(3, 2));
        JTextField productNameField = new JTextField();
        JTextField quantityField = new JTextField();
        JButton addToCartButton = new JButton("Add to Cart");

        addToCartButton.addActionListener(e -> {
            String productName = productNameField.getText();
            int quantity;
            try {
                quantity = Integer.parseInt(quantityField.getText());
                if (pos.addToCart(productName, quantity)) {
                    cartArea.setText(pos.displayCart());
                    inventoryArea.setText(inventory.displayInventory());
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid product or insufficient stock.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid quantity.");
            }
        });

        addToCartPanel.add(new JLabel("Product Name:"));
        addToCartPanel.add(productNameField);
        addToCartPanel.add(new JLabel("Quantity:"));
        addToCartPanel.add(quantityField);
        addToCartPanel.add(addToCartButton);

        // Combine Panels
        frame.add(inventoryPanel, BorderLayout.WEST);
        frame.add(cartPanel, BorderLayout.EAST);
        frame.add(addToCartPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
