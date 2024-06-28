package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Đọc dữ liệu từ client
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = reader.readLine();
            System.out.println("Nhận yêu cầu đăng ký: " + message);

            // Phân tích dữ liệu đăng ký
            String[] data = message.split(" ");
            String username = data[0];
            String password = data[1];
            String gmail = data[2];

            // Xử lý logic đăng ký (ví dụ: lưu vào cơ sở dữ liệu)
            boolean registrationSuccessful = registerUser(username, password, gmail);

            // Gửi phản hồi về client
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            if (registrationSuccessful) {
                writer.println("Đăng ký thành công");
            } else {
                writer.println("Đăng ký thất bại");
            }

            // Đóng các luồng và socket
            reader.close();
            writer.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean registerUser(String username, String password, String gmail) {
        // Thực hiện logic đăng ký tại đây
        // Có thể liên quan đến các thao tác với cơ sở dữ liệu, kiểm tra hợp lệ, v.v.
        // Thực hiện logic đăng ký trong CSDL
        boolean registrationSuccessful = DatabaseManager.createUser(username, password, gmail);
        if (registrationSuccessful) {
            System.out.println("Đăng ký người dùng thành công.");
        } else {
            System.out.println("Đăng ký người dùng thất bại.");
        }

        return registrationSuccessful;
        // Trong ví dụ này, chúng ta giả sử việc đăng ký luôn thành công
        
    }
}
