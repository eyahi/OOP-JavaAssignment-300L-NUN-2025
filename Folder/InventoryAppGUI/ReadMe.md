# InventoryManagement

# Authors
Rayyan Akindele - 2022115
Boniafce Nnajiofor - 20221874

# Description
A Java-based console application that simulates a simple inventory management system. It allows users to:
- Add new products
- View all products
- Update product stock
- Delete products

# OOP Principles Used
Abstraction
Encapsulation

## Using VS Code / NetBeans:
1. Clone or extract the project.
2. Navigate to `src/` and compile using:
   ```bash
   javac com/inventory/model/*.java com/inventory/main/InventoryApp.java 

# How to create a jar file
mkdir build
javac -d build src\com\inventory\model\*.java src\com\inventory\main\InventoryAppGUI.java
jar cfm InventoryAppGUI.jar manifest.txt -C build .

# How to run
# through the java file
java com.inventory.main.InventoryApp

# through the jar file
java -jar InventoryAppGUI.jar