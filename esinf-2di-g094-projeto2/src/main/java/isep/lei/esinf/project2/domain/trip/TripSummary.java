package isep.lei.esinf.project2.domain.trip;

public class TripSummary implements Comparable<TripSummary> {

    private int tripRecordsNum;

    private Double averageSpeed;
    private Double standardDeviationSpeed;

    private Coordinates origin;
    private Coordinates destination;

    private int totalTime;

    public TripSummary(int tripRecordsNum, Double averageSpeed, Double standardDeviationSpeed, Coordinates origin,
            Coordinates destination, int totalTime) {
        this.tripRecordsNum = tripRecordsNum;
        this.averageSpeed = averageSpeed;
        this.standardDeviationSpeed = standardDeviationSpeed;
        this.origin = origin;
        this.destination = destination;
        this.totalTime = totalTime;
    }

    public TripSummary() {
        this.tripRecordsNum = 0;
        this.averageSpeed = 0.0;
        this.standardDeviationSpeed = 0.0;
        this.origin = null;
        this.destination = null;
        this.totalTime = 0;
    }

    public int getTripRecordsNum() {
        return tripRecordsNum;
    }

    public void setTripRecordsNum(int tripRecordsNum) {
        this.tripRecordsNum = tripRecordsNum;
    }

    public Coordinates getOrigin() {
        return origin;
    }

    public void setOrigin(Coordinates origin) {
        this.origin = origin;
    }

    public Coordinates getDestination() {
        return destination;
    }

    public void setDestination(Coordinates destination) {
        this.destination = destination;
    }

    public Double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(Double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public Double getStandardDeviationSpeed() {
        return standardDeviationSpeed;
    }

    public void setStandardDeviationSpeed(Double standardDeviationSpeed) {
        this.standardDeviationSpeed = standardDeviationSpeed;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totaltime) {
        this.totalTime = totaltime;
    }

    public String toString() {
        return "-> TripSummary \n" +
                "   -Number of registers = " + tripRecordsNum + "\n" +
                "   -Average speed = " + (double) Math.round(averageSpeed * 100.0) / 100.0 + " km/h\n" +
                "   -Standard deviation speed = " + (double) Math.round(standardDeviationSpeed * 100.0) / 100.0
                + " km/h\n" +
                "   -Origin = " + origin + "\n" +
                "   -Destination = " + destination + "\n" +
                "   -Total time = " + totalTime + " seconds";
    }

    @Override
    public int compareTo(TripSummary arg0) {

        return this.tripRecordsNum - arg0.tripRecordsNum;
    }

}