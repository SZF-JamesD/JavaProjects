package LE_09._01.vehicles;

public class Car extends Vehicle {
    private int fuelLevel;

    public Car(String licensePlate, int mileage, String fuelType, int fuelLevel) {
        super(licensePlate, mileage, fuelType);
        this.fuelLevel = fuelLevel;
    }

    @Override
    public void refuel(int amount) {
        fuelLevel += amount;
        System.out.printf("%s refueled with %d liters of %s. Fuel level: %d\n", licensePlate, amount, fuelType, fuelLevel);
    }

    @Override
    public void displayInfo() {
        System.out.printf("Car %s\nMileage: %d km\nFuel Type: %s\nFuel level: %dL\n", licensePlate, mileage, fuelType, fuelLevel);
    }
}