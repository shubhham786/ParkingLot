package Strategies;

import models.ParkingLot;
import models.ParkingSpot;
import models.Vehicle;

public class CloserToEntryGateStrategy implements SpotSelectionStrategy{

    @Override
    public ParkingSpot getEmptySlot(ParkingLot parkingLot, Vehicle vehicle) {
        return null;
    }
}
