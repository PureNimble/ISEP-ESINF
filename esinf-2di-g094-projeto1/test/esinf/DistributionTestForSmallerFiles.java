package esinf;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class DistributionTestForSmallerFiles {

    private static Distribution distribution1 = new Distribution();
    private static boolean initialized = false;

    static {
        if (!initialized) {
            try {
                URL chargerUrl = DistributionTestForSmallerFiles.class.getResource("/smaller_carregadores_europa.csv");
                List<String> chargerList = Files.readAllLines(Paths.get(chargerUrl.toURI()));
                distribution1.buildCharger(chargerList);
                URL evSalesUrl = DistributionTestForSmallerFiles.class.getResource("/smaller_ev_sales.csv");
                List<String> evSalesList = Files.readAllLines(Paths.get(evSalesUrl.toURI()));
                distribution1.buildEvSales(evSalesList);
                initialized = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Smaller Test of getCityStalls method, of class Distribution.
     */
    @Test
    public void smallerTestNumberOfStallsByCity() {
        //check if the values exist in the map
        System.out.println("numberOfStallsByCity");
        Map<String, Set<CityStalls>> map = distribution1.getCityStalls();
        Set <CityStalls> r = map.get("Portugal");

        r.stream().filter((c) -> (c.getCity().equals("F�tima"))).forEachOrdered((c) -> {
            assertEquals(14, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Montemor-o-Novo"))).forEachOrdered((c) -> {
            assertEquals(8, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Matosinhos"))).forEachOrdered((c) -> {
            assertEquals(12, c.getStalls());
        });
        r.clear();

        r = map.get("United Kingdom");
        r.stream().filter((c) -> (c.getCity().equals("London"))).forEachOrdered((c) -> {
            assertEquals(17, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Rugby"))).forEachOrdered((c) -> {
            assertEquals(12, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("London Rd"))).forEachOrdered((c) -> {
            assertEquals(8, c.getStalls());
        });
        r.clear();

        r = map.get("Netherlands");
        r.stream().filter((c) -> (c.getCity().equals("Arnhem"))).forEachOrdered((c) -> {
            assertEquals(20, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Amsterdam"))).forEachOrdered((c) -> {
            assertEquals(24, c.getStalls());
        });
        r.clear();

        r = map.get("Luxembourg");
        r.stream().filter((c) -> (c.getCity().equals("M�nsbech"))).forEachOrdered((c) -> {
            assertEquals(4, c.getStalls());
        });
        r.clear();

        r = map.get("Poland");
        r.stream().filter((c) -> (c.getCity().equals("Warsaw"))).forEachOrdered((c) -> {
            assertEquals(2, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Radom"))).forEachOrdered((c) -> {
            assertEquals(4, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Krak�w"))).forEachOrdered((c) -> {
            assertEquals(4, c.getStalls());
        });
        r.clear();
        
        r = map.get("Hungary");
        r.stream().filter((c) -> (c.getCity().equals("Szigetszentmikl�s"))).forEachOrdered((c) -> {
            assertEquals(20, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Debrecen"))).forEachOrdered((c) -> {
            assertEquals(8, c.getStalls());
        });
        r.clear();
    }


    /**
     * Smaller Test of getCityStalls method, of class Distribution.
     */
    @Test
    public void smallerTestNumberOfStallsByCityIfRepeated() {
        //check if the repeated cities are in the map with the right number of summed stalls
        System.out.println("numberOfStallsByCity");
        Map<String, Set<CityStalls>> map = distribution1.getCityStalls();
        Set <CityStalls> r = map.get("Netherlands");
        r.stream().filter((c) -> (c.getCity().equals("Amsterdam"))).forEachOrdered((c) -> {
            assertEquals(24, c.getStalls());
        });
        r.clear();
    }

    /**
     * Smaller Test of getEvolutionByCountry method, of class Distribution.
     */
    @Test
    public void smallerTestPositiveEvoByCountry(){
        //check if the positive values are being calculated correctly
        System.out.println("evoByCountry");
        Map<String, Double> map = distribution1.getEvolutionByCountry(2012, 2013);

        Double r = map.get("Portugal");
        assertEquals(0.16, r, 0.0);

        r = map.get("Poland");
        assertEquals(0.77, r, 0.0);
    }

    /**
     * Smaller Test of getEvolutionByCountry method, of class Distribution.
     */
    @Test
    public void smallerTestNegativeEvoByCountry(){
        //check if the negative values are being calculated correctly
        System.out.println("negativeEvoByCountry");
        Map<String, Double> map = distribution1.getEvolutionByCountry(2012, 2015);

        Double r = map.get("Portugal");
        assertEquals(-0.94, r, 0.0);

        r = map.get("Poland");
        assertEquals(-0.05, r, 0.0);
    }

    /**
     * Smaller Test of getEvolutionByCountry method, of class Distribution.
     */
    @Test
    public void smallerTestZeroEvoByCountry(){
        //check if the zero values are being calculated correctly
        System.out.println("zeroEvoByCountry");
        Map<String, Double> map = distribution1.getEvolutionByCountry(2012, 2013);

        Double r = map.get("Netherlands");
        assertEquals(0, r, 0.0);
    }


    /**
     * Smaller Test of getEvolutionByCountry and verifyYears methods, of class Distribution.
     */
    @Test
    public void smallerTestErrorIfYearIsAboveMax() {
        // Check if the year is above the maximum year
        System.out.println("errorIfYearIsAboveMax");
        assertThrows(IllegalArgumentException.class, () -> distribution1.getEvolutionByCountry(2011, 3000));
    }

    /**
     * Smaller Test of getEvolutionByCountry and verifyYears methods, of class Distribution.
     */
    @Test
    public void smallerTestErrorIfYearIsBelowMin() {
        // Check if the year is below the minimum year
        System.out.println("errorIfYearIsBelowMin");
        assertThrows(IllegalArgumentException.class, () -> distribution1.getEvolutionByCountry(2000, 2014));
    }

    /**
     * Smaller Test of getEvolutionByCountry and verifyYears methods, of class Distribution.
     */
    @Test
    public void smallerTestErrorIfYearsNotInLimits() {
        // Check if the year is not in the limits
        System.out.println("errorIfYearIsZero");
        assertThrows(IllegalArgumentException.class, () -> distribution1.getEvolutionByCountry(1999, 2500));
    }

    /**
     * This method smallerTests the getNumChargersKW method of the Distribution class.
     */
    @Test
    public void smallerTestGetNumChargersKWSize() {

        System.out.println("numChargersKWSize");
        // Check if the map has the correct size
        SortedSet<Map.Entry<String, NumChargersKW>> result = distribution1.getNumChargersKW(150);
        assertEquals(6, result.size());
    }

    /**
     * This method smallerTests the getNumChargersKW method of the Distribution class.
     */
    @Test
    public void smallerTestGetNumChargersKWCountryOrder() {

        System.out.println("getNumChargersKWCountryOrder");
        // Check if each key is sorted and has the correct Order
        SortedSet<Map.Entry<String, NumChargersKW>> result = distribution1.getNumChargersKW(150);
        Iterator<Map.Entry<String, NumChargersKW>> it = result.iterator();

        assertEquals("Netherlands", it.next().getKey());
        assertEquals("Poland", it.next().getKey());
        assertEquals("Portugal", it.next().getKey());
    }

    /**
     * This method smallerTests the getNumChargersKW method of the Distribution class.
     */
    @Test
    public void smallerTestGetNumChargersKWEachCountryValues() {

        System.out.println("getNumChargersKWEachCountryValues");
        // Check if each key has the correct values
        SortedSet<Map.Entry<String, NumChargersKW>> result = distribution1.getNumChargersKW(150);
        Iterator<Map.Entry<String, NumChargersKW>> it = result.iterator();
        
        Map.Entry<String, NumChargersKW> map = it.next();
        assertEquals(1, map.getValue().getTotalBelowKW());
        assertEquals(2,map.getValue().getTotalAboveKW());
        assertEquals(3,map.getValue().getTotal());

        map = it.next();
        assertEquals(3, map.getValue().getTotalBelowKW());
        assertEquals(0,map.getValue().getTotalAboveKW());
        assertEquals(3,map.getValue().getTotal());

        map = it.next();
        assertEquals(2, map.getValue().getTotalBelowKW());
        assertEquals(1,map.getValue().getTotalAboveKW());
        assertEquals(3,map.getValue().getTotal());
    }

    /**
     * Smaller Test of getMinimalAutonomy method, of class Distribution.
     */
    @Test
    public void smallerTestMinimalAutonomyMapSize() {

        //Check if the map has the correct size
        System.out.println("minimalAutonomyMapSize");
        Map<String, Double> result = distribution1.getMinimalAutonomy();
        assertEquals(3, result.size());
    }

    /**
     * Smaller Test of getMinimalAutonomy method, of class Distribution.
     */
    @Test
    public void smallerTestMinimalAutonomyValues() {

        //Check if each key has the correct values
        System.out.println("minimalAutonomyValues");
        Map<String, Double> result = distribution1.getMinimalAutonomy();
        assertEquals(287.4, result.get("Portugal"), 0.0);
        assertEquals(520.4, result.get("Hungary"), 0.0);
        assertEquals(520.4, result.get("Poland"), 0.0);
    }

    /**
     * Smaller Test of getMinimalAutonomy method, of class Distribution.
     */
    @Test
    public void smallerTestMinimalAutonomyDescendingOrder() {

        //Check if the map has the keys in the correct order
        System.out.println("minimalAutonomyDescendingOrder");
        Map<String, Double> result = distribution1.getMinimalAutonomy();
        String[] keyArray = result.keySet().toArray(new String[0]);
        
        assertEquals("Hungary", keyArray[0]);
        assertEquals("Portugal", keyArray[2]);
    }

    /**
     * Test of getMinimalAutonomy method, of class Distribution.
     */
    @Test
    public void smallerTestMinimalAutonomyIfEqualOrder() {

        //Check if the map has the keys in the correct order
        System.out.println("minimalAutonomyIfEqualOrder");
        Map<String, Double> result = distribution1.getMinimalAutonomy();
        String[] keyArray = result.keySet().toArray(new String[0]);
        assertEquals("Hungary", keyArray[0]);
        assertEquals("Poland", keyArray[1]);
    }

    /**
     * Smaller Test of getMinimalAutonomy method, of class Distribution.
     */
    @Test
    public void smallerTestMinimalAutonomyForOnlyOneCharger() {

        //Check if the map does not contain the countries that have less than 2 chargers
        System.out.println("minimalAutonomyForOnlyOneCharger");
        Map<String, Double> result = distribution1.getMinimalAutonomy();
        assertFalse(result.containsKey("Luxemburg"));
    }

    /**
     * Smaller Test of getMinimalAutonomy method, of class Distribution.
     */
    @Test
    public void smallerTestMinimalAutonomyZero() {

        //Check if the distance is not zero
        System.out.println("minimalAutonomyForOnlyOneCharger");
        Map<String, Double> result = distribution1.getMinimalAutonomy();
        for (Map.Entry<String, Double> entry : result.entrySet()) {
            assertFalse(entry.getValue() == 0);
        }
    }

    /**
     * Smaller Test of getClusters method, of class Distribution.
     */
    @Test
    public void smallerTestClustersMapSize() {
        
        //Check if the map has the correct size
        System.out.println("clustersMapSize");
        ArrayList<String> list = new ArrayList<>();
        list.add("69.407105, 19.676377");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");
        Map<String, List<String>> result = distribution1.getClusters(list);
        assertEquals(2, result.size());
    }

    /**
     * Smaller Test of getClusters method, of class Distribution.
     */
    @Test
    public void smallerTestClustersMapValues() {
        
        // Check if each key contains the expected value
        System.out.println("clustersMapValues");
        ArrayList<String> list = new ArrayList<>();
        list.add("69.407105, 19.676377");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");
        Map<String, List<String>> result = distribution1.getClusters(list);

        assertTrue(result.get("POI2").contains("Apeldoorn Oost, Netherlands"));
        assertTrue(result.get("POI2").contains("'s-Hertogenbosch, Netherlands"));
        assertTrue(result.get("POI2").contains("Amsterdam, Netherlands"));
        assertTrue(result.get("POI2").contains("Szigetszentmikl�s, Hungary"));
        assertTrue(result.get("POI2").contains("Debrecen, Hungary"));
        assertTrue(result.get("POI2").contains("Warsaw, Poland"));
        assertTrue(result.get("POI2").contains("Radom, Poland"));
        assertTrue(result.get("POI2").contains("Krakow, Poland"));

        assertTrue(result.get("POI3").contains("M�nsbech, Luxembourg"));
        assertTrue(result.get("POI3").contains("Montemor-o-Novo, Portugal"));
        assertTrue(result.get("POI3").contains("Matosinhos, Portugal"));
    }

    /**
     * Smaller Test of getClusters method, of class Distribution.
     */
    @Test
    public void smallerTestClustersMapDescendingOrder() {
        
        //Check if the map has the keys in the correct order
        System.out.println("clustersMapDescendingOrder");
        ArrayList<String> list = new ArrayList<>();
        list.add("69.407105, 19.676377");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");
        Map<String, List<String>> result = distribution1.getClusters(list);

        String[] keyArray = result.keySet().toArray(new String[0]);
        assertEquals("POI2", keyArray[0]);
        assertEquals("POI3", keyArray[1]);
    }

    /**
     * Smaller Test of getClusters method, of class Distribution.
     */
    @Test
    public void smallerTestClusterWithEqualPois() {
        
        //Check if the POI´s have the same coordinates
        System.out.println("clusterWithEqualPois");
        ArrayList<String> list = new ArrayList<>();
        list.add("69.407105, 19.676377");
        list.add("69.407105, 19.676377");
        list.add("69.407105, 19.676377");

        assertThrows(IllegalArgumentException.class, () -> distribution1.getClusters(list));
    }

    /**
     * Smaller Test of getClusters method, of class Distribution.
     */
    @Test
    public void smallerTestClusterWithEqualPois2() {
        
        //Check if the POI´s have the same coordinates
        System.out.println("clusterWithEqualPois2");
        ArrayList<String> list = new ArrayList<>();
        list.add("69.407105, 19.676377");
        list.add("59.122098, 9.707564");
        list.add("69.407105, 19.676377");

        assertThrows(IllegalArgumentException.class, () -> distribution1.getClusters(list));
    }

    /**
     * Smaller Test of getClusters method, of class Distribution.
     */
    @Test
    public void smallerTestClusterEqualValuesInDifferentPois() {
        
        //Check if different POI´s have the same values
        System.out.println("clusterEqualValuesInDifferentPois");
        ArrayList<String> list = new ArrayList<>();
        list.add("69.407105, 19.676377");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");
        Map<String, List<String>> result = distribution1.getClusters(list);

        assertTrue(result.get("POI3").contains("M�nsbech, Luxembourg"));
        assertTrue(result.get("POI3").contains("Montemor-o-Novo, Portugal"));
        assertTrue(result.get("POI3").contains("Matosinhos, Portugal"));

        assertTrue(result.get("POI2").contains("Amsterdam, Netherlands"));
        assertTrue(result.get("POI2").contains("Szigetszentmikl�s, Hungary"));
        assertTrue(result.get("POI2").contains("Debrecen, Hungary"));
        assertTrue(result.get("POI2").contains("Warsaw, Poland"));
        assertTrue(result.get("POI2").contains("Radom, Poland"));
        assertTrue(result.get("POI2").contains("Krakow, Poland"));

        assertFalse(result.get("POI2").contains("M�nsbech, Luxembourg"));
        assertFalse(result.get("POI2").contains("Montemor-o-Novo, Portugal"));
        assertFalse(result.get("POI2").contains("Matosinhos, Portugal"));

        assertFalse(result.get("POI3").contains("Amsterdam, Netherlands"));
        assertFalse(result.get("POI3").contains("Szigetszentmikl�s, Hungary"));
        assertFalse(result.get("POI3").contains("Debrecen, Hungary"));
        assertFalse(result.get("POI3").contains("Warsaw, Poland"));
        assertFalse(result.get("POI3").contains("Radom, Poland"));
        assertFalse(result.get("POI3").contains("Krakow, Poland"));
    }

    /**
     * Smaller Test of getClusters method, of class Distribution.
     */
    @Test
    public void smallerTestClusterMaxPositiveLatitude() {
        
        //Check if the latitude is above the maximum value
        System.out.println("clusterMaxPositiveLatitude");
        ArrayList<String> list = new ArrayList<>();
        list.add("90.000001, 19.676377");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");

        assertThrows(IllegalArgumentException.class, () -> distribution1.getClusters(list));
    }

    /**
     * Smaller Test of getClusters method, of class Distribution.
     */
    @Test
    public void smallerTestClusterMinNegativeLatitude() {
        
        //Check if the latitude is below the minimul value
        System.out.println("clusterMaxPositiveLatitude");
        ArrayList<String> list = new ArrayList<>();
        list.add("-90.000001, 19.676377");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");

        assertThrows(IllegalArgumentException.class, () -> distribution1.getClusters(list));
    }

    /**
     * Smaller Test of getClusters method, of class Distribution.
     */
    @Test
    public void smallerTestClusterMaxPositiveLongitude() {
        
        //Check if the longitude is above the maximum value
        System.out.println("clusterMaxPositiveLongitude");
        ArrayList<String> list = new ArrayList<>();
        list.add("43.03200, 180.000001");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");

        assertThrows(IllegalArgumentException.class, () -> distribution1.getClusters(list));
    }

    /**
     * Smaller Test of getClusters method, of class Distribution.
     */
    @Test
    public void smallerTestClusterMinNegativeLongitude() {
        
        //Check if the longitude is below the minimul value
        System.out.println("clusterMaxPositiveLongitude");
        ArrayList<String> list = new ArrayList<>();
        list.add("43.03200, -180.000001");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");

        assertThrows(IllegalArgumentException.class, () -> distribution1.getClusters(list));
    }

    /**
     * Smaller Test of getClusters method, of class Distribution.
     */
    @Test
    public void smallerTestClusterImpossibleCoordinates() {
        
        //Check if the coordinates are impossible
        System.out.println("clusterImpossibleCoordinates");
        ArrayList<String> list = new ArrayList<>();
        list.add("90.000001, -180.000001");
        list.add("-90.000001, 180.000001");
        list.add("-90.000001, -180.000001");
        list.add("90.000001, 180.000001");

        assertThrows(IllegalArgumentException.class, () -> distribution1.getClusters(list));
    }

    /**
     * Smaller Test of getClusters method, of class Distribution.
     */
    @Test
    public void smallerTestClusterEmptyCoordinates() {
        
        //Check if there are no coordinates
        System.out.println("clusterEmptyCoordinates");
        ArrayList<String> list = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> distribution1.getClusters(list));
    }
    
    /**
     * ex 3
     * Tests the method getCountriesWithNoIncrease() of the Distribution class.
     * It checks if the returned map contains the correct keys and values.
     */
    @Test
    public void smallerTestGetCountriesWithNoIncreaseCountriesList() {
        Map<String, List<PowertrainDif>> result = distribution1.getCountriesWithNoIncrease();
        System.out.println("getCountriesWithNoIncreaseCountriesList");
        // Check if each key has the correct values
        assertTrue(result.containsKey("Portugal"));
        assertTrue(result.containsKey("Poland"));
        assertTrue(result.containsKey("Israel"));
        assertTrue(result.containsKey("USA"));
    }
    
    /**
     * ex 3
     * Tests the method that returns a map with the countries that have no increase
     * in powertrain difference.
     * It checks if the map has the correct size.
     */
    @Test
    public void smallerTestGetCountriesWithNoIncreaseSize() {
        Map<String, List<PowertrainDif>> result = distribution1.getCountriesWithNoIncrease();
        System.out.println("getCountriesWithNoIncreaseSize");

        // Check if the map has the correct size
        assertEquals(4, result.size());
    }
    
    /**
     * ex 3
     * Tests the method getCountriesWithNoIncrease() from the Distribution class.
     * It checks if the returned map has the expected values for the difference from
     * last year for each country and powertrain type.
     * The expected values are hardcoded in the assertions.
     */
    @Test
    public void smallerTestgetCountriesWithNoIncreaseCorrectValues() {
        Map<String, List<PowertrainDif>> result = distribution1.getCountriesWithNoIncrease();
        System.out.println("getCountriesWithNoIncreaseCorrectValues");

        assertEquals(-69, result.get("Poland").get(0).getDifferenceFromLastYearBev());
        assertEquals(-33, result.get("Poland").get(0).getDifferenceFromLastYearPhev());
        assertEquals(-98, result.get("Portugal").get(0).getDifferenceFromLastYearPhev());
        assertEquals(-178, result.get("Portugal").get(0).getDifferenceFromLastYearBev());
        assertEquals(-3, result.get("Israel").get(0).getDifferenceFromLastYearBev());
        assertEquals(0, result.get("Israel").get(0).getDifferenceFromLastYearPhev());
        assertEquals(-50, result.get("USA").get(0).getDifferenceFromLastYearBev());
        assertEquals(0, result.get("USA").get(0).getDifferenceFromLastYearPhev());

        
        
    }
    
    /**
     * ex 3
     * Tests the getCountriesWithNoIncrease method of the Distribution class.
     * It checks if the returned map contains the correct year for each country with
     * no increase.
     */
    @Test
    public void smallerTestgetCountriesWithNoIncreaseCorrectYears() {
        Map<String, List<PowertrainDif>> result = distribution1.getCountriesWithNoIncrease();
        System.out.println("getCountriesWithNoIncreaseCorrectYears");
        assertEquals(2014, result.get("Poland").get(0).getYear());
        assertEquals(2014, result.get("Portugal").get(0).getYear());
        assertEquals(2012, result.get("Israel").get(0).getYear());
        assertEquals(2012, result.get("USA").get(0).getYear());
    }

    /**
     * Exercício 8
     * Tests the method getCountryTopNCharger of the Distribution class, which
     * returns the top N charging capacity states of a given list of countries.
     */
    @Test
    public void smallerTestCountryTopNChargerStatesCountryList() {

        System.out.println("countryTopNChargerStatesCountryList");
        List<String> countries = new ArrayList<>();
        countries.add("Portugal");
        countries.add("United Kingdom");
        countries.add("Netherlands");
        countries.add("Luxembourg");
        countries.add("Poland");
        countries.add("Hungary");
        ChargingCapacity result = distribution1.getCountryTopNCharger(3, countries);
        
        assertTrue(result.getStates().contains("Gelderland"));
        assertTrue(result.getStates().contains("Central Hungary"));
        assertTrue(result.getStates().contains("Norte"));


    }
    
    /**
     * Exercício 8
     * Tests the method that returns the top N charging cities for a given list of
     * countries.
     */
    @Test
    public void smallerTestCountryTopNChargerCitiesCountryList() {

        System.out.println("countryTopNChargerCitiesCountryList");
        List<String> countries = new ArrayList<>();
        countries.add("Portugal");
        countries.add("United Kingdom");
        countries.add("Netherlands");
        countries.add("Luxembourg");
        countries.add("Poland");
        countries.add("Hungary");
        ChargingCapacity result = distribution1.getCountryTopNCharger(3, countries);
        
        assertTrue(result.getCities().contains("Arnhem"));
        assertTrue(result.getCities().contains("Szigetszentmikl�s"));
        assertTrue(result.getCities().contains("Matosinhos"));
    }
    
    /**
     * Exercício 8
     * Tests the method that returns the top N chargers in a list of countries,
     * based on their charging capacity.
     */
    @Test
    public void smallerTestCountryTopNChargerCapacityCountryList() {

        System.out.println("countryTopNChargerCapacityCountryList");
        List<String> countries = new ArrayList<>();
        countries.add("Portugal");
        countries.add("United Kingdom");
        countries.add("Netherlands");
        countries.add("Luxembourg");
        countries.add("Poland");
        countries.add("Hungary");
        ChargingCapacity result = distribution1.getCountryTopNCharger(3, countries);

        assertTrue(result.getTotalCapacity() == 13000);
    }
    
    /**
     * Exercício 8
     * when the top N chargers is zero and the country list is not empty.
     */
    @Test
    public void smallerTestCountryTopNChargerTopZeroCountryList() {

        System.out.println("countryTopNChargerTopZeroCountryList");
        List<String> countries = new ArrayList<>();
        countries.add("Portugal");
        countries.add("United Kingdom");
        countries.add("Netherlands");
        countries.add("Luxembourg");
        countries.add("Poland");
        countries.add("Hungary");

        assertThrows(IllegalArgumentException.class, () -> distribution1.getCountryTopNCharger(0, countries));
    }

    /**
     * Exercício 8
     * Tests the getCountryTopNCharger method of the Distribution class when the
     * country list is empty.
     */
    @Test
    public void smallerTestCountryTopNChargerEmptyCountryList() {

        System.out.println("countryTopNChargerEmptyCountryList");
        List<String> countries = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> distribution1.getCountryTopNCharger(3, countries));
    }

    /**
     * Exercício 8
     * Tests the method getCountryTopNCharger() from the Distribution class
     * when an inexistent country is given.
     */
    @Test
    public void smallerTestCountryTopNChargerInexistentCountry() {

        System.out.println("countryTopNChargerInexistentCountry");
        List<String> countries = new ArrayList<>();

        countries.add("Portucale");
        countries.add("Vaticano");

        assertThrows(IllegalArgumentException.class, () -> distribution1.getCountryTopNCharger(3, countries));
    }

    /**
     * Exercício 8
     * Tests the method countryTopNChargerStatesStateList from the Distribution
     * class.
     */
    @Test
    public void smallerTestCountryTopNChargerStatesStateList() {

        System.out.println("countryTopNChargerStatesStateList");
        List<String> states = new ArrayList<>();
        states.add("Centro");
        states.add("Alentejo");
        states.add("Norte");
        states.add("Kent");
        states.add("Warwickshire");
        states.add("Dorking");
        states.add("Gelderland");
        states.add("North Holland");
        states.add("Luxembourg");
        states.add("Masovia");
        states.add("Lesser Poland");
        states.add("Central Hungary");
        states.add("Northern Great Plain");
        ChargingCapacity result = distribution1.getCountryTopNCharger(3, states);

        assertTrue(result.getStates().contains("Central Hungary"));
        assertTrue(result.getStates().contains("Gelderland"));
        assertTrue(result.getStates().contains("Norte"));

    }

    /**
     * Exercício 8
     * Tests smallerTestCountryTopNChargerCitiesStateList.
     */
    @Test
    public void smallerTestCountryTopNChargerCitiesStateList() {

        System.out.println("countryTopNChargerCitiesStateList");
        List<String> states = new ArrayList<>();
        states.add("Centro");
        states.add("Alentejo");
        states.add("Norte");
        states.add("Kent");
        states.add("Warwickshire");
        states.add("Dorking");
        states.add("Gelderland");
        states.add("North Holland");
        states.add("Luxembourg");
        states.add("Masovia");
        states.add("Lesser Poland");
        states.add("Central Hungary");
        states.add("Northern Great Plain");
        ChargingCapacity result = distribution1.getCountryTopNCharger(3, states);
        
        assertTrue(result.getCities().contains("Arnhem"));
        assertTrue(result.getCities().contains("Szigetszentmikl�s"));
        assertTrue(result.getCities().contains("Matosinhos"));

    }
    
    /**
     * Exercício 8
     * Tests if the total capacity of the top N chargers in a list of states matches
     * the expected value.
     */
    @Test
    public void smallerTestCountryTopNChargerCapacityStateList() {

        System.out.println("countryTopNChargerCapacityStateList");
        List<String> states = new ArrayList<>();
        states.add("Centro");
        states.add("Alentejo");
        states.add("Norte");
        states.add("Kent");
        states.add("Warwickshire");
        states.add("Dorking");
        states.add("Gelderland");
        states.add("North Holland");
        states.add("Luxembourg");
        states.add("Masovia");
        states.add("Lesser Poland");
        states.add("Central Hungary");
        states.add("Northern Great Plain");
        ChargingCapacity result = distribution1.getCountryTopNCharger(3, states);
        result.getStates().forEach(System.out::println);
        assertTrue(result.getTotalCapacity() == 13000);
    }
    
    /**
     * Exercício 8
     * Tests the class when the topN parameter is zero and a list of states is
     * provided.
     */
    @Test
    public void smallerTestCountryTopNChargerTopZeroStateList() {

        System.out.println("countryTopNChargerTopZeroStateList");
        List<String> states = new ArrayList<>();
        states.add("Centro");
        states.add("Alentejo");
        states.add("Norte");
        states.add("Kent");
        states.add("Warwickshire");
        states.add("Dorking");
        states.add("Gelderland");
        states.add("North Holland");
        states.add("Luxembourg");
        states.add("Masovia");
        states.add("Lesser Poland");
        states.add("Central Hungary");
        states.add("Northern Great Plain");

        assertThrows(IllegalArgumentException.class, () -> distribution1.getCountryTopNCharger(0, states));
    }

    /**
     * Exercício 8
     * Tests the method getCountryTopNCharger with an empty list of states.
     */
    @Test
    public void smallerTestCountryTopNChargerEmptyStateList() {

        System.out.println("countryTopNChargerEmptyStateList");
        List<String> states = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> distribution1.getCountryTopNCharger(3, states));
    }

    /**
     * Exercício 8
     * This smallerTest ensures that an thrown when attempting to get the top N chargers
     * for a list of countries and states.
     */
    @Test
    public void smallerTestCountryTopNChargerCountryAndStateList() {

        System.out.println("countryTopNChargerCountryAndStateList");
        List<String> countryAndStates = new ArrayList<>();

        countryAndStates.add("Portugal");
        countryAndStates.add("Spain");
        countryAndStates.add("Netherlands");
        countryAndStates.add("Luxemburg");
        countryAndStates.add("Sweden");
        countryAndStates.add("Alentejo");
        countryAndStates.add("Norte");
        countryAndStates.add("Algarve");
        countryAndStates.add("Centro");

        assertThrows(IllegalArgumentException.class, () -> distribution1.getCountryTopNCharger(3, countryAndStates));
    }
    
    /**
     * Exercício 8
     * Tests the class when an inexistent state is provided in the list of states.
     */
    @Test
    public void smallerTestCountryTopNChargerInexistentState() {

        System.out.println("countryTopNChargerInexistentState");
        List<String> states = new ArrayList<>();

        states.add("Algarve");
        states.add("Norte");
        states.add("Nortada");
        assertThrows(IllegalArgumentException.class, () -> distribution1.getCountryTopNCharger(3, states));
    }


    /**
     * Smaller Test of getReportQuotas method, of class Distribution.
     */
    @Test
    public void smallerTestGetReportQuotasWhenYearDoesNotExist() {
        // Check if the year does not exist in the map
        System.out.println("getReportQuotasWhenYearDoesNotExist");
        assertThrows(IllegalArgumentException.class, () -> distribution1.getReportQuotas(12, 10, 1));
    }

    /**
     * Smaller Test of getReportQuotas method, of class Distribution.
     */
    @Test
    public void smallerTestGetReportQuotas() {
        // Check if the map prints the correct values
        System.out.println("getReportQuotas");
        Map<String, ReportQuotas> map = distribution1.getReportQuotas(2014, 10, 1);

        ReportQuotas reportQuotas = map.get("Portugal");
        assertEquals(34, reportQuotas.getStallsTotal());
        assertEquals(14, reportQuotas.getEletricVehiclesTotal());
        assertEquals(2428.0, reportQuotas.getQuota(), 0.1);

        reportQuotas = map.get("Netherlands");
        assertEquals(44, reportQuotas.getStallsTotal());
        assertEquals(1950, reportQuotas.getEletricVehiclesTotal());
        assertEquals(22.0, reportQuotas.getQuota(), 0.1);

        reportQuotas = map.get("Poland");
        assertEquals(10, reportQuotas.getStallsTotal());
        assertEquals(61, reportQuotas.getEletricVehiclesTotal());
        assertEquals(163.0, reportQuotas.getQuota(), 0.1);
    }

    /**
     * Smaller Test of getReportQuotas method, of class Distribution.
     */
    @Test
    public void smallerTestGetReportQuotasWhenStallsIsNegative() {
        // Check when number of stalls is a negative number
        System.out.println("getReportQuotasWhenStallsIsNegative");
        Map<String, ReportQuotas> map = distribution1.getReportQuotas(2014, 10, -1);
        assertEquals(0, map.size());
    }

    /**
     * smalSmaller TestlerTest of getReportQuotas method, of class Distribution.
     */
    @Test
    public void smallerTestGetReportQuotasWhenStallsIsZero() {
        // Check when the number of stalls is zero
        System.out.println("getReportQuotasWhenStallsIsZero");
        Map<String, ReportQuotas> map = distribution1.getReportQuotas(2014, 10, 0);
        assertEquals(0, map.size());
    }

    /**
     * Smaller Test of getReportQuotas method, of class Distribution.
     */
    @Test
    public void smallerTestGetReportQuotasWhenRatioIsZero() {
        // Check when the ratio is zero
        System.out.println("getReportQuotasWhenRatioIsZero");
        Map<String, ReportQuotas> map = distribution1.getReportQuotas(2014, 0, 1);
        assertEquals(0, map.size());
    }
}
