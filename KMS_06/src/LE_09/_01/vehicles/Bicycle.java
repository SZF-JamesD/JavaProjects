package LE_09._01.vehicles;

public class Bicycle extends Vehicle {
    private final int gearCount;


    public Bicycle(String licensePlate, int mileage, String fuelType, int gearCount) {
        super(licensePlate, mileage, fuelType);
        this.gearCount = gearCount;
    }

    public int getGearCount() {
        return gearCount;
    }

    @Override
    public void displayInfo() {
        System.out.printf("Bicycle %s\nMileage: %d km\nFuel Type: %s\nGear Count: %d\n", licensePlate, mileage, fuelType, gearCount);
    }
}