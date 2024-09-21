package isep.lei.esinf.project2.domain.domainAVL;

import isep.lei.esinf.project2.struct.AVL;

import java.util.Objects;

public class Vehicle implements Comparable<Vehicle> {

    private int vehID;
    private String vehType;
    private String vehicleClass;

    private String engineConfig;

    private String transmission;

    private String driveWheels;

    private String generalizedWeight;

    // Add avl
    private AVL<DayNum> dayNumAVL;

    public Vehicle(int vehID, String vehType, String vehicleClass, String engineConfig, String transmission,
            String driveWheels, String generalizedWeight) {
        this.vehID = vehID;
        this.vehType = vehType;
        this.vehicleClass = vehicleClass;
        this.engineConfig = engineConfig;
        this.transmission = transmission;
        this.driveWheels = driveWheels;
        this.generalizedWeight = generalizedWeight;
    }

    public Vehicle(int vehID, String vehType, String vehicleClass, String engineConfig, String transmission,
            String driveWheels, String generalizedWeight, AVL<DayNum> dayNumAVL) {
        this.vehID = vehID;
        this.vehType = vehType;
        this.vehicleClass = vehicleClass;
        this.engineConfig = engineConfig;
        this.transmission = transmission;
        this.driveWheels = driveWheels;
        this.generalizedWeight = generalizedWeight;
        this.dayNumAVL = dayNumAVL;
    }

    public Vehicle(int vehID) {
        this.vehID = vehID;
    }

    public AVL<DayNum> getDayNumAVL() {
        return this.dayNumAVL;
    }

    public void setDayNumAVL(AVL<DayNum> dayNumAVL) {
        this.dayNumAVL = dayNumAVL;
    }

    public int getVehID() {
        return vehID;
    }

    public String getVehIDString() {
        return String.valueOf(vehID);
    }

    public void setVehID(int vehID) {
        this.vehID = vehID;
    }

    public String getVehType() {
        return vehType;
    }

    public void setVehType(String vehType) {
        this.vehType = vehType;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getEngineConfig() {
        return engineConfig;
    }

    public void setEngineConfig(String engineConfig) {
        this.engineConfig = engineConfig;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDriveWheels() {
        return driveWheels;
    }

    public void setDriveWheels(String driveWheels) {
        this.driveWheels = driveWheels;
    }

    public String getGeneralizedWeight() {
        return generalizedWeight;
    }

    public void setGeneralizedWeight(String generalizedWeight) {
        this.generalizedWeight = generalizedWeight;
    }

    @Override
    public String toString() {
        return "Vehicles{" + "vehID=" + vehID + ", vehType=" + vehType + ", vehicleClass=" + vehicleClass + ", " +
                "engineConfig=" + engineConfig + ", transmission=" + transmission + ", driveWheels=" + driveWheels +
                ", generalizedWeight=" + generalizedWeight + '}';
    }

    @Override
    public int compareTo(Vehicle o) {
        return Integer.compare(this.vehID, o.vehID);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Vehicle)) {
            return false;
        }
        Vehicle v = (Vehicle) o;
        return vehID == v.vehID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(vehID);
    }
}