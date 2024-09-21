package esinf;

public class ReportQuotas {

    int stallsTotal;

    int eletricVehiclesTotal;

    double quota;


    /**
     * @param stallsTotal
     * @param eletricVehiclesTotal
     * @param quota
     */
    public ReportQuotas(int stallsTotal, int eletricVehiclesTotal, double quota) {
        this.stallsTotal = stallsTotal;
        this.eletricVehiclesTotal = eletricVehiclesTotal;
        this.quota = quota;
    }

    /**
     * @return the stallsTotal
     */
    public int getStallsTotal() {
        return stallsTotal;
    }

    /**
     * @return the eletricVehiclesTotal
     */
    public int getEletricVehiclesTotal() {
        return eletricVehiclesTotal;
    }

    /**
     * @return the quota
     */
    public double getQuota() {
        return quota;
    }
}
