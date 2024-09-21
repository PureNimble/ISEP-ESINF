package isep.lei.esinf.project2.domain.trip;

import java.util.Comparator;
import java.util.Objects;

public class Coordinates implements Comparator<Coordinates> {
    private static final double R = 6371e3;
    private double latitude;
    private double longitude;

    public Coordinates(){
        latitude = 0;
        longitude = 0;
    }

    public Coordinates(String latitude, String longitude) {
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public int compare(Coordinates o1, Coordinates o2) {
        return Double.compare(o1.distance(o2), 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return latitude + "," + longitude;
    }

    public double distance(Coordinates other) {
        double phi1 = Math.toRadians(latitude);
        double phi2 = Math.toRadians(other.getLatitude());
        double deltaPhi = Math.toRadians(other.getLatitude() - latitude);
        double deltaLambda = Math.toRadians(other.getLongitude() - longitude);

        double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = R * c;

        return distance;
    }
}
