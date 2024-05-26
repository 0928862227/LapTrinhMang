package controller;

import model.User;
import server.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    public boolean createUser(String username, String password, String email) {
        return DatabaseManager.createUser(username, password, email);
    }

    public List<User> getAllUsers() {
        List<String> userData = DatabaseManager.readAllUsers();
        List<User> users = new ArrayList<>();

        for (String data : userData) {
            String[] userDetails = data.split(", ");
            int id = Integer.parseInt(userDetails[0].split(": ")[1]);
            String username = userDetails[1].split(": ")[1];
            String email = userDetails[2].split(": ")[1];
            users.add(new User(id, username, "", email));
        }

        return users;
    }

    public boolean updateUser(int id, String username, String password, String email) {
        return DatabaseManager.updateUser(id, username, password, email);
    }

    public boolean deleteUser(int id) {
        return DatabaseManager.deleteUser(id);
    }
}
