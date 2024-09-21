package isep.lei.esinf.project2.domain;

public class MaxMinAverageDayNum {

    private String dayNum;

    private String vehicleType;

    private double maxVehicleSpeed;
    private double minVehicleSpeed;
    private double avgVehicleSpeed;

    private double maxAbsoluteLoad;
    private double minAbsoluteLoad;
    private double avgAbsoluteLoad;

    private double maxOutsideAirTemperature;
    private double minOutsideAirTemperature;
    private double avgOutsideAirTemperature;

    public MaxMinAverageDayNum(){
        dayNum = "";
        vehicleType = "";
        maxVehicleSpeed = 0;
        minVehicleSpeed = 0;
        avgVehicleSpeed = 0;
        maxAbsoluteLoad = 0;
        minAbsoluteLoad = 0;
        avgAbsoluteLoad = 0;
        maxOutsideAirTemperature = 0;
        minOutsideAirTemperature = 0;
        avgOutsideAirTemperature = 0;
    }

    public MaxMinAverageDayNum(String vehicleType) {
        this.vehicleType = vehicleType;
        maxVehicleSpeed = 0;
        minVehicleSpeed = 0;
        avgVehicleSpeed = 0;
        maxAbsoluteLoad = 0;
        minAbsoluteLoad = 0;
        avgAbsoluteLoad = 0;
        maxOutsideAirTemperature = 0;
        minOutsideAirTemperature = 0;
        avgOutsideAirTemperature = 0;
    }

    public String getDayNum() {
        return dayNum;
    }

    public void setDayNum(String dayNum) {
        this.dayNum = dayNum;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getMaxVehicleSpeed() {
        return maxVehicleSpeed;
    }

    public void setMaxVehicleSpeed(double maxVehicleSpeed) {
        this.maxVehicleSpeed = maxVehicleSpeed;
    }

    public double getMinVehicleSpeed() {
        return minVehicleSpeed;
    }

    public void setMinVehicleSpeed(double minVehicleSpeed) {
        this.minVehicleSpeed = minVehicleSpeed;
    }

    public double getAvgVehicleSpeed() {
        return avgVehicleSpeed;
    }

    public void setAvgVehicleSpeed(double avgVehicleSpeed) {
        this.avgVehicleSpeed = avgVehicleSpeed;
    }

    public double getMaxAbsoluteLoad() {
        return maxAbsoluteLoad;
    }

    public void setMaxAbsoluteLoad(double maxAbsoluteLoad) {
        this.maxAbsoluteLoad = maxAbsoluteLoad;
    }

    public double getMinAbsoluteLoad() {
        return minAbsoluteLoad;
    }

    public void setMinAbsoluteLoad(double minAbsoluteLoad) {
        this.minAbsoluteLoad = minAbsoluteLoad;
    }

    public double getAvgAbsoluteLoad() {
        return avgAbsoluteLoad;
    }

    public void setAvgAbsoluteLoad(double avgAbsoluteLoad) {
        this.avgAbsoluteLoad = avgAbsoluteLoad;
    }

    public double getMaxOutsideAirTemperature() {
        return maxOutsideAirTemperature;
    }

    public void setMaxOutsideAirTemperature(double maxOutsideAirTemperature) {
        this.maxOutsideAirTemperature = maxOutsideAirTemperature;
    }

    public double getMinOutsideAirTemperature() {
        return minOutsideAirTemperature;
    }

    public void setMinOutsideAirTemperature(double minOutsideAirTemperature) {
        this.minOutsideAirTemperature = minOutsideAirTemperature;
    }

    public double getAvgOutsideAirTemperature() {
        return avgOutsideAirTemperature;
    }

    public void setAvgOutsideAirTemperature(double avgOutsideAirTemperature) {
        this.avgOutsideAirTemperature = avgOutsideAirTemperature;
    }
}