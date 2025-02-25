package LE_10.Management;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import LE_10.Classes.*;
import Utils.DBConnection;


public class DBManager {
    public static void createDatabase() {
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS fleet_management";
            stmt.executeUpdate(sql);
            System.out.println("Database created or already exists");

            try(Connection dbCon = DBConnection.getConnection("fleet_management");
                Statement stmt2 = dbCon.createStatement()) {
                System.out.println("Connected to database.");

                String createEmployees = """
                        CREATE TABLE IF NOT EXISTS employees (
                        employee_id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(50) NOT NULL,
                        role VARCHAR(50) NOT NULL
                        )
                        """;

                String createVehicles = """
                        CREATE TABLE IF NOT EXISTS vehicles (
                        vehicle_id INT PRIMARY KEY AUTO_INCREMENT,
                        type VARCHAR(50) NOT NULL,
                        brand VARCHAR(50) NOT NULL,
                        model VARCHAR(50) NOT NULL,
                        year INT NOT NULL,
                        mileage DOUBLE NOT NULL,
                        fuel_capacity INT,
                        payload_capacity DOUBLE,
                        engine_displacement INT,
                        has_side_car BOOLEAN,
                        gear_count INT,
                        last_refuel_amount DOUBLE DEFAULT 0.0,
                        assigned_driver_id INT,
                        FOREIGN KEY (assigned_driver_id) REFERENCES employees (employee_id)
                        )
                        """;

                stmt2.executeUpdate(createEmployees);
                stmt2.executeUpdate(createVehicles);

                System.out.println("Tables created or already exists");
            }

        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public void getAllVehicles() throws SQLException {
        String sql = "SELECT * FROM vehicles";

        try (Connection con = DBConnection.getConnection("fleet_management");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int vehicle_id = rs.getInt("vehicle_id");
                String type = rs.getString("type");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                int year = rs.getInt("year");
                double mileage = rs.getDouble("mileage");
                int fuel_capacity = rs.getInt("fuel_capacity");
                double last_refuel_amount = rs.getDouble("last_refuel_amount");
                int assigned_driver = rs.getInt("assigned_driver_id");

                Employee driver = null;
                if (assigned_driver > 0) {
                    String driverQuery = "SELECT name, role FROM employees WHERE employee_id = ?";
                    try (PreparedStatement driverStmt = con.prepareStatement(driverQuery)) {
                        driverStmt.setInt(1, assigned_driver);
                        try (ResultSet driverRs = driverStmt.executeQuery()) {
                            if (driverRs.next()) {
                                String name = driverRs.getString("name");
                                String role = driverRs.getString("role");
                                driver = new Employee(assigned_driver, name, role);
                            }
                        }

                        switch (type) {
                            case "Car":
                                Car car = new Car(vehicle_id, brand, model, year, mileage, fuel_capacity);
                                car.refuel(last_refuel_amount);
                                car.assignDriver(driver);
                                System.out.println(car.displayInfo());
                                break;
                            case "Truck":
                                double payload_capacity = rs.getDouble("payload_capacity");
                                Truck truck = new Truck(vehicle_id, brand, model, year, mileage, fuel_capacity, payload_capacity);
                                truck.refuel(last_refuel_amount);
                                truck.assignDriver(driver);
                                System.out.println(truck.displayInfo());
                                break;
                            case "Motorcycle":
                                int engine_displacement = rs.getInt("engine_displacement");
                                boolean has_side_car = rs.getBoolean("has_side_car");
                                Motorcycle moto = new Motorcycle(vehicle_id, brand, model, year, mileage, fuel_capacity, engine_displacement, has_side_car);
                                moto.refuel(last_refuel_amount);
                                moto.assignDriver(driver);
                                System.out.println(moto.displayInfo());
                                break;
                            case "Bicycle":
                                int gearCount = rs.getInt("gear_count");
                                Bicycle bicycle = new Bicycle(vehicle_id, brand, model, year, mileage, gearCount);
                                bicycle.assignDriver(driver);
                                System.out.println(bicycle.displayInfo());
                                break;
                        }
                    }
                }
            }
        }
    }

