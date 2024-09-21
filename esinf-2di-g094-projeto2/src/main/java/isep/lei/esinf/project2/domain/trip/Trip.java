package isep.lei.esinf.project2.domain.trip;

import isep.lei.esinf.project2.domain.domainAVL.TripID;

public class Trip implements Comparable<Trip> {

    private String dayNum;

    private TripID tripId;

    private BatteryUsage batteryUsage;

    private int timestamp;

    private Coordinates coordenates;

    private FuelExpendures fuelExpendures;

    private FuelTrimBank fuelTrimBank;

    private String VehId;

    public Trip(String dayNum, TripID tripId, BatteryUsage batteryUsage, String timestamp, Coordinates coordenates,
            FuelExpendures fuelExpendures, FuelTrimBank fuelTrimBank, String vehId) {
        this.dayNum = dayNum;
        this.tripId = tripId;
        this.batteryUsage = batteryUsage;
        this.timestamp = Integer.valueOf(timestamp);
        this.coordenates = coordenates;
        this.fuelExpendures = fuelExpendures;
        this.fuelTrimBank = fuelTrimBank;
        this.VehId = vehId;
    }

    public String getDayNum() {
        return dayNum;
    }

    public TripID getTripId() {
        return tripId;
    }

    public BatteryUsage getBatteryUsage() {
        return batteryUsage;
    }

    public String getVehId() {
        return VehId;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getTimestampString() {
        return String.valueOf(timestamp);
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
        return "Trip [dayNum=" + dayNum + ", tripId=" + tripId + ", batteryUsage=" + batteryUsage + ", timestamp="
                + timestamp + ", coordenates=" + coordenates + ", fuelExpendures=" + fuelExpendures + ", fuelTrimBank="
                + fuelTrimBank + ", VehId=" + VehId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dayNum == null) ? 0 : dayNum.hashCode());
        result = prime * result + ((tripId == null) ? 0 : tripId.hashCode());
        result = prime * result + ((batteryUsage == null) ? 0 : batteryUsage.hashCode());
        result = prime * result + timestamp;
        result = prime * result + ((coordenates == null) ? 0 : coordenates.hashCode());
        result = prime * result + ((fuelExpendures == null) ? 0 : fuelExpendures.hashCode());
        result = prime * result + ((fuelTrimBank == null) ? 0 : fuelTrimBank.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Trip other = (Trip) obj;
        if (dayNum == null) {
            if (other.dayNum != null)
                return false;
        } else if (!dayNum.equals(other.dayNum))
            return false;
        if (tripId == null) {
            if (other.tripId != null)
                return false;
        } else if (!tripId.equals(other.tripId))
            return false;
        if (batteryUsage == null) {
            if (other.batteryUsage != null)
                return false;
        } else if (!batteryUsage.equals(other.batteryUsage))
            return false;
        if (timestamp != other.timestamp)
            return false;
        if (coordenates == null) {
            if (other.coordenates != null)
                return false;
        } else if (!coordenates.equals(other.coordenates))
            return false;
        if (fuelExpendures == null) {
            if (other.fuelExpendures != null)
                return false;
        } else if (!fuelExpendures.equals(other.fuelExpendures))
            return false;
        if (fuelTrimBank == null) {
            if (other.fuelTrimBank != null)
                return false;
        } else if (!fuelTrimBank.equals(other.fuelTrimBank))
            return false;
        return true;
    }

    @Override
    public int compareTo(Trip o) {
        return Integer.compare(this.timestamp, o.getTimestamp());
    }
}