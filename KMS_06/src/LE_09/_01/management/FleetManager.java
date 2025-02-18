package LE_09._01.management;

import java.util.Scanner;
import LE_09._01.vehicles.*;
import java.util.ArrayList;
import java.util.List;

public class FleetManager {
    private List<Vehicle> vehicles = new ArrayList<>();

    public void createVehicle(String vehicleType, Scanner sc) {
        String fuelType = "";
        int fuelLevel = 0;
        boolean validInput = false;


        System.out.println("Enter License Plate Info: ");
        String licensePlateInfo = sc.next();
        System.out.println("Enter Current Mileage: ");
        int currentMileage = sc.nextInt();


        while (!validInput) {
            System.out.println("Enter Fuel Type (Petrol, Diesel, Electric, None): ");
            fuelType = sc.next().toLowerCase();
            if (fuelType.equals("petrol") || fuelType.equals("diesel") || fuelType.equals("electric") || fuelType.equals("none")) {
                validInput = true;
            } else {
                System.out.println("Invalid Fuel Type, please enter a valid option.");
            }
        }


        validInput = false;
        while (!validInput) {
            System.out.println("Enter Fuel Level (0 if No fuel type): ");
            fuelLevel = sc.nextInt();
            if (fuelLevel >= 0) {
                validInput = true;
            } else {
                System.out.println("Invalid Fuel Level, cannot be a negative number.");
            }
        }


        switch (vehicleType.toLowerCase()) {
            case "car":
                if (!fuelType.equals("none")) {
                    addVehicle(new Car(licensePlateInfo, currentMileage, fuelType, fuelLevel));
                } else {
                    System.out.println("Invalid Fuel Type, car cannot have type 'None'");
                }
                break;

            case "truck":
                if (!fuelType.equals("none")) {
                    int cargoCapacity = 0, remainingCargoWeight = 0;
                    validInput = false;
                    while (!validInput) {
                        System.out.println("Enter Cargo Capacity: ");
                        cargoCapacity = sc.nextInt();
                        if (cargoCapacity < 0) {
                            System.out.println("Invalid Cargo Capacity, cannot be a negative number");
                        } else {
                            validInput = true;
                        }
                    }

                    validInput = false;
                    while (!validInput) {
                        System.out.println("Enter Remaining Cargo Weight: ");
                        remainingCargoWeight = sc.nextInt();
                        if (remainingCargoWeight > cargoCapacity) {
                            System.out.println("Invalid Remaining Cargo Weight, cannot exceed Cargo Capacity");
                        } else {
                            validInput = true;
                        }
                    }

                    addVehicle(new Truck(licensePlateInfo, currentMileage, fuelType, fuelLevel, cargoCapacity, remainingCargoWeight));
                } else {
                    System.out.println("Invalid Fuel Type, truck cannot have type 'None'");
                }
                break;

            case "motorcycle":
                if (!fuelType.equals("none")) {
                    boolean sideCar = false;
                    validInput = false;
                    while (!validInput) {
                        System.out.println("Does it have a side car? (Y/N): ");
                        char a = sc.next().charAt(0);
                        a = Character.toUpperCase(a);
                        if (a == 'Y') {
                            sideCar = true;
                            validInput = true;
                        } else if (a == 'N') {
                            sideCar = false;
                            validInput = true;
                        } else {
                            System.out.println("Invalid input, please enter Y or N.");
                        }
                    }
                    addVehicle(new Motorcycle(licensePlateInfo, currentMileage, fuelType, fuelLevel, sideCar));
                } else {
                    System.out.println("Invalid Fuel Type, Motorcycle cannot have type 'None'");
                }
                break;

            case "bicycle":
                if (fuelType.equals("petrol") || fuelType.equals("diesel")) {
                    System.out.println("Invalid Fuel Type, Bicycle cannot have type petrol or diesel");
                } else {
                    int gearCount = 0;
                    validInput = false;
                    while (!validInput) {
                        System.out.println("Enter Gear Count: ");
                        if (sc.hasNextInt()) {
                            gearCount = sc.nextInt();
                            if (gearCount > 0) {
                                addVehicle(new Bicycle(licensePlateInfo, currentMileage, fuelType, gearCount));
                                validInput = true;
                            } else {
                                System.out.println("Invalid input, gear count must be greater than 0.");
                            }
                        } else {
                            System.out.println("Invalid input, please enter an integer for gear count.");
                            sc.next();
                        }
                    }
                }
                break;

            default:
                System.out.println("Invalid input, please enter a valid string for the vehicle type.");
                break;
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
        if (distance > 0) {
        for (Vehicle v : vehicles) {
            if (v.getLicensePlate().equals(plate)) {
                v.drive(distance);
                return;
            }
        }
        System.out.println("Vehicle Not Found");
    }else {
            System.out.println("Distance cannot be a negative number");
        }
        }

    public void refuelVehicle(String plate, int amount) {
        if (amount > 0) {
        for (Vehicle v : vehicles) {
            if (v.getLicensePlate().equals(plate)) {
                v.refuel(amount);
                return;
            }
        }
        System.out.println("Vehicle Not Found");
    }else {
        System.out.println("Deposited fuel cannot be a negative number");}
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
