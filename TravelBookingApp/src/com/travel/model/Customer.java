package com.travel.model;

public class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    // Getters
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + name;
    }
}



