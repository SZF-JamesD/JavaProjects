package LE_09._02.teams.members;

public abstract class Person {
    private String name;
    private int age;
    private String team;

    public Person(String name, int age, String team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getTeam() {
        return team;
    }

    public abstract void displayInfo();
}
