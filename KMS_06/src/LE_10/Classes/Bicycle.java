package LE_10.Classes;

public class Bicycle extends Vehicle {
    private final int gearCount;

    public Bicycle(int id, String brand, String model, int year, double mileage, int gearCount) {
        super(id, brand, model, year, mileage);
        this.gearCount = gearCount;
    }

    public int getGearCount() {return gearCount;}

    @Override
    public String displayInfo() {
        Employee driver = getAssignedDriver();
        return String.format(("Bicycle: %s %s (%s)\nMileage: %f Km\nGear count: %d\nAssigned Driver: %s"), brand, model, year, mileage, gearCount, (driver != null ? driver.getName() : "None"));
    }
}
