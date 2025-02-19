package LE_09._02.teams.members;

public class Player extends Person {
    private String position;
    private double salary;


    public Player(String name, int age, String position, double salary, String team) {
        super(name, age, team);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String displayInfo() {
       return String.format("Player %sPosition: %s\nSalary: %.2f\n", super.displayInfo(), getPosition(), getSalary());
    }


}