# Parking Lot System

## Overview
A robust, multi-level parking lot management system designed with object-oriented principles and enterprise-grade features. The system handles vehicle parking, billing, and space management with thread-safe operations.

## Key Features
- Multi-floor parking management
- Dynamic pricing (Hourly/Daily)
- Vehicle type-based spot allocation
- Thread-safe operations
- Automated billing
- Extensible strategy patterns

## Architecture

### Core Components

1. **Models**
   - `ParkingLot`: Central system orchestrator
   - `ParkingFloor`: Manages individual floor operations
   - `ParkingSpot`: Handles individual parking spots
   - `Vehicle`: Stores vehicle information
   - `Ticket`: Manages parking sessions
   - `Bill`: Handles payment calculations
   - `Gate`: Controls entry/exit points

2. **Strategies**
   - Spot Selection Strategy
     - Natural ordering
     - Proximity-based selection
   - Billing Strategy
     - Hourly billing
     - Daily billing
     - Strategy selector based on duration

3. **Enums**
   - `VehicleType`: TWO_WHEELER, FOUR_WHEELER
   - `SpotStatus`: EMPTY, OCCUPIED, UNDER_MAINTENANCE
   - `GateType`: OPEN, CLOSED

## System Flow

1. **Vehicle Entry**
   ```
   Vehicle Arrives → Gate Validation → Spot Assignment → Ticket Generation
   ```

2. **Vehicle Exit**
   ```
   Vehicle at Exit → Ticket Validation → Bill Generation → Payment → Spot Release
   ```

## Technical Details

### Capacity Management
- 40% Two-wheeler spots
- 60% Four-wheeler spots
- Dynamic spot status tracking

### Pricing Structure
```
Hourly Rates:
- Two-wheeler: ₹10/hour
- Four-wheeler: ₹20/hour

Daily Rates:
- Two-wheeler: ₹50/day
- Four-wheeler: ₹100/day
```

### Thread Safety
- Synchronized parking operations
- Thread-safe spot allocation
- Concurrent ticket management

## Exception Handling
- Custom `ParkingLotException`
- Graceful error management
- Business rule validations

## Design Patterns
1. **Strategy Pattern**
   - Flexible billing strategies
   - Customizable spot selection
   - Runtime strategy switching

2. **Factory Pattern**
   - Dynamic billing strategy creation
   - Based on parking duration

## Code Structure
```
src/
├── models/
│   ├── ParkingLot.java
│   ├── ParkingFloor.java
│   ├── ParkingSpot.java
│   └── ...
├── strategies/
│   ├── BillingStrategy.java
│   └── SpotSelectionStrategy.java
├── enums/
│   ├── VehicleType.java
│   └── SpotStatus.java
└── exceptions/
    └── ParkingLotException.java
```

## Usage Example
```java
// Initialize parking lot
ParkingLot lot = new ParkingLot();

// Create vehicle
Vehicle vehicle = new Vehicle("BR-01-1156", "John", VehicleType.FOUR_WHEELER);

// Park vehicle
Ticket ticket = lot.assignSpotAndGenerateTicket(
    lot.getGates().get(0),
    vehicle,
    new NaturalSpotSelection()
);

// Generate bill and exit
Bill bill = lot.removeSpotAndGenerateBill(vehicle.getVehicleNumber());
```

## System Constraints
- Single entry/exit gate
- Fixed pricing for vehicle types
- No reservation system
- Basic billing strategies

## Future Enhancements
1. Payment processing integration
2. Multiple parking lot support
3. Reservation system
4. Electric vehicle charging spots
5. Valet parking service
6. Analytics and reporting
7. Mobile app integration
8. Loyalty program
