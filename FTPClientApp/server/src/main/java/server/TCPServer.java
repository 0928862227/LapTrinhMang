package server;

import java.io.*;
import java.net.*;

public class TCPServer {
    private ServerSocket serverSocket;

    // Tạo một serverSocket để lắng nghe kết nối từ Client
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            // Chấp nhận kết nối từ phía Client
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * ClientHandler xử lý mỗi kết nối client trong một thread riêng biệt.
     * Nó đọc các thông điệp từ client và gửi lại phản hồi.
     */
    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received: " + inputLine);
                    if ("exit".equalsIgnoreCase(inputLine)) {
                        out.println("Goodbye!");
                        break;
                    }
                    out.println("Echo: " + inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        server.start(6666);
    }
}
