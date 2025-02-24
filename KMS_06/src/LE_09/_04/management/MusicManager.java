package LE_09._04.management;

import java.sql.SQLException;
import java.util.Scanner;

public class MusicManager {
    private DBManager dbm;
    private Scanner sc;

    public MusicManager(DBManager dbm) {
        this.dbm = dbm;
        this.sc = new Scanner(System.in);
    }

    public void runApp() {
        while (true) {
            printMenu();
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    addAlbum();
                    break;
                case "2":
                    addTrack();
                    break;
                case "3":
                    deleteAlbum();
                    break;
                case "4":
                    deleteTrack();
                    break;
                case "5":
                    displayAlbum();
                    break;
                case "6":
                    displayCollection();
                    break;
                case "7":
                    System.out.println("Exiting..");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nMusic Manager:\n1. Add Album\n2. Add Track\n3. Delete Album\n4. Delete Track\n5. Display Album\n6. Display Collection\n7. Exit");
        System.out.print("Enter your choice: ");
    }


    public void addAlbum() {
        try {
            System.out.print("Enter album title: ");
            String title = sc.nextLine();
            System.out.print("Enter album artist: ");
            String artist = sc.nextLine();

            int album_id = dbm.addAlbum(title, artist);
            if (album_id != -1) {
                System.out.println("Album added with ID: " +album_id);
            } else {
                System.out.println("Album could not be added.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding album: " + e.getMessage());
        }
    }


    public void addTrack() {
        try {
            System.out.print("Enter album ID for the track: ");
            int album_id = Integer.parseInt(sc.nextLine());
            System.out.println("Enter track title: ");
            String track_title = sc.nextLine();
            System.out.print("Enter track duration(MM:SS or seconds): ");
            String track_duration = sc.nextLine();
            System.out.print("Enter MP3 File Name: ");
            String mp3_file_name = sc.nextLine();

            int track_id = dbm.addTrack(track_title, track_duration, mp3_file_name, album_id);
            if (track_id != -1) {
                System.out.println("Track added with ID: " +track_id);
            } else {
                System.out.println("Track could not be added.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding track: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number input.");
        }
    }

    public void deleteAlbum() {
        try {
            System.out.print("Enter album ID to delete: ");
            int album_id = sc.nextInt();
            dbm.deleteAlbum(album_id);
            System.out.println("Album deleted.");
        } catch (SQLException e) {
            System.err.println("Error deleting album: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid album ID.");
        }
    }

    public void deleteTrack() {
        try{
            System.out.print("Enter track ID to delete: ");
            int track_id = sc.nextInt();
            dbm.deleteTrack(track_id);
            System.out.println("Track deleted.");
        } catch (SQLException e) {
            System.err.println("Error deleting track: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid track ID.");
        }
    }

    private void displayAlbum() {
        try {
            System.out.print("Enter album ID to display: ");
            int album_id = sc.nextInt();
            dbm.displayAlbum(album_id);
        } catch (SQLException e) {
            System.err.println("Error displaying album: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid album ID.");
        }
    }

    private void displayCollection() {
        try {
            dbm.displayAllAlbums();
        } catch (SQLException e) {
            System.err.println("Error displaying collection: " + e.getMessage());
        }
    }

}
