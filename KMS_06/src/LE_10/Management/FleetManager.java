package LE_10.Management;

import LE_10.Classes.*;
import Utils.InputHelper;
import Utils.ValidationUtil;
import java.util.Scanner;
import java.sql.SQLException;

public class FleetManager {
    private DBManager dbm;
    private Scanner sc;

    public FleetManager(DBManager dbm) {
        this.dbm = dbm;
        this.sc = new Scanner(System.in);
    }

    public void runApp() throws SQLException {
        while (true) {
            printMenu();
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> addVehicle();
                case "2" -> assignDriver();
                case "3" -> deleteVehicle();
                case "4" -> displayVehicle();
                case "5" -> displayAllVehicles();
                case "6" -> addEmployee();
                case "7" -> deleteEmployee();
                case "8" -> displayEmployee();
                case "9" -> {
                    System.out.println("Exiting..");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nFleet Manager:\n1. Add Vehicle\n2. Assign Driver\n3. Delete Vehicle\n4. Display Vehicle\n5. Display Fleet\n6. Add Employee\n7.Delete Employee\n8.View Employee/s\n9. Exit");
        System.out.print("Enter your choice: ");
    }

    public void addVehicle() throws SQLException {
        int choice = InputHelper.getValidInt(("Please select the type of vehicle from the following:\n1: Car\n2: Truck\n3: Motorcycle\n4: Bicycle\nPlease enter your choice: "),
                ValidationUtil::isValidVehicleChoice, "Invalid choice. Please select a valid vehicle type (1-4)");


        String brand = InputHelper.getValidString("Enter vehicle brand: ", input -> ValidationUtil.isNonEmptyString(input) && ValidationUtil.isAlphanumeric(input),
                "Invalid brand. Please enter a  non-empty alphanumeric string.");



        String model = InputHelper.getValidString("Enter vehicle model: ",
                input -> ValidationUtil.isNonEmptyString(input) && ValidationUtil.isAlphanumeric(input),
                "Invalid model. Please enter a non-empty alphanumeric string.");

        int year = InputHelper.getValidInt("Enter vehicle year: ",
                ValidationUtil::isValidYear,
                "Invalid year. Please enter a year between 1886 and 2100.");

        double mileage = InputHelper.getValidDouble("Enter vehicle mileage: ",
                ValidationUtil::isNonNegativeDouble,
                "Invalid mileage. Please enter a non-negative number.");

        int fuelCapacity = 0;

        if (choice >= 1 && choice <= 3) {
            fuelCapacity = InputHelper.getValidInt("Enter fuel capacity: ",
                    ValidationUtil::isPositiveInt,
                    "Invalid fuel capacity. Please enter a positive number.");
        }

        switch (choice) {
            case 1 -> {
                Car car = new Car(0, brand, model, year, mileage, fuelCapacity);
                dbm.addVehicle(car);
            }
            case 2 -> {
                double payloadCapacity = InputHelper.getValidDouble("Enter payload capacity: ",
                        ValidationUtil::isNonNegativeDouble,
                        "Invalid payload capacity. Please enter a non-negative number.");
                Truck truck = new Truck(0, brand, model, year, mileage, fuelCapacity, payloadCapacity);
                dbm.addVehicle(truck);
            }
            case 3 -> {
                int displacement = InputHelper.getValidInt("Enter engine displacement: ",
                        ValidationUtil::isPositiveInt,
                        "Invalid displacement. Please enter a positive number.");

                boolean sideCar;
                while (true) {
                    System.out.print("Does the vehicle have a side car (y/n): ");
                    String sideCarStr = sc.nextLine().toLowerCase();
                    if (sideCarStr.length() > 0) {
                        char sideCarInput = sideCarStr.charAt(0);
                        if (ValidationUtil.isValidChar(sideCarInput, 'y', 'n')) {
                            sideCar = (sideCarInput == 'y');
                            break;
                        }
                    }
                    System.out.println("Invalid option. Please try again.");
                }
                Motorcycle moto = new Motorcycle(0, brand, model, year, mileage, fuelCapacity, displacement, sideCar);
                dbm.addVehicle(moto);
            }
            case 4 -> {
                int gearCount = InputHelper.getValidInt("Enter gear count: ",
                        ValidationUtil::isPositiveInt,
                        "Invalid gear count. Please enter a positive number.");
                Bicycle bike = new Bicycle(0, brand, model, year, mileage, gearCount);
                dbm.addVehicle(bike);
            }
        }
        sc.nextLine();
    }

    public void assignDriver() throws SQLException {
        System.out.print("Enter Vehicle ID: ");
        int vehicleID = sc.nextInt();
        System.out.print("Enter Driver ID: ");
        int driverID = sc.nextInt();
        dbm.assignDriver(vehicleID, driverID);
        sc.next();
    }

    public void deleteVehicle() throws SQLException {
        System.out.print("Enter Vehicle ID: ");
        int vehicleID = sc.nextInt();
        dbm.deleteVehicle(vehicleID);
        sc.nextLine();
    }

    public void displayVehicle() throws SQLException {
        System.out.print("Enter Vehicle ID: ");
        int vehicleID = sc.nextInt();
        dbm.displayVehicle(vehicleID);
        sc.nextLine();
    }

    public void displayAllVehicles() throws SQLException {
        dbm.getAllVehicles();
        sc.nextLine();
    }

    public void addEmployee() throws SQLException {
        System.out.print("Enter Employee last name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter Employee role: ");
        String role = sc.nextLine();
        dbm.addEmployee(lastName, role);
        sc.nextLine();
    }

    public void deleteEmployee() throws SQLException {
        System.out.print("Enter Employee ID: ");
        int employee_id = sc.nextInt();
        dbm.deleteEmployee(employee_id);
        sc.nextLine();
    }

    public void displayEmployee() throws SQLException {
        System.out.print("Enter Employee ID or -1 to view all: ");
        int employee_id = sc.nextInt();
        if (employee_id == -1) {
            dbm.getAllEmployees();
        } else if (employee_id >= 1) {
            dbm.getEmployee(employee_id);
        } else {
            System.out.print("Please enter a valid employee ID: ");
        }
        sc.nextLine();
    }
}



