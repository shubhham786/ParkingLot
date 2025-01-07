package models;

import enums.VehicleType;

public class Vehicle {
    String vehicleNumber;   // Unique identifier
    String driverName;      // Driver info
    VehicleType vehicleType;

    public Vehicle(String vehicleNumber, String driverName, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.driverName = driverName;
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
