package model;

public class Folder {
    private int id;
    private int userId;
    private String folderName;
    private Integer parentFolderId;

    public Folder(int id, int userId, String folderName, Integer parentFolderId) {
        this.id = id;
        this.userId = userId;
        this.folderName = folderName;
        this.parentFolderId = parentFolderId;
    }

    public Folder(int userId, String folderName, Integer parentFolderId) {
        this.userId = userId;
        this.folderName = folderName;
        this.parentFolderId = parentFolderId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getFolderName() {
        return folderName;
    }

    public Integer getParentFolderId() {
        return parentFolderId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void setParentFolderId(Integer parentFolderId) {
        this.parentFolderId = parentFolderId;
    }
}
