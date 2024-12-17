import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Inventory Area
        inventoryArea = new JTextArea(inventory.displayInventory());
        inventoryArea.setEditable(false);
        frame.add(new JScrollPane(inventoryArea), BorderLayout.CENTER);

        // Cart Area
        cartArea = new JTextArea("Cart is empty.");
        cartArea.setEditable(false);
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.add(new JScrollPane(cartArea), BorderLayout.CENTER);
        JButton checkoutButton = new JButton("Checkout");

        checkoutButton.addActionListener(e -> {
            double total = pos.calculateTotal();
            JOptionPane.showMessageDialog(frame, "Total: $" + total);
            pos.processTransaction();
            cartArea.setText("Cart is empty.");
            inventoryArea.setText(inventory.displayInventory());
        });

        cartPanel.add(checkoutButton, BorderLayout.SOUTH);
        frame.add(cartPanel, BorderLayout.EAST);

        // Add Product to Cart
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JTextField productNameField = new JTextField();
        JTextField quantityField = new JTextField();
        JButton addToCartButton = new JButton("Add to Cart");

        addToCartButton.addActionListener(e -> {
            String productName = productNameField.getText();
            try {
                int quantity = Integer.parseInt(quantityField.getText());
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

        inputPanel.add(new JLabel("Product Name:"));
        inputPanel.add(productNameField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(addToCartButton);

        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}

