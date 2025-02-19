package LE_09._02.main;

import LE_09._02.management.ClubManager;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClubManagementApp {
    public static void main(String[] args) {
        ClubManager clubManager = new ClubManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try{
                System.out.println("\nWelcome to the Club Management System\nPlease make a selection: ");
                System.out.println("1: Add a Team\n2: Remove a Team\n3: Add a Person to a Team\n4: Arrange a Match\n5: Teams Info\n6: Players and Coaches list\n7: Remove a person from a team\n8: Exit");
                System.out.print("\nEnter your selection: ");
                int selection = sc.nextInt();
                switch (selection) {
                    case 1:
                        sc.nextLine();
                        clubManager.createTeam(sc);
                        break;
                    case 2:
                        sc.nextLine();
                        System.out.println("Enter team name that you want to remove: ");
                        String teamName = sc.next();
                        clubManager.removeTeam(teamName);
                        break;
                    case 3:
                        sc.nextLine();
                        clubManager.addPersonToTeam(sc);
                        break;
                    case 4:
                        sc.nextLine();
                        System.out.println("Enter team name that you want to add: ");
                        String team1 = sc.next();
                        System.out.println("Enter the opposing team's name: ");
                        String team2 = sc.next();
                        System.out.println("Enter the date of the match (dd-MM-yyyy): ");
                        String date = sc.next();
                        System.out.println("Enter the name of the match venue: ");
                        String venue = sc.next();
                        clubManager.scheduleMatch(team1, team2, date, venue);
                        break;
                    case 5:
                        clubManager.teamsInfo();
                        break;
                    case 6:
                        clubManager.playersAndCoachesInfo();
                        break;
                    case 7:
                        sc.nextLine();
                        clubManager.removePersonFromTeam(sc);
                        break;
                    case 8:
                        System.out.println("Exiting, thank you for using Club Management System");
                        return;
                    default:
                        System.out.println("Invalid selection");
                        sc.next();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a valid selection");
                sc.next();
            }
        }
    }
}