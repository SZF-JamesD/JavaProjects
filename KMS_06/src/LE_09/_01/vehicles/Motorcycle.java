package LE_09._01.vehicles;

public class Motorcycle extends Vehicle {
    private int fuelLevel;
    private final boolean hasSideCar;


    public Motorcycle(String licensePlate, int mileage, String fuelType, int fuelLevel, boolean hasSideCar) {
        super(licensePlate, mileage, fuelType);
        this.fuelLevel = fuelLevel;
        this.hasSideCar = hasSideCar;
    }

    @Override
    public void refuel(int amount) {
        fuelLevel += amount;
        System.out.printf("%s refueled with %d liters or %s. Fuel level: %d\n", licensePlate, amount, fuelType, fuelLevel);
    }

    public boolean getSideCar() {
        return hasSideCar;
    }


    @Override
    public void displayInfo() {
        System.out.printf("Motorcycle %s\nMileage: %d km\nFuel level: %dL\nFuel Type: %s\nSide car: %b\n", licensePlate, mileage, fuelLevel, fuelType, hasSideCar);
    }
}