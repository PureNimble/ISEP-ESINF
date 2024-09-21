package isep.lei.esinf.project2.domain.domainAVL;

import isep.lei.esinf.project2.domain.trip.*;

public class TimeStamp implements Comparable<TimeStamp> {

    private int timestamp;

    private TripID tripId;

    private BatteryUsage batteryUsage;

    private Coordinates coordenates;

    private FuelExpendures fuelExpendures;

    private FuelTrimBank fuelTrimBank;

    public TimeStamp(String timestamp, TripID tripId, BatteryUsage batteryUsage, Coordinates coordenates,
            FuelExpendures fuelExpendures, FuelTrimBank fuelTrimBank) {
        this.timestamp = Integer.valueOf(timestamp);
        this.tripId = tripId;
        this.batteryUsage = batteryUsage;
        this.coordenates = coordenates;
        this.fuelExpendures = fuelExpendures;
        this.fuelTrimBank = fuelTrimBank;
    }

    public TripID getTripId() {
        return tripId;
    }

    public BatteryUsage getBatteryUsage() {
        return batteryUsage;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public Coordinates getCoordenates() {
        return coordenates;
    }

    public FuelExpendures getFuelExpendures() {
        return fuelExpendures;
    }

    public FuelTrimBank getFuelTrimBank() {
        return fuelTrimBank;
    }

    @Override
    public String toString() {
        return "TimeStamp [timestamp=" + timestamp + ", tripId=" + tripId + ", batteryUsage=" + batteryUsage
                + ", coordenates=" + coordenates + ", fuelExpendures=" + fuelExpendures + ", fuelTrimBank="
                + fuelTrimBank + "]";
    }

    @Override
    public int compareTo(TimeStamp o) {
        return this.timestamp - o.timestamp;
    }
}
