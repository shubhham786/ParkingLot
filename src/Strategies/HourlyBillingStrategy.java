package Strategies;

import enums.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class HourlyBillingStrategy implements BillingStrategy{
    // Hourly rates for different vehicle types
    private static final double TWO_WHEELER_HOURLY_RATE = 10.0;
    private static final double FOUR_WHEELER_HOURLY_RATE = 20.0;

    @Override
    public double getBillAmount(LocalDateTime entryTime, LocalDateTime exitTime, VehicleType type) {

         Duration duration = Duration.between(entryTime, exitTime);

           long hours = Math.max(1,(long)Math.ceil(duration.toSeconds()));

           double hourlyRate = (type==VehicleType.TWO_WHELLER)?TWO_WHEELER_HOURLY_RATE:FOUR_WHEELER_HOURLY_RATE;

           return hours * hourlyRate;
    }
}
