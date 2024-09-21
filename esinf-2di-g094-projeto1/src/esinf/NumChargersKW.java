package esinf;

public class NumChargersKW {
    private int totalBelowKW;
    private int totalAboveKW;
    private int total;

    /**
     * @param totalBelowKW
     * @param totalAboveKW
     */
    public NumChargersKW(int totalBelowKW, int totalAboveKW){
        this.totalBelowKW = totalBelowKW;
        this.totalAboveKW = totalAboveKW;
        this.total = totalAboveKW + totalBelowKW;
    }

    /**
     * @return the totalAboveKW
     */
    public int getTotalAboveKW() {
        return totalAboveKW;
    }

    /**
     * @return the totalBelowKW
     */
    public int getTotalBelowKW() {
        return totalBelowKW;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @param totalAboveKW the totalAboveKW to set
     */
    public void setTotalAboveKW(int totalAboveKW) {
        this.totalAboveKW = totalAboveKW;
    }

    /**
     * @param totalBelowKW the totalBelowKW to set
     */
    public void setTotalBelowKW(int totalBelowKW) {
        this.totalBelowKW = totalBelowKW;
    }
}
