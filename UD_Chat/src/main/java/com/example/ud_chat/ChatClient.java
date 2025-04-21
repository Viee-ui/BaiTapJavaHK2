package com.example.ud_chat;

import java.io.*;
import java.net.*;

public class ChatClient {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public boolean connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public void receiveMessages(MessageListener listener) {
        new Thread(() -> {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    listener.onMessageReceived(message);
                }
            } catch (IOException e) {
                listener.onMessageReceived("Mất kết nối đến server.");
            }
        }).start();
    }

    public interface MessageListener {
        void onMessageReceived(String message);
    }
}
