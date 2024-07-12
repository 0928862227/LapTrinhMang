package server;

import java.io.IOException;

public class IOExceptionCus {
    public static void CusIOException(IOException e) {
        e.printStackTrace();
        // Sẽ thêm các logic xử lý khác,
        // ví dụ: ghi vào file log, thông báo người dùng, v.v.
    }

}
