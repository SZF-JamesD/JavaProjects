package LE_09._04.main;


import LE_09._04.management.DBManager;
import LE_09._04.management.MusicManager;

public class MusicManagerApp {
    public static void main(String[] args) {
        try {
            DBManager dbm = new DBManager();
            MusicManager mm = new MusicManager(dbm);
            dbm.createDatabase();
            mm.runApp();
            dbm.close();
        } catch (Exception e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
