package controller;

import model.Image;
import server.DatabaseManager;

import java.util.List;

public class ImageController {
    private DatabaseManager dbManager = new DatabaseManager();

    public boolean createImage(Image image) {
        return dbManager.createImage(image);
    }

    public Image getImageById(int id) {
        return dbManager.getImageById(id);
    }

    public List<Image> getAllImages() {
        return dbManager.getAllImages();
    }

    public boolean updateImage(Image image) {
        return dbManager.updateImage(image);
    }

    public boolean deleteImage(int id) {
        return dbManager.deleteImage(id);
    }
}
