package Strategies;

import enums.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class DailyBillingStrategy implements BillingStrategy{

    private static final double TWO_WHEELER_DAILY_RATE = 50.0;
    private static final double FOUR_WHEELER_DAILY_RATE = 100.0;
    @Override
    public double getBillAmount(LocalDateTime entryTime, LocalDateTime exitTime, VehicleType type) {

        Duration duration = Duration.between(entryTime, exitTime);

         long days=Math.max(1,(long)Math.ceil(duration.toDays()));
        // Select rate based on vehicle type
        double dailyRate = (type == VehicleType.TWO_WHELLER)
                ? TWO_WHEELER_DAILY_RATE
                : FOUR_WHEELER_DAILY_RATE;

        // Calculate total bill
        return days * dailyRate;

    }
}
