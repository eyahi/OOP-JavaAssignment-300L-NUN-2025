package com.chatapp.server;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
  private Socket socket;
  private PrintWriter out;

  ClientHandler(Socket sock) throws IOException {
    this.socket = sock;
    this.out = new PrintWriter(sock.getOutputStream(), true);
  }

  @Override
  public void run() {
    try (BufferedReader in = new BufferedReader(
          new InputStreamReader(socket.getInputStream()))) {
      String line;
      while ((line = in.readLine()) != null) {
        ChatServer.broadcast(line);
      }
    } catch (IOException e) {
      // ignore
    } finally {
      ChatServer.remove(this);
    }
  }

  void send(String msg) {
    out.println(msg);
  }
}
