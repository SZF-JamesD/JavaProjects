package LE_11.Classes.members;

public class Player extends Person {
    private String position;
    private double salary;
    private int playerID;


    public Player(String name, int age, String position, double salary, int playerID) {
        super(name, age);
        this.position = position;
        this.salary = salary;
        this.playerID = playerID;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getPlayerID() {return playerID;}

    @Override
    public String displayInfo() {
       return String.format("Player %s\nID: %d\nPosition: %s\nSalary: %.2f\n", super.displayInfo(), getPlayerID(), getPosition(), getSalary());
    }


}