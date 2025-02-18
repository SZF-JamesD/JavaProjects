package LE_09._01.main;

import LE_09._01.management.FleetManager;
import java.util.Scanner;

public class FleetManagementApp {
    public static void main(String[] args) {
        FleetManager fleetManager = new FleetManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nWelcome to the Fleet Management System\nPlease make a selection: ");
                System.out.println("1: Add Vehicle\n2: Refuel Vehicle\n3: Add Mileage\n4: Vehicle Specifics\n5: View Fleet\n6: Exit");
                System.out.println("\nEnter your selection: ");
                int selection = sc.nextInt();
                switch (selection) {
                    case 1: {
                        System.out.println("Enter Vehicle Type(Car, Truck, Motorcycle, or Bicycle): ");
                        String vehicleType = sc.next();
                        fleetManager.createVehicle(vehicleType, sc);
                        break;
                            }
                    case 2: {
                        System.out.println("Enter Vehicle License Plate Number:");
                        String licensePlateNumber = sc.next();
                        System.out.println("Enter Fuel Amount: ");
                        int fuelAmount = sc.nextInt();
                        fleetManager.refuelVehicle(licensePlateNumber, fuelAmount);
                        break;
                    }
                    case 3: {
                        System.out.println("Enter Vehicle License Plate Number:");
                        String licensePlateNumber = sc.next();
                        System.out.println("Enter Distance Traveled: ");
                        int distanceTraveled = sc.nextInt();
                        fleetManager.drive(licensePlateNumber, distanceTraveled);
                        break;
                    }
                    case 4: {
                        System.out.println("Enter Vehicle Type: ");
                        String vehicleType = sc.next();
                        fleetManager.vehicleSpecifics(vehicleType);
                        break;
                    }
                    case 5: {
                        fleetManager.displayFleet();
                        break;
                    }
                    case 6: {
                        System.out.println("Exiting Application");
                        return;
                    }
                    default: {
                        System.out.println("Invalid selection");
                        sc.next();
                        break;
                    }
                        }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    sc.nextLine();
                }

            }
        }
}






