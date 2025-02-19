package LE_09._02.teams;

import LE_09._02.teams.members.*;

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

    public int getTeamID() {
        return teamID;
    }

    public String getLeague() {
        return league;
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
            System.out.println("Coach added: " + coach.getName());
        } else {
            System.out.println("Coach already exists: " + coach.getName());
        }
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
            System.out.println("Player added: " + player.getName());
        } else {
            System.out.println("Player already exists: " + player.getName());
        }
    }

    public void displayInfo() {
        String coachNames = coaches.stream().map(Coach::getName).collect(Collectors.joining("\n"));
        String playerNames = players.stream().map(Player::getName).collect(Collectors.joining("\n"));
        System.out.printf("Team Name: %s\nTeam ID: %d\nLeague: %s\nCoach/es:\n %s\nPlayers:\n %s\n", teamName, teamID, league, coachNames, playerNames);
    }
}