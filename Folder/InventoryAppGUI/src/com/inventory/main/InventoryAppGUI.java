package com.inventory.main;

import com.inventory.model.Inventory;
import com.inventory.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class InventoryAppGUI extends JFrame {
    private Inventory inventory;
    private DefaultTableModel tableModel;
    private JTable table;

    public InventoryAppGUI() {
        inventory = new Inventory();
        setTitle("Inventory Management System");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table setup
        String[] columns = {"Product ID", "Name", "Description", "Price", "Stock"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Buttons
        JButton addButton = new JButton("Add Product");
        JButton updateButton = new JButton("Update Stock");
        JButton deleteButton = new JButton("Delete Product");
        JButton refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Actions
        addButton.addActionListener(e -> addProductDialog());
        updateButton.addActionListener(e -> updateStockDialog());
        deleteButton.addActionListener(e -> deleteProductDialog());
        refreshButton.addActionListener(e -> refreshTable());

        refreshTable();
    }

    private void addProductDialog() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField descField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField stockField = new JTextField();
        Object[] message = {
            "Product ID:", idField,
            "Name:", nameField,
            "Description:", descField,
            "Price:", priceField,
            "Stock Quantity:", stockField
        };
        int option = JOptionPane.showConfirmDialog(this, message, "Add New Product", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String id = idField.getText();
                String name = nameField.getText();
                String desc = descField.getText();
                double price = Double.parseDouble(priceField.getText());
                int stock = Integer.parseInt(stockField.getText());

                Product product = new Product(id, name, desc, price, stock);
                inventory.addProduct(product);
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        }
    }

    private void updateStockDialog() {
        String id = JOptionPane.showInputDialog(this, "Enter Product ID:");
        String qtyStr = JOptionPane.showInputDialog(this, "Enter quantity change (positive or negative):");
        try {
            int qty = Integer.parseInt(qtyStr);
            inventory.updateStock(id, qty);
            refreshTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void deleteProductDialog() {
        String id = JOptionPane.showInputDialog(this, "Enter Product ID to delete:");
        inventory.deleteProduct(id);
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Product p : inventory.getAllProducts()) {
            tableModel.addRow(new Object[] {
                p.getProductId(), p.getName(), p.getDescription(), p.getPrice(), p.getStockQuantity()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InventoryAppGUI().setVisible(true);
        });
    }
}
