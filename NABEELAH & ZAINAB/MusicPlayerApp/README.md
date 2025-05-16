

Project Description

This Java application simulates a music player with a graphical user interface (GUI) built using JavaFX. It allows users to manage a music library by adding songs, create playlists, and control song playback (play, pause, stop). The app uses JavaFX’s Media and MediaPlayer classes to handle actual audio playback.


## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

Without GUI

## javac com/musicplayer/model/Song.java com/musicplayer/model/Playlist.java com/musicplayer/model/MusicPlayer.java com/musicplayer/main/MusicPlayerApp.java - Compile

##java com.musicplayer.main.MusicPlayerApp - Run after compiling 

With GUI

"/c/Program Files/Java/jdk-24/bin/javac" --enable-preview --release 24 --module-path "C:/Users/Hussain/Desktop/javafx-sdk-24.0.1/lib" --add-modules javafx.controls com/musicplayer/model/*.java com/musicplayer/main/*.java
 compile

"/c/Program Files/Java/jdk-24/bin/java" --enable-preview --module-path "C:/Users/Hussain/Desktop/javafx-sdk-24.0.1/lib" --add-modules javafx.controls com.musicplayer.main.MusicPlayerAppGUI
  Run after compiling
