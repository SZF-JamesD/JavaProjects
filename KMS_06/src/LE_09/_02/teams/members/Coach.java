package LE_09._02.teams.members;

public class Coach extends Person {
    private int coachID;


    public Coach(String name, int age, int coachID, String team) {
        super(name, age, team);
        this.coachID = coachID;
    }

    public int getCoachID() {
        return coachID;
    }

    @Override
    public void displayInfo() {
        System.out.printf("Coach: %s\nTeam: %s\nAge: %d\nCoachID: %s\n", getTeam(), getName(), getAge(), getCoachID());
    }
}
