package LE_09._01.vehicles;

public class Truck extends Vehicle {
    private int fuelLevel;
    private final int cargoCapacity;
    private int remainingWeight;


    public Truck(String licensePlate, int mileage, String fuelType, int fuelLevel, int cargoCapacity, int cargoWeight) {
        super(licensePlate, mileage, fuelType);
        this.fuelLevel = fuelLevel;
        this.cargoCapacity = cargoCapacity;
        this.remainingWeight = cargoWeight;
    }

    @Override
    public void refuel(int amount) {
        fuelLevel += amount;
        System.out.printf("%s refueled with %d liters of %s. Fuel level: %d\n", licensePlate, amount, fuelType, fuelLevel);
    }

    public void loadCargo(int weight) {
        if (weight < remainingWeight) {
            System.out.println("Loading " + weight + " kg of cargo. Remaining Capacity: " + (cargoCapacity - weight) +" kg.");
            remainingWeight -= weight;}
        else {
            System.out.println("New cargo is too heavy, the remaining capacity is " + remainingWeight + " kg.");
        }
    }

    @Override
    public void displayInfo(){
        System.out.printf("Truck %s\nMileage: %d km\nFuel level: %dL\nFuel Type: %s\nCargo Capacity: %d\nRemaining Capacity: %d\n", licensePlate, mileage, fuelLevel, fuelType, cargoCapacity, remainingWeight);
    }


}