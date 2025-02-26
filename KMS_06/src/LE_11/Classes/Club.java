package LE_11.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Club {
    private int clubID;
    private String clubName;
    private String clubLocation;
    private List<Team> teams = new ArrayList<>();

    public String displayInfo() {
        String teamNames = teams.stream().map(Team::getTeamName).collect(Collectors.joining("\n"));
        return String.format("Club: %s\nClub ID: %d\nClub Location: %s\nTeams: %s\n", clubName, clubID, clubLocation,teamNames);
    }
}
