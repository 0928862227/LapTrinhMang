package view;

import controller.UserController;
import model.User;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private UserController userController = new UserController();
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        while (true) {
            System.out.println("1. Đăng ký");
            System.out.println("2. Xem tất cả người dùng");
            System.out.println("3. Cập nhật thông tin người dùng");
            System.out.println("4. Xóa người dùng");
            System.out.println("5. Thoát");
            System.out.print("Chọn tùy chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    displayAllUsers();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private void registerUser() {
        System.out.print("Nhập tên đăng nhập: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();

        if (userController.createUser(username, password, email)) {
            System.out.println("Đăng ký thành công!");
        } else {
            System.out.println("Đăng ký thất bại!");
        }
    }

    private void displayAllUsers() {
        List<User> users = userController.getAllUsers();
        for (User user : users) {
            System.out.println(
                    "ID: " + user.getId() + ", Username: " + user.getUsername() + ", Email: " + user.getEmail());
        }
    }

    private void updateUser() {
        System.out.print("Nhập ID người dùng: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nhập tên đăng nhập mới: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu mới: ");
        String password = scanner.nextLine();
        System.out.print("Nhập email mới: ");
        String email = scanner.nextLine();

        if (userController.updateUser(id, username, password, email)) {
            System.out.println("Cập nhật thông tin thành công!");
        } else {
            System.out.println("Cập nhật thông tin thất bại!");
        }
    }

    private void deleteUser() {
        System.out.print("Nhập ID người dùng: ");
        int id = scanner.nextInt();

        if (userController.deleteUser(id)) {
            System.out.println("Xóa người dùng thành công!");
        } else {
            System.out.println("Xóa người dùng thất bại!");
        }
    }

    public static void main(String[] args) {
        UserView userView = new UserView();
        userView.displayMenu();
    }
}
