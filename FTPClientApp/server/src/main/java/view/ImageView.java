package view;

import controller.ImageController;
import model.Image;

import java.util.List;
import java.util.Scanner;

public class ImageView {
    private ImageController imageController = new ImageController();
    private Scanner scanner = new Scanner(System.in);

    public void displayAllImages() {
        List<Image> images = imageController.getAllImages();
        for (Image image : images) {
            System.out.println("ID: " + image.getId() + ", User ID: " + image.getUserId() +
                    ", Image Name: " + image.getImageName());
        }
    }

    public void displayImageById() {
        System.out.println("Enter Image ID:");
        int id = scanner.nextInt();
        Image image = imageController.getImageById(id);
        if (image != null) {
            System.out.println("ID: " + image.getId() + ", User ID: " + image.getUserId() +
                    ", Image Name: " + image.getImageName());
        } else {
            System.out.println("Image not found.");
        }
    }

    public void createImage() {
        System.out.println("Enter User ID:");
        int userId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter Image Name:");
        String imageName = scanner.nextLine();
        System.out.println("Enter Image Data (as text):");
        String imageData = scanner.nextLine(); // for simplicity, we use text as image data

        Image image = new Image(userId, imageName, imageData.getBytes());
        if (imageController.createImage(image)) {
            System.out.println("Image created successfully.");
        } else {
            System.out.println("Failed to create image.");
        }
    }

    public void updateImage() {
        System.out.println("Enter Image ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter new User ID:");
        int userId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter new Image Name:");
        String imageName = scanner.nextLine();
        System.out.println("Enter new Image Data (as text):");
        String imageData = scanner.nextLine(); // for simplicity, we use text as image data

        Image image = new Image(id, userId, imageName, imageData.getBytes());
        if (imageController.updateImage(image)) {
            System.out.println("Image updated successfully.");
        } else {
            System.out.println("Failed to update image.");
        }
    }

    public void deleteImage() {
        System.out.println("Enter Image ID to delete:");
        int id = scanner.nextInt();
        if (imageController.deleteImage(id)) {
            System.out.println("Image deleted successfully.");
        } else {
            System.out.println("Failed to delete image.");
        }
    }
}
