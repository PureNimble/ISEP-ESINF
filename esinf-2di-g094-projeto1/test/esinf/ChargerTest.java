package esinf;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChargerTest {

    /**
     * Test of equals method, of class Charger.
     */
    @Test
    public void testEquals() {
        System.out.println("equals01");
        Object obj = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        Charger instance = new Charger("2", "2", "2", "2", "2", "2", 2, 2, "2", 2, "OPEN");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Charger.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals02");
        Object obj = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Charger.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        int expResult = instance.getCountry().hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of compareTo method, of class Charger.
     */
    @Test
    public void testCompareTo01() {
        System.out.println("compareTo01");
        Charger o = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        Charger instance = new Charger("2", "2", "2", "2", "2", "2", 2, 2, "2", 2, "OPEN");
        int result = instance.compareTo(o);
        assertTrue(result>0);
    }

    /**
     * Test of compareTo method, of class Charger.
     */
    @Test
    public void testCompareTo02() {
        System.out.println("compareTo02");
        Charger o = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        int result = instance.compareTo(o);
        assertTrue(result==0);
    }

    /**
     * Test of compareTo method, of class Charger.
     */
    @Test
    public void testCompareTo03() {
        System.out.println("compareTo03");
        Charger o = new Charger("2", "2", "2", "2", "2", "2", 2, 2, "2", 2, "OPEN");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        int result = instance.compareTo(o);
        assertTrue(result<0);
    }
    
    /**
     * Tests the setSupercharger method of the Charger class.
     */
    @Test
    public void testSetSupercharger() {
        System.out.println("setSupercharger");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        String supercharger = "Tesla";
        instance.setSupercharger(supercharger);
        assertEquals(supercharger, instance.getSupercharger());
    }
    
    /**
     * Tests the setStreetAddress method of the Charger class.
     */
    @Test
    public void testSetStreetAddress() {
        System.out.println("setStreetAddress");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        String streetAddress = "123 Main St";
        instance.setStreetAddress(streetAddress);
        assertEquals(streetAddress, instance.getStreetAddress());
    }

    /**
     * It tests if the city of a Charger object is correctly set and retrieved.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        String city = "San Francisco";
        instance.setCity(city);
        assertEquals(city, instance.getCity());
    }

    /**
     * Tests the setState method of the Charger class.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        String state = "CA";
        instance.setState(state);
        assertEquals(state, instance.getState());
    }

    /**
     * Tests the setZip method of the Charger class.
     */
    @Test
    public void testSetZip() {
        System.out.println("setZip");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        String zip = "94107";
        instance.setZip(zip);
        assertEquals(zip, instance.getZip());
    }

    /**
     * Test the setCountry method of the Charger class.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        String country = "USA";
        instance.setCountry(country);
        assertEquals(country, instance.getCountry());
    }

    /**
     * Tests the setStalls method of the Charger class.
     */
    @Test
    public void testSetStalls() {
        System.out.println("setStalls");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        int stalls = 8;
        instance.setStalls(stalls);
        assertEquals(stalls, instance.getStalls());
    }

    /**
     * Test of setkW method, of class Charger.
     */
    @Test
    public void testSetkW() {
        System.out.println("setkW");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        int kW = 250;
        instance.setkW(kW);
        assertEquals(kW, instance.getkW());
    }

    /**
     * Tests the setGps method of the Charger class.
     */
    @Test
    public void testSetGps() {
        System.out.println("setGps");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        String gps = "37.7749° N, 122.4194° W";
        instance.setGps(gps);
        assertEquals(gps, instance.getGps());
    }

    /**
     * Tests the setElevm method of the Charger class.
     */
    @Test
    public void testSetElevm() {
        System.out.println("setElevm");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        int elevm = 10;
        instance.setElevm(elevm);
        assertEquals(elevm, instance.getElevm());
    }

    /**
     * Test of setStatus method, of class Charger.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        Charger instance = new Charger("1", "1", "1", "1", "1", "1", 1, 1, "1", 1, "OPEN");
        String status = "CLOSED";
        instance.setStatus(status);
        assertEquals(status, instance.getStatus());
    }
}