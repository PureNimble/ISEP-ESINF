package esinf;

import java.util.*;
import java.util.stream.Collectors;


public class Distribution {

    public static Map<String, List<Charger>> chargersMap = new HashMap<>();
    public static Map<String, List<EvSales>> evSalesMap = new HashMap<>();
    private static final double R = 6371e3;

    /**
     * Builds the chargers map
     * @param chargerList
     * @throws Exception
     */
    public void buildCharger(List<String> chargerList) throws Exception {
        for(String charger : chargerList) {
            if(charger.contains("Supercharger")) {
                continue;
            }
            String[] chargerArray = charger.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            for(int i = 0; i < chargerArray.length; i++) {
                chargerArray[i] = chargerArray[i].replaceAll("\"", "");
            }
            Charger c = new Charger(chargerArray[0], chargerArray[1], chargerArray[2], chargerArray[3], chargerArray[4],
                    chargerArray[5], Integer.parseInt(chargerArray[6]), Integer.parseInt(chargerArray[7]),
                    chargerArray[8], Integer.parseInt(chargerArray[9]), chargerArray[10]);
            if(chargersMap.containsKey(chargerArray[5])) {
                chargersMap.get(chargerArray[5]).add(c);
            } else {
                List<Charger> chargerList1 = new ArrayList<>();
                chargerList1.add(c);
                chargersMap.put(chargerArray[5], chargerList1);
            }
        }
    }


    /**
     * Returns a map containing the list of chargers for each country.
     *
     * @return a map containing the list of chargers for each country
     */
    public Map<String, List<Charger>> getCharger(){
        return chargersMap;
    }

    /**
     * Builds the evSales map
     * @param evSalesList
     * @throws Exception
     */
    public void buildEvSales(List<String> evSalesList) throws Exception{
        for (String evSales : evSalesList) {
            if(evSales.contains("country") || evSales.isBlank()){
                continue;
            }
            String[] evSalesArray = evSales.split(",");
            EvSales e = new EvSales(evSalesArray[0], evSalesArray[1], Integer.parseInt(evSalesArray[2]),
                    Integer.parseInt(evSalesArray[3]));
            if(evSalesMap.containsKey(evSalesArray[0])) {
                evSalesMap.get(evSalesArray[0]).add(e);
            } else {
                List<EvSales> evSalesList1 = new ArrayList<>();
                evSalesList1.add(e);
                evSalesMap.put(evSalesArray[0], evSalesList1);
            }
        }
    }

    /**
     * Returns a map with the number of sales per country.
     *
     * @return a map with the number of sales per country
     */
    public Map<String, List<EvSales>> getEvSales(){
        return evSalesMap;
    }


    /**
     *
     * @return a map with the number of stalls per city for each country
     */
    public Map<String, Set<CityStalls>> getCityStalls() {
        Map<String, Set<CityStalls>> cityStallsMap = new HashMap<>();

        for (Map.Entry<String, List<Charger>> entry : chargersMap.entrySet()) {
            String country = entry.getKey();
            List<Charger> chargers = entry.getValue();

            Map<String, Integer> cityStalls = chargers.stream().collect(Collectors.toMap(
                            Charger::getCity, // Group by city name
                            Charger::getStalls, // Get stalls count
                            Integer::sum // Sum the stalls if city name is the same
            ));

            Set<CityStalls> cityStallsSet = cityStalls.entrySet().stream()
                    .map(entry1 -> new CityStalls(entry1.getKey(), entry1.getValue()))
                    .collect(Collectors.toSet());

            cityStallsMap.put(country, cityStallsSet);
        }

        return cityStallsMap;
    }


