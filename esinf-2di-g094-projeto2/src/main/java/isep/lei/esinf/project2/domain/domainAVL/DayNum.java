package isep.lei.esinf.project2.domain.domainAVL;

import isep.lei.esinf.project2.struct.AVL;

public class DayNum implements Comparable<DayNum> {

    private String daynum;

    AVL<TripID> tripAVL;

    public DayNum(String daynum, AVL<TripID> tripAVL) {
        this.daynum = daynum;
        this.tripAVL = tripAVL;
    }

    public DayNum(String daynum){
        this.daynum = daynum;
    }

    public String getDaynum() {
        return daynum;
    }

    public void setDaynum(String daynum) {
        this.daynum = daynum;
    }

    public AVL<TripID> getTripAVL() {
        return this.tripAVL;
    }

    public void setTripAVL(AVL<TripID> tripAVL) {
        this.tripAVL = tripAVL;
    }

    @Override
    public String toString() {
        return "DayNum [daynum=" + daynum + "]";
    }

    @Override
    public int compareTo(DayNum o) {
        return this.daynum.compareTo(o.daynum);
    }
}
