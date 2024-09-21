package isep.lei.esinf.project2.domain.trip;

import java.util.Objects;

public class FuelExpendures {

    private Double speed;

    private String maf;

    private String rpm;

    private String load;

    private String oat;

    private String fuelRate;

    public FuelExpendures(String speed, String maf, String rpm, String load, String oat, String fuelRate) {
        this.speed = Double.valueOf(speed);
        this.maf = maf;
        this.rpm = rpm;
        this.load = load;
        this.oat = oat;
        this.fuelRate = fuelRate;
    }

    public Double getSpeed() {
        return speed;
    }

    public String getMaf() {
        return maf;
    }

    public String getRpm() {
        return rpm;
    }

    public String getLoad() {
        return load;
    }

    public String getOat() {
        return oat;
    }

    public String getFuelRate() {
        return fuelRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FuelExpendures that = (FuelExpendures) o;
        return Objects.equals(speed, that.speed) && Objects.equals(maf, that.maf) && Objects.equals(rpm, that.rpm)
                && Objects.equals(load, that.load) && Objects.equals(oat, that.oat)
                && Objects.equals(fuelRate, that.fuelRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speed, maf, rpm, load, oat, fuelRate);
    }
}
