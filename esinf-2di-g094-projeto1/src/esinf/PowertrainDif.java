package esinf;

public class PowertrainDif {

    private int differenceFromLastYearPhev;
    private int differenceFromLastYearBev;
    private int year;
    public static final int DEFAULT_VALUE = 999999999;

    /**
     * @param year
     * @param differenceFromLastYearPhev
     * @param differenceFromLastYearBev
     */
    PowertrainDif(int year){
        this.year = year;
        this.differenceFromLastYearPhev = DEFAULT_VALUE;
        this.differenceFromLastYearBev  = DEFAULT_VALUE;
    }

    /**
     * @return the differenceFromLastYearPhev
     */
    public int getDifferenceFromLastYearPhev() {
        return differenceFromLastYearPhev;
    }

    /**
     * @param differenceFromLastYearPhev the differenceFromLastYearPhev to set
     */
    public void setDifferenceFromLastYearPhev(int differenceFromLastYearPhev) {
        this.differenceFromLastYearPhev = differenceFromLastYearPhev;
    }

    /**
     * @return the differenceFromLastYearBev
     */
    public int getDifferenceFromLastYearBev() {
        return differenceFromLastYearBev;
    }

    /**
     * @param differenceFromLastYearBev the differenceFromLastYearBev to set
     */
    public void setDifferenceFromLastYearBev(int differenceFromLastYearBev) {
        this.differenceFromLastYearBev = differenceFromLastYearBev;
    }


    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

}
