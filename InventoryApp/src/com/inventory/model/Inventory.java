package com.inventory.model;

import java.util.List;
import java.util.ArrayList;

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
            System.out.println("Error: This product already exists.");
        }
    }

    public Product getProductById(String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;
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
            int newQuantity = product.getStockquantity() + quantityChange;
            if (newQuantity >= 0) {
                product.setStockquantity(newQuantity);
                System.out.println("Stock updated successfully.");
            } else {
                System.out.println("Error: Stock cannot be negative.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    public void deleteProduct(String productId) {
        Product product = getProductById(productId);
        if (product != null) {
            products.remove(product);
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
}