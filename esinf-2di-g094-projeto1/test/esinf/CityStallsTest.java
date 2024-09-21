package esinf;

import org.junit.Test;

import static org.junit.Assert.*;

public class CityStallsTest {

    /**
     * Test of equals method, of class CityStalls.
     */
    @Test
    public void testEquals() {
        System.out.println("equals01");
        Object obj = new CityStalls("1", 1);
        CityStalls instance = new CityStalls("2", 2);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class CityStalls.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals02");
        Object obj = new CityStalls("1", 1);
        CityStalls instance = new CityStalls("1", 1);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class CityStalls.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CityStalls instance = new CityStalls("1", 1);
        int expResult = instance.getCity().hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class CityStalls.
     */
    @Test
    public void testCompareTo01() {
        System.out.println("compareTo01");
        CityStalls o = new CityStalls("1", 1);
        CityStalls instance = new CityStalls("2", 2);
        int result = instance.compareTo(o);
        assertTrue(result>0);

    }

    /**
     * Test of compareTo method, of class CityStalls.
     */
    @Test
    public void testCompareTo02() {
        System.out.println("compareTo02");
        CityStalls o = new CityStalls("1", 1);
        CityStalls instance = new CityStalls("1", 1);
        int result = instance.compareTo(o);
        assertTrue(result==0);
    }

    /**
     * Test of compareTo method, of class CityStalls.
     */
    @Test
    public void testCompareTo03() {
        System.out.println("compareTo03");
        CityStalls o = new CityStalls("2", 2);
        CityStalls instance = new CityStalls("1", 1);
        int result = instance.compareTo(o);
        assertTrue(result<0);
    }
    
    /**
     * Test case for the getCity method of the CityStalls class.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        CityStalls instance = new CityStalls("Lisbon", 10);
        String expResult = "Lisbon";
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStalls method, of class CityStalls.
     */
    @Test
    public void testGetStalls() {
        System.out.println("getStalls");
        CityStalls instance = new CityStalls("Lisbon", 10);
        int expResult = 10;
        int result = instance.getStalls();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCity method, of class CityStalls.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        CityStalls instance = new CityStalls("Lisbon", 10);
        instance.setCity("Porto");
        String expResult = "Porto";
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStalls method, of class CityStalls.
     */
    @Test
    public void testSetStalls() {
        System.out.println("setStalls");
        CityStalls instance = new CityStalls("Lisbon", 10);
        instance.setStalls(20);
        int expResult = 20;
        int result = instance.getStalls();
        assertEquals(expResult, result);
    }
}