    /**
     *
     * @param yearInf
     * @param yearSup
     * @return a map with the evolution of the number of electric vehicles sold per country comparing two years
     */
    public Map<String, Double> getEvolutionByCountry(int yearInf, int yearSup) {
        int maxYear = evSalesMap.values().stream().flatMap(Collection::stream).mapToInt(EvSales::getYear).max()
                .getAsInt();
        int minYear = evSalesMap.values().stream().flatMap(Collection::stream).mapToInt(EvSales::getYear).min()
                .getAsInt();
        verifyYears(yearInf, yearSup,maxYear, minYear);
        Map<String, Double> evolutionByCountry = new HashMap<>();
        for (Map.Entry<String, List<EvSales>> entry : evSalesMap.entrySet()){
            double taxa = 0;
            int totalInf = 0;
            int totalSup = 0;
            String country = entry.getKey();
            List<EvSales> evSales = entry.getValue();

            totalInf = evSales.stream().filter(evSales1 -> evSales1.getYear() == yearInf)
                    .mapToInt(EvSales::getNumber_of_vehicles).sum();

            totalSup = evSales.stream().filter(evSales1 -> evSales1.getYear() == yearSup)
                    .mapToInt(EvSales::getNumber_of_vehicles).sum();

            if(totalInf != 0) {
                taxa = (double) (totalSup - totalInf) / totalInf;
                taxa = Math.round(taxa * 100.0) / 100.0;
            }

            evolutionByCountry.put(country, taxa);
        }

        return evolutionByCountry;
    }

    /**Exercício 3
     * Finds all countries that had years with no increase compared to the previous year
     * and returns a map with the data from those countries.
     * 
     * @return a map with the data from all countries that had years with no increase
     */
    public Map<String, List<PowertrainDif>> getCountriesWithNoIncrease() {
        Map<String, List<PowertrainDif>> countriesWithNoIncrease = new HashMap<>();
        evSalesMap.entrySet().stream().forEach( (country) ->{
            String countryName =country.getKey();
            List<EvSales> evSales = country.getValue();

            //Get the total number of vehicles sold per year
            Map<Integer,Integer> totalVehicle = evSales.stream().collect(Collectors.groupingBy(EvSales::getYear,
                Collectors.summingInt(EvSales::getNumber_of_vehicles)));
            //Sort the map by year
            Map<Integer,Integer> totalVehicleCopy = new HashMap<>(totalVehicle);           
            //Get the years with no increase
            Map<Integer,Integer> yearsWithNoIncrease = new HashMap<>();
            totalVehicle.entrySet().stream().forEach((year) ->{
                if(totalVehicleCopy.containsKey(year.getKey() - 1))
                    if(year.getValue() < totalVehicleCopy.get(year.getKey() - 1))
                        yearsWithNoIncrease.put(year.getKey(), year.getValue());

            });
            totalVehicleCopy.clear();
            //Get the difference from the previous year
            List<PowertrainDif> result = new ArrayList<>();

            for (Map.Entry<Integer, Integer> entry : yearsWithNoIncrease.entrySet()) {
                int year = entry.getKey();
                for (EvSales evSale : evSales) {
                    if (evSale.getYear() == year) {
                        for (EvSales evSale1 : evSales) {
                            int nextYear = evSale1.getYear();
                            if (nextYear == year - 1 && evSale.getPowertrain().equals(evSale1.getPowertrain())) {
                                PowertrainDif powertrainDif = new PowertrainDif(year);
                                int difference = evSale.getNumber_of_vehicles() - evSale1.getNumber_of_vehicles();
                                if (evSale.getPowertrain().equals("PHEV")) {
                                    powertrainDif.setDifferenceFromLastYearPhev(difference);
                                } else {
                                    powertrainDif.setDifferenceFromLastYearBev(difference);
                                }
                                result.add(powertrainDif);
                            }
                        }
                    }
                }
            }
            //Sort the list by year and link all the data (removing duplicates)
            result.stream().sorted(Comparator.comparing(PowertrainDif::getYear))
                    .reduce((prev, curr) -> {
                        if (prev.getYear() == curr.getYear()) {
                            if (curr.getDifferenceFromLastYearBev() == PowertrainDif.DEFAULT_VALUE) 
                                curr.setDifferenceFromLastYearBev(prev.getDifferenceFromLastYearBev());
                            if (curr.getDifferenceFromLastYearPhev() == PowertrainDif.DEFAULT_VALUE) 
                                curr.setDifferenceFromLastYearPhev(prev.getDifferenceFromLastYearPhev());

                            result.remove(prev);
                        }
                        return curr;
                    });
            //Fill the missing data
            result.stream().forEach((i) -> {
                //if last year exists -> difference = -last year
                if (i.getDifferenceFromLastYearBev() == PowertrainDif.DEFAULT_VALUE) {
                    evSales.stream().forEach((evSale) -> {
                        if( evSale.getYear() == i.getYear()- 1 && evSale.getPowertrain().equalsIgnoreCase("BEV")){
                            i.setDifferenceFromLastYearBev(-evSale.getNumber_of_vehicles());
                        }
                    });
                }
                //if last year exists -> difference = -last year
                if (i.getDifferenceFromLastYearPhev() == PowertrainDif.DEFAULT_VALUE) {
                    evSales.stream().forEach((evSale) -> {
                        if( evSale.getYear() == i.getYear() - 1 && evSale.getPowertrain().equalsIgnoreCase("PHEV")){
                            i.setDifferenceFromLastYearPhev(-evSale.getNumber_of_vehicles());
                        }
                    
                    });
                }
            });
            //if last year doesn't exist -> difference = 0
            result.stream().forEach((i) -> {
                if (i.getDifferenceFromLastYearBev() == PowertrainDif.DEFAULT_VALUE) {
                    i.setDifferenceFromLastYearBev(0);
                }
                if (i.getDifferenceFromLastYearPhev() == PowertrainDif.DEFAULT_VALUE) {
                    i.setDifferenceFromLastYearPhev(0);
                }
            });
            

            countriesWithNoIncrease.computeIfAbsent(countryName, k -> new ArrayList<>()).addAll(result);

        });
        //Remove countries with no data
        countriesWithNoIncrease.values().removeIf(List::isEmpty);

        return countriesWithNoIncrease;
    }

    /**
     * Returns a map with the number of chargers below and above a given kW value for each country.
     *
     * @param kwValue the kW value to compare the chargers with
     * @return a map with the number of chargers below and above the given kW value for each country
     */
    public SortedSet<Map.Entry<String, NumChargersKW>> getNumChargersKW(int kwValue) {
        Map<String, NumChargersKW> numChargersMap = new HashMap<>();

        for (Map.Entry<String, List<Charger>> entry : chargersMap.entrySet()){
            String country = entry.getKey();
            List<Charger> chargers = entry.getValue();
            int totalBelowKW = 0;
            int totalAboveKW= 0;

            totalBelowKW = (int)chargers.stream().filter(chargers1 -> chargers1.getkW() <= kwValue)
                    .count();

            totalAboveKW = (int) chargers.stream().filter(chargers1 -> chargers1.getkW() > kwValue)
                    .count();

            NumChargersKW numChargersKW = new NumChargersKW(totalBelowKW, totalAboveKW);

            numChargersMap.put(country,numChargersKW);

        }
        return sortNumChargerMap(numChargersMap);
    }

    private SortedSet<Map.Entry<String, NumChargersKW>> sortNumChargerMap(Map<String, NumChargersKW> map){
        SortedSet<Map.Entry<String, NumChargersKW>> sortedEntries = new TreeSet<Map.Entry<String,NumChargersKW>>(
                new Comparator<Map.Entry<String,NumChargersKW>>() {
                    @Override public int compare(Map.Entry<String,NumChargersKW> e1, Map.Entry<String,NumChargersKW> e2) {
                        if (e1.getValue().getTotal() > e2.getValue().getTotal()){
                            return -1;
                        } else if (e1.getValue().getTotal() < e2.getValue().getTotal()){
                            return 1;
                        } else {
                            return (e1.getKey().compareTo(e2.getKey()));
                        }
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    /**
     *
     * @param year
     * @param ratio
     * @param stalls
     * @return a map with the quotas for each country
     */
    public Map<String, ReportQuotas> getReportQuotas(int year, int ratio, int stalls) {
        Map<String, ReportQuotas> reportQuotasMap = new HashMap<>();

        if(evSalesMap.values().stream().flatMap(Collection::stream).noneMatch(evSales -> evSales.getYear() == year)) {
            throw new IllegalArgumentException("The year does not exist in the map");
        }

        for (Map.Entry<String, List<EvSales>> entry : evSalesMap.entrySet()) {
            String country = entry.getKey();
            List<EvSales> evSales = entry.getValue();

            int totalVehicles = evSales.stream()
                    .filter(evSales1 -> evSales1.getYear() == year)
                    .mapToInt(EvSales::getNumber_of_vehicles)
                    .sum();

            if(chargersMap.get(country) == null) {
                continue;
            }

            int totalStalls = chargersMap.get(country).stream()
                    .mapToInt(Charger::getStalls)
                    .sum();

            if(totalVehicles == 0 || totalStalls == 0 || ratio == 0 || stalls <= 0) {
                continue;
            }

            double totalQuotas = ((double) totalStalls / totalVehicles) * ((double) ratio / stalls) * 100;

            ReportQuotas reportQuotas = new ReportQuotas(totalStalls, totalVehicles, (Math.round(totalQuotas*1000)/1000));

            reportQuotasMap.put(country, reportQuotas);
        }
        return reportQuotasMap;
    }

    /**
     *
     * @return a map with the minimal autonomy for each country
     */
    public Map<String, Double> getMinimalAutonomy() {
        
        Map<String, Double> minimalAutonomyMap = new HashMap<>();

        for (Map.Entry<String, List<Charger>> entry : chargersMap.entrySet()) {

            double minimalAutonomy = 0;
            String country = entry.getKey();
            List<Charger> chargers = entry.getValue();

            if (chargers.size() < 2) {
                continue;
            }

            for (Charger charger : chargers) {

                Double minDistanceForEachPair = Double.MAX_VALUE;
                double lat1 = Double.parseDouble(charger.getGps().split(",")[0]);
                double lon1 = Double.parseDouble(charger.getGps().split(",")[1]);

                if (verifyCoordinates(lat1, lon1)) {
                    continue;
                }

                for (Charger charger1 : chargers) {

                    if (charger == charger1) {
                        continue;
                    }

                    double lat2 = Double.parseDouble(charger1.getGps().split(",")[0]);
                    double lon2 = Double.parseDouble(charger1.getGps().split(",")[1]);

                    if (verifyCoordinates(lat2, lon2) || verifyEqualCoordinates(lat1, lon1, lat2, lon2)) {
                        continue;
                    }

                    double distance = haversineFormula(lat1, lon1, lat2, lon2);

                    if(distance < minDistanceForEachPair) {
                        minDistanceForEachPair = distance;
                    }
                }
                if (minimalAutonomy < minDistanceForEachPair) {
                    minimalAutonomy = minDistanceForEachPair;
                }

            }
            if (minimalAutonomy > 0 && minimalAutonomy < Double.MAX_VALUE) {
                String minimalAutonomyString = String.format("%.1f", minimalAutonomy / 1000).replace(',', '.');
                minimalAutonomyMap.put(country, Double.parseDouble(minimalAutonomyString));
            }
        }
    
        Map<String, Double> sortedMinimalAutonomyMap = minimalAutonomyMap.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder())
            .thenComparing(Map.Entry.comparingByKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
                        
        return sortedMinimalAutonomyMap;
    }

    /**
     *
     * @param pois
     * @return a map with the clusters that are closest to the points of interest
     */
    public Map<String, List<String>> getClusters(ArrayList<String> pois) {

        if (pois.isEmpty()) throw new IllegalArgumentException("The list of coordinates must not be empty");

        verifyPointsOfInterest(pois);

        Map<String, List<String>> clusters = new HashMap<>();
    
        for (Map.Entry<String, List<Charger>> entry : chargersMap.entrySet()) {
    
            List<Charger> chargers = entry.getValue();
    
            for (Charger charger : chargers) {
    
                double lat1 = Double.parseDouble(charger.getGps().split(",")[0]);
                double lon1 = Double.parseDouble(charger.getGps().split(",")[1]);

                if (verifyCoordinates(lat1, lon1)) {
                    continue;
                }

                int i;
                int poi = 0;
                double minDistance = Double.MAX_VALUE;
                String supercharger = "";
    
                for (i = 0; i < pois.size(); i++) {
    
                    double lat2 = Double.parseDouble(pois.get(i).split(",")[0]);
                    double lon2 = Double.parseDouble(pois.get(i).split(",")[1]);

                    if (verifyCoordinates(lat2, lon2)) {
                        throw new IllegalArgumentException("The coordinates must be between -90 and 90 for latitude and -180 and 180 for longitude");
                    }

                    double distance = haversineFormula(lat1, lon1, lat2, lon2);
    
                    if (distance < minDistance) {
                        minDistance = distance;
                        poi = i;
                        supercharger = charger.getSupercharger();
                    }
                }
                String poiKey = "POI" + (poi + 1);
                if (clusters.containsKey(poiKey)) {
                    clusters.get(poiKey).add(supercharger);
                } else {
                    List<String> poiChargers = new ArrayList<>();
                    poiChargers.add(supercharger);
                    clusters.put(poiKey, poiChargers);
                }
            }
        }
        List<Map.Entry<String, List<String>>> list = new ArrayList<>(clusters.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, List<String>>>() {
            public int compare(Map.Entry<String, List<String>> o1, Map.Entry<String, List<String>> o2) {
                int sizeCompare = Integer.compare(o2.getValue().size(), o1.getValue().size());
                if (sizeCompare != 0) {
                    return sizeCompare;
                } else {
                    return o1.getKey().compareTo(o2.getKey());
                }
            }
        });
        Map<String, List<String>> sortedClusters = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> entry : list) {
            sortedClusters.put(entry.getKey(), entry.getValue());
        }
        return sortedClusters;
    }

    /**
     *
     * @param yearInf
     * @param yearSup
     */
    private void verifyYears(int yearInf, int yearSup, int maxYear, int minYear) {
        if(yearInf > yearSup) {
            throw new IllegalArgumentException("The yearInf must be lower than yearSup");
        }
        if(yearInf == yearSup) {
            throw new IllegalArgumentException("The yearInf must be different from yearSup");
        }
        if (yearInf < 0 || yearSup <0){
            throw new IllegalArgumentException("The years must be positive");
        }
        if (yearInf < minYear || yearSup > maxYear){
            throw new IllegalArgumentException("The years must be between " + minYear + " and " + maxYear);
        }
    }

    /**
     * Verifies if two sets of coordinates are equal.
     * @param lat1 the latitude of the first coordinate
     * @param lon1 the longitude of the first coordinate
     * @param lat2 the latitude of the second coordinate
     * @param lon2 the longitude of the second coordinate
     * @return true if the coordinates are equal, false otherwise
     */
    private boolean verifyEqualCoordinates(double lat1, double lon1, double lat2, double lon2) {

        if (lat1 == lat2 && lon1 == lon2) {
            return true;
        }
        
        return false;
    }

    /**
     *
     * @param pois
     */
    private void verifyPointsOfInterest(ArrayList<String> pois) {

        Set<String> seen = new HashSet<>();
        for (String poi : pois) {
            if (seen.contains(poi)) {
                throw new IllegalArgumentException("The points of interest must be different");
            }
            seen.add(poi);
        }
    }

    /**
     *
     * @param lat
     * @param lon
     */
    private boolean verifyCoordinates(double lat, double lon) {
        
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return distance from two cordinates
     */
    private double haversineFormula(double lat1, double lon1, double lat2, double lon2) {

        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double deltaPhi = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);

        double a = Math.sin(deltaPhi/2) * Math.sin(deltaPhi/2) +
            Math.cos(phi1) * Math.cos(phi2) *
            Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double distance = R * c;

        return distance;
    }

    /** Exercício 8
     * Returns the top N charging capacity of a list of countries or states.
     * @param n the number of top states to return
     * @param list the list of countries or states to consider
     * @return a ChargingCapacity object containing the top N states, their cities, and the total charging capacity
     * @throws IllegalArgumentException if n is less than or equal to 0 or if the list is empty
     */
    public ChargingCapacity getCountryTopNCharger(int n, List<String> list) {
        // verify if the n is greater than 0
        if (n <= 0) throw new IllegalArgumentException("The number of states must be greater than 0");
        // verify if the list is empty
        if (list.isEmpty()) throw new IllegalArgumentException("The list of countries must not be empty");
        verifyCountriesAndStates(list);
        List<Charger> cities = new ArrayList<>();

        //add cities to list
        list.stream().forEach((i) -> {
            // if the list contains a country
            if (chargersMap.containsKey(i)) 
                cities.addAll(chargersMap.get(i));
            // if the list contains a state
            else{
                chargersMap.entrySet().stream().forEach((country) -> {
                    country.getValue().stream().forEach((city) -> {
                        if(city.getState().equalsIgnoreCase(i))
                            cities.add(city);
                    });
                });
            }
        });
        list.clear();

        // sum of stalls per city
        Map<String, Integer> stallsMap = cities.stream().filter((city) -> city.getStatus().equalsIgnoreCase(
            "Open"))
            .collect(Collectors.groupingBy(Charger::getCity,
                Collectors.summingInt(Charger::getStalls)));
        // sum of kW per city
        Map<String, Integer> kwMap = cities.stream().filter((city) -> city.getStatus().equalsIgnoreCase("Open"))
                .collect(Collectors.groupingBy(Charger::getCity,
                        Collectors.summingInt(Charger::getkW)));

        // multiples the kwMap with the stallsMap
        Map<String, Integer> capacityMap = stallsMap.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue() * kwMap.getOrDefault(entry.getKey(), 0)));

        //clear maps
        stallsMap.clear();
        kwMap.clear();

        // group cities by state
        Map<String, Set<String>> states = cities.stream()
                .filter(city -> city.getStatus().equalsIgnoreCase("Open"))
                .collect(Collectors.groupingBy(
                        Charger::getState,
                        Collectors.mapping(Charger::getCity, Collectors.toSet())));
        // sum capacity by state
        Map<String, Integer> statesValues = states.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                        .filter(capacityMap::containsKey)
                        .mapToInt(capacityMap::get)
                        .sum()));
        //clear map
        capacityMap.clear();
        // sort states by capacity and get top n
        List<String> statesList = statesValues.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        // get cities that match the statesList
        Set<String> citiesList = statesList.stream()
                .filter(states::containsKey) // Filter states that exist in the 'states' map
                .flatMap(state -> states.get(state).stream()) // Flatten the lists of cities
                .collect(Collectors.toSet()); // Collect the cities into a list
        
