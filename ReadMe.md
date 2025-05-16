#  Members
**Name:** Eyitayo Akiode
          Chukwurah Sean 
          
**Student ID:** 20221971
                20221125

# 🎵 JavaFX Music Player

A full-featured, object-oriented desktop music player built in Java using JavaFX.  
Includes playlist support, metadata display, local library loading, threaded media playback, and a responsive GUI.

---

## 🗂️ Project Structure

```
MusicPlayerApp/
├── src/
│   └── com/musicplayer/
│       ├── model/
│       │   ├── Song.java
│       │   ├── Playlist.java
│       │   └── MusicPlayer.java
│       ├── main/
│       │   ├── MusicPlayerApp.java
│       │   ├── SongDialog.java
│       │   └── TextInputDialogHelper.java
│       └── util/
│           └── LocalLibraryLoader.java
├── resources/
│   └── music/
└── README.md
```

---

## 🚀 Features

- ✅ Add songs from disk with title, artist, genre metadata.
- ✅ Play, pause, and stop individual tracks.
- ✅ Display playlists and add/remove songs from them.
- ✅ Play entire playlists sequentially.
- ✅ Search songs by title in real time.
- ✅ Preload local library (`resources/music/`) on startup.
- ✅ Media playback runs on a background thread for UI responsiveness.
- ✅ GUI built in JavaFX with responsive layout and controls.

---

## 🛠️ Requirements

- Java 24+
- JavaFX SDK (24+)

Download JavaFX SDK here: https://gluonhq.com/products/javafx/

---

## 🧪 Running the App

### Compile

```bash
javac --module-path /path/to/javafx-sdk-24.0.1/lib --add-modules javafx.controls,javafx.media \
  -d out $(find ./src -name "*.java")
```

### Run

```bash
java --module-path /path/to/javafx-sdk-24.0.1/lib --add-modules javafx.controls,javafx.media \
  -cp out com.musicplayer.main.musicPlayerApp
```

---

## 🧱 Key Components

### Song.java
Represents a music track with title, artist, genre, and file path.

### Playlist.java
Contains a named collection of `Song` objects.

### MusicPlayer.java
Handles:
- Library management
- Playlist management
- MediaPlayer control (play/pause/stop)
- Playlist playback (with `setOnEndOfMedia`)
- Background-threaded playback setup

### MusicPlayerApp.java
Main UI controller:
- Displays library and playlists
- Controls playback
- Manages user input and song display

### SongDialog.java / TextInputDialogHelper.java
Reusable dialogs for metadata input and playlist naming.

### LocalLibraryLoader.java
Preloads songs from the `/resources/music/` folder.

---

## 🎯 OOP Principles

| Principle     | Implementation Example                                      |
|---------------|-------------------------------------------------------------|
| Encapsulation | Fields in `Song`, `Playlist` are private with getters       |
| Abstraction   | `MusicPlayer` exposes high-level methods like `.play()`     |
| Inheritance   | Not used in current design (single-responsibility favored)  |
| Polymorphism  | JavaFX ListView with overridden `updateItem()` rendering    |

---

## 🎨 Ideas for Future Features

- 🔁 Shuffle / Repeat modes
- 💾 Save & load playlists and library to disk (e.g., JSON)
- 🎚️ Volume slider and track seek bar
- 🎧 Support for more formats (FLAC, OGG)
- 🌙 Dark mode or custom themes
- ⏳ Song duration + progress bar
- 🔍 Filter by genre/artist

---

## 📦 Packaging (EXE or JAR)

You can build a standalone executable using:

### Option 1: JPackage (Java 24+)
```bash
jpackage --name MusicPlayer \
  --input out --main-jar MusicPlayerApp.jar \
  --main-class com.musicplayer.Main.MusicPlayerApp \
  --module-path /path/to/javafx-sdk-17/lib \
  --add-modules javafx.controls,javafx.media \
  --type exe
```

### Option 2: Launch4j + JarBundler
1. Build a `MusicPlayerApp.jar`
2. Use Launch4j GUI to generate `.exe` wrapper

---

## 📌 Notes

- This project assumes a relative path for resources (e.g., `src/resources/music/...`). For production, you may want to use resource loading via `getClass().getResource(...)`.
- JavaFX `Media` only supports certain formats. Ensure `.mp3` files are well-encoded.
- This app is tested on Java 17 + JavaFX 17.

---

## 📣 Credits

Built as a learning project for OOP, JavaFX, and media APIs in Java.

---