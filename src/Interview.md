# Parking Lot System - Interview Questions & Answers

## System Design Questions

### 1. Explain your high-level design approach
**A:** The system follows a modular design with clear separation of concerns:
- Core entities (ParkingLot, Floor, Spot) manage physical resources
- Strategies handle business logic (billing, spot selection)
- Thread-safe operations for concurrent access
- Custom exceptions for error handling
```java
// Example of modular design
public class ParkingLot {
    private List<ParkingFloor> floors;
    private Map<String, Ticket> activeTickets;
    private List<Gate> gates;
}
```

### 2. How do you handle concurrent parking requests?
**A:** Multiple approaches:
```java
// Thread-safe spot management
public synchronized void parkVehicle(Vehicle vehicle) {
    if (spotStatus != SpotStatus.EMPTY)
        throw new ParkingLotException("Spot occupied");
    this.vehicle = vehicle;
    this.spotStatus = SpotStatus.OCCUPIED;
}
```
- Synchronized methods for critical sections
- Concurrent collections for ticket management
- Atomic operations for status updates

### 3. Explain your strategy pattern implementation
**A:** Used for flexible billing and spot selection:
```java
// Strategy interface
public interface BillingStrategy {
    double getBillAmount(LocalDateTime entry, LocalDateTime exit, VehicleType type);
}

// Implementation
public class HourlyBillingStrategy implements BillingStrategy {
    @Override
    public double getBillAmount(...) {
        // Hourly billing logic
    }
}
```

## Implementation Questions

### 1. How would you add payment processing?
**A:** Add payment strategy:
```java
public interface PaymentStrategy {
    boolean processPayment(Bill bill);
}

public class CreditCardPayment implements PaymentStrategy {
    public boolean processPayment(Bill bill) {
        // Payment processing logic
        return true;
    }
}
```

### 2. How to implement a notification system?
**A:** Create notification service:
```java
public interface NotificationService {
    void notifyEntry(Ticket ticket);
    void notifyPaymentDue(Bill bill);
    void notifySpotFull(VehicleType type);
}
```

### 3. How would you scale for multiple parking lots?
**A:** Create a management layer:
```java
public class ParkingLotManager {
    private Map<String, ParkingLot> parkingLots;
    
    public Ticket parkVehicle(String lotId, Vehicle vehicle) {
        ParkingLot lot = parkingLots.get(lotId);
        return lot.parkVehicle(vehicle);
    }
}
```

## Edge Cases

### 1. System failure during parking
**A:** Implement transaction-like behavior:
```java
public Ticket parkVehicle(Vehicle vehicle) {
    try {
        ParkingSpot spot = findSpot(vehicle);
        spot.markPending();  // Temporary state
        Ticket ticket = generateTicket(vehicle, spot);
        spot.confirm();      // Final state
        return ticket;
    } catch (Exception e) {
        spot.reset();        // Rollback
        throw e;
    }
}
```

### 2. Handle peak hour traffic
**A:** Multiple solutions:
```java
public class PeakHourManager {
    private boolean isPeakHour(LocalDateTime time) {
        int hour = time.getHour();
        return (hour >= 9 && hour <= 11) || (hour >= 16 && hour <= 18);
    }
    
    public double getRate(double baseRate) {
        return isPeakHour(LocalDateTime.now()) ? baseRate * 1.5 : baseRate;
    }
}
```

## Performance Questions

### 1. Optimize spot finding
**A:** Use data structures for quick lookup:
```java
public class OptimizedParkingFloor {
    private Map<VehicleType, Queue<ParkingSpot>> availableSpots;
    
    public ParkingSpot findSpot(VehicleType type) {
        return availableSpots.get(type).poll();
    }
    
    public void releaseSpot(ParkingSpot spot) {
        availableSpots.get(spot.getType()).offer(spot);
    }
}
```

### 2. Handle large-scale data
**A:** Implement caching and indexing:
```java
public class ParkingDataManager {
    private Cache<String, Ticket> ticketCache;
    private Map<VehicleType, Integer> spotCountIndex;
    
    public void updateSpotIndex(VehicleType type, int delta) {
        spotCountIndex.merge(type, delta, Integer::sum);
    }
}
```

## Extension Questions

### 1. Add valet parking
**A:** Create valet service:
```java
public class ValetService {
    private Queue<ParkingAttendant> availableAttendants;
    
    public Ticket parkWithValet(Vehicle vehicle) {
        ParkingAttendant attendant = availableAttendants.poll();
        return attendant.parkVehicle(vehicle);
    }
}
```

### 2. Implement electric charging
**A:** Extend parking spot:
```java
public class ChargingSpot extends ParkingSpot {
    private boolean isCharging;
    private double chargingRate;
    
    public void startCharging() {
        this.isCharging = true;
        // Start charging logic
    }
}
```

## Design Pattern Questions

### 1. Why use Factory pattern for billing?
**A:** Benefits:
- Encapsulates strategy creation
- Runtime strategy selection
- Easy to add new strategies
```java
public class BillingStrategyFactory {
    public static BillingStrategy getStrategy(Duration parkingDuration) {
        return parkingDuration.toHours() > 12 
            ? new DailyBillingStrategy() 
            : new HourlyBillingStrategy();
    }
}
```

### 2. How to add new vehicle types?
**A:** System is extensible:
- Add to VehicleType enum
- Create corresponding billing rates
- Update spot allocation logic
```java
public enum VehicleType {
    TWO_WHEELER,
    THREE_WHEELER,  // New type
    FOUR_WHEELER,
    HEAVY_VEHICLE   // New type
}
```

## Testing Approach

### 1. Unit Testing Strategy
```java
@Test
public void testParkingFullScenario() {
    ParkingLot lot = new ParkingLot(1, 1);  // 1 floor, 1 spot
    Vehicle v1 = new Vehicle("TEST1", VehicleType.FOUR_WHEELER);
    Vehicle v2 = new Vehicle("TEST2", VehicleType.FOUR_WHEELER);
    
    lot.parkVehicle(v1);  // Should succeed
    assertThrows(ParkingLotException.class, () -> lot.parkVehicle(v2));
}
```

### 2. Integration Testing
```java
@Test
public void testCompleteFlow() {
    // Test entire flow from entry to exit
    ParkingLot lot = new ParkingLot();
    Vehicle vehicle = new Vehicle("TEST1", VehicleType.FOUR_WHEELER);
    
    Ticket ticket = lot.parkVehicle(vehicle);
    assertNotNull(ticket);
    
    Bill bill = lot.generateBill(ticket);
    assertTrue(bill.getAmount() > 0);
}
```