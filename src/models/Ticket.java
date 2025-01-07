package models;

import java.time.LocalDateTime;
import java.util.Date;

public class Ticket {
    String ticketId;           // Unique identifier
    LocalDateTime entryTime;           // Entry timestamp
    Vehicle vehicleDetails;    // Vehicle information
    ParkingSpot assignedSpot; // Assigned parking spot

    public Ticket(String ticketId, LocalDateTime entryTime, Vehicle vehicleDetails, ParkingSpot assignedSpot) {
        this.ticketId = ticketId;
        this.entryTime = entryTime;
        this.vehicleDetails = vehicleDetails;
        this.assignedSpot = assignedSpot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Vehicle getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(Vehicle vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public ParkingSpot getAssignedSpot() {
        return assignedSpot;
    }

    public void setAssignedSpot(ParkingSpot assignedSpot) {
        this.assignedSpot = assignedSpot;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", entryTime=" + entryTime +
                ", vehicleDetails=" + vehicleDetails +
                ", assignedSpot=" + assignedSpot +
                '}';
    }
}
