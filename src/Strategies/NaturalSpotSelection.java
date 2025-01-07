package Strategies;

import models.ParkingFloor;
import models.ParkingLot;
import models.ParkingSpot;
import models.Vehicle;

import java.util.List;

public class NaturalSpotSelection implements  SpotSelectionStrategy{

    @Override
    public ParkingSpot getEmptySlot(ParkingLot parkingLot, Vehicle vehicle) {
        for (ParkingFloor floor : parkingLot.getParkingFloors()) {
            List<ParkingSpot> emptySpots = floor.getEmptySlot(vehicle.getVehicleType());
            if (!emptySpots.isEmpty()) {
                return emptySpots.get(0); // Return first available spot
            }
        }
        return null; // No spot available

    }
}
