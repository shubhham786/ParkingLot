package models;

import Strategies.BillingStrategy;
import Strategies.SpotSelectionStrategy;
import Utils.BillingStrategySelectoer;
import enums.GateType;

import Exception.ParkingLotException;


import java.time.LocalDateTime;
import java.util.*;

//entry gate validation
//exit gate validation
//parkvehicle-->genrate ticket
//unpark vehilce-->generate bill with amount


public class ParkingLot {
    List<Gate> gates;
    List<ParkingFloor> parkingFloors;
    Map<String, Ticket> ongoingTickets;  // vehicleNumber -> Ticket


    public List<Gate> getGates() {
        return gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public void setParkingFloors(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }

    public Map<String, Ticket> getOngoingTickets() {
        return ongoingTickets;
    }

    public void setOngoingTickets(Map<String, Ticket> ongoingTickets) {
        this.ongoingTickets = ongoingTickets;
    }


    public ParkingLot() {
         gates = new ArrayList<>();
          gates.add(new Gate(1, GateType.OPEN));
          gates.add(new Gate(2, GateType.CLOSED));
           parkingFloors = new ArrayList<>();
           parkingFloors.add(new ParkingFloor(1,4));
           parkingFloors.add(new ParkingFloor(2,5));
          ongoingTickets = new HashMap<>();
     }

    public Ticket assignSpotAndGenerateTicket(Gate gate, Vehicle vehicle, SpotSelectionStrategy strategy) throws ParkingLotException {
        // 1. Validate gate (ensure it's an entry gate)
        if (gate.getGateType() != GateType.OPEN) {
            throw new ParkingLotException("Invalid entry gate");
        }
        // 2. Find empty spot using strategy
         ParkingSpot parkingSpot=strategy.getEmptySlot(this,vehicle);;
          if(parkingSpot==null) {
                throw new ParkingLotException("No parking spot avalaible");
           }

          //park the vehicle
           parkingSpot.parkVehicle(vehicle);

          String ticketId=getUniqueTicketId();

          Ticket ticket=new Ticket(ticketId,LocalDateTime.now(),vehicle,parkingSpot);
          ongoingTickets.put(vehicle.getVehicleNumber(), ticket);

        // 3. Create and return ticket
        return ticket;
    }

      public String getUniqueTicketId() {

          return   "TICKET-" + System.currentTimeMillis();
      }


   public  Bill removeSpotAndGenerateBill(String vehicleNumber) throws ParkingLotException {
        // 1. Find ticket
         Ticket ticket=ongoingTickets.get(vehicleNumber);
         if(ticket==null) {
              throw new ParkingLotException("No Ongoing Ticket found for this Vehicle");
         }
        // 2. Calculate amount
       LocalDateTime exitTime=LocalDateTime.now();
       //decide the biiling stragety based on time
       BillingStrategy strategy =
               BillingStrategySelectoer.selectBillingStrategy(ticket.getEntryTime(), exitTime, ticket.getVehicleDetails().getVehicleType());
        double amount= strategy.getBillAmount(ticket.getEntryTime(),exitTime,ticket.getVehicleDetails().getVehicleType());
       Bill bill=new Bill(ticket,amount);
        // 3. Free spot
        ParkingSpot parkingSpot=ticket.getAssignedSpot();

         parkingSpot.unparkVehicle();
        // 4. Return bill

        ongoingTickets.remove(vehicleNumber);
        return bill;
    }
}
