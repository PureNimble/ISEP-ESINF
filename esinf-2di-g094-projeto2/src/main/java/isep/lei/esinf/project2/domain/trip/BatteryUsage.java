package isep.lei.esinf.project2.domain.trip;

import java.util.Objects;

public class BatteryUsage {
    private String airPowerKw;

    private String airPowerWatts;

    private String heatPowerWatts;

    private String baterryCurrent;

    private String batterySoc;

    private String batteryVoltage;

    public BatteryUsage(String airPowerKw, String airPowerWatts, String heatPowerWatts, String baterryCurrent,
            String batterySoc, String batteryVoltage) {
        this.airPowerKw = airPowerKw;
        this.airPowerWatts = airPowerWatts;
        this.heatPowerWatts = heatPowerWatts;
        this.baterryCurrent = baterryCurrent;
        this.batterySoc = batterySoc;
        this.batteryVoltage = batteryVoltage;
    }

    public String getAirPowerKw() {
        return airPowerKw;
    }

    public String getAirPowerWatts() {
        return airPowerWatts;
    }

    public String getHeatPowerWatts() {
        return heatPowerWatts;
    }

    public String getBaterryCurrent() {
        return baterryCurrent;
    }

    public String getBatterySoc() {
        return batterySoc;
    }

    public String getBatteryVoltage() {
        return batteryVoltage;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BatteryUsage)) {
            return false;
        }
        BatteryUsage batteryUsage = (BatteryUsage) o;
        return Objects.equals(airPowerKw, batteryUsage.airPowerKw)
                && Objects.equals(airPowerWatts, batteryUsage.airPowerWatts)
                && Objects.equals(baterryCurrent, batteryUsage.baterryCurrent)
                && Objects.equals(batterySoc, batteryUsage.batterySoc)
                && Objects.equals(batteryVoltage, batteryUsage.batteryVoltage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airPowerKw, airPowerWatts, baterryCurrent, batterySoc, batteryVoltage);
    }

    @Override
    public String toString() {
        return "{" + " airPowerKw='" + getAirPowerKw() + "'" + ", airPowerWatts='" + getAirPowerWatts() + "'"
                + ", heatPowerWatts='" + getHeatPowerWatts() + "'" + ", baterryCurrent='" + getBaterryCurrent() + "'"
                + ", batterySoc='" + getBatterySoc() + "'" + ", batteryVoltage='" + getBatteryVoltage() + "'" + "}";
    }
}
