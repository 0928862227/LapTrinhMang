package model;

public class File {
    private int id;
    private int userId;
    private String fileName;
    private byte[] fileData;

    // Constructors
    public File() {
    }

    public File(int userId, String fileName, byte[] fileData) {
        this.userId = userId;
        this.fileName = fileName;
        this.fileData = fileData;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
