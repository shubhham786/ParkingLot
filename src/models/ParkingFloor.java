package models;

import enums.SpotStatus;
import enums.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingFloor {

    int floorNumber;
    List<ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNumber, int capacity) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ArrayList<ParkingSpot>();
        intilazieSpot(capacity);
    }

    private void intilazieSpot(int capacity) {

        // 40% 2 wheeler and 60% 4 wheller
      int twoWheller= (int) (0.4*capacity);
      int fourWheeler=(int) (0.6*capacity);
            int id=1;
       for(int i=0;i<twoWheller;i++)
       {
            parkingSpots.add(new ParkingSpot(id, VehicleType.TWO_WHELLER));
            id++;
       }

       for(int i=0;i<fourWheeler;i++)
      {
       parkingSpots.add(new ParkingSpot(id, VehicleType.FOUR_WHELLER));
       id++;
      }

    }



   public List<ParkingSpot> getEmptySlot(VehicleType type) {

       return parkingSpots.stream().filter(spot->spot.getSpotStatus()== SpotStatus.EMPTY
                          && type==spot.getSupportedVehicleType()).collect(Collectors.toList());

    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }
}
