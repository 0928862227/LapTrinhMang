package service;

import view.FolderView;
import view.FileView;
import view.ImageView;

import java.util.Scanner;

public class DataTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FolderView folderView = new FolderView();
        FileView fileView = new FileView();
        ImageView imageView = new ImageView();

        boolean running = true;
        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Display All Folders");
            System.out.println("2. Display Folder by ID");
            System.out.println("3. Create Folder");
            System.out.println("4. Update Folder");
            System.out.println("5. Delete Folder");
            System.out.println("6. Display All Files");
            System.out.println("7. Upload File");
            System.out.println("8. Update File");
            System.out.println("9. Delete File");
            System.out.println("10. Display All Images");
            System.out.println("11. Display Image by ID");
            System.out.println("12. Create Image");
            System.out.println("13. Update Image");
            System.out.println("14. Delete Image");
            System.out.println("15. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    folderView.displayAllFolders();
                    break;
                case 2:
                    folderView.displayFolderById();
                    break;
                case 3:
                    folderView.createFolder();
                    break;
                case 4:
                    folderView.updateFolder();
                    break;
                case 5:
                    folderView.deleteFolder();
                    break;
                case 6:
                    fileView.displayAllFiles();
                    break;
                case 7:
                    fileView.updateFile();
                    break;
                case 8:
                    fileView.updateFile();
                    break;
                case 9:
                    fileView.deleteFile();
                    break;
                case 10:
                    imageView.displayAllImages();
                    break;
                case 11:
                    imageView.displayImageById();
                    break;
                case 12:
                    imageView.createImage();
                    break;
                case 13:
                    imageView.updateImage();
                    break;
                case 14:
                    imageView.deleteImage();
                    break;
                case 15:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            scanner.close();
        }
    }
}
