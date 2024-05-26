package model;

public class Image {
    private int id;
    private int userId;
    private String imageName;
    private byte[] imageData;

    public Image(int userId, String imageName, byte[] imageData) {
        this.userId = userId;
        this.imageName = imageName;
        this.imageData = imageData;
    }

    public Image(int id, int userId, String imageName, byte[] imageData) {
        this.id = id;
        this.userId = userId;
        this.imageName = imageName;
        this.imageData = imageData;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getImageName() {
        return imageName;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
