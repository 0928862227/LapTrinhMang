package controller;

import model.File;
import server.DatabaseManager;

import java.util.List;

public class FileController {

    public boolean createFile(File file) {
        return DatabaseManager.createFile(file);
    }

    public File getFile(int id) {
        return DatabaseManager.readFile(id);
    }

    public List<File> getAllFiles() {
        return DatabaseManager.readAllFiles();
    }

    public boolean updateFile(File file) {
        return DatabaseManager.updateFile(file);
    }

    public boolean deleteFile(int id) {
        return DatabaseManager.deleteFile(id);
    }
}
