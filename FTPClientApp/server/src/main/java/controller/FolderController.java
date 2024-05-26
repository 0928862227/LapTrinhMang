package controller;

import model.Folder;
import server.DatabaseManager;

import java.util.List;

public class FolderController {
    private DatabaseManager dbManager;

    public FolderController() {
        this.dbManager = new DatabaseManager();
    }

    public boolean createFolder(Folder folder) {
        return dbManager.createFolder(folder.getUserId(), folder.getFolderName(), folder.getParentFolderId());
    }

    public List<Folder> getAllFolders() {
        return dbManager.getAllFolders();
    }

    public Folder getFolderById(int id) {
        return dbManager.getFolderById(id);
    }

    public boolean updateFolder(Folder folder) {
        return dbManager.updateFolder(folder.getId(), folder.getUserId(), folder.getFolderName(),
                folder.getParentFolderId());
    }

    public boolean deleteFolder(int id) {
        return dbManager.deleteFolder(id);
    }
}
