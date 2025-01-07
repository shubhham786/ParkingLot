package Strategies;

import models.ParkingLot;
import models.ParkingSpot;
import models.Vehicle;

public interface SpotSelectionStrategy {
    ParkingSpot getEmptySlot(ParkingLot parkingLot, Vehicle vehicle);
}
