package LE_10.Classes;

public abstract class FuelVehicle extends Vehicle {
    protected int fuelCapacity;
    protected double lastRefuelAmount;

    public FuelVehicle(int id, String brand, String model, int year, double mileage, int fuelCapacity) {
        super(id, brand, model, year, mileage);
        this.fuelCapacity = fuelCapacity;
        this.lastRefuelAmount = 0.0;
    }

    public int getFuelCapacity() {return fuelCapacity;}
    public void setFuelCapacity(int fuelCapacity) {this.fuelCapacity = fuelCapacity;}
    public double getLastRefuelAmount() {return lastRefuelAmount;}

    public void refuel(double amount) {
        this.lastRefuelAmount = amount;
        System.out.println("Vehicle " + id + "refueled with " + amount + " liters.");
    }
}
