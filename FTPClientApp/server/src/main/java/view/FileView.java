package view;

import controller.FileController;
import model.File;

import java.util.List;
import java.util.Scanner;

public class FileView {
    private FileController fileController = new FileController();
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        while (true) {
            System.out.println("1. Tải lên file");
            System.out.println("2. Xem tất cả file");
            System.out.println("3. Cập nhật file");
            System.out.println("4. Xóa file");
            System.out.println("5. Thoát");
            System.out.print("Chọn tùy chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    uploadFile();
                    break;
                case 2:
                    displayAllFiles();
                    break;
                case 3:
                    updateFile();
                    break;
                case 4:
                    deleteFile();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private void uploadFile() {
        System.out.print("Nhập ID người dùng: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập tên file: ");
        String fileName = scanner.nextLine();
        System.out.print("Nhập đường dẫn tới file: ");
        String filePath = scanner.nextLine();

        // Đọc file từ đường dẫn và chuyển thành byte array
        byte[] fileData = null;
        try {
            fileData = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath));
        } catch (java.io.IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
            return;
        }

        File file = new File(userId, fileName, fileData);
        if (fileController.createFile(file)) {
            System.out.println("File đã được tải lên thành công!");
        } else {
            System.out.println("Tải lên file thất bại!");
        }
    }

    private void displayAllFiles() {
        List<File> files = fileController.getAllFiles();
        for (File file : files) {
            System.out.println(
                    "ID: " + file.getId() + ", UserID: " + file.getUserId() + ", FileName: " + file.getFileName());
        }
    }

    private void updateFile() {
        System.out.print("Nhập ID file: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập ID người dùng mới: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập tên file mới: ");
        String fileName = scanner.nextLine();
        System.out.print("Nhập đường dẫn tới file mới: ");
        String filePath = scanner.nextLine();

        // Đọc file từ đường dẫn và chuyển thành byte array
        byte[] fileData = null;
        try {
            fileData = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath));
        } catch (java.io.IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
            return;
        }

        File file = new File(userId, fileName, fileData);
        file.setId(id);
        if (fileController.updateFile(file)) {
            System.out.println("File đã được cập nhật thành công!");
        } else {
            System.out.println("Cập nhật file thất bại!");
        }
    }

    private void deleteFile() {
        System.out.print("Nhập ID file: ");
        int id = scanner.nextInt();

        if (fileController.deleteFile(id)) {
            System.out.println("File đã được xóa thành công!");
        } else {
            System.out.println("Xóa file thất bại!");
        }
    }

    public static void main(String[] args) {
        FileView fileView = new FileView();
        fileView.displayMenu();
    }
}
