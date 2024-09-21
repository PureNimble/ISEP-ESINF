package esinf;

public class EvSales implements Comparable<EvSales>{

    private String country;

    private String powertrain;

    private int year;

    private int number_of_vehicles;

    /**
     * @param country
     * @param powertrain
     * @param year
     * @param numberOfVehicles
     */
    public EvSales(String country, String powertrain, int year, int numberOfVehicles) {
        this.country = country;
        this.powertrain = powertrain;
        this.year = year;
        this.number_of_vehicles = numberOfVehicles;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return the powertrain
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the year
     */
    public String getPowertrain() {
        return powertrain;
    }

    /**
     * @return the numberOfVehicles
     */
    public void setPowertrain(String powertrain) {
        this.powertrain = powertrain;
    }

    /**
     * @return the numberOfVehicles
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the numberOfVehicles
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the numberOfVehicles
     */
    public int getNumber_of_vehicles() {
        return number_of_vehicles;
    }

    /**
     * @return the numberOfVehicles
     */
    public void setNumber_of_vehicles(int number_of_vehicles) {
        this.number_of_vehicles = number_of_vehicles;
    }

    @Override
    public boolean equals(Object obj) {
        EvSales other = (EvSales) obj;
        return this.country.equals(other.country);
    }

    @Override
    public int hashCode() {
       return country.hashCode();
    }

    @Override
    public int compareTo(EvSales o) {
        return this.country.compareTo(o.country);
    }
}
