package LE_10.Classes;

public class Motorcycle extends FuelVehicle{
    private int engineDisplacement;
    private boolean hasSideCar;

    public Motorcycle(int id, String brand, String model, int year, double mileage, int fuelCapacity, int engineDisplacement, boolean hasSideCar) {
        super(id, brand,model,year, mileage,fuelCapacity);
        this.engineDisplacement = engineDisplacement;
        this.hasSideCar = hasSideCar;
    }

    public int getEngineDisplacement() {return engineDisplacement;}
    public void setEngineDisplacement(int engineDisplacement) {this.engineDisplacement = engineDisplacement;}
    public boolean hasSideCar() {return hasSideCar;}
    public void setHasSideCar(boolean hasSideCar) {this.hasSideCar = hasSideCar;}

    @Override
    public String displayInfo() {
        Employee driver = getAssignedDriver();
        return String.format(("Motorcycle: %s %s (%s)\nMileage: %f Km\nFuel Capacity: %d L\nLast Refuel: %f L\nEngine: %d\nSide Car: %b\nAssigned Driver: %s"), brand, model, year, mileage, fuelCapacity, lastRefuelAmount, engineDisplacement, hasSideCar, (driver != null ? driver.getName() : "None"));
    }
}
