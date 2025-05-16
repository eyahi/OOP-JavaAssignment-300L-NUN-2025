# Inventory Management System Application

**Authors:**  
- Jamila Mato (ID: 20220642)  
- Haseena Abdullahi (ID: 20220600)  

**Course:** Object-Oriented Programming  

## Project Description

This is a simple Java console application that simulates an Inventory Management System. It allows users to:

- Add new products
- View the entire inventory
- Update stock levels
- Delete products

The system is designed using **Object-Oriented Programming (OOP)** principles such as:

- **Encapsulation:** Each product's data is kept private within the `Product` class with access provided through getter/setter methods.
- **Association:** The `Inventory` class maintains a collection (list) of `Product` objects and operates on them.
- **Modularity:** Classes are organized by purpose and package to enhance readability and maintainability.

## Project Structure

```
InventoryApp/
├── src/
│   └── com/
│       └── inventory/
│           ├── model/
│           │   ├── Product.java
│           │   └── Inventory.java
│           └── main/
│               └── InventoryApp.java
└── README.md
```

## How to Compile and Run

### 1. Open Terminal or Command Prompt.

### 2. Navigate to the `src` directory of the project:
```bash
cd path/to/InventoryApp/src
```

### 3. Compile all Java files:
```bash
javac com/inventory/model/*.java com/inventory/main/InventoryApp.java
```

### 4. Run the application:
```bash
java com.inventory.main.InventoryApp
```

## Example Use

```text
=== Inventory Management System ===
1. Add New Product
2. View Inventory
3. Update Stock
4. Delete Product
5. Exit
Choose an option (1-5):
```

## Notes

- Input validation is included to handle invalid numeric input.
- Stock updates prevent negative values.
- Product IDs must be unique.