package LE_11.Classes;

import LE_11.Classes.members.Coach;
import LE_11.Classes.members.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Team {
    private String teamName;
    private int teamID;
    private String league;
    private List<Coach> coaches = new ArrayList<>();
    private List<Player> players = new ArrayList<>();

    public Team(String teamName, int teamID, String league) {
        this.teamName = teamName;
        this.teamID = teamID;
        this.league = league;
    }

    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamID() {
        return teamID;
    }
    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getLeague() {
        return league;
    }
    public void setLeague(String league) {
        this.league = league;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addCoach(Coach coach) {
        if (!coaches.contains(coach)) {
            coaches.add(coach);
        } else {
            System.out.println("Coach already exists: " + coach.getName());
        }
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        } else {
            System.out.println("Player already exists: " + player.getName());
        }
    }

    public String displayInfo() {
        String coachNames = coaches.stream().map(Coach::getName).collect(Collectors.joining("\n"));
        String playerNames = players.stream().map(Player::getName).collect(Collectors.joining("\n"));
        return String.format("Team Name: %s\nTeam ID: %d\nLeague: %s\nCoach/es:\n %s\nPlayers:\n %s\n", teamName, teamID, league, coachNames, playerNames);
    }
}