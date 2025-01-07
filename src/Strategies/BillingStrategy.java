package Strategies;

import enums.VehicleType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public interface BillingStrategy {
    double getBillAmount(LocalDateTime entryTime, LocalDateTime exitTime, VehicleType type);
}
