package LE_09._04.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public final class DBConnection {
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream configFile = new FileInputStream("C:/Users/052277/java_projects/KMS_06/src/config.properties");
            properties.load(configFile);
        } catch (IOException e) {
            System.out.println("Error loading config.properties file. "+ e.getMessage());
        }
    }


    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }

    public static Connection getConnection(String database) throws SQLException {
        String url = properties.getProperty("db.url") + database;
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }
}
