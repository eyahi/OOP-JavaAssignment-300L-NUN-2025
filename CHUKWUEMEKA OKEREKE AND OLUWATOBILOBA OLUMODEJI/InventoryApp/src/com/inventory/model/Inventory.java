/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Abubakar Kana
 */
package com.inventory.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    public boolean addProduct(Product product) {
        if (getProductById(product.getProductId()) != null) {
            return false;
        }
        products.add(product);
        return true;
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

    public boolean updateStock(String productId, int quantityChange) {
        Product product = getProductById(productId);
        if (product != null) {
            int newStock = product.getStockQuantity() + quantityChange;
            if (newStock < 0) {
                return false;
            }
            product.setStockQuantity(newStock);
            return true;
        }
        return false;
    }

    public boolean deleteProduct(String productId) {
        Product product = getProductById(productId);
        if (product != null) {
            products.remove(product);
            return true;
        }
        return false;
    }
}