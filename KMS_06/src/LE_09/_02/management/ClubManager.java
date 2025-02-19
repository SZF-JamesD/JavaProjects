package LE_09._02.management;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;
import LE_09._02.teams.Match;
import LE_09._02.teams.Team;
import LE_09._02.teams.members.Coach;
import LE_09._02.teams.members.Player;
import java.util.ArrayList;
import java.util.List;


public class ClubManager {
    private List<Team> teams = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();

    public void createTeam(Scanner sc) {
        int id = -1;
        String teamName = null;
        boolean teamExists = false;
        boolean idExists = false;

        while (true) {
            System.out.println("Enter team name: ");

            if (sc.hasNextLine()) {
                teamName = sc.nextLine();
            }


            if (teamName != null && !teamName.isEmpty()) {
                String finalTeamName = teamName;
                teamExists = teams.stream().anyMatch(t -> t.getTeamName().equals(finalTeamName));

                if (teamExists) {
                    System.out.println("Team already exists! Please choose a different team name.");
                } else {
                    break;
                }
        }
        }

        while (true) {
            System.out.println("Enter an integer to become the id of the team: ");
            id = Integer.parseInt(sc.nextLine());

            int finalId = id;
            idExists = teams.stream().anyMatch(team -> team.getTeamID() == finalId);

            if (idExists) {
                System.out.println("Team already exists! Please choose a different id.");
            } else {
                break;
            }
        }

        System.out.println("Enter the league the team plays in: ");
        String league = sc.nextLine();

        Team newTeam = new Team(teamName, id, league);
        teams.add(newTeam);
        System.out.printf("Team %s added successfully!\n", newTeam.getTeamName());
    }

    public void removeTeam(String teamName) {
        Team teamToRemove = teams.stream().filter(t -> t.getTeamName().equals(teamName)).findFirst().orElse(null);

        if (teamToRemove != null) {
            teams.remove(teamToRemove);
            System.out.println("Team " + teamName + " has been removed.");
        } else {
            System.out.println("Team does not exist! Please choose a different team name.");
        }
    }

    public void scheduleMatch(String team1, String team2, String matchDate, String venue) {
        boolean team1Exists = teams.stream().anyMatch(t -> t.getTeamName().equals(team1));
        boolean team2Exists = teams.stream().anyMatch(t -> t.getTeamName().equals(team2));


        if (team1Exists && team2Exists) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false);
                Date parsedDate = sdf.parse(matchDate);


                Match newmatch = new Match(team1, team2, parsedDate, venue);
                matches.add(newmatch);
                System.out.println("✅ Match added successfully!");
            } catch (DateTimeParseException e) {
                System.out.println("❌ Invalid date format! Please enter a valid date (DD-MM-YYYY)!");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ " + e.getMessage());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("One or both teams don't exist. Please choose  different team names.");
        }
    }


    public void teamsInfo() {
        System.out.println("\nTeams status: ");
        for (Team team : teams) {
            team.displayInfo();
        }
    }

    public void playersAndCoachesInfo() {
        System.out.println("\nPlayers status: ");
        for (Team team : teams) {
            System.out.println(team.getTeamName());
            for (Player player : team.getPlayers()) {
                if (!team.getPlayers().isEmpty()) {
                    System.out.println(player.displayInfo());
                } else {
                    System.out.println("No players on this team.");}}
            for (Coach coach : team.getCoaches()) {
                if (!team.getCoaches().isEmpty()) {
                    System.out.println(coach.displayInfo());
                } else {
                    System.out.println("No coaches on this team.");
                }
            }
            }

        }

    public void addPersonToTeam(Scanner sc) {
        String teamName;
        Team team = null;

        while (true) {
            System.out.print("Enter team name: ");
            teamName = sc.nextLine();

            String finalTeamName = teamName;
            team = teams.stream().filter(t -> t.getTeamName().equals(finalTeamName)).findFirst().orElse(null);

            if (team == null) {
                System.out.println("Team doesn't exist! Please choose a different team name.");
            } else {
                break;
            }
        }


        System.out.print("Enter 1 to add a player or 2 to add a coach: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            while (true) {
                sc.nextLine();
                System.out.println("Enter the name of the player: ");
                String playerName = sc.nextLine();

                boolean playerExists = team.getPlayers().stream().anyMatch(p -> p.getName().equals(playerName));

                if (playerExists) {
                    System.out.println("Player already exists! Please choose a different name.");
                } else {
                    System.out.println("Enter the player's age: ");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter the player's position: ");
                    String position = sc.nextLine();
                    System.out.println("Enter the player's salary: ");
                    double salary = Double.parseDouble(sc.nextLine());

                    Player newPlayer = new Player(playerName, age, position, salary, teamName);
                    team.addPlayer(newPlayer);

                    System.out.printf("Player %s added to %s successfully!\n", newPlayer.getName(), teamName);
                    return;
                }
            }
        } else if (choice == 2) {
            sc.nextLine();
            while (true) {
                System.out.println("Enter the name of the coach: ");
                String coachName = sc.nextLine();

                boolean coachExists = team.getCoaches().stream().anyMatch(c -> c.getName().equals(coachName));

                if (coachExists) {
                    System.out.println("Coach already exists! Please choose a different name.");
                } else {
                    System.out.println("Enter the coach's age: ");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter the coach's ID: ");
                    int id = sc.nextInt();

                    Coach newCoach = new Coach(coachName, age, id, teamName);
                    team.addCoach(newCoach);

                    System.out.printf("Coach %s added to %s successfully!\n", newCoach.getName(), teamName);
                    return;
                }
            }
        }
        }

    public void removePersonFromTeam(Scanner sc) {
        String teamName;
        Team team = null;

        while (true) {
            System.out.print("Enter team name: ");
            teamName = sc.nextLine();

            String finalTeamName = teamName;
            team = teams.stream().filter(t -> t.getTeamName().equals(finalTeamName)).findFirst().orElse(null);

            if (team == null) {
                System.out.println("Team doesn't exist! Please choose a different team name.");
            } else {
                break;
            }
        }
            System.out.print("Enter 1 to remove a player or 2 to remove a coach: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                while (true) {
                    System.out.println("Enter the name of the player: ");
                    String playerName = sc.nextLine();

                    Player playerToRemove = team.getPlayers().stream().filter(p -> p.getName().equals(playerName)).findFirst().orElse(null);

                    if (playerToRemove == null) {

                        System.out.println("Player does not exist on this team! Please choose a different name.");
                    } else {
                        team.getPlayers().remove(playerToRemove);

                        System.out.printf("Player %s removed from %s successfully!\n", playerName, teamName);
                        return;
                    }
                }
            } else if (choice == 2) {
                while (true) {
                    System.out.println("Enter the name of the coach: ");
                    String coachName = sc.nextLine();

                    Coach coachToRemove = team.getCoaches().stream().filter(c -> c.getName().equals(coachName)).findFirst().orElse(null);

                    if (coachToRemove == null) {
                        System.out.println("Coach does not exist on this team! Please choose a different name.");
                    } else {
                        team.getCoaches().remove(coachToRemove);

                        System.out.printf("Coach %s removed from %s successfully!\n", coachName, teamName);
                        return;
                }
            }
        }
    }
}


