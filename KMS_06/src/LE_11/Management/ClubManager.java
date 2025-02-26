package LE_11.Management;

import LE_11.Classes.Match;
import LE_11.Classes.Team;
import Utils.InputHelper;
import Utils.ValidationUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ClubManager {
    private DBManager dbm;
    private Scanner sc;

    public ClubManager(DBManager dbm) {
        this.dbm = dbm;
        this.sc = new Scanner(System.in);
    }

    public void runApp() throws SQLException {
        while (true) {
            printMenu();
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> addClub();
                case "2" -> removeClub();
                case "3" -> addTeam();
                case "4" -> removeTeam();
                case "5" -> addPerson();
                case "6" -> removePerson();
                case "7" -> displayTeam();
                case "8" -> scheduleMatch();
                case "9" -> displayMatch();
                case "10" -> {
                    System.out.println("Exiting..");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
            sc.nextLine();
        }
    }

    private void printMenu() {
        System.out.println("\nClub Manager:\n1. Add Club\n2. Remove Club\n3. Add Team to Club\n4. Remove Team from Club" +
                "\n5. Add Person\n6. Remove Person\n7. Display  Team/s\n8. Arrange a Match\n9. View Match/es\n10. Exit");
        System.out.print("Enter your choice: ");
    }

    public void addClub() throws SQLException {
        String cName = InputHelper.getValidString("Enter Club Name: ", input -> ValidationUtil.isNonEmptyString(input) && ValidationUtil.isAlphanumeric(input),
                "Invalid name. Please enter a  non-empty alphanumeric string.");

        String cAddress = InputHelper.getValidString("Enter Club Address: ", input -> ValidationUtil.isNonEmptyString(input) && ValidationUtil.isAlphanumeric(input),
                "Invalid address. Please enter a  non-empty alphanumeric string.");

        dbm.addClub(cName, cAddress);
        //;
    }

    public void removeClub() throws SQLException {
        int cId = InputHelper.getValidInt("Enter Club ID: ", ValidationUtil::isPositiveInt, "Invalid input, please input a positive integer.");

            dbm.removeClub(cId);
    }

    public void addTeam() throws SQLException {
        int cId = InputHelper.getValidInt("Enter the ID of the Club this Team belongs to: ", ValidationUtil::isPositiveInt, "Invalid input, please input a positive integer.");
        String tName = InputHelper.getValidString("Enter Team Name: ", input -> ValidationUtil.isNonEmptyString(input) && ValidationUtil.isAlphanumeric(input),
                "Invalid Name. Please enter a non empty alphanumeric string. ");
        String tLeague = InputHelper.getValidString("Enter the league the team plays in: ", input -> ValidationUtil.isNonEmptyString(input) && ValidationUtil.isAlphanumeric(input),
                "Invalid League Name. Please enter a non empty alphanumeric string. ");

        dbm.addTeam(tName, tLeague, cId);
    }

    public void removeTeam() throws SQLException {
        int cId = InputHelper.getValidInt("Enter the ID of the Club this Team belongs to: ", ValidationUtil::isPositiveInt, "Invalid input, please input a positive integer.");
        int tId = InputHelper.getValidInt("Enter the team ID: ", ValidationUtil::isPositiveInt, "Invalid input, please input a positive integer.");
        dbm.removeTeam(cId, tId);
    }

    public void addPerson() throws SQLException {
        while (true) {
            System.out.print("Enter 1 to add a player, or 2 to add a coach: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                String name = InputHelper.getValidString("Enter the name of the player: ", input -> ValidationUtil.isNonEmptyString(input) && ValidationUtil.isAlphanumeric(input),
                        "Invalid name, please try again.");
                int age = InputHelper.getValidInt("Enter the age of the player: ", ValidationUtil::isPositiveInt, "Invalid age, please try again.");
                int tId = InputHelper.getValidInt("Enter the ID of the Team: ", ValidationUtil::isPositiveInt, "Invalid ID, please try again.");

                if (choice == 1) {
                    double pSalary = InputHelper.getValidDouble("Enter the player salary: ", ValidationUtil::isNonNegativeDouble, "Invalid player salary, please try again.");
                    String pPosition = InputHelper.getValidString("Enter the position of the player: ", input -> ValidationUtil.isNonEmptyString(input) && ValidationUtil.isAlphanumeric(input),
                            "Invalid input, please try again.");
                    dbm.addPlayer(name, age, pSalary, pPosition, tId);
                    break;
                } else if (choice == 2) {
                    dbm.addCoach(name, age, tId);
                    break;
                } else {
                    System.out.println("Invalid choice, please choose either 1 or 2.");

                }
            }
        }
    }

    public void removePerson() throws SQLException {
        while (true) {
            System.out.print("Enter 1 to remove a player, or 2 to remove a coach: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                int tId = InputHelper.getValidInt("Enter the ID of the Team: ", ValidationUtil::isPositiveInt, "Invalid ID, please try again.");
                if (choice == 1) {
                    int pId = InputHelper.getValidInt("Enter the Player ID: ", ValidationUtil::isPositiveInt, "Invalid ID, please try again.");
                    dbm.removePlayer(tId, pId);
                    break;
                } else if (choice == 2) {
                    int cId = InputHelper.getValidInt("Enter the Coach ID: ", ValidationUtil::isPositiveInt, "Invalid ID, please try again.");
                    dbm.removeCoach(tId, cId);
                    break;
                } else {
                    System.out.println("Invalid choice, please choose either 1 or 2.");
                }
            }
        }
    }

    public void displayTeam() throws SQLException {
        System.out.print("Enter Team ID or -1 to view all: ");
        int tId = sc.nextInt();
        if (tId == -1) {
            for (Team team : dbm.getAllTeams()) {
                System.out.println(team.displayInfo());
            }
        } else if (tId >= 1) {
            System.out.println(dbm.getTeam(tId));
        } else {
            System.out.print("Please enter a valid Team ID: ");
        }
    }

    public void scheduleMatch(){
        System.out.println("Enter ID of team 1: ");
        int team1 = sc.nextInt();
        System.out.println("Enter the opposing team's ID: ");
        int team2 = sc.nextInt();
        System.out.println("Enter the date of the match (dd-MM-yyyy): ");
        String date = sc.next();
        System.out.println("Enter the name of the match venue: ");
        String venue = sc.next();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        java.util.Date utilDate = null;
        try {
            System.out.println(date);
            utilDate = sdf.parse(date);
            Date sqlDate = new java.sql.Date(utilDate.getTime());
            dbm.scheduleMatch(sqlDate, venue, team1, team2);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void displayMatch() {
        System.out.print("Enter match ID or -1 to view all: ");
        try{
            int mId = sc.nextInt();
            if (mId == -1) {
                for (Match match : dbm.getAllMatches()) {
                    System.out.println(match.displayInfo());
                }
            } else if (mId >= 1) {
                System.out.println(dbm.getMatch(mId).displayInfo());
            } else {
                System.out.print("Please enter a valid match ID: ");
            }

        } catch (SQLException e) {
            System.out.println("Error accessing the database: " + e.getMessage());
        }
    }
}
