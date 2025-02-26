package LE_11.Classes.members;

public class Coach extends Person {
    private int coachID;


    public Coach(String name, int age, int coachID) {
        super(name, age);
        this.coachID = coachID;
    }

    public int getCoachID() {
        return coachID;
    }
    public void setCoachID(int coachID) {
        this.coachID = coachID;
    }

    @Override
    public String displayInfo() {
        return String.format("Coach %s CoachID: %s\n", super.displayInfo(), getCoachID());
    }
}
