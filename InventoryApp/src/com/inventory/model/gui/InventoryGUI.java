package com.inventory.model.gui;

import com.inventory.model.Inventory;
import com.inventory.model.Product;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;

public class InventoryGUI extends JFrame {
    private Inventory inventory;

    // GUI components
    private JTextField idField, nameField, descField, priceField, stockField;
    private JTextArea displayArea;

    public InventoryGUI() {
        inventory = new Inventory();

        // Setup the frame
        setTitle("Inventory Management System");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Product Details"));

        inputPanel.add(new JLabel("Product ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Description:"));
        descField = new JTextField();
        inputPanel.add(descField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Stock Quantity:"));
        stockField = new JTextField();
        inputPanel.add(stockField);

        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(e -> addProduct());
        inputPanel.add(addButton);

        JButton updateButton = new JButton("Update Stock");
        updateButton.addActionListener(e -> updateStock());
        inputPanel.add(updateButton);

        add(inputPanel, BorderLayout.NORTH);

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Bottom buttons
        JPanel buttonPanel = new JPanel();

        JButton viewButton = new JButton("View Inventory");
        viewButton.addActionListener(e -> viewInventory());
        buttonPanel.add(viewButton);

        JButton deleteButton = new JButton("Delete Product");
        deleteButton.addActionListener(e -> deleteProduct());
        buttonPanel.add(deleteButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addProduct() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            String desc = descField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());

            Product product = new Product(id, name, desc, price, stock);
            inventory.addProduct(product);
            displayArea.setText("Product added.\n");
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid number format.\n");
        }
    }

    private void viewInventory() {
        StringBuilder sb = new StringBuilder();
        if (inventory.getAllProducts().isEmpty()) {
            sb.append("Inventory is empty.\n");
        } else {
            for (Product p : inventory.getAllProducts()) {
                sb.append(p).append("\n");
            }
        }
        displayArea.setText(sb.toString());
    }

    private void updateStock() {
        String id = idField.getText();
        try {
            int change = Integer.parseInt(stockField.getText());
            inventory.updateStock(id, change);
            displayArea.setText("Stock updated.\n");
        } catch (NumberFormatException e) {
            displayArea.setText("Invalid number format.\n");
        }
    }

    private void deleteProduct() {
        String id = idField.getText();
        inventory.deleteProduct(id);
        displayArea.setText("Product deleted if it existed.\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryGUI::new);
    }
}
