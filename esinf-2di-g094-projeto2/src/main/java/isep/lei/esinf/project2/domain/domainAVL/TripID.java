package isep.lei.esinf.project2.domain.domainAVL;

import isep.lei.esinf.project2.struct.AVL;

import java.util.Objects;

public class TripID implements Comparable<TripID> {
    private String tripID;

    private AVL<TimeStamp> timeStampAVL;

    public TripID(String tripID, AVL<TimeStamp> timeStampAVL) {
        this.tripID = tripID;
        this.timeStampAVL = timeStampAVL;
    }

    public TripID(String tripID) {
        this.tripID = tripID;
    }

    public String getId() {
        return tripID;
    }

    public void setId(String id) {
        this.tripID = id;
    }

    public AVL<TimeStamp> getTimeStampAVL() {
        return this.timeStampAVL;
    }

    public void setTimeStampAVL(AVL<TimeStamp> timeStampAVL) {
        this.timeStampAVL = timeStampAVL;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tripID);
    }

    @Override
    public String toString() {
        return "tripid: {" +
                "id='" + tripID + '}';
    }

    @Override
    public int compareTo(TripID o) {
        return this.tripID.compareTo(o.tripID);
    }
}
