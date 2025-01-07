package Utils;

import Strategies.BillingStrategy;
import Strategies.DailyBillingStrategy;
import Strategies.HourlyBillingStrategy;
import enums.VehicleType;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class BillingStrategySelectoer {

    public static BillingStrategy selectBillingStrategy(LocalDateTime entryTime, LocalDateTime exitTime, VehicleType vehicleType) {
        // Static method approach
        Duration duration = Duration.between(entryTime, exitTime);
        long durationHours = duration.toHours();

        if (durationHours <= 12) {
            return new HourlyBillingStrategy();
        } else {
            return new DailyBillingStrategy();
        }
    }


}
