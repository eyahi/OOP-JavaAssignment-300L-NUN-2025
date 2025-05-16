package com.inventory.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (getProductById(product.getProductId()) == null) {
            products.add(product);
            System.out.println("Product added.");
        } else {
            System.out.println("Product ID already exists.");
        }
    }

    public Product getProductById(String productId) {
        for (Product p : products) {
            if (p.getProductId().equalsIgnoreCase(productId)) {
                return p;
            }
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void updateStock(String productId, int quantityChange) {
        Product p = getProductById(productId);
        if (p != null) {
            int newStock = p.getStockQuantity() + quantityChange;
            if (newStock >= 0) {
                p.setStockQuantity(newStock);
                System.out.println("Stock updated.");
            } else {
                System.out.println("Error: Stock cannot go below zero.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    public void deleteProduct(String productId) {
        Product p = getProductById(productId);
        if (p != null) {
            products.remove(p);
            System.out.println("Product removed.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void displayInventory() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Product p : products) {
                System.out.println(p);
            }
        }
    }
}
