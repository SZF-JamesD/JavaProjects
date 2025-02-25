package LE_10.Classes;

public abstract class Vehicle {
    protected static int totalVehicles = 0;

    protected int id;
    protected String brand;
    protected String model;
    protected int year;
    protected double mileage;
    protected Employee assignedDriver;

    public Vehicle(int id, String brand, String model, int year, double mileage) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        totalVehicles++;
    }

    public int getId() {return id;}
    public String getBrand() {return brand;}
    public String getModel() {return model;}
    public int getYear() {return year;}
    public double getMileage() {return mileage;}
    public void setMileage(double mileage) {this.mileage = mileage;}

    public Employee getAssignedDriver() {return assignedDriver;}
    public void assignDriver(Employee assignedDriver) {this.assignedDriver = assignedDriver;}

    public static int getTotalVehicles() {return totalVehicles;}

    public abstract String displayInfo();
}
