/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Abubakar Kana
 */
package com.inventory.main;

import com.inventory.model.Inventory;
import com.inventory.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InventoryApp extends JFrame {
    private final Inventory inventory;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField idField, nameField, descField, priceField, quantityField;

    public InventoryApp() {
        inventory = new Inventory();
        initComponents();
    }

    private void initComponents() {
        setTitle("Inventory Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {}

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));
        add(panel);

        JPanel inputPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Product Details"));
        inputPanel.setBackground(new Color(245, 245, 245));

        idField = new JTextField();
        nameField = new JTextField();
        descField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();

        inputPanel.add(new JLabel("Product ID:"));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(new JLabel("Quantity:"));

        inputPanel.add(idField);
        inputPanel.add(nameField);
        inputPanel.add(descField);
        inputPanel.add(priceField);
        inputPanel.add(quantityField);

        panel.add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Description", "Price", "Quantity"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        panel.add(tableScroll, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addProduct());

        JButton updateButton = new JButton("Update Stock");
        updateButton.addActionListener(e -> updateStock());

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteProduct());

        JButton viewButton = new JButton("View Inventory");
        viewButton.addActionListener(e -> viewInventory());

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addProduct() {
        try {
            Product product = new Product(
                idField.getText().trim(),
                nameField.getText().trim(),
                descField.getText().trim(),
                Double.parseDouble(priceField.getText().trim()),
                Integer.parseInt(quantityField.getText().trim())
            );
            if (inventory.addProduct(product)) {
                JOptionPane.showMessageDialog(this, "Product added successfully.");
                viewInventory();
            } else {
                JOptionPane.showMessageDialog(this, "Product ID already exists.");
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void updateStock() {
        try {
            String id = idField.getText().trim();
            int qty = Integer.parseInt(quantityField.getText().trim());
            if (inventory.updateStock(id, qty)) {
                JOptionPane.showMessageDialog(this, "Stock updated.");
                viewInventory();
            } else {
                JOptionPane.showMessageDialog(this, "Product not found or invalid stock.");
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void deleteProduct() {
        String id = idField.getText().trim();
        if (inventory.deleteProduct(id)) {
            JOptionPane.showMessageDialog(this, "Product deleted.");
            viewInventory();
        } else {
            JOptionPane.showMessageDialog(this, "Product not found.");
        }
    }

    private void viewInventory() {
        tableModel.setRowCount(0);
        for (Product p : inventory.getAllProducts()) {
            tableModel.addRow(new Object[]{
                p.getProductId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getStockQuantity()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventoryApp().setVisible(true));
    }
}