@echo off
echo Compiling Java files...
mkdir build
javac -d build src\com\inventory\model\*.java src\com\inventory\main\InventoryAppGUI.java

echo Creating JAR file...
jar cfm InventoryAppGUI.jar manifest.txt -C build .

echo Running JAR file...
java -jar InventoryAppGUI.jar

echo Done!
pause
