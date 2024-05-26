package view;

import controller.FolderController;
import model.Folder;

import java.util.List;
import java.util.Scanner;

public class FolderView {
    private FolderController folderController = new FolderController();
    private Scanner scanner = new Scanner(System.in);

    public void displayAllFolders() {
        List<Folder> folders = folderController.getAllFolders();
        for (Folder folder : folders) {
            System.out.println("ID: " + folder.getId() + ", User ID: " + folder.getUserId() +
                    ", Folder Name: " + folder.getFolderName() + ", Parent Folder ID: " + folder.getParentFolderId());
        }
    }

    public void createFolder() {
        System.out.println("Enter User ID:");
        int userId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter Folder Name:");
        String folderName = scanner.nextLine();
        System.out.println("Enter Parent Folder ID (or press Enter if none):");
        String parentFolderIdInput = scanner.nextLine();
        Integer parentFolderId = parentFolderIdInput.isEmpty() ? null : Integer.parseInt(parentFolderIdInput);

        Folder folder = new Folder(userId, folderName, parentFolderId);
        if (folderController.createFolder(folder)) {
            System.out.println("Folder created successfully.");
        } else {
            System.out.println("Failed to create folder.");
        }
    }

    public void displayFolderById() {
        System.out.println("Enter Folder ID:");
        int id = scanner.nextInt();
        Folder folder = folderController.getFolderById(id);
        if (folder != null) {
            System.out.println("ID: " + folder.getId() + ", User ID: " + folder.getUserId() +
                    ", Folder Name: " + folder.getFolderName() +
                    ", Parent Folder ID: " + folder.getParentFolderId());
        } else {
            System.out.println("Folder not found.");
        }
    }

    public void updateFolder() {
        System.out.println("Enter Folder ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter new User ID:");
        int userId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter new Folder Name:");
        String folderName = scanner.nextLine();
        System.out.println("Enter new Parent Folder ID (or press Enter if none):");
        String parentFolderIdInput = scanner.nextLine();
        Integer parentFolderId = parentFolderIdInput.isEmpty() ? null : Integer.parseInt(parentFolderIdInput);

        Folder folder = new Folder(id, userId, folderName, parentFolderId);
        if (folderController.updateFolder(folder)) {
            System.out.println("Folder updated successfully.");
        } else {
            System.out.println("Failed to update folder.");
        }
    }

    public void deleteFolder() {
        System.out.println("Enter Folder ID to delete:");
        int id = scanner.nextInt();
        if (folderController.deleteFolder(id)) {
            System.out.println("Folder deleted successfully.");
        } else {
            System.out.println("Failed to delete folder.");
        }
    }
}
