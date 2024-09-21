package isep.lei.esinf.project2.domain.domainKDTree;

import isep.lei.esinf.project2.domain.domainAVL.TimeStamp;
import isep.lei.esinf.project2.struct.KDTree.KDTree;

public class TripKD implements Comparable<TripKD> {
    private String tripID;

    private KDTree<TimeStamp> timeStampKDTree;

    public TripKD(String tripID, KDTree<TimeStamp> timeStampKDTree) {
        this.tripID = tripID;
        this.timeStampKDTree = timeStampKDTree;
    }

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public KDTree<TimeStamp> getTimeStampKDTree() {
        return timeStampKDTree;
    }

    public void setTimeStampKDTree(KDTree<TimeStamp> timeStampKDTree) {
        this.timeStampKDTree = timeStampKDTree;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tripID == null) ? 0 : tripID.hashCode());
        result = prime * result + ((timeStampKDTree == null) ? 0 : timeStampKDTree.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TripKD other = (TripKD) obj;
        if (tripID == null) {
            if (other.tripID != null)
                return false;
        } else if (!tripID.equals(other.tripID))
            return false;
        if (timeStampKDTree == null) {
            if (other.timeStampKDTree != null)
                return false;
        } else if (!timeStampKDTree.equals(other.timeStampKDTree))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "tripid: {" +
                "id='" + tripID + '}';
    }

    @Override
    public int compareTo(TripKD o) {
        return this.tripID.compareTo(o.tripID);
    }

}