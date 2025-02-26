package LE_11.Management;

import LE_11.Classes.Match;
import LE_11.Classes.Team;
import LE_11.Classes.members.Coach;
import LE_11.Classes.members.Player;
import Utils.DBConnection;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    @FunctionalInterface
    public interface SQLConsumer<T> {
        void accept(T t) throws SQLException;
    }

    @FunctionalInterface
    public interface SQLFunction<T, R> {
        R apply(T t) throws SQLException;
    }

    public DBManager() throws SQLException {
    }

    public void addClub(String cName, String cAddress) throws SQLException {
        String sql = "INSERT INTO clubs (club_name, club_location) VALUES (?, ?)";
        executeUpdate(sql, stmt -> {
            stmt.setString(1, cName);
            stmt.setString(2, cAddress);
        });
    }

    public void removeClub(int cId) throws SQLException {
        executeDelete("clubs", "club_id=?", stmt -> stmt.setInt(1, cId));
    }

    public void addTeam(String tName, String tLeague, int cId) throws SQLException {
        String sql = "INSERT INTO teams (team_name, team_league, club_id) VALUES (?, ?, ?)";
        executeUpdate(sql, stmt -> {
            stmt.setString(1, tName);
            stmt.setString(2, tLeague);
            stmt.setInt(3, cId);
        });
    }


    public void removeTeam(int cId, int tId) throws SQLException {
        executeDelete("teams", "club_id=? AND tId=?", stmt -> {
            stmt.setInt(1, cId);
            stmt.setInt(2, tId);
        });
    }

    public void addPlayer(String name, int age, double pSalary, String pPosition, int tId) throws SQLException {
        String sql = "INSERT INTO players (player_name, player_age, player_salary, player_position, team_id) VALUES (?, ?, ?, ?, ?)";
        executeUpdate(sql, stmt -> {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setDouble(3, pSalary);
            stmt.setString(4, pPosition);
            stmt.setInt(5, tId);
        });
    }

    public void addCoach(String name, int age, int tId) throws SQLException {
        String sql = "INSERT INTO coaches (coach_name, coach_age, club_id) VALUES (?, ?, ?)";
        executeUpdate(sql, stmt -> {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setInt(3, tId);
        });
    }

    public void removePlayer(int tId, int pId) throws SQLException {
        executeDelete("players", "team_id=? AND player_id=?", stmt -> {
            stmt.setInt(1, tId);
            stmt.setInt(2, pId);
        });
        }


    public void removeCoach(int tId, int cId)  throws SQLException{
        executeDelete("coaches", "team_id=? AND coach_id=?", stmt -> {
            stmt.setInt(1, tId);
            stmt.setInt(2, cId);
        });
    }


    public List<Team> getAllTeams() throws SQLException {
        String sql = "SELECT * FROM teams ORDER BY team_id DESC";

        return executeQuery(sql, stmt -> {},
                rs -> {
            Team team = new Team(
                    rs.getString("team_name"),
                    rs.getInt("team_id"),
                    rs.getString("team_league")
            );

            loadPlayers(team);

            loadCoaches(team);

            return team;
            }
        );
    }

    public String getTeam(int tId) throws SQLException {
        String sql = "SELECT * FROM teams WHERE team_id=?";
        return executeSingleResultQuery(sql,
stmt -> stmt.setInt(1, tId),
        rs -> {
                Team team = new Team(
                        rs.getString("team_name"),
                        rs.getInt("team_id"),
                        rs.getString("team_league")
                );

                loadPlayers(team);
                loadCoaches(team);
                return team.displayInfo();
            }
        );
    }

    public void scheduleMatch(Date date, String venue, int team1, int team2) throws SQLException {
        String sql = "INSERT INTO matches (match_date, match_venue, team_1_id, team_2_id) VALUES (?, ?, ?, ?)";
        executeUpdate(sql, stmt -> {
            stmt.setDate(1, date);
            stmt.setString(2, venue);
            stmt.setInt(3, team1);
            stmt.setInt(4, team2);
        });
    }

    public Match getMatch(int mId) throws SQLException {
        String sql = "SELECT * FROM matches WHERE match_id=?";
        return executeSingleResultQuery(sql, stmt ->{
            stmt.setInt(1, mId);
            },
            rs -> {
                        int team1Id = rs.getInt("team_1_id");
                        int team2Id = rs.getInt("team_2_id");
                        Date matchDate = rs.getDate("match_date");
                        String venue = rs.getString("match_venue");

                        String team1 = getTeamNameById(team1Id);
                        String team2 = getTeamNameById(team2Id);

                return new Match(team1, team2, matchDate, venue);
            }
        );
    }

    public List<Match> getAllMatches() throws SQLException {
        String sql = "SELECT * FROM matches ORDER BY match_id DESC";
        return executeQuery(sql, stmt -> {},
                rs -> {
                    return new Match(
                            getTeamNameById(rs.getInt("team_1_id")),
                            getTeamNameById(rs.getInt("team_2_id")),
                            rs.getDate("match_date"),
                            rs.getString("match_venue")
                    );
            });
    }

    private void loadPlayers(Team team) throws SQLException {
        String sql = "SELECT * FROM players WHERE team_id=?";
        executeQuery(sql,
                stmt -> stmt.setInt(1, team.getTeamID()),
                rs -> {
                    Player player = new Player(
                            rs.getString("player_name"),
                            rs.getInt("player_age"),
                            rs.getString("player_position"),
                            rs.getDouble("player_salary"),
                            rs.getInt("player_id")
                    );
                    team.addPlayer(player);
                    return null;
                }
        );
    }

    private void loadCoaches(Team team) throws SQLException {
        String sql = "SELECT coach_name FROM coaches WHERE team_id=?";
        executeQuery(sql,
                stmt -> stmt.setInt(1, team.getTeamID()),
                rs -> {
                    Coach coach = new Coach(
                            rs.getString("coach_name"),
                            rs.getInt("coach_age"),
                            rs.getInt("coach_id")
                    );
                    team.addCoach(coach);
                    return null;
                }
        );
    }

    private String getTeamNameById(int teamId) throws SQLException {
        String sql = "SELECT team_name FROM teams WHERE team_id=?";

        try (Connection con = getConnection("club_management");
         PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, teamId);
            try (ResultSet rs = stmt.executeQuery()) {  // Ensure this result set is separate
                if (rs.next()) {
                    String teamName = rs.getString("team_name");
                    return teamName;
                } else {
                    System.out.println("No rows found for team_id: " + teamId);
                    return null;
                }
            }
        }
    }

    private static Connection getConnection(String db_name) throws SQLException {
        return DBConnection.getConnection(db_name);
    }

    protected static void executeUpdate(String sql, SQLConsumer<PreparedStatement> stmtSetter){
        try (Connection con = getConnection("club_management");
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmtSetter.accept(stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error executing SQL: " + e.getMessage());
        }
    }

    private <T> List<T> executeQuery(String sql, SQLConsumer<PreparedStatement> stmtSetter, SQLFunction<ResultSet, T> mapper) throws SQLException {
        List<T> results = new ArrayList<>();
        try (Connection con = getConnection("club_management");
        PreparedStatement stmt = con.prepareStatement(sql)) {
            stmtSetter.accept(stmt);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(mapper.apply(rs));
                }
            }
        }
        return results;
    }

    private <T> T executeSingleResultQuery(String sql, SQLConsumer<PreparedStatement> stmtSetter, SQLFunction<ResultSet, T> resultSetMapper) throws SQLException {
        try (Connection con = getConnection("club_management");
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmtSetter.accept(stmt);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return resultSetMapper.apply(rs);
            } else {
                return null;
            }
        }
    }

    private void executeDelete(String table, String condition, SQLConsumer<PreparedStatement> stmtSetter) throws SQLException {
        String sql = "DELETE FROM " + table + " WHERE " + condition;
        executeUpdate(sql, stmtSetter);
    }

    public void close() throws SQLException {
        Connection con = DBConnection.getConnection("club_management");
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

}
