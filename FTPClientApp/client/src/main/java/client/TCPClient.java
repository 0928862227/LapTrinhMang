package client;

import java.io.*;
import java.net.*;

public class TCPClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    // Nhập ip và port để kết nối tới Server
    // Sử dụng PrintWriter để gửi thông điệp và BufferedReader để nhận phản hồi từ
    // server.
    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Gửi tin nhắn từ phía Client tới Server
    public String sendMessage(String msg) {
        out.println(msg);
        String resp = null;
        try {
            resp = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TCPClient client = new TCPClient();
        client.startConnection("127.0.0.1", 6666);
        String response = client.sendMessage("Hello Server");
        System.out.println("Server response: " + response);
        response = client.sendMessage("stopConnection");
        System.out.println("Server response: " + response);
        client.stopConnection();
    }
}
