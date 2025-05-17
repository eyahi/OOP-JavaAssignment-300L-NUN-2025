package com.chatapp.server;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ChatServer {
  private static final int PORT = 5555;
  private static ConcurrentLinkedQueue<ClientHandler> clients = new ConcurrentLinkedQueue<>();

  public static void main(String[] args) throws IOException {
    ServerSocket server = new ServerSocket(PORT);
    System.out.println("Server running on port " + PORT);
    while (true) {
      ClientHandler handler = new ClientHandler(server.accept());
      clients.add(handler);
      new Thread(handler).start();
    }
  }

  static void broadcast(String msg) {
    for (ClientHandler c : clients) c.send(msg);
  }

  static void remove(ClientHandler c) {
    clients.remove(c);
  }
}
