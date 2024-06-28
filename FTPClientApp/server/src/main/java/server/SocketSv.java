package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketSv {

    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server đang lắng nghe trên cổng " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Kết nối từ một client mới");

                new ClientHandler(socket).start();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

