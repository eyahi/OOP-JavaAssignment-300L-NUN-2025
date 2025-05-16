Music Player Application
Student Information

Name: Toby Franklin- Chizaram Jude Ezeanyika 
Student ID: 20220801 , 20220809

Project Overview
This is a Java application that simulates a music player with a graphical user interface. 
The application allows users to manage a library of songs, create and manage playlists, and control the playback of songs with features like:

Play, pause, stop functionality
Previous and next track buttons for playlist navigation
Volume control
Progress bar with seek functionality
Library management
Playlist creation and management

Object-Oriented Programming Principles Demonstrated
This project demonstrates several key OOP principles:

Encapsulation:

Private attributes with public getter methods
Proper data hiding and access control


Association:

Relationship between MusicPlayer, Playlist, and Song classes
Clear separation of responsibilities


Collection Management:

Managing lists of songs in the library and playlists
Using appropriate data structures (ArrayList, HashMap)


MVC Pattern:

Model: Song, Playlist, MusicPlayer classes
View: MusicPlayerGUI
Controller: MusicPlayerApp and event handlers



Getting Started
Prerequisites

Java Development Kit (JDK) 8 or higher
Audio files in WAV format for playback

Compilation and Running

Compile all the Java files:
javac -d bin com/musicplayer/model/*.java com/musicplayer/player/*.java com/musicplayer/gui/*.java com/musicplayer/main/*.java

Run the application:
java -cp bin com.musicplayer.main.MusicPlayerApp

Using the Application
Adding Songs:
Click the "Add Song" button
Browse for audio files
Enter song details (title, artist, genre)
Creating Playlists:
Click the "Create Playlist" button
Enter a name for the playlist
Adding Songs to Playlists:
Select a song from the library
Select a playlist from the dropdown
Click "Add to Playlist"


Playing Songs:

Double-click on a song in the library or playlist
Use the control buttons (play, pause, stop)
Adjust volume using the volume slider
Navigate through the song using the progress bar


Playlist Navigation:

Use the previous and next buttons to navigate through songs in a playlist

Project Structure
MusicPlayerApp/
├── src/
│   ├── com/musicplayer/
│   ├── model/
│       ├── Song.java
│       ├── Playlist.java
│       └── MusicPlayer.java
│   ├── player/
│       └── AudioPlayer.java
│   ├── gui/
│       └── MusicPlayerGUI.java
│   └── main/
│       └── MusicPlayerApp.java

Extensions and Enhancements
This implementation extends the basic requirements by adding:

A full graphical user interface
Real audio playback capability using Java Sound API
Progress bar with seek functionality
Volume control
Playlist navigation with previous/next buttons
Double-click to play functionality
File browser for adding songs
Visual feedback during playback

Troubleshooting

No Sound: Ensure your audio files are in WAV, MP3, or AIFF format
File Not Found: Check the file paths of your audio files
Unsupported Audio Format: The Java Sound API has limited support for certain audio formats