package LE_09._01.vehicles;

public abstract class Vehicle {
    protected String licensePlate;
    protected int mileage;
    protected String fuelType;
    protected static int totalVehicles = 0;

    public Vehicle(String licensePlate, int mileage, String fuelType) {
        this.licensePlate = licensePlate;
        this.mileage = mileage;
        this.fuelType = fuelType;
        totalVehicles++;
    }

    public void drive(int kilometers) {
        mileage += kilometers;
        System.out.printf("%s drove %dkm. Total mileage: %dkm.\n", licensePlate, kilometers, mileage);
    }

    public void refuel(int amount) {
        System.out.println("This vehicle does not require refueling");
    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public int getMileage() {
        return mileage;
    }

    public String getFuelType() {
        return fuelType;
    }

    public static int getTotalVehicles() {
        return totalVehicles;
    }



    public abstract void displayInfo();
}