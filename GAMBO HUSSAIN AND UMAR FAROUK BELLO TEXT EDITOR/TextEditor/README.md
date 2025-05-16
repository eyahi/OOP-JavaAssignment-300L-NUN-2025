20221538 GAMBO HUSSAIN SALISU & 20221436 UMAR FAROUK BELLO

#  Java Text Editor Application

This is a simple **Text Editor** built in Java using Object-Oriented Programming principles and the **Command Design Pattern**. It allows you to:

- Open and save text files
- Insert and delete text
- Undo and redo actions
- Interact through a **Graphical User Interface (GUI)**

---

## How to Compile and Run

### 1. Compile
```bash
javac -d out src/com/texteditor/**/*.java

### 2. Run  
java -cp out com.texteditor.main.TextEditor

Object-Oriented Design
This project applies core OOP principles:

Encapsulation
Each class (e.g., Document, Command) hides its internal state and only exposes necessary methods.

Association
Commands (like InsertCommand) work with the Document instance, showing how classes interact without tight coupling.

Management of Collections
Undo and redo actions are handled with Stack<Command> objects, showing how data structures manage application state.

 Features
GUI built with JavaFX

Insert/delete with undo-redo functionality

Save and load .txt files

csharp
Copy
Edit


## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
