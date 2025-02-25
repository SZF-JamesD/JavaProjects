package LE_10.Classes;

public class Employee {
    private int id;
    private String name;
    private String role;

    public Employee(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getRole() {return role;}
    public void setName(String name) {this.name = name;}
    public void setRole(String role) {this.role = role;}

    public String displayInfo() {
        return "Employee: " + name + "\nID: " + id + "\nRole: " + role+"\n";
    }
}
