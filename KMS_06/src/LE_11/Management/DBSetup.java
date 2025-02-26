package LE_11.Management;

import Utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static LE_11.Management.DBManager.executeUpdate;

public class DBSetup {
    public static void createDatabaseAndTables() {
        try {
            try(Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()){

            String sql = "CREATE DATABASE IF NOT EXISTS  club_management";
            stmt.executeUpdate(sql);
            System.out.println("Database created or already exists");
        }

            try (Connection dbCon = DBConnection.getConnection("club_management")) {
                System.out.println("Connected to database");

                executeUpdate("""
                        CREATE TABLE IF NOT EXISTS clubs(
                        club_id INT PRIMARY KEY AUTO_INCREMENT,
                        club_name VARCHAR(255) NOT NULL,
                        club_location VARCHAR(255) NOT NULL
                        )
                        """, stmt -> {});

                executeUpdate("""
                        CREATE TABLE IF NOT EXISTS teams(
                        team_id INT PRIMARY KEY AUTO_INCREMENT,
                        team_name VARCHAR(255) NOT NULL,
                        team_league VARCHAR(255) NOT NULL,
                        club_id INT,
                        FOREIGN KEY (club_id) REFERENCES clubs(club_id) ON DELETE CASCADE
                        )
                        """, stmt -> {});

                executeUpdate("""
                        CREATE TABLE IF NOT EXISTS players(
                        player_id INT PRIMARY KEY AUTO_INCREMENT,
                        player_name VARCHAR(255) NOT NULL,
                        player_age INT NOT NULL,
                        player_position VARCHAR(255) NOT NULL,
                        player_salary DOUBLE NOT NULL,
                        team_id INT,
                        FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE CASCADE
                        )
                        """, stmt -> {});

                executeUpdate("""
                        CREATE TABLE IF NOT EXISTS coaches(
                        coach_id INT PRIMARY KEY AUTO_INCREMENT,
                        coach_name VARCHAR(255) NOT NULL,
                        coach_age INT NOT NULL,
                        team_id INT,
                        FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE CASCADE)
                        """, stmt -> {});

                executeUpdate("""
                        CREATE TABLE IF NOT EXISTS matches(
                        match_id INT PRIMARY KEY AUTO_INCREMENT,
                        match_date DATE NOT NULL,
                        match_venue VARCHAR(255) NOT NULL,
                        team_1_id INT NOT NULL,
                        team_2_id INT NOT NULL,
                        FOREIGN KEY (team_1_id) REFERENCES teams(team_id),
                        FOREIGN KEY (team_2_id) REFERENCES teams(team_id)
                        )
                        """, stmt -> {});


                System.out.println("Tables created or already exists");
            }
        } catch(SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }
}
