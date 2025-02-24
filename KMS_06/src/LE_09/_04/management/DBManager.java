package LE_09._04.management;


import LE_09._04.music.Album;
import LE_09._04.music.Track;
import LE_09._04.utils.DBConnection;
import LE_09._04.utils.TimeUtils;

import java.sql.*;


public class DBManager {
    public static void createDatabase() {
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS music_db";
            stmt.executeUpdate(sql);
            System.out.println("Database created or already exists");

            try(Connection dbCon = DBConnection.getConnection("music_db");
                Statement stmt2 = dbCon.createStatement()) {
                System.out.println("Connected to database.");

                String createAlbumsTable = """
                        CREATE TABLE IF NOT EXISTS albums (
                        album_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                        title VARCHAR(100),
                        artist VARCHAR(100),
                        total_playtime INT DEFAULT 0
                        )
                        """;
                String createTracksTable = """
                        CREATE TABLE IF NOT EXISTS tracks (
                        track_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                        track_title VARCHAR(100),
                        track_duration INT,
                        mp3_file_name VARCHAR(100),
                        album_id INTEGER,
                        FOREIGN KEY (album_id) REFERENCES albums(album_id) ON DELETE CASCADE
                        )
                        """;
                stmt2.executeUpdate(createAlbumsTable);
                stmt2.executeUpdate(createTracksTable);

                System.out.println("Tables created or already exists");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public int addAlbum(String title, String artist) throws SQLException {
        String sql = "INSERT INTO albums (title, artist) VALUES (?, ?)";

        try (Connection dbCon = DBConnection.getConnection("music_db");
             PreparedStatement stmt = dbCon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, title);
            stmt.setString(2, artist);

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public int addTrack(String track_title, String track_duration, String mp3_file_name, int album_id) throws SQLException {
        String sql = "INSERT INTO tracks (track_title, track_duration, mp3_file_name, album_id) VALUES (?, ?, ?, ?)";
        int track_id = -1;

        try (Connection dbCon = DBConnection.getConnection("music_db");
             PreparedStatement stmt = dbCon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, track_title);
            stmt.setInt(2, TimeUtils.parseDuration(track_duration));
            stmt.setString(3, mp3_file_name);
            stmt.setInt(4, album_id);

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    track_id = rs.getInt(1);
                }
            }
        }
        updateTotalPlaytime(album_id);
        return track_id;
    }

    public void updateTotalPlaytime(int album_id) throws SQLException {
        String Sql = "UPDATE albums SET total_playtime = (SELECT SUM(track_duration) FROM tracks WHERE album_id = ?) WHERE album_id = ?";
        try (Connection dbCon = DBConnection.getConnection("music_db");
            PreparedStatement stmt = dbCon.prepareStatement(Sql)) {
            stmt.setInt(1, album_id);
            stmt.setInt(2, album_id);
            stmt.executeUpdate();
        }
    }

    public void deleteAlbum(int album_id) throws SQLException {
        String sql = "DELETE FROM albums WHERE album_id = ?";

        try (Connection conn = DBConnection.getConnection("music_db");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, album_id);
            stmt.executeUpdate();
        }
    }

    public void deleteTrack(int track_id) throws SQLException {
        int album_id = -1;
        String getAlbumSql = "SELECT album_id FROM tracks WHERE track_id = ?";

        try (Connection con = DBConnection.getConnection("music_db");
        PreparedStatement stmt = con.prepareStatement(getAlbumSql)) {
            stmt.setInt(1, track_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    album_id = rs.getInt("album_id");
                }
            }
        }
        String trackSql = "DELETE FROM tracks WHERE track_id = ?";
        try (Connection con = DBConnection.getConnection("music_db");
        PreparedStatement stmt = con.prepareStatement(trackSql)) {
            stmt.setInt(1, track_id);
            stmt.executeUpdate();
        }
        if (album_id != -1) {
            updateTotalPlaytime(album_id);
        }
    }


    public void displayAlbum (int album_id) throws SQLException {
        String albumSql = "SELECT * FROM albums WHERE album_id = ?";
        String trackSql = "SELECT * FROM tracks WHERE album_id = ?";

        Album album = null;

        try (Connection con = DBConnection.getConnection("music_db");
             PreparedStatement albumStmt = con.prepareStatement(albumSql)) {

            albumStmt.setInt(1, album_id);
            try (ResultSet albumRs = albumStmt.executeQuery()) {
                if (albumRs.next()) {
                    album = new Album(
                        albumRs.getInt("album_id"),
                        albumRs.getString("title"),
                        albumRs.getString("artist"),
                        albumRs.getInt("total_playtime")
                    );
                }
            }
        }

        if (album == null) {
            System.out.println("Album not found");
            return;
        }

        System.out.println(album);
        System.out.println("Tracks: ");
        try (Connection con = DBConnection.getConnection("music_db");
             PreparedStatement trackStmt = con.prepareStatement(trackSql)) {
            trackStmt.setInt(1, album_id);
            try (ResultSet trackRs = trackStmt.executeQuery()) {
                while (trackRs.next()) {
                    Track track = new Track(
                        trackRs.getInt("track_id"),
                        trackRs.getString("track_title"),
                        trackRs.getInt("track_duration"),
                        trackRs.getString("mp3_file_name")
                    );
                    System.out.println(track);
                }
            }
        }
    }

    public void displayAllAlbums() throws SQLException {
        String albumSql = "SELECT * FROM albums ORDER BY album_id DESC";
        String trackSql = "SELECT * FROM tracks WHERE album_id = ?";

        try (Connection con = DBConnection.getConnection("music_db");
        PreparedStatement albumStmt = con.prepareStatement(albumSql);
        ResultSet albumRs = albumStmt.executeQuery()) {

            if (!albumRs.isBeforeFirst()) {
                System.out.println("Album not found");
                return;
            }

            while (albumRs.next()) {
                Album album = new Album(
                    albumRs.getInt("album_id"),
                    albumRs.getString("title"),
                    albumRs.getString("artist"),
                    albumRs.getInt("total_playtime")
                );

                System.out.println(album);
                System.out.println("Tracks: ");
                try (Connection con2 = DBConnection.getConnection("music_db");
                PreparedStatement trackStmt = con2.prepareStatement(trackSql)) {
                    trackStmt.setInt(1, album.album_id());
                    try (ResultSet trackRs = trackStmt.executeQuery()) {
                        while (trackRs.next()) {
                            Track track = new Track(
                                trackRs.getInt("track_id"),
                                trackRs.getString("track_title"),
                                trackRs.getInt("track_duration"),
                                trackRs.getString("mp3_file_name")
                            );
                        System.out.println(track);
                        }
                    }
                }
            }
        }
    }

    public void close() throws SQLException {
        Connection con = DBConnection.getConnection("music_db");
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}

