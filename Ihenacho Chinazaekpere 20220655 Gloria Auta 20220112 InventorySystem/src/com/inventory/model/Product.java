package com.inventory.model;

public class Product {
    private String productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;

    // Constructor
    public Product(String productId, String name, String description, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    // toString
    @Override
    public String toString() {
        return "Product ID: " + productId + "\nName: " + name + "\nDescription: " + description +
               "\nPrice: $" + price + "\nStock: " + stockQuantity + "\n";
    }
}
