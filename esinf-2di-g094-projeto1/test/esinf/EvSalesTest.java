package esinf;

import org.junit.Test;
import static org.junit.Assert.*;


public class EvSalesTest {

    /**
     * Test of equals method, of class EvSales.
     */
    @Test
    public void testEquals1() {
        System.out.println("equals01");
        Object obj = new EvSales("Country1", "BEV", 2014, 1000);
        EvSales instance = new EvSales("Country2", "PHEV", 2014, 1800);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class EvSales.
     */
    @Test
    public void testEquals2(){
        System.out.println("equals02");
        Object obj = new EvSales("Country1", "BEV", 2014, 1000);
        EvSales instance = new EvSales("Country1", "PHEV", 2014, 1800);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class EvSales.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        EvSales instance = new EvSales("Country1", "BEV", 2018, 1000);
        int expResult = instance.getCountry().hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class EvSales.
     */
    @Test
    public void compareTo1() {
        System.out.println("compareTo01");
        EvSales o = new EvSales("Country1", "BEV", 2016, 1000);
        EvSales instance = new EvSales("Country2", "PHEV", 2016, 1800);
        int expResult = 1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class EvSales.
     */
    @Test
    public void compareTo2() {
        System.out.println("compareTo02");
        EvSales o = new EvSales("Country1", "BEV", 2015, 1000);
        EvSales instance = new EvSales("Country1", "BEV", 2016, 1800);
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class EvSales.
     */
    @Test
    public void compareTo3() {
        System.out.println("compareTo03");
        EvSales o = new EvSales("Country3", "BEV", 2016, 1000);
        EvSales instance = new EvSales("Country2", "BEV", 2015, 1800);
        int expResult = -1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test the getters of the EvSales class.
     */
    @Test
    public void testGetters() {
        System.out.println("testGetters");
        EvSales instance = new EvSales("Country1", "BEV", 2018, 1000);
        assertEquals("Country1", instance.getCountry());
        assertEquals("BEV", instance.getPowertrain());
        assertEquals(2018, instance.getYear());
        assertEquals(1000, instance.getNumber_of_vehicles());
    }

    /**
     * Tests the equals method of the EvSales class.
     */
    @Test
    public void testEquals() {
        System.out.println("testEquals");
        EvSales instance1 = new EvSales("Country1", "BEV", 2018, 1000);
        EvSales instance2 = new EvSales("Country1", "BEV", 2018, 1000);
        assertTrue(instance1.equals(instance2));
    }

    /**
     * Tests the compareTo method of the EvSales class.
     */
    @Test
    public void testCompareTo() {
        System.out.println("testCompareTo");
        EvSales instance1 = new EvSales("Country1", "BEV", 2018, 1000);
        EvSales instance2 = new EvSales("Country2", "PHEV", 2018, 1000);
        assertTrue(instance1.compareTo(instance2) < 0);
        assertTrue(instance2.compareTo(instance1) > 0);
        assertTrue(instance1.compareTo(instance1) == 0);
    }

    /**
     * Tests the setCountry method of the EvSales class.
     */
    @Test
    public void testSetCountry() {
        System.out.println("testSetCountry");
        EvSales instance = new EvSales("Country1", "BEV", 2018, 1000);
        instance.setCountry("Country2");
        assertEquals("Country2", instance.getCountry());
    }
    
    /**
     * Tests the setPowertrain method of the EvSales class.
     */
    @Test
    public void testSetPowertrain() {
        System.out.println("testSetPowertrain");
        EvSales instance = new EvSales("Country1", "BEV", 2018, 1000);
        instance.setPowertrain("PHEV");
        assertEquals("PHEV", instance.getPowertrain());
    }
    
    /**
     * Tests the setYear method of the EvSales class.
     */
    @Test
    public void testSetYear() {
        System.out.println("testSetYear");
        EvSales instance = new EvSales("Country1", "BEV", 2018, 1000);
        instance.setYear(2019);
        assertEquals(2019, instance.getYear());
    }
    
    /**
     * Test case for the setNumber_of_vehicles method of the EvSales class.
     */
    @Test
    public void testSetNumber_of_vehicles() {
        System.out.println("testSetNumber_of_vehicles");
        EvSales instance = new EvSales("Country1", "BEV", 2018, 1000);
        instance.setNumber_of_vehicles(2000);
        assertEquals(2000, instance.getNumber_of_vehicles());
    }
}