        // sum the capacity of the statesList
        int totalCapacity = statesList.stream()
                                    .filter(statesValues::containsKey) // Filter states that exist in 'statesValues'
                                    .mapToInt(statesValues::get) // Map to the values in 'statesValues'
                                    .sum(); // Sum the values
        // make the final
        ChargingCapacity chargingCapacity = new ChargingCapacity(statesList, citiesList, totalCapacity);

        return chargingCapacity;
    }

    /**Exercício 8
     * Verifies if the list of places contains only countries or states, or a mix of both.
     * Throws an IllegalArgumentException if the list contains a place that is neither a country nor a state,
     * or if the list contains both countries and states.
     * @param list the list of places to verify
     * @throws IllegalArgumentException if the list contains a place that is neither a country nor a state,
     * or if the list contains both countries and states.
     */
    private void verifyCountriesAndStates(List<String> list) {
        boolean hasCountry = false;
        boolean hasState = false;
        for (String place : list) {
            // Check if the place is a country
            if (chargersMap.containsKey(place)) {
                hasCountry = true;
            // Check if the place is a state
            } else if (chargersMap.entrySet().stream()
                    .anyMatch(entry -> entry.getValue().stream().anyMatch(city -> city.getState().equals(place)))) {
                hasState = true;
            } else {
                throw new IllegalArgumentException("The country or state does not exist");
            }
        }
        if (hasCountry && hasState) {
            // Allow for a mix of countries and states
            // Do not throw an exception
        } else if ((hasState && !hasCountry) || (!hasState && hasCountry)) {
            // Allow for a list of states or a list of countries
        } else {
            throw new IllegalArgumentException("The list must contain only countries or states");
        }
    }
}