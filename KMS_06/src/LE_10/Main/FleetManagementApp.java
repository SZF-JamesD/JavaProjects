package LE_10.Main;

import LE_10.Management.DBManager;
import LE_10.Management.FleetManager;

public class FleetManagementApp {
    public static void main(String[] args) {
        try {
            DBManager dbm = new DBManager();
            FleetManager fm = new FleetManager(dbm);
            dbm.createDatabase();
            fm.runApp();
            dbm.close();
        } catch (Exception e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
