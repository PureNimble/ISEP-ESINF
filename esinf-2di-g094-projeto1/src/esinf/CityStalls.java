package esinf;

public class CityStalls implements Comparable<CityStalls>{

    private String city;

    private int stalls;

    /**
     * @param city
     * @param stalls
     */
    public CityStalls(String city, int stalls) {
        this.city = city;
        this.stalls = stalls;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the stalls
     */
    public int getStalls() {
        return stalls;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @param stalls the stalls to set
     */
    public void setStalls(int stalls) {
        this.stalls = stalls;
    }


    @Override
    public boolean equals(Object obj) {
        CityStalls other = (CityStalls) obj;
        return this.city.equals(other.city);
    }

    @Override
    public int hashCode() {
        return city.hashCode();
    }

    @Override
    public int compareTo(CityStalls o) {
        return this.city.compareTo(o.city);
    }

}
