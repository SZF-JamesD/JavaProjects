package LE_10.Classes;

public class Truck extends FuelVehicle{
    private double payloadCapacity;

    public Truck(int id, String brand, String model, int year, double mileage, int fuelCapacity, double payloadCapacity) {
        super(id, brand, model, year, mileage, fuelCapacity);
        this.payloadCapacity = payloadCapacity;
    }

    public double getPayloadCapacity() {return payloadCapacity;}
    public void setPayloadCapacity(double payloadCapacity) {this.payloadCapacity = payloadCapacity;}

    @Override
    public String displayInfo() {
        Employee driver = getAssignedDriver();
        return String.format(("Truck: %s %s (%s)\nMileage: %f Km\nFuel Capacity: %d L\nLast Refuel: %f L\nPayload Capacity: %f\nAssigned Driver: %s"), brand, model, year, mileage, fuelCapacity, lastRefuelAmount, payloadCapacity, (driver != null ? driver.getName() : "None"));
    }
}
