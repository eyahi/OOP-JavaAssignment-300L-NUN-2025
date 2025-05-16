package com.inventory.model.main;

import com.inventory.model.Product;

import java.util.Scanner;

import com.inventory.model.Inventory;

public class InventoryApp {
     public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Inventory Management System ---");
            System.out.println("1. Add New Product");
            System.out.println("2. View Inventory");
            System.out.println("3. Update Stock");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addNewProduct(scanner, inventory);
                    break;

                case "2":
                    inventory.displayInventory();
                    break;

                case "3":
                    updateStock(scanner, inventory);
                    break;

                case "4":
                    deleteProduct(scanner, inventory);
                    break;

                case "5":
                    running = false;
                    System.out.println("Exiting the application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }

        scanner.close();
    }

    private static void addNewProduct(Scanner scanner, Inventory inventory) {
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Product Description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter Product Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter Stock Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        Product newProduct = new Product(id, name, desc, price, quantity);
        inventory.addProduct(newProduct);
        System.out.println("Product added successfully.");
    }

    private static void updateStock(Scanner scanner, Inventory inventory) {
        System.out.print("Enter Product ID: ");
        String updateId = scanner.nextLine();

        System.out.print("Enter Quantity Change (positive to add, negative to remove): ");
        int qtyChange = Integer.parseInt(scanner.nextLine());

        inventory.updateStock(updateId, qtyChange);
        System.out.println("Stock updated successfully.");
    }

    private static void deleteProduct(Scanner scanner, Inventory inventory) {
        System.out.print("Enter Product ID to Delete: ");
        String deleteId = scanner.nextLine();

        inventory.deleteProduct(deleteId);
        System.out.println("Product deleted successfully.");
    }
}
