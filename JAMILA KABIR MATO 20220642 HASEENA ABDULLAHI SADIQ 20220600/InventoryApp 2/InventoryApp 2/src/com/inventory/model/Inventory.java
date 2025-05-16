package com.inventory.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        for (Product p : products) {
            if (p.getProductId().equalsIgnoreCase(product.getProductId())) {
                System.out.println("Product with ID " + product.getProductId() + " already exists.");
                return;
            }
        }
        products.add(product);
        System.out.println("Product added successfully.");
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
            int newQuantity = p.getStockQuantity() + quantityChange;
            if (newQuantity < 0) {
                System.out.println("Cannot reduce stock below 0.");
            } else {
                p.setStockQuantity(newQuantity);
                System.out.println("Stock updated successfully.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    public void deleteProduct(String productId) {
        Product p = getProductById(productId);
        if (p != null) {
            products.remove(p);
            System.out.println("Product deleted successfully.");
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