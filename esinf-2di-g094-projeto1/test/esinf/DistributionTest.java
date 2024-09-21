package esinf;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class DistributionTest {


    private static Distribution distribution = new Distribution();
    private static boolean initialized = false;

    static {
        if (!initialized) {
            try {
                URL chargerUrl = DistributionTest.class.getResource("/carregadores_europa.csv");
                List<String> chargerList = Files.readAllLines(Paths.get(chargerUrl.toURI()));
                distribution.buildCharger(chargerList);
                URL evSalesUrl = DistributionTest.class.getResource("/ev_sales.csv");
                List<String> evSalesList = Files.readAllLines(Paths.get(evSalesUrl.toURI()));
                distribution.buildEvSales(evSalesList);
                initialized = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Test of getCityStalls method, of class Distribution.
     */
    @Test
    public void testNumberOfStallsByCity() {
        //check if the values exist in the map
        System.out.println("numberOfStallsByCity");
        Map<String, Set<CityStalls>> map = distribution.getCityStalls();
        Set <CityStalls> r = map.get("Portugal");
        r.stream().filter((c) -> (c.getCity().equals("F�tima"))).forEachOrdered((c) -> {
            assertEquals(14, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Montemor-o-Novo"))).forEachOrdered((c) -> {
            assertEquals(8, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Alcantarilha"))).forEachOrdered((c) -> {
            assertEquals(8, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Guarda"))).forEachOrdered((c) -> {
            assertEquals(8, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Ribeira de Pena"))).forEachOrdered((c) -> {
            assertEquals(10, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Mealhada"))).forEachOrdered((c) -> {
            assertEquals(20, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Alcac�r do Sal"))).forEachOrdered((c) -> {
            assertEquals(10, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Almancil"))).forEachOrdered((c) -> {
            assertEquals(8, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Matosinhos"))).forEachOrdered((c) -> {
            assertEquals(12, c.getStalls());
        });
        r.stream().filter((c) -> (c.getCity().equals("Castelo Branco"))).forEachOrdered((c) -> {
            assertEquals(8, c.getStalls());
        });
        r.clear();
        r = map.get("United Kingdom");
        r.stream().filter((c) -> (c.getCity().equals("Winchester"))).forEachOrdered((c) -> {
            assertEquals(14, c.getStalls());
        });
    }


    /**
     * Test of getCityStalls method, of class Distribution.
     */
    @Test
    public void testNumberOfStallsByCityIfRepeated() {
        //check if the repeated cities are in the map with the right number of summed stalls
        System.out.println("numberOfStallsByCity");
        Map<String, Set<CityStalls>> map = distribution.getCityStalls();
        Set <CityStalls> r = map.get("Ireland");
        r.stream().filter((c) -> (c.getCity().equals("Castlebellingham"))).forEachOrdered((c) -> {
            assertEquals(16, c.getStalls());
        });
        r.clear();
        r = map.get("Poland");
        r.stream().filter((c) -> (c.getCity().equals("Katowice"))).forEachOrdered((c) -> {
            assertEquals(8, c.getStalls());
        });
        r.clear();
        r = map.get("United Kingdom");
        r.stream().filter((c) -> (c.getCity().equals("Winchester"))).forEachOrdered((c) -> {
            assertEquals(14, c.getStalls());
        });
    }

    /**
     * Test of getEvolutionByCountry method, of class Distribution.
     */
    @Test
    public void testPositiveEvoByCountry(){
        //check if the positive values are being calculated correctly
        System.out.println("evoByCountry");
        Map<String, Double> map = distribution.getEvolutionByCountry(2016, 2017);
        Double r = map.get("Austria");
        assertEquals(0.42, r, 0.0);
        r = map.get("Australia");
        assertEquals(0.68, r, 0.0);
        r = map.get("Belgium");
        assertEquals(0.6, r, 0.0);
    }

    /**
     * Test of getEvolutionByCountry method, of class Distribution.
     */
    @Test
    public void testNegativeEvoByCountry(){
        //check if the negative values are being calculated correctly
        System.out.println("negativeEvoByCountry");
        Map<String, Double> map = distribution.getEvolutionByCountry(2011, 2012);
        Double r = map.get("Chile");
        assertEquals(-0.17, r, 0.0);
    }

    /**
     * Test of getEvolutionByCountry method, of class Distribution.
     */
    @Test
    public void testZeroEvoByCountry(){
        //check if the zero values are being calculated correctly
        System.out.println("zeroEvoByCountry");
        Map<String, Double> map = distribution.getEvolutionByCountry(2012, 2013);
        Double r = map.get("Chile");
        assertEquals(0, r, 0.0);
    }

    /**
     * Test of getEvolutionByCountry and verifyYears methods, of class Distribution.
     */
    @Test
    public void testErrorInInferiorYear() {
        // Check if the inferior year is inferior to the superior year
        System.out.println("errorInInferiorYear");
        assertThrows(IllegalArgumentException.class, () -> distribution.getEvolutionByCountry(2015, 2014));
        assertThrows(IllegalArgumentException.class, () -> distribution.getEvolutionByCountry(2022, 2012));
    }

    /**
     * Test of getEvolutionByCountry and verifyYears methods, of class Distribution.
     */
    @Test
    public void testErrorIfNegativeYear() {
        // Check if the year isn't negative
        System.out.println("errorIfNegativeYear");
        assertThrows(IllegalArgumentException.class, () -> distribution.getEvolutionByCountry(-2015, 2014));
        assertThrows(IllegalArgumentException.class, () -> distribution.getEvolutionByCountry(2022, -2012));
        assertThrows(IllegalArgumentException.class, () -> distribution.getEvolutionByCountry(-2013, -2023));
    }

    /**
     * Test of getEvolutionByCountry and verifyYears methods, of class Distribution.
     */
    @Test
    public void testErrorIfYearIsAboveMax() {
        // Check if the year is above the maximum year
        System.out.println("errorIfYearIsAboveMax");
        assertThrows(IllegalArgumentException.class, () -> distribution.getEvolutionByCountry(2011, 3000));
    }

    /**
     * Test of getEvolutionByCountry and verifyYears methods, of class Distribution.
     */
    @Test
    public void testErrorIfYearIsBelowMin() {
        // Check if the year is below the minimum year
        System.out.println("errorIfYearIsBelowMin");
        assertThrows(IllegalArgumentException.class, () -> distribution.getEvolutionByCountry(2000, 2014));
    }

    /**
     * Test of getEvolutionByCountry and verifyYears methods, of class Distribution.
     */
    @Test
    public void testErrorIfYearIsEqual() {
        // Check if the year is equal
        System.out.println("errorIfYearIsEqual");
        assertThrows(IllegalArgumentException.class, () -> distribution.getEvolutionByCountry(2014, 2014));
    }

    /**
     * Test of getEvolutionByCountry and verifyYears methods, of class Distribution.
     */
    @Test
    public void testErrorIfYearsNotInLimits() {
        // Check if the year is not in the limits
        System.out.println("errorIfYearIsZero");
        assertThrows(IllegalArgumentException.class, () -> distribution.getEvolutionByCountry(1999, 2500));
    }
    /**
     * Test of getReportQuotas method, of class Distribution.
     */
    @Test
    public void testGetReportQuotasWhenYearDoesNotExist() {
        // Check if the year does not exist in the map
        System.out.println("getReportQuotasWhenYearDoesNotExist");
        assertThrows(IllegalArgumentException.class, () -> distribution.getReportQuotas(12, 10, 1));
    }

    /**
     * Test of getReportQuotas method, of class Distribution.
     */
    @Test
    public void testGetReportQuotas() {
        // Check if the map prints the correct values
        System.out.println("getReportQuotas");
        Map<String, ReportQuotas> map = distribution.getReportQuotas(2022, 10, 1);

        ReportQuotas reportQuotas = map.get("Portugal");
        assertEquals(106, reportQuotas.getStallsTotal());
        assertEquals(34000, reportQuotas.getEletricVehiclesTotal());
        assertEquals(3.0, reportQuotas.getQuota(), 0.1);
    }

    /**
     * Test of getReportQuotas method, of class Distribution.
     */
    @Test
    public void testGetReportQuotasWhenStallsIsNegative() {
        // Check when number of stalls is a negative number
        System.out.println("getReportQuotasWhenStallsIsNegative");
        Map<String, ReportQuotas> map = distribution.getReportQuotas(2022, 10, -1);
        assertEquals(0, map.size());
    }

    /**
     * Test of getReportQuotas method, of class Distribution.
     */
    @Test
    public void testGetReportQuotasWhenStallsIsZero() {
        // Check when the number of stalls is zero
        System.out.println("getReportQuotasWhenStallsIsZero");
        Map<String, ReportQuotas> map = distribution.getReportQuotas(2022, 10, 0);
        assertEquals(0, map.size());
    }

    /**
     * Test of getReportQuotas method, of class Distribution.
     */
    @Test
    public void testGetReportQuotasWhenRatioIsZero() {
        // Check when the ratio is zero
        System.out.println("getReportQuotasWhenRatioIsZero");
        Map<String, ReportQuotas> map = distribution.getReportQuotas(2022, 0, 1);
        assertEquals(0, map.size());
    }

    /**
     * Test of getMinimalAutonomy method, of class Distribution.
     */
    @Test
    public void testMinimalAutonomyMapSize() {

        //Check if the map has the correct size
        System.out.println("minimalAutonomyMapSize");
        Map<String, Double> result = distribution.getMinimalAutonomy();
        assertEquals(29, result.size());
    }

    /**
     * Test of getMinimalAutonomy method, of class Distribution.
     */
    @Test
    public void testMinimalAutonomyValues() {

        //Check if each key has the correct values
        System.out.println("minimalAutonomyValues");
        Map<String, Double> result = distribution.getMinimalAutonomy();
        assertEquals(132.7, result.get("Norway"), 0.0);
        assertEquals(177.2, result.get("United Kingdom"), 0.0);
        assertEquals(148.1, result.get("Sweden"), 0.0);
        assertEquals(110.7, result.get("France"), 0.0);
    }

    /**
     * Test of getMinimalAutonomy method, of class Distribution.
     */
    @Test
    public void testMinimalAutonomyDescendingOrder() {

        //Check if the map has the keys in the correct order
        System.out.println("minimalAutonomyDescendingOrder");
        Map<String, Double> result = distribution.getMinimalAutonomy();
        String[] keyArray = result.keySet().toArray(new String[0]);
        assertEquals("Turkey", keyArray[0]);
        assertEquals("Romania", keyArray[1]);
        assertEquals("Italy", keyArray[7]);
        assertEquals("Bulgaria", keyArray[15]);
        assertEquals("Austria", keyArray[28]);
    }

    /**
     * Test of getMinimalAutonomy method, of class Distribution.
     */
    @Test
    public void testMinimalAutonomyIfEqualOrder() {

        //Check if the map has the keys in the correct order
        System.out.println("minimalAutonomyIfEqualOrder");
        Map<String, Double> result = distribution.getMinimalAutonomy();
        String[] keyArray = result.keySet().toArray(new String[0]);
        assertEquals("Hungary", keyArray[12]);
        assertEquals("Sweden", keyArray[13]);
    }

    /**
     * Test of getMinimalAutonomy method, of class Distribution.
     */
    @Test
    public void testMinimalAutonomyForOnlyOneCharger() {

        //Check if the map does not contain the countries that have less than 2 chargers
        System.out.println("minimalAutonomyForOnlyOneCharger");
        Map<String, Double> result = distribution.getMinimalAutonomy();
        assertFalse(result.containsKey("Russia"));
        assertFalse(result.containsKey("Luxemburg"));
        assertFalse(result.containsKey("Lithuania"));
    }

    /**
     * Test of getClusters method, of class Distribution.
     */
    @Test
    public void testClustersMapSize() {
        
        //Check if the map has the correct size
        System.out.println("clustersMapSize");
        ArrayList<String> list = new ArrayList<>();
        list.add("69.407105, 19.676377");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");
        Map<String, List<String>> result = distribution.getClusters(list);
        assertEquals(3, result.size());
    }

    /**
     * Test of getClusters method, of class Distribution.
     */
    @Test
    public void testClustersMapValues() {
        
        // Check if each key contains the expected value
        System.out.println("clustersMapValues");
        ArrayList<String> list = new ArrayList<>();
        list.add("69.407105, 19.676377");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");
        Map<String, List<String>> result = distribution.getClusters(list);

        assertTrue(result.get("POI1").contains("Innhavet, Norway"));
        assertTrue(result.get("POI1").contains("Hartola, Finland"));
        assertTrue(result.get("POI2").contains("Hermsdorf, Germany"));
        assertTrue(result.get("POI2").contains("Zevenaar, Netherlands"));
        assertTrue(result.get("POI3").contains("Nice, France"));
        assertTrue(result.get("POI3").contains("Matosinhos, Portugal"));
    }

    /**
     * Test of getClusters method, of class Distribution.
     */
    @Test
    public void testClustersMapDescendingOrder() {
        
        //Check if the map has the keys in the correct order
        System.out.println("clustersMapDescendingOrder");
        ArrayList<String> list = new ArrayList<>();
        list.add("69.407105, 19.676377");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");
        Map<String, List<String>> result = distribution.getClusters(list);

        String[] keyArray = result.keySet().toArray(new String[0]);
        assertEquals("POI1", keyArray[2]);
        assertEquals("POI2", keyArray[0]);
        assertEquals("POI3", keyArray[1]);
    }

    /**
     * Test of getClusters method, of class Distribution.
     */
    @Test
    public void testClusterWithEqualPois() {
        
        //Check if the POI´s have the same coordinates
        System.out.println("clusterWithEqualPois");
        ArrayList<String> list = new ArrayList<>();
        list.add("69.407105, 19.676377");
        list.add("69.407105, 19.676377");
        list.add("69.407105, 19.676377");

        assertThrows(IllegalArgumentException.class, () -> distribution.getClusters(list));
    }

    /**
     * Test of getClusters method, of class Distribution.
     */
    @Test
    public void testClusterWithEqualPois2() {
        
        //Check if the POI´s have the same coordinates
        System.out.println("clusterWithEqualPois2");
        ArrayList<String> list = new ArrayList<>();
        list.add("69.407105, 19.676377");
        list.add("59.122098, 9.707564");
        list.add("69.407105, 19.676377");

        assertThrows(IllegalArgumentException.class, () -> distribution.getClusters(list));
    }

    /**
     * Test of getClusters method, of class Distribution.
     */
    @Test
    public void testClusterEqualValuesInDifferentPois() {
        
        //Check if different POI´s have the same values
        System.out.println("clusterEqualValuesInDifferentPois");
        ArrayList<String> list = new ArrayList<>();
        list.add("69.407105, 19.676377");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");
        Map<String, List<String>> result = distribution.getClusters(list);

        assertTrue(result.get("POI1").contains("Kaaresuvanto, Finland"));
        assertFalse(result.get("POI2").contains("Kaaresuvanto, Finland"));
        assertFalse(result.get("POI3").contains("Kaaresuvanto, Finland"));
        assertTrue(result.get("POI2").contains("Badhoevedorp, Netherlands"));
        assertFalse(result.get("POI1").contains("Badhoevedorp, Netherlands"));
        assertFalse(result.get("POI3").contains("Badhoevedorp, Netherlands"));
    }

    /**
     * Test of getClusters method, of class Distribution.
     */
    @Test
    public void testClusterMaxPositiveLatitude() {
        
        //Check if the latitude is above the maximum value
        System.out.println("clusterMaxPositiveLatitude");
        ArrayList<String> list = new ArrayList<>();
        list.add("90.000001, 19.676377");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");

        assertThrows(IllegalArgumentException.class, () -> distribution.getClusters(list));
    }

    /**
     * Test of getClusters method, of class Distribution.
     */
    @Test
    public void testClusterMinNegativeLatitude() {
        
        //Check if the latitude is below the minimum value
        System.out.println("clusterMaxPositiveLatitude");
        ArrayList<String> list = new ArrayList<>();
        list.add("-90.000001, 19.676377");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");

        assertThrows(IllegalArgumentException.class, () -> distribution.getClusters(list));
    }

    /**
     * Test of getClusters method, of class Distribution.
     */
    @Test
    public void testClusterMaxPositiveLongitude() {
        
        //Check if the longitude is above the maximum value
        System.out.println("clusterMaxPositiveLongitude");
        ArrayList<String> list = new ArrayList<>();
        list.add("43.03200, 180.000001");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");

        assertThrows(IllegalArgumentException.class, () -> distribution.getClusters(list));
    }

    /**
     * Test of getClusters method, of class Distribution.
     */
    @Test
    public void testClusterMinNegativeLongitude() {
        
        //Check if the longitude is below the minimum value
        System.out.println("clusterMaxPositiveLongitude");
        ArrayList<String> list = new ArrayList<>();
        list.add("43.03200, -180.000001");
        list.add("59.122098, 9.707564");
        list.add("52.420611, -7.988028");

        assertThrows(IllegalArgumentException.class, () -> distribution.getClusters(list));
    }

    /**
     * Test of getClusters method, of class Distribution.
     */
    @Test
    public void testClusterImpossibleCoordinates() {
        
        //Check if the coordinates are impossible
        System.out.println("clusterImpossibleCoordinates");
        ArrayList<String> list = new ArrayList<>();
        list.add("90.000001, -180.000001");
        list.add("-90.000001, 180.000001");
        list.add("-90.000001, -180.000001");
        list.add("90.000001, 180.000001");

        assertThrows(IllegalArgumentException.class, () -> distribution.getClusters(list));
    }

    /**
     * Test of getClusters method, of class Distribution.
     */
    @Test
    public void testClusterEmptyCoordinates() {
        
        //Check if there are no coordinates
        System.out.println("clusterEmptyCoordinates");
        ArrayList<String> list = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> distribution.getClusters(list));
    }

    /** ex 3
     * Tests the method getCountriesWithNoIncrease() of the Distribution class.
     * It checks if the returned map contains the correct keys and values.
     */
    @Test
    public void testGetCountriesWithNoIncreaseCountriesList() {
        Map<String, List<PowertrainDif>> result = distribution.getCountriesWithNoIncrease();
        System.out.println("getCountriesWithNoIncreaseCountriesList");
        // Check if each key has the correct values
        assertTrue(result.containsKey("Poland"));
        assertTrue(result.containsKey("China"));
        assertTrue(result.containsKey("Portugal"));
        assertTrue(result.containsKey("USA"));
        assertTrue(result.containsKey("Belgium"));
        assertTrue(result.containsKey("Denmark"));
        assertTrue(result.containsKey("South Africa"));
        assertTrue(result.containsKey("Mexico"));
        assertTrue(result.containsKey("Italy"));
        assertTrue(result.containsKey("Israel"));
        assertTrue(result.containsKey("Australia"));
    }


    /** ex 3
     * Tests the method that returns a map with the countries that have no increase in powertrain difference.
     * It checks if the map has the correct size.
     */
    @Test
    public void testGetCountriesWithNoIncreaseSize(){
        Map<String, List<PowertrainDif>> result = distribution.getCountriesWithNoIncrease();
        System.out.println("getCountriesWithNoIncreaseSize");

        // Check if the map has the correct size
        assertEquals(24, result.size());
    }


    /** ex 3
     * Tests the method getCountriesWithNoIncrease() from the Distribution class.
     * It checks if the returned map has the expected values for the difference from last year for each country and powertrain type.
     * The expected values are hardcoded in the assertions.
     */
    @Test
    public void testgetCountriesWithNoIncreaseCorrectValues(){
        Map<String, List<PowertrainDif>> result = distribution.getCountriesWithNoIncrease();
        System.out.println("getCountriesWithNoIncreaseCorrectValues");
        assertEquals(-13, result.get("Poland").get(0).getDifferenceFromLastYearBev());
        assertEquals(2, result.get("Poland").get(0).getDifferenceFromLastYearPhev());
        assertEquals(0, result.get("USA").get(0).getDifferenceFromLastYearBev());
        assertEquals(-21000, result.get("USA").get(1).getDifferenceFromLastYearPhev());
        assertEquals(-10000, result.get("USA").get(1).getDifferenceFromLastYearBev());
        assertEquals(2014, result.get("Brazil").get(1).getYear());
        assertEquals(-33, result.get("Brazil").get(1).getDifferenceFromLastYearPhev());
        assertEquals(-69, result.get("Brazil").get(1).getDifferenceFromLastYearBev());
        assertEquals(20, result.get("Portugal").get(2).getDifferenceFromLastYearBev());
        assertEquals(-28, result.get("Portugal").get(2).getDifferenceFromLastYearPhev());
        assertEquals(5100, result.get("Netherlands").get(1).getDifferenceFromLastYearBev());
        assertEquals(-17900, result.get("Netherlands").get(1).getDifferenceFromLastYearPhev()); 
        assertEquals(0,result.get("Mexico").get(1).getDifferenceFromLastYearPhev());
    }

    /** ex 3
     * Tests the getCountriesWithNoIncrease method of the Distribution class.
     * It checks if the returned map contains the correct year for each country with no increase.
     */
    @Test
    public void testgetCountriesWithNoIncreaseCorrectYears(){
        Map<String, List<PowertrainDif>> result = distribution.getCountriesWithNoIncrease();
        System.out.println("getCountriesWithNoIncreaseCorrectYears");
        assertEquals(2013, result.get("Poland").get(0).getYear());
        assertEquals(2019, result.get("USA").get(0).getYear());
        assertEquals(2014, result.get("Brazil").get(1).getYear());
        assertEquals(2014, result.get("Portugal").get(2).getYear());
        assertEquals(2017, result.get("Netherlands").get(1).getYear());
        assertEquals(2019, result.get("Korea").get(0).getYear());
        assertEquals(2020, result.get("New Zealand").get(0).getYear());

    }
    @Test
    public void testGetNumChargersKWSize() {

        System.out.println("numChargersKWSize");
        // Check if the map has the correct size
        SortedSet<Map.Entry<String, NumChargersKW>> result = distribution.getNumChargersKW(150);
        assertEquals(33, result.size());
    }

    /**
     * This method tests the getNumChargersKW method of the Distribution class.
     */
    @Test
    public void testGetNumChargersKWCountryOrder() {

        System.out.println("getNumChargersKWCountryOrder");
        // Check if each key is sorted and has the correct Order
        SortedSet<Map.Entry<String, NumChargersKW>> result = distribution.getNumChargersKW(150);
        Iterator<Map.Entry<String, NumChargersKW>> it = result.iterator();

        assertEquals("Germany", it.next().getKey());
        assertEquals("France", it.next().getKey());
        assertEquals("United Kingdom", it.next().getKey());
        assertEquals("Norway", it.next().getKey());
        assertEquals("Sweden", it.next().getKey());
        assertEquals("Italy", it.next().getKey());
        assertEquals("Spain", it.next().getKey());
        assertEquals("Netherlands", it.next().getKey());
        assertEquals("Austria", it.next().getKey());
        assertEquals("Switzerland", it.next().getKey());
        assertEquals("Denmark", it.next().getKey());
        assertEquals("Finland", it.next().getKey());
        assertEquals("Belgium", it.next().getKey());
        assertEquals("Poland", it.next().getKey());
        assertEquals("Portugal", it.next().getKey());
        assertEquals("Iceland", it.next().getKey());
        assertEquals("Ireland", it.next().getKey());
        assertEquals("Croatia", it.next().getKey());
        assertEquals("Hungary", it.next().getKey());
        assertEquals("Czech Republic", it.next().getKey());
        assertEquals("Greece", it.next().getKey());
        assertEquals("Morocco", it.next().getKey());
        assertEquals("Romania", it.next().getKey());
        assertEquals("Slovenia", it.next().getKey());
        assertEquals("Slovakia", it.next().getKey());
        assertEquals("Turkey", it.next().getKey());
        assertEquals("Serbia", it.next().getKey());
        assertEquals("Bulgaria", it.next().getKey());
        assertEquals("Latvia", it.next().getKey());
        assertEquals("Liechtenstein", it.next().getKey());
        assertEquals("Lithuania", it.next().getKey());
        assertEquals("Luxembourg", it.next().getKey());
        assertEquals("Russia", it.next().getKey());
    }

     /**
     * This method tests the getNumChargersKW method of the Distribution class.
     */
    @Test
    public void testGetNumChargersKWEachCountryValues() {

        System.out.println("getNumChargersKWEachCountryValues");
        // Check if each key has the correct values
        SortedSet<Map.Entry<String, NumChargersKW>> result = distribution.getNumChargersKW(150);
        Iterator<Map.Entry<String, NumChargersKW>> it = result.iterator();
        Map.Entry<String, NumChargersKW> map = it.next();
        
        assertEquals(50, map.getValue().getTotalBelowKW());
        assertEquals(133,map.getValue().getTotalAboveKW());
        assertEquals(183,map.getValue().getTotal());

        map = it.next();
        assertEquals(69, map.getValue().getTotalBelowKW());
        assertEquals(84,map.getValue().getTotalAboveKW());
        assertEquals(153,map.getValue().getTotal());

        map = it.next();
        assertEquals(62, map.getValue().getTotalBelowKW());
        assertEquals(88,map.getValue().getTotalAboveKW());
        assertEquals(150,map.getValue().getTotal());

        map = it.next();
        assertEquals(56, map.getValue().getTotalBelowKW());
        assertEquals(72,map.getValue().getTotalAboveKW());
        assertEquals(128,map.getValue().getTotal());

        map = it.next();
        assertEquals(32, map.getValue().getTotalBelowKW());
        assertEquals(42,map.getValue().getTotalAboveKW());
        assertEquals(74,map.getValue().getTotal());

        map = it.next();
        assertEquals(31, map.getValue().getTotalBelowKW());
        assertEquals(42,map.getValue().getTotalAboveKW());
        assertEquals(73,map.getValue().getTotal());

        map = it.next();
        assertEquals(35, map.getValue().getTotalBelowKW());
        assertEquals(25,map.getValue().getTotalAboveKW());
        assertEquals(60,map.getValue().getTotal());

        map = it.next();
        assertEquals(19, map.getValue().getTotalBelowKW());
        assertEquals(25,map.getValue().getTotalAboveKW());
        assertEquals(44,map.getValue().getTotal());

        map = it.next();
        assertEquals(22, map.getValue().getTotalBelowKW());
        assertEquals(16,map.getValue().getTotalAboveKW());
        assertEquals(38,map.getValue().getTotal());

        map = it.next();
        assertEquals(17, map.getValue().getTotalBelowKW());
        assertEquals(15,map.getValue().getTotalAboveKW());
        assertEquals(32,map.getValue().getTotal());

        map = it.next();
        assertEquals(11, map.getValue().getTotalBelowKW());
        assertEquals(14,map.getValue().getTotalAboveKW());
        assertEquals(25,map.getValue().getTotal());

        map = it.next();
        assertEquals(7, map.getValue().getTotalBelowKW());
        assertEquals(17,map.getValue().getTotalAboveKW());
        assertEquals(24,map.getValue().getTotal());

        map = it.next();
        assertEquals(7, map.getValue().getTotalBelowKW());
        assertEquals(13,map.getValue().getTotalAboveKW());
        assertEquals(20,map.getValue().getTotal());


        map = it.next();
        assertEquals(9, map.getValue().getTotalBelowKW());
        assertEquals(3,map.getValue().getTotalAboveKW());
        assertEquals(12,map.getValue().getTotal());

        map = it.next();
        assertEquals(7, map.getValue().getTotalBelowKW());
        assertEquals(3,map.getValue().getTotalAboveKW());
        assertEquals(10,map.getValue().getTotal());

        map = it.next();
        assertEquals(3, map.getValue().getTotalBelowKW());
        assertEquals(6,map.getValue().getTotalAboveKW());
        assertEquals(9,map.getValue().getTotal());

        map = it.next();
        assertEquals(5, map.getValue().getTotalBelowKW());
        assertEquals(4,map.getValue().getTotalAboveKW());
        assertEquals(9,map.getValue().getTotal());

        map = it.next();
        assertEquals(8, map.getValue().getTotalBelowKW());
        assertEquals(0,map.getValue().getTotalAboveKW());
        assertEquals(8,map.getValue().getTotal());

        map = it.next();
        assertEquals(3, map.getValue().getTotalBelowKW());
        assertEquals(5,map.getValue().getTotalAboveKW());
        assertEquals(8,map.getValue().getTotal());

        map = it.next();
        assertEquals(4, map.getValue().getTotalBelowKW());
        assertEquals(3,map.getValue().getTotalAboveKW());
        assertEquals(7,map.getValue().getTotal());

        map = it.next();
        assertEquals(1, map.getValue().getTotalBelowKW());
        assertEquals(5,map.getValue().getTotalAboveKW());
        assertEquals(6,map.getValue().getTotal());

        map = it.next();
        assertEquals(6, map.getValue().getTotalBelowKW());
        assertEquals(0,map.getValue().getTotalAboveKW());
        assertEquals(6,map.getValue().getTotal());

        map = it.next();
        assertEquals(0, map.getValue().getTotalBelowKW());
        assertEquals(6,map.getValue().getTotalAboveKW());
        assertEquals(6,map.getValue().getTotal());

        map = it.next();
        assertEquals(4, map.getValue().getTotalBelowKW());
        assertEquals(1,map.getValue().getTotalAboveKW());
        assertEquals(5,map.getValue().getTotal());

        map = it.next();
        assertEquals(3, map.getValue().getTotalBelowKW());
        assertEquals(1,map.getValue().getTotalAboveKW());
        assertEquals(4,map.getValue().getTotal());

        map = it.next();
        assertEquals(0, map.getValue().getTotalBelowKW());
        assertEquals(4,map.getValue().getTotalAboveKW());
        assertEquals(4,map.getValue().getTotal());

        map = it.next();
        assertEquals(2, map.getValue().getTotalBelowKW());
        assertEquals(1,map.getValue().getTotalAboveKW());
        assertEquals(3,map.getValue().getTotal());

        map = it.next();
        assertEquals(1, map.getValue().getTotalBelowKW());
        assertEquals(1,map.getValue().getTotalAboveKW());
        assertEquals(2,map.getValue().getTotal());

        map = it.next();
        assertEquals(1, map.getValue().getTotalBelowKW());
        assertEquals(1,map.getValue().getTotalAboveKW());
        assertEquals(2,map.getValue().getTotal());


        map = it.next();
        assertEquals(1, map.getValue().getTotalBelowKW());
        assertEquals(0,map.getValue().getTotalAboveKW());
        assertEquals(1,map.getValue().getTotal());

        map = it.next();
        assertEquals(0, map.getValue().getTotalBelowKW());
        assertEquals(1,map.getValue().getTotalAboveKW());
        assertEquals(1,map.getValue().getTotal());

        map = it.next();
        assertEquals(1, map.getValue().getTotalBelowKW());
        assertEquals(0,map.getValue().getTotalAboveKW());
        assertEquals(1,map.getValue().getTotal());

        map = it.next();
        assertEquals(1, map.getValue().getTotalBelowKW());
        assertEquals(0,map.getValue().getTotalAboveKW());
        assertEquals(1,map.getValue().getTotal());
    }

    /** ex 8
     * Tests the method getCountryTopNCharger of the Distribution class, which returns the top N charging capacity states of a given list of countries.
     */
    @Test
    public void testCountryTopNChargerStatesCountryList() {

        System.out.println("countryTopNChargerStatesCountryList");
        List<String> countries = new ArrayList<>();
        countries.add("Portugal");
        countries.add("Spain");
        countries.add("Netherlands");
        countries.add("Luxembourg");
        countries.add("Sweden");
        ChargingCapacity result = distribution.getCountryTopNCharger(3, countries);

        assertTrue(result.getStates().contains("Utrecht"));
        assertTrue(result.getStates().contains("South Holland"));
        assertTrue(result.getStates().contains("Andalusia"));
    }

    /** ex 8
     * Tests the method that returns the top N charging cities for a given list of countries.
     */
    @Test
    public void testCountryTopNChargerCitiesCountryList() {

        System.out.println("countryTopNChargerCitiesCountryList");
        List<String> countries = new ArrayList<>();
        countries.add("Portugal");
        countries.add("Spain");
        countries.add("Netherlands");
        countries.add("Luxembourg");
        countries.add("Sweden");
        ChargingCapacity result = distribution.getCountryTopNCharger(3, countries);

        assertTrue(result.getCities().contains("Algeciras"));
        assertTrue(result.getCities().contains("C�llar"));
        assertTrue(result.getCities().contains("Granada"));
        assertTrue(result.getCities().contains("Sevilla"));
        assertTrue(result.getCities().contains("Breukelen"));
        assertTrue(result.getCities().contains("Meerkerk"));
    }

    /** ex 8
     * Tests the method that returns the top N chargers in a list of countries, based on their charging capacity.
     */
    @Test
    public void testCountryTopNChargerCapacityCountryList() {

        System.out.println("countryTopNChargerCapacityCountryList");
        List<String> countries = new ArrayList<>();
        countries.add("Portugal");
        countries.add("Spain");
        countries.add("Netherlands");
        countries.add("Luxembourg");
        countries.add("Sweden");
        ChargingCapacity result = distribution.getCountryTopNCharger(3, countries);

        assertTrue(result.getTotalCapacity() == 78000);
    }

    /** ex 8
     * when the top N chargers is zero and the country list is not empty.
     */
    @Test
    public void testCountryTopNChargerTopZeroCountryList() {

        System.out.println("countryTopNChargerTopZeroCountryList");
        List<String> countries = new ArrayList<>();
        countries.add("Portugal");
        countries.add("Spain");
        countries.add("Netherlands");
        countries.add("Luxemburg");
        countries.add("Sweden");

        assertThrows(IllegalArgumentException.class, () -> distribution.getCountryTopNCharger(0, countries));
    }

    /** ex 8
     * Tests the getCountryTopNCharger method of the Distribution class when the country list is empty.
     */
    @Test
    public void testCountryTopNChargerEmptyCountryList() {

        System.out.println("countryTopNChargerEmptyCountryList");
        List<String> countries = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> distribution.getCountryTopNCharger(3, countries));
    }

    /** ex 8
     * Tests the method getCountryTopNCharger() from the Distribution class 
     * when an inexistent country is given.
     */
    @Test
    public void testCountryTopNChargerInexistentCountry() {

        System.out.println("countryTopNChargerInexistentCountry");
        List<String> countries = new ArrayList<>();

        countries.add("Portugal");
        countries.add("Spain");
        countries.add("Netherlands");
        countries.add("Portucalense");
        countries.add("Sweden");
        assertThrows(IllegalArgumentException.class, () -> distribution.getCountryTopNCharger(3, countries));
    }

    /** ex 8
     * Tests the method countryTopNChargerStatesStateList from the Distribution class.
     */
    @Test
    public void testCountryTopNChargerStatesStateList() {

        System.out.println("countryTopNChargerStatesStateList");
        List<String> states = new ArrayList<>();
        states.add("Alentejo");
        states.add("Norte");
        states.add("Algarve");
        states.add("Centro");
        ChargingCapacity result = distribution.getCountryTopNCharger(3, states);

        assertTrue(result.getStates().contains("Centro"));
        assertTrue(result.getStates().contains("Norte"));
        assertTrue(result.getStates().contains("Centro"));
    }

    /** ex 8
     * Tests testCountryTopNChargerCitiesStateList.
     */
    @Test
    public void testCountryTopNChargerCitiesStateList() {

        System.out.println("countryTopNChargerCitiesStateList");
        List<String> states = new ArrayList<>();
        states.add("Alentejo");
        states.add("Norte");
        states.add("Algarve");
        states.add("Centro");
        ChargingCapacity result = distribution.getCountryTopNCharger(3, states);

        assertTrue(result.getCities().contains("F�tima"));
        assertTrue(result.getCities().contains("Mealhada"));
        assertTrue(result.getCities().contains("Castelo Branco"));
        assertTrue(result.getCities().contains("Guarda"));
        assertTrue(result.getCities().contains("Ribeira de Pena"));
        assertTrue(result.getCities().contains("Matosinhos"));
        assertTrue(result.getCities().contains("Alcantarilha"));
        assertTrue(result.getCities().contains("Almancil"));
    }

    /** ex 8
     * Tests if the total capacity of the top N chargers in a list of states matches
     * the expected value.
     */
    @Test
    public void testCountryTopNChargerCapacityStateList() {

        System.out.println("countryTopNChargerCapacityStateList");
        List<String> states = new ArrayList<>();
        states.add("Alentejo");
        states.add("Norte");
        states.add("Algarve");
        states.add("Centro");
        ChargingCapacity result = distribution.getCountryTopNCharger(3, states);

        assertTrue(result.getTotalCapacity() == 16000);
    }

    /** ex 8
     * Tests the class when the topN parameter is zero and a list of states is provided.
     */
    @Test
    public void testCountryTopNChargerTopZeroStateList() {

        System.out.println("countryTopNChargerTopZeroStateList");
        List<String> states = new ArrayList<>();
        states.add("Alentejo");
        states.add("Norte");
        states.add("Algarve");
        states.add("Centro");

        assertThrows(IllegalArgumentException.class, () -> distribution.getCountryTopNCharger(0, states));
    }

    /** ex 8
     * Tests the method getCountryTopNCharger with an empty list of states.
     */
    @Test
    public void testCountryTopNChargerEmptyStateList() {

        System.out.println("countryTopNChargerEmptyStateList");
        List<String> states = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> distribution.getCountryTopNCharger(3, states));
    }

    /** ex 8
     * This test ensures that an thrown when attempting to get the top N chargers 
     * for a list of countries and states.
     */
    @Test
    public void testCountryTopNChargerCountryAndStateList() {

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

        assertThrows(IllegalArgumentException.class, () -> distribution.getCountryTopNCharger(3, countryAndStates));
    }

    /** ex 8
     * Tests the class when an inexistent state is provided in the list of states.
     */
    @Test
    public void testCountryTopNChargerInexistentState() {

        System.out.println("countryTopNChargerInexistentState");
        List<String> states = new ArrayList<>();

        states.add("Algarve");
        states.add("Norte");
        states.add("Nortada");
        assertThrows(IllegalArgumentException.class, () -> distribution.getCountryTopNCharger(3, states));
    }

    /**
     * Tests the getCharger method of the Distribution class.
     */
    @Test
    public void testGetCharger() {
        Map<String, List<Charger>> chargersMap = distribution.getCharger();
        assertNotNull(chargersMap);
        assertFalse(chargersMap.isEmpty());
        assertTrue(chargersMap.containsKey("Portugal"));
        assertTrue(chargersMap.containsKey("Spain"));
        assertTrue(chargersMap.containsKey("France"));
        assertTrue(chargersMap.containsKey("Germany"));
        assertTrue(chargersMap.containsKey("Italy"));
        assertTrue(chargersMap.containsKey("United Kingdom"));
        assertTrue(chargersMap.containsKey("Netherlands"));
        assertTrue(chargersMap.containsKey("Switzerland"));
        assertTrue(chargersMap.containsKey("Austria"));
        assertTrue(chargersMap.containsKey("Belgium"));
        assertTrue(chargersMap.containsKey("Norway"));
        assertTrue(chargersMap.containsKey("Sweden"));
        assertTrue(chargersMap.containsKey("Denmark"));
        assertTrue(chargersMap.containsKey("Finland"));
        assertTrue(chargersMap.containsKey("Ireland"));
        assertTrue(chargersMap.containsKey("Poland"));
        assertTrue(chargersMap.containsKey("Czech Republic"));
        assertTrue(chargersMap.containsKey("Slovakia"));
        assertTrue(chargersMap.containsKey("Hungary"));
        assertTrue(chargersMap.containsKey("Romania"));
        assertTrue(chargersMap.containsKey("Bulgaria"));
        assertTrue(chargersMap.containsKey("Greece"));
        assertTrue(chargersMap.containsKey("Turkey"));
        assertTrue(chargersMap.containsKey("Russia"));
    }

    /**
     * Tests the getEvSales method of the Distribution class.
     */
    @Test
    public void testGetEvSales() {
        Map<String, List<EvSales>> evSalesMap = distribution.getEvSales();
        assertNotNull(evSalesMap);
        assertFalse(evSalesMap.isEmpty());
        assertTrue(evSalesMap.containsKey("Austria"));
        assertTrue(evSalesMap.containsKey("Australia"));
        assertTrue(evSalesMap.containsKey("Belgium"));
        assertTrue(evSalesMap.containsKey("Canada"));
        assertTrue(evSalesMap.containsKey("Chile"));
        assertTrue(evSalesMap.containsKey("China"));
        assertTrue(evSalesMap.containsKey("Denmark"));
        assertTrue(evSalesMap.containsKey("Finland"));
        assertTrue(evSalesMap.containsKey("France"));
        assertTrue(evSalesMap.containsKey("Germany"));
        assertTrue(evSalesMap.containsKey("Greece"));
        assertTrue(evSalesMap.containsKey("Iceland"));
        assertTrue(evSalesMap.containsKey("Italy"));
        assertTrue(evSalesMap.containsKey("Japan"));
        assertTrue(evSalesMap.containsKey("Korea"));
        assertTrue(evSalesMap.containsKey("Mexico"));
        assertTrue(evSalesMap.containsKey("Netherlands"));
        assertTrue(evSalesMap.containsKey("New Zealand"));
        assertTrue(evSalesMap.containsKey("Norway"));
        assertTrue(evSalesMap.containsKey("Poland"));
        assertTrue(evSalesMap.containsKey("Portugal"));
        assertTrue(evSalesMap.containsKey("Spain"));
        assertTrue(evSalesMap.containsKey("Sweden"));
        assertTrue(evSalesMap.containsKey("Switzerland"));
        assertTrue(evSalesMap.containsKey("United Kingdom"));
    }
}