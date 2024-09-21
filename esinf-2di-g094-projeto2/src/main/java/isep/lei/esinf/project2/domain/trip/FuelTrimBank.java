package isep.lei.esinf.project2.domain.trip;

import java.util.Objects;

public class FuelTrimBank {
    private String shortFuelTrimBank1;
    private String shortFuelTrimBank2;

    private String longFuelTrimBank1;
    private String longFuelTrimBank2;

    public FuelTrimBank(String shortFuelTrimBank1, String shortFuelTrimBank2, String longFuelTrimBank1, String longFuelTrimBank2) {
        this.shortFuelTrimBank1 = shortFuelTrimBank1;
        this.shortFuelTrimBank2 = shortFuelTrimBank2;
        this.longFuelTrimBank1 = longFuelTrimBank1;
        this.longFuelTrimBank2 = longFuelTrimBank2;
    }

    public String getShortFuelTrimBank1() {
        return shortFuelTrimBank1;
    }

    public String getShortFuelTrimBank2() {
        return shortFuelTrimBank2;
    }

    public String getLongFuelTrimBank1() {
        return longFuelTrimBank1;
    }

    public String getLongFuelTrimBank2() {
        return longFuelTrimBank2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelTrimBank that = (FuelTrimBank) o;
        return Objects.equals(shortFuelTrimBank1, that.shortFuelTrimBank1) && Objects.equals(shortFuelTrimBank2, that.shortFuelTrimBank2) && Objects.equals(longFuelTrimBank1, that.longFuelTrimBank1) && Objects.equals(longFuelTrimBank2, that.longFuelTrimBank2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortFuelTrimBank1, shortFuelTrimBank2, longFuelTrimBank1, longFuelTrimBank2);
    }


    @Override
    public String toString() {
        return "fuelBank: {" +
                "shortFuelTrimBank1='" + shortFuelTrimBank1 + '\'' +
                ", shortFuelTrimBank2='" + shortFuelTrimBank2 + '\'' +
                ", longFuelTrimBank1='" + longFuelTrimBank1 + '\'' +
                ", longFuelTrimBank2='" + longFuelTrimBank2 + '\'' +
                '}';
    }
}
