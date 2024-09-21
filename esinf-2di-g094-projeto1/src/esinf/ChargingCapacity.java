package esinf;

import java.util.List;
import java.util.Set;

public class ChargingCapacity {
    private List<String> states;
    private Set<String> cities; 
    private int totalCapacity;
    
    ChargingCapacity(List<String> states, Set<String> cities, int totalCapacity){
        this.states = states;
        this.cities = cities;
        this.totalCapacity = totalCapacity;
    }

    public List<String> getStates(){
        return this.states;
    }
    public Set<String> getCities(){
        return this.cities;
    }
    public int getTotalCapacity(){
        return this.totalCapacity;
    }

}
