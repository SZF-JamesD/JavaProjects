package LE_09._01.management;

import java.util.Scanner;
import LE_09.vehicles.*;
import java.util.ArrayList;
import java.util.List;

public class FleetManager {
    private List<Vehicle> vehicles = new ArrayList<>();

    public void createVehicle(String vehicleType, Scanner sc) {
        vehicleType = vehicleType.toLowerCase();
        System.out.println("Enter License Plate Info: ");
        String licensePlateInfo = sc.next();
        System.out.println("Enter Current Mileage: ");
        int currentMileage = sc.nextInt();
        System.out.println("Enter Fuel Type(Petrol, Diesel, Electric, None): ");
        String fuelType = sc.next();
        fuelType = fuelType.toLowerCase();
        System.out.println("Enter Fuel Level(0 if No fuel type): ");
        int fuelLevel = sc.nextInt();
        switch (vehicleType) {
            case "car": {
                addVehicle(new Car(licensePlateInfo, currentMileage, fuelType, fuelLevel));
                break;
            }
            case "truck": {
                System.out.println("Enter Cargo Capacity: ");
                int cargoCapacity = sc.nextInt();
                System.out.println("Enter Remaining Cargo Weight: ");
                int remainingCargoWeight = sc.nextInt();
                addVehicle(new Truck(licensePlateInfo, currentMileage, fuelType, fuelLevel, cargoCapacity, remainingCargoWeight));
                break;
            }
            case "motorcycle": {
                System.out.println("Does it have a side car? (Y/N): ");
                boolean sideCar;
                char a = sc.next().charAt(0);
                a = Character.toUpperCase(a);
                if (a == 'Y') {
                    sideCar = true;
                } else if (a == 'N') {
                    sideCar = false;
                } else {
                    System.out.println("Invalid input, please enter Y or N.");
                    return;
                }
                addVehicle(new Motorcycle(licensePlateInfo, currentMileage, fuelType, fuelLevel, sideCar));
                break;
            }
            case "bicycle": {
                System.out.println("Enter Gear Count: ");
                if (sc.hasNextInt()) {
                    int gearCount = sc.nextInt();
                    if (gearCount > 0) {
                        addVehicle(new Bicycle(licensePlateInfo, currentMileage, fuelType, gearCount));
                        break;
                    } else {
                        System.out.println("Invalid input, gear count must be greater than 0.");
                        break;
                    }
                }
                System.out.println("Invalid input, please enter an integer for gear count.");
                break;
            }
            default: {
                System.out.println("Invalid input, please enter a valid string for the vehicle type.");
                break;
            }
            }

        }



    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
        System.out.println("Added: "+ vehicle.getClass().getSimpleName() + " with License " + vehicle.getLicensePlate());
    }

    public void displayFleet() {
        System.out.println("\nFleet Overview: ");
        for (Vehicle v : vehicles) {
            v.displayInfo();
        }
        System.out.println("Total Vehicles: " + Vehicle.getTotalVehicles());
    }

    public void drive(String plate, int distance){
        for (Vehicle v : vehicles) {
            if (v.getLicensePlate().equals(plate)) {
                v.drive(distance);
                return;
            }
        }
        System.out.println("Vehicle Not Found");
    }

    public void refuelVehicle(String plate, int amount) {
        for (Vehicle v : vehicles) {
            if (v.getLicensePlate().equals(plate)) {
                v.refuel(amount);
                return;
            }
        }
        System.out.println("Vehicle Not Found");
    }

    public void vehicleSpecifics(String type) {
        Scanner sc = new Scanner(System.in);
        switch (type) {
            case "Car": {
                System.out.println("No vehicle specific attributes for Cars");
                break;
            }
            case "Truck": {
                System.out.println("Enter license plate number to load cargo: ");
                String plate = sc.nextLine();
                for (Vehicle v : vehicles) {
                    if (v.getLicensePlate().equals(plate)) {
                        if (v instanceof Truck){
                            System.out.println("Please enter new cargo weight:");
                            int cargoWeight = sc.nextInt();
                            ((Truck) v).loadCargo(cargoWeight);
                        } else {
                            System.out.println("Entered vehicle is not a Truck.");
                        }
                        break;
                    }
                }
                System.out.println("Vehicle Not Found");
                break;
            }
            case "Motorcycle": {
                System.out.println("No mutable vehicle specific attributes for Motorcycles.");
            }
            case "Bicycle": {
                System.out.println("No mutable vehicle specific attributes for Bicycles. ");
            }
            default: {
                System.out.println("Invalid input, please enter a valid string for the vehicle type.");
            }
        }
    }
}
