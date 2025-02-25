package LE_10.Classes;

public class Car extends FuelVehicle {
    public Car(int id, String brand, String model, int year, double mileage, int fuelCapacity) {
        super(id, brand, model, year, mileage, fuelCapacity);
    }

    @Override
    public String displayInfo() {
        Employee driver = getAssignedDriver();
        return String.format("Car: %s %s (%s)\nMileage: %f Km\nFuel Capacity: %d L\nLast Refuel: %f L\nAssigned Driver: %s", brand, model, year, mileage, fuelCapacity, lastRefuelAmount, (driver != null ? driver.getName() : "None"));
    }
}
