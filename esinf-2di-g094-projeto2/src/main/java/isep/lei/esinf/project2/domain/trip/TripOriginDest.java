package isep.lei.esinf.project2.domain.trip;

public class TripOriginDest {

    private String tripID;
    private Double originLatitude;
    private Double originLongitude;
    private Double destinationLatitude;
    private Double destinationLongitude;

    public TripOriginDest(Double originLatitude, Double originLongitude, Double destinationLatitude, Double destinationLongitude) {
        this.originLatitude = originLatitude;
        this.originLongitude = originLongitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
    }

    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    public Double getDestinationLongitude() {
        return destinationLongitude;
    }

    public Double getOriginLatitude() {
        return originLatitude;
    }

    public Double getOriginLongitude() {
        return originLongitude;
    }

    public String getTripID() {
        return tripID;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public void setOriginLatitude(Double originLatitude) {
        this.originLatitude = originLatitude;
    }

    public void setOriginLongitude(Double originLongitude) {
        this.originLongitude = originLongitude;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    @Override
    public String toString() {
        return "TripOriginDest{" +
                "tripID=" + tripID +
                ", originLatitude=" + originLatitude +
                ", originLongitude=" + originLongitude +
                ", destinationLatitude=" + destinationLatitude +
                ", destinationLongitude=" + destinationLongitude +
                '}';
    }
}
