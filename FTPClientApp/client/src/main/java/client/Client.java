package client;

import java.io.*;
import java.net.Socket;

public class Client {
    private String hostname;
    private int port;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void start() {
        try {
            socket = new Socket(hostname, port);
            System.out.println("Connected to the server");

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            new Thread(new ReadThread()).start();
            new Thread(new WriteThread()).start();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class ReadThread implements Runnable {
        @Override
        public void run() {
            try {
                String response;
                while ((response = reader.readLine()) != null) {
                    System.out.println("Server: " + response);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class WriteThread implements Runnable {
        @Override
        public void run() {
            try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
                String message;
                while ((message = consoleReader.readLine()) != null) {
                    writer.println(message);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 12345);
        client.start();
    }
}