    public void addVehicle(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO vehicles (type, brand, model, year, mileage, fuel_capacity, payload_capacity, engine_displacement, has_side_car, gear_count, last_refuel_amount, assigned_driver_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection("fleet_management");
            PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(2, vehicle.getBrand());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getYear());
            stmt.setDouble(5, vehicle.getMileage());

            stmt.setNull(6, Types.INTEGER);     // fuel_capacity
            stmt.setNull(7, Types.DOUBLE);      // payload_capacity
            stmt.setNull(8, Types.INTEGER);     // engine_displacement
            stmt.setNull(9, Types.BOOLEAN);     // has_side_car
            stmt.setNull(10, Types.INTEGER);    // gear_count
            stmt.setNull(11, Types.DOUBLE);     // last_refuel_amount
            stmt.setNull(12, Types.INTEGER);    //driver_id


            switch (vehicle) {
                case Car car -> {
                    stmt.setString(1, "Car");
                    stmt.setInt(6, car.getFuelCapacity());
                    stmt.setDouble(11, car.getLastRefuelAmount());
                }
                case Truck truck -> {
                    stmt.setString(1, "Truck");
                    stmt.setInt(6, truck.getFuelCapacity());
                    stmt.setDouble(7, truck.getPayloadCapacity());
                    stmt.setDouble(11, truck.getLastRefuelAmount());
                }
                case Motorcycle moto -> {
                    stmt.setString(1, "Motorcycle");
                    stmt.setInt(6, moto.getFuelCapacity());
                    stmt.setInt(8, moto.getEngineDisplacement());
                    stmt.setBoolean(9, moto.hasSideCar());
                    stmt.setDouble(11, moto.getLastRefuelAmount());
                }
                case Bicycle bicycle -> {
                    stmt.setString(1, "Bicycle");
                    stmt.setInt(10, bicycle.getGearCount());
                }
                default -> {
                }
            }
            stmt.executeUpdate();
        }
    }

    public void assignDriver(int vehicle_id, int driver_id) throws SQLException {
        String sql = "UPDATE vehicles SET assigned_driver_id = ? WHERE vehicle_id = ?";
        try (Connection con = DBConnection.getConnection("fleet_management");
        PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, driver_id);
            stmt.setInt(2, vehicle_id);
            stmt.executeUpdate();
        }
    }

    public void deleteVehicle(int vehicle_id) throws SQLException {
        String sql = "DELETE FROM vehicles WHERE vehicle_id=?";
        try (Connection con = DBConnection.getConnection("fleet_management");
        PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, vehicle_id);
            stmt.executeUpdate();
        }
    }

    public void displayVehicle(int vehicle_id) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE vehicle_id=?";
        try (Connection con = DBConnection.getConnection("fleet_management");
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, vehicle_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String type = rs.getString("type");
                int assigned_driver = rs.getInt("assigned_driver_id");
                Employee driver = null;
                if (assigned_driver > 0) {
                    String driverQuery = "SELECT name, role FROM employees WHERE employee_id = ?";
                    try (PreparedStatement driverStmt = con.prepareStatement(driverQuery)) {
                        driverStmt.setInt(1, assigned_driver);
                        try (ResultSet driverRs = driverStmt.executeQuery()) {
                            if (driverRs.next()) {
                                String name = driverRs.getString("name");
                                String role = driverRs.getString("role");
                                driver = new Employee(assigned_driver, name, role);
                            }
                        }
                        switch (type) {
                            case "Car" -> {
                                Car car = new Car(
                                        rs.getInt("vehicle_id"),
                                        rs.getString("brand"),
                                        rs.getString("model"),
                                        rs.getInt("year"),
                                        rs.getDouble("mileage"),
                                        rs.getInt("fuel_capacity")
                                );
                                car.assignDriver(driver);
                                System.out.println(car.displayInfo());
                            }
                            case "Truck" -> {
                                Truck truck = new Truck(
                                        rs.getInt("vehicle_id"),
                                        rs.getString("brand"),
                                        rs.getString("model"),
                                        rs.getInt("year"),
                                        rs.getDouble("mileage"),
                                        rs.getInt("fuel_capacity"),
                                        rs.getDouble("payload_capacity")
                                );
                                truck.assignDriver(driver);
                                System.out.println(truck.displayInfo());
                            }
                            case "Motorcycle" -> {
                                Motorcycle motorcycle = new Motorcycle(
                                        rs.getInt("vehicle_id"),
                                        rs.getString("brand"),
                                        rs.getString("model"),
                                        rs.getInt("year"),
                                        rs.getDouble("mileage"),
                                        rs.getInt("fuel_capacity"),
                                        rs.getInt("engine_displacement"),
                                        rs.getBoolean("has_side_car")
                                );
                                motorcycle.assignDriver(driver);
                                System.out.println(motorcycle.displayInfo());
                            }
                            default -> {
                                Bicycle bicycle = new Bicycle(
                                        rs.getInt("vehicle_id"),
                                        rs.getString("brand"),
                                        rs.getString("model"),
                                        rs.getInt("year"),
                                        rs.getDouble("mileage"),
                                        rs.getInt("gear_count")
                                );
                                bicycle.assignDriver(driver);
                                System.out.println(bicycle.displayInfo());
                            }
                        }
                    }
                }
            }
        }
    }

    public void addEmployee(String name, String role) throws SQLException {
        String sql = "INSERT INTO employees (name, role) VALUES (?,?)";
        try (Connection con = DBConnection.getConnection("fleet_management");
        PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, role);
            stmt.executeUpdate();
        }
    }

    public void deleteEmployee(int employee_id) throws SQLException {
        String sql = "DELETE FROM employees WHERE employee_id=?";
        try (Connection con = DBConnection.getConnection("fleet_management");
        PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, employee_id);
            stmt.executeUpdate();
        }
    }

    public void getEmployee(int employee_id) throws SQLException {
        String sql = "SELECT * FROM employees WHERE employee_id=?";
        try (Connection con = DBConnection.getConnection("fleet_management");
        PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, employee_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("role")
                );
                System.out.println(employee.displayInfo());
            }
        }
    }

    public void getAllEmployees() throws SQLException {
        String sql = "SELECT * FROM employees";

        try (Connection con = DBConnection.getConnection("fleet_management");
        PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("name"),
                    rs.getString("role")
                );
                System.out.println(employee.displayInfo());
            }
        }
    }

    public void close() throws SQLException {
        Connection con = LE_09._04.utils.DBConnection.getConnection("fleet_management");
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}
