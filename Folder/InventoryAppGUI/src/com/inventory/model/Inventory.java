package com.inventory.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> products;
    private static final String FILE_NAME = "products.csv";

    public Inventory() {
        this.products = new ArrayList<>();
        loadFromFile();
    }

    public void addProduct(Product product) {
        if (getProductById(product.getProductId()) == null) {
            products.add(product);
            saveToFile();
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Product with this ID already exists.");
        }
    }

    public Product getProductById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equalsIgnoreCase(productId)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void updateStock(String productId, int quantityChange) {
        Product product = getProductById(productId);
        if (product != null) {
            int newQuantity = product.getStockQuantity() + quantityChange;
            if (newQuantity >= 0) {
                product.setStockQuantity(newQuantity);
                saveToFile();
                System.out.println("Stock updated. New quantity: " + newQuantity);
            } else {
                System.out.println("Error: Stock quantity cannot be negative.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    public void deleteProduct(String productId) {
        Product product = getProductById(productId);
        if (product != null) {
            products.remove(product);
            saveToFile();
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void displayInventory() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Product p : products) {
                writer.println(p.getProductId() + "," + p.getName() + "," + p.getDescription().replace(",", " ") + "," +
                        p.getPrice() + "," + p.getStockQuantity());
            }
        } catch (IOException e) {
            System.out.println("Error saving products to file.");
        }
    }

    private void loadFromFile() {
        products.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", 5);
                if (data.length == 5) {
                    String id = data[0];
                    String name = data[1];
                    String desc = data[2];
                    double price = Double.parseDouble(data[3]);
                    int quantity = Integer.parseInt(data[4]);
                    products.add(new Product(id, name, desc, price, quantity));
                }
            }
        } catch (IOException e) {
            // No action needed if file doesn't exist yet
        }
    }
}
