Music Player Application

Student Information

Name:DANIEL NYERERE 20220869  AND CHIELMELIE OKEKE 20220937

Project Overview

This is a Java application that simulates a music player with a graphical user interface.  
The application allows users to manage a library of songs, create and manage playlists, and control the playback of songs with features like:

- Play, pause, stop functionality
- Volume control
- Progress bar with playback tracking
- Library management
- Playlist creation and management

Object-Oriented Programming Principles Demonstrated

🔹 Encapsulation
- Private attributes with public getter methods
- Proper data hiding and access control

🔹 Association
- Relationships between `MusicPlayer`, `Playlist`, and `Song` classes
- Clear separation of responsibilities

🔹 Collection Management
- Managing lists of songs in the library and playlists
- Use of `ArrayList` and `HashMap` for dynamic data handling

🔹 MVC Pattern
- Model:`Song`, `Playlist`, `MusicPlayer`
- View: `MusicPlayerView` (Swing GUI)
- Controller: `MusicPlayerController` (handles user interaction and playback logic)
 
Prerequisites
- Java Development Kit (JDK) 8 or higher
- Audio files in **WAV** format for playback

Compilation and Running

TO Compile all the Java files:
cd MUSICPLAYERAPP1/src
javac com/musicplayer/model/*.java com/musicplayer/controller/*.java com/musicplayer/view/*.java com/musicplayer/main/*.java

TO Run The App
java com.musicplayer.main.MusicPlayerApp

  Using the Application;

Adding Songs:
  Click the "Add Song" button

  Browse for WAV audio files

  Song is automatically added to the library

Creating Playlists:

  Click the "New Playlist" button

  Enter a name for the playlist

Adding Songs to Playlists:
  Select a song from the Library

  Select a playlist from the dropdown

  Click "Add to Playlist"

Playing Songs:
  Double-click on a song in the Library or Playlist

  Use control buttons (Play, Pause, Stop)

  Adjust volume using the Volume slider

  Track song progress with the Progress Bar

Project Structure
MusicPlayerApp/
├── src/
│   └── com/
│       └── musicplayer/
│           ├── model/
│           │   ├── Song.java
│           │   ├── Playlist.java
│           │   └── MusicPlayer.java
│           ├── controller/
│           │   └── MusicPlayerController.java
│           ├── view/
│           │   └── MusicPlayerView.java
│           └── main/
│               └── MusicPlayerApp.java
Extensions and Enhancements
This implementation extends the basic requirements by adding:
A full graphical user interface with Java Swing
Real audio playback using the Java Sound API
Progress bar with seek functionality
Volume control via slider
Double-click to play functionality
File browser integration for importing WAV files
Visual playback feedback and track info

