package LE_09._02.teams.members;

public class Player extends Person {
    private String position;
    private float salary;


    public Player(String name, int age, String position, int salary, String team) {
        super(name, age, team);
        this.position = position;
        this.salary = salary;

    }

    public String getPosition() {
        return position;
    }

    public float getSalary() {
        return salary;
    }

    @Override
    public void displayInfo() {
        System.out.printf("Player: %s\nTeam: %s\nAge: %d\nPosition: %s\nSalary: %f\n", getTeam(), getName(), getAge(), getPosition(), getSalary());
    }


}