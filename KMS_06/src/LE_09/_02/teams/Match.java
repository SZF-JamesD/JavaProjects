package LE_09._02.teams;

import java.util.Date;

public class Match {
    private String team1, team2;
    private Date matchDate;
    private String venue;

    public Match(String team1, String team2, Date matchDate, String venue) {
        this.team1 = team1;
        this.team2 = team2;
        this.matchDate = matchDate;
        this.venue = venue;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public String getVenue() {
        return venue;
    }


}