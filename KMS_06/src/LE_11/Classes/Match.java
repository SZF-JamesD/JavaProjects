package LE_11.Classes;
import java.text.SimpleDateFormat;
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
    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }
    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public Date getMatchDate() {
        return matchDate;
    }
    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getVenue() {
        return venue;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String displayInfo() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String date = df.format(matchDate);

        return String.format("%s vs %s\n%s\n%s", team1, team2, date, venue);
    }
}