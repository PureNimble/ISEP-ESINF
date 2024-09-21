package esinf;

public class Charger implements Comparable<Charger>{

    private String supercharger;

    private String streetAddress;

    private String city;

    private String state;

    private String zip;

    private String country;

    private int stalls;

    private int kW;

    private String gps;

    private int elevm;

    private String status;

    /**
     * @param supercharger
     * @param streetAddress
     * @param city
     * @param state
     * @param zip
     * @param country
     * @param stalls
     * @param kW
     * @param gps
     * @param elevm
     * @param status
     */
    public Charger(String supercharger, String streetAddress, String city, String state, String zip, String country,
                   int stalls, int kW, String gps, int elevm, String status) {
        this.supercharger = supercharger;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.stalls = stalls;
        this.kW = kW;
        this.gps = gps;
        this.elevm = elevm;
        this.status = status;
    }

    /**
     * @return the supercharger
     */
    public String getSupercharger() {
        return supercharger;
    }

    /**
     * @param supercharger the supercharger to set
     */
    public void setSupercharger(String supercharger) {
        this.supercharger = supercharger;
    }

    /**
     * @return the streetAddress
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * @param streetAddress the streetAddress to set
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     *
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return the stalls
     */
    public int getStalls() {
        return stalls;
    }

    /**
     *
     * @param stalls the stalls to set
     */
    public void setStalls(int stalls) {
        this.stalls = stalls;
    }

    /**
     *
     * @return the kW
     */
    public int getkW() {
        return kW;
    }

    /**
     *
     * @param kW the kW to set
     */
    public void setkW(int kW) {
        this.kW = kW;
    }

    /**
     *
     * @return the gps
     */
    public String getGps() {
        return gps;
    }

    /**
     *
     * @param gps the gps to set
     */
    public void setGps(String gps) {
        this.gps = gps;
    }

    /**
     *
     * @return the elevm
     */
    public int getElevm() {
        return elevm;
    }

    /**
     *
     * @param elevm the elevm to set
     */
    public void setElevm(int elevm) {
        this.elevm = elevm;
    }

    /**
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        Charger other = (Charger) obj;
        return this.country.equals(other.country);
    }

    @Override
    public int hashCode() {
        return country.hashCode();
    }

    @Override
    public int compareTo(Charger o) {
        return this.country.compareTo(o.country);
    }

}
