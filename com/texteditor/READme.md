# Text Editor Application

## Author
**Name**: [Olabode Tolani and Ajele Khimiye Karen]  
**Student ID**: [ 20220091 and 20220087]

## Description
A simple Java-based text editor application with:
- Text editing (insert/delete)
- File operations (open, save, save as)
- Undo/redo functionality using the **Command design pattern**
- A JavaFX **Graphical User Interface (GUI)**

## Features
- Object-Oriented design
- Use of `StringBuilder` for efficient string operations
- Command pattern for reversible operations
- GUI using JavaFX for a user-friendly experience

## Project Structure
```
TextEditor/
├── src/
│   ├── com/texteditor/
│   │   ├── model/
│   │   │   └── Document.java
│   │   ├── command/
│   │   │   ├── Command.java
│   │   │   ├── InsertCommand.java
│   │   │   └── DeleteCommand.java
│   │   ├── util/
│   │   │   └── UndoStack.java
│   │   └── gui/
│   │       └── TextEditorApp.java
└── README.md
```

## How to Compile and Run

### Requirements
- Java 11 or later
- JavaFX SDK (17+ recommended)

### Compilation
If using the command line:

1. Compile the code (replace `/path/to/javafx/lib` with the actual path):
```
javac --module-path /path/to/javafx/lib --add-modules javafx.controls -d out src/com/texteditor/**/*.java
```

2. Run the application:
```
java --module-path /path/to/javafx/lib --add-modules javafx.controls -cp out com.texteditor.gui.TextEditorApp
```

### IntelliJ IDEA or Eclipse
- Add JavaFX as a library or module dependency.
- Set `TextEditorApp` as the main class.
- Configure VM options:
```
--module-path /path/to/javafx/lib --add-modules javafx.controls
```

## OOP Principles Demonstrated
- **Encapsulation**: `Document` encapsulates file content and state.
- **Abstraction & Polymorphism**: `Command` interface with multiple implementations.
- **Separation of Concerns**: Clear separation between model, command, utility, and UI layers.
- **Command Pattern**: Enables clean undo/redo handling.

