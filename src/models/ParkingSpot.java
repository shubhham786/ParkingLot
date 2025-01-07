package models;

import enums.SpotStatus;
import enums.VehicleType;
import  Exception.ParkingLotException;

public class ParkingSpot {

    int spotId;
    Vehicle vehicle;                // Currently parked vehicle
    VehicleType supportedVehicleType;// Type of vehicle supported
    SpotStatus spotStatus;// Current status


     public ParkingSpot(int spotId, VehicleType supportedVehicleType) {
         this.spotId = spotId;
         this.supportedVehicleType = supportedVehicleType;
         this.spotStatus = SpotStatus.EMPTY;
     }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleType getSupportedVehicleType() {
        return supportedVehicleType;
    }

    public void setSupportedVehicleType(VehicleType supportedVehicleType) {
        this.supportedVehicleType = supportedVehicleType;
    }

    public SpotStatus getSpotStatus() {
        return spotStatus;
    }

    public void setSpotStatus(SpotStatus spotStatus) {
        this.spotStatus = spotStatus;
    }

    public synchronized void parkVehicle(Vehicle vehicle) throws ParkingLotException {

         if(this.spotStatus!=SpotStatus.EMPTY)
              throw new ParkingLotException("Spot is not Empty");

         if(vehicle.vehicleType!=this.supportedVehicleType)
             throw new ParkingLotException("Vehicle is not supported");


         this.vehicle = vehicle;
         this.spotStatus=SpotStatus.OCCUPIED;

    }
   public synchronized void  unparkVehicle() throws ParkingLotException {

         if(this.spotStatus!=SpotStatus.OCCUPIED)
            throw new ParkingLotException("No Vehicle is parked here");

            this.vehicle=null;
            this.spotStatus=SpotStatus.EMPTY;
    }
}
