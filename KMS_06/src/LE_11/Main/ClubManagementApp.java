package LE_11.Main;

import LE_11.Management.DBManager;
import LE_11.Management.ClubManager;
import LE_11.Management.DBSetup;

public class ClubManagementApp {
    public static void main(String[] args) {
        try {
            DBManager dbm = new DBManager();
            ClubManager cm = new ClubManager(dbm);
            DBSetup.createDatabaseAndTables();
            cm.runApp();
            dbm.close();
        } catch (Exception e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
