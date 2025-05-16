# 🎵 Android Music Player Application
Esiri Oghenemaro 20220012
Charles Ebenezer 20220820

## 📘 Project Overview

This project is a **Java-based Android music player application**, developed using Android Studio. It simulates the core functionalities of a basic music player while emphasizing Object-Oriented Programming (OOP) principles. The app allows users to add songs to a personal music library, create and manage playlists, and play, pause, or stop audio using Android’s built-in `MediaPlayer` API.

It serves both as a functional multimedia app and a demonstration of key programming concepts including encapsulation, association, collection handling, and separation of concerns between data models and the user interface.

---

## 📦 Key Functionalities

- ✅ **Add songs** to a music library with metadata (title, artist, genre, filename)
- ✅ **Play** a sample audio file (`sample1.mp3`) using `MediaPlayer`
- ✅ **Pause and stop** playback
- ✅ **Create playlists** by name
- ✅ **View songs** and add/remove them from playlists
- ✅ **View playlists**, inspect songs in each, and remove specific songs
- ✅ **Dynamic screen navigation** between multiple activities

---

## 🧱 How to Compile and Run the App

### 🛠 Prerequisites
- Android Studio (Electric Eel or newer recommended)
- Java 8+ SDK
- Android SDK (API 30+)
- A physical or virtual Android device (AVD)

### 🧭 Steps to Run the Application

1. **Clone or Open Project**
   - Open Android Studio → *Open Existing Project* → Select project root folder

2. **Add Sample MP3**
   - Create the folder: `app/src/main/res/raw/` (if it doesn’t exist)
   - Add a sample MP3 file named `sample1.mp3` to that folder

3. **Build and Run**
   - Click the green **Run ▶️** button
   - Choose an emulator or connect a physical Android device

4. **Permissions**
   - No external permissions required since playback uses internal resources

---

## 🧠 Object-Oriented Programming (OOP) Principles in Action

This project is intentionally designed to demonstrate the use of OOP principles in Android development.

### 🔐 Encapsulation

- **Classes encapsulate their own logic and data:**
  - `Song` class contains song details (title, artist, genre, filepath)
  - `Playlist` manages a list of songs internally and exposes only methods like `addSong()` and `removeSong()`
  - `MusicPlayer` exposes functionality like `addSongToLibrary()`, `createPlaylist()`, while hiding how it stores them

### 🤝 Association

- A `Playlist` is **associated with multiple songs** (One-to-Many relationship)
- The `MusicPlayer` class holds a **collection of playlists**, each of which is associated with multiple `Song` instances
- Activities like `ViewPlaylistsActivity` and `ViewSongsActivity` access shared data through a central `MusicStore` singleton

### 📚 Collection Management

- Songs are stored using a `List<Song>` inside `MusicPlayer`
- Playlists are stored in a `Map<String, Playlist>` to allow fast access and management by name
- Playlists themselves use internal `List<Song>` collections
- `ListView` in the UI reflects the current state of these collections

---

## 📁 Major Components

| File | Purpose |
|------|---------|
| `Song.java` | Represents a music track |
| `Playlist.java` | Manages a collection of songs |
| `MusicPlayer.java` | Manages library, playlists, and playback state |
| `MusicStore.java` | Singleton used to share the `MusicPlayer` instance across activities |
| `MusicPlayerApp.java` | Main screen for adding songs, creating playlists, and playback control |
| `ViewSongsActivity.java` | Displays all songs with interaction options |
| `ViewPlaylistsActivity.java` | Displays playlists, allows song removal |
| `res/layout/...` | XML layouts for UI design |
| `res/raw/sample1.mp3` | Sample MP3 file for testing playback |

---



## 🎓 Conclusion

The Music Player App successfully demonstrates the principles of clean Android development and OOP design. It’s a hands-on implementation of how user input, stateful objects, and UI interaction come together in a real-world mobile application.

It can be further extended to include:
- Real-time song selection
- File system integration
- Volume control
- Persistent storage using Room or SQLite

---

