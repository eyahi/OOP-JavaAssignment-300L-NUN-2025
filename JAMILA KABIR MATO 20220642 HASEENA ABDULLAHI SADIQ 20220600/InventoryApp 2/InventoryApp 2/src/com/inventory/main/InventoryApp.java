package com.inventory.main;

import com.inventory.model.Inventory;
import com.inventory.model.Product;

import java.util.Scanner;

public class InventoryApp {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Inventory Management System ===");
            System.out.println("1. Add New Product");
            System.out.println("2. View Inventory");
            System.out.println("3. Update Stock");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Enter Product ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Product Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Product Description: ");
                        String desc = scanner.nextLine();
                        System.out.print("Enter Product Price: ");
                        double price = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter Initial Stock Quantity: ");
                        int qty = Integer.parseInt(scanner.nextLine());

                        Product product = new Product(id, name, desc, price, qty);
                        inventory.addProduct(product);
                        break;

                    case 2:
                        inventory.displayInventory();
                        break;

                    case 3:
                        System.out.print("Enter Product ID: ");
                        String updateId = scanner.nextLine();
                        System.out.print("Enter Quantity Change (positive to add, negative to remove): ");
                        int change = Integer.parseInt(scanner.nextLine());
                        inventory.updateStock(updateId, change);
                        break;

                    case 4:
                        System.out.print("Enter Product ID to delete: ");
                        String deleteId = scanner.nextLine();
                        inventory.deleteProduct(deleteId);
                        break;

                    case 5:
                        running = false;
                        System.out.println("Exiting the application...");
                        break;

                    default:
                        System.out.println("Invalid option. Please choose between 1-5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter the correct data type.");
            }
        }

        scanner.close();
    }
}