import Strategies.NaturalSpotSelection;
import enums.VehicleType;
import models.Bill;
import models.ParkingLot;
import models.Ticket;
import models.Vehicle;
import Exception.ParkingLotException;
public class Main {
    public static void main(String[] args) {

        ParkingLot lot = new ParkingLot();

        Vehicle vehicle = new Vehicle("BR-01-1156","Shubham", VehicleType.FOUR_WHELLER);
        Vehicle vehicle1 = new Vehicle("BR-02-1102","Shantanu", VehicleType.TWO_WHELLER);

        try {
            Ticket ticket = lot.assignSpotAndGenerateTicket(lot.getGates().get(0),vehicle,new NaturalSpotSelection());
            System.out.println(ticket);
        } catch (ParkingLotException e) {
             System.out.println(e);
        }

        try {
            Bill bill = lot.removeSpotAndGenerateBill(vehicle.getVehicleNumber());
            System.out.println(bill);
        } catch (ParkingLotException e) {
            throw new RuntimeException(e);
        }

    }
}