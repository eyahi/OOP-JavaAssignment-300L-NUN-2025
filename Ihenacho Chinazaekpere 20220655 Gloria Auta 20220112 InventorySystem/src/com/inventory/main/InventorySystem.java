package com.inventory.main;

import com.inventory.model.Product;
import com.inventory.model.Inventory;

import java.util.Scanner;

public class InventorySystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Inventory Menu ===");
            System.out.println("1. Add New Product");
            System.out.println("2. View Inventory");
            System.out.println("3. Update Stock");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Product ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Stock Quantity: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine(); // clear buffer
                    inventory.addProduct(new Product(id, name, desc, price, stock));
                    break;

                case 2:
                    inventory.displayInventory();
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    String pid = scanner.nextLine();
                    System.out.print("Enter quantity change (+/-): ");
                    int qtyChange = scanner.nextInt();
                    scanner.nextLine();
                    inventory.updateStock(pid, qtyChange);
                    break;

                case 4:
                    System.out.print("Enter Product ID to delete: ");
                    String delId = scanner.nextLine();
                    inventory.deleteProduct(delId);
                    break;

                case 5:
                    System.out.println("Exiting application.");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
