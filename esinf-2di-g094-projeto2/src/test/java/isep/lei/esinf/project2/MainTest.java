package isep.lei.esinf.project2;

import isep.lei.esinf.project2.domain.trip.TripOriginDest;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

import isep.lei.esinf.project2.domain.MaxMinAverageDayNum;
import isep.lei.esinf.project2.domain.domainAVL.TimeStamp;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {

        private Main main;

        /**
         * Sets up the test fixture.
         * (Called before every test case method.)
         */
        @Before
        public void setUp() {
                main = new Main();
        }

        /**
         * Test case to verify if the size of the dynamic list is correct.
         * It checks if the size of the list returned by the getTrips method is equal to
         * 461047.
         */
        // @Test
        public void testBuildDynamicListSize() {
                assertEquals(461047, main.getTrips().size());
        }

        /**
         * Test the size of the combined static list of vehicles.
         * 
         */
        // @Test
        public void testBuildCombinedStaticListSize() {
                assertEquals(384, main.getVehicles().size());
        }

        /**
         * Test case for the method exercicio5, which prints the AVL tree of TimeStamps
         * within a given geographical area.
         * 
         * @see Main#exercicio5(String, String, String, String)
         */
        @Test
        public void printAVL() {

                List<TimeStamp> output = main.exercicio5("48.28457278", "-80.74321417", "43.29456361", "-86.70448778");
                for (TimeStamp timeStamp : output) {
                        System.out.println(timeStamp.toString());
                }
        }

        /**
         * Test case for the normal scenario of the exercise 1.
         */
        @Test
        public void testExercicio1NormalCase() {
                System.out.println("testExercício4 Normal Case");

                assertEquals(772, main.exercicio1(10, "2213").getTripRecordsNum());

        }

        /**
         * Test case for the method exercicio1() in Main class with an invalid vehicle
         * ID.
         * It asserts that an IllegalArgumentException is thrown when the method is
         * called with the given parameters.
         */
        @Test
        public void testExercicio1InvalidVehicle() {
                /*
                 * 0,1,3,4,6,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,
                 * 35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60
                 * ,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,
                 * 86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,109
                 * ,...
                 */
                System.out.println("testExercício1 Invalid Vehicle");
                int vehicleId = 0;
                String tripId = "2213";

                assertThrows(IllegalArgumentException.class, () -> main.exercicio1(vehicleId, tripId));
        }

        /**
         * Test case for the method exercicio1 when the vehicle has no trips.
         */
        @Test
        public void testExercicio1VehicleWithNoTrips() {
                /*
                 * 2,5,7,8,12,110,116,119,120,123,125,126,129,130,131,133,137,138,142,143,145,
                 * 148,150,153,155,159,160,162,164,165,167,172,176,179,187,190,191,193,202,207,
                 * 211,212,214,215,216,222,225,234,237,238,240,241,246,247,250,251,254,255,257,
                 * 258,260,262,264,265,272,273,274,278,280,288,289,291,292,293,298,300,302,306,
                 * 308,312,315,318,321,324,325,328,329,330,332,333,337,344,345,346,348,354,357,
                 * 359,360,368,376,378,380,386,394,402,403,404,407,409,413,414,426,428,432,433,
                 * 434,435,440,448,454,460,461,473,474,476,477,483,485,486,487,489,494,500,502,
                 * 503,504,505,506,516,517,519,522,527,535,539,543,548,552,555,558,571,573,577,
                 * 578,580,587,591,592,599,602,610,616,618,624,625,630,9,379,431,453,497,545,
                 */
                System.out.println("testExercício1 Vehicle With No Trips");
                int vehicleId = 2;
                String tripId = "";

                assertThrows(IllegalArgumentException.class, () -> main.exercicio1(vehicleId, tripId));
        }

        /**
         * Tests the method exercicio1 with a null vehicleId parameter.
         */
        @Test
        public void testExercicio1NullVehicle() {
                System.out.println("testExercício1 Empty List");
                Integer vehicleId = null;
                String tripId = "2213";
                assertThrows(Throwable.class, () -> main.exercicio1(vehicleId, tripId));

        }

        /**
         * Tests the method exercicio1 with a null tripId parameter.
         */
        @Test
        public void testExercicio1NullTrip() {
                System.out.println("testExercício1 Null List");
                int vehicleId = 10;
                String tripId = null;

                assertThrows(Throwable.class, () -> main.exercicio1(vehicleId, tripId));

        }

        /**
         * Test case for the scenario where there is no trip available for the given
         * vehicle.
         */
        @Test
        public void testExercicio1NoTrip() {
                System.out.println("testExercício1 No Trip");
                int vehicleId = 10;
                String tripId = "1";

                assertThrows(IllegalArgumentException.class, () -> main.exercicio1(vehicleId, tripId));
        }

        /**
         * Test case for the method exercicio2.
         * It tests the calculation of the maximum, minimum and average values of
         * vehicle speed, absolute load and outside air temperature
         * for a given range of days.
         * It prints the results for each vehicle type.
         */
        @Test
        public void testExercicio2() {
                System.out.println("testExercício 2");
                String dayNum1 = "150";
                String dayNum2 = "156";
                List<MaxMinAverageDayNum> result = main.exercicio2(dayNum1, dayNum2);
                for (MaxMinAverageDayNum maxMinAverageDayNum : result) {
                        System.out.println("----------------------------------");
                        System.out.println("Vehicle Type: " + maxMinAverageDayNum.getVehicleType());
                        System.out.println("Vehicle Speed [km/h]");
                        System.out.println("MaxVehicleSpeed: " +
                                        maxMinAverageDayNum.getMaxVehicleSpeed());
                        System.out.println("MinVehicleSpeed: " +
                                        maxMinAverageDayNum.getMinVehicleSpeed());
                        System.out.println("AvgVehicleSpeed: " +
                                        maxMinAverageDayNum.getAvgVehicleSpeed());
                        System.out.println("\nAbsolute Load [%]");
                        System.out.println("MaxAbsoluteLoad: " +
                                        maxMinAverageDayNum.getMaxAbsoluteLoad());
                        System.out.println("MinAbsoluteLoad: " +
                                        maxMinAverageDayNum.getMinAbsoluteLoad());
                        System.out.println("AvgAbsoluteLoad: " +
                                        maxMinAverageDayNum.getAvgAbsoluteLoad());
                        System.out.println("\nOAT [ºC]");
                        System.out.println("MaxOAT: " +
                                        maxMinAverageDayNum.getMaxOutsideAirTemperature());
                        System.out.println("MinOAT: " +
                                        maxMinAverageDayNum.getMinOutsideAirTemperature());
                        System.out.println("AvgOAT: " +
                                        maxMinAverageDayNum.getAvgOutsideAirTemperature());
                }

        }

        /**
         * Test case for the method exercicio2 when dayNum is zero.
         * It should throw an IllegalArgumentException.
         */
        @Test
        public void testExercicio2DayNumZero() {
                System.out.println("exercicio2DayNumZero");
                assertThrows(IllegalArgumentException.class, () -> main.exercicio2("0", "0"));
        }

        /**
         * Test case for the method exercicio2 when both parameters are equal.
         * It should throw an IllegalArgumentException.
         */
        @Test
        public void testExercicio2DayNumEquals() {
                System.out.println("exercicio2DayNumEquals");
                assertThrows(IllegalArgumentException.class, () -> main.exercicio2("150", "150"));
        }

        /**
         * Test case for the method exercicio2 when the second day number is smaller
         * than the first one.
         * It should throw an IllegalArgumentException.
         */
        @Test
        public void testExercicio2DayNum2Menor() {
                System.out.println("exercicio2DayNum2Menor");
                assertThrows(IllegalArgumentException.class, () -> main.exercicio2("150", "149"));
        }

        /**
         * Test case for the method exercicio2() from the Main class.
         * This test verifies if the method throws an IllegalArgumentException when the
         * input parameters are invalid.
         * 
         * @throws IllegalArgumentException when the input parameters are invalid.
         */
        @Test
        public void testExercicio2DayNumLetras() {
                System.out.println("exercicio2DayNumLetras");
                assertThrows(IllegalArgumentException.class, () -> main.exercicio2("150a", "149"));
        }

        /**
         * Test case for the method exercicio2() from the Main class.
         * Tests if the method throws an IllegalArgumentException when the first
         * argument contains a comma and the second argument is less than the first.
         * 
         * @throws IllegalArgumentException when the first argument contains a comma and
         *                                  the second argument is less than the first.
         */
        @Test
        public void testExercicio2DayNumVirgulas() {
                System.out.println("exercicio2DayNumVirgulas");
                assertThrows(IllegalArgumentException.class, () -> main.exercicio2("150,0", "149"));
        }

        // testar que o metodo exercicio2 esta a retornar os valores corretos
        /**
         * Test case for the method exercicio2 when the input is correct.
         * It tests if the method returns a list with the expected size.
         * FILEPATH:
         * /C:/Bitbucket/esinf-g94-2di-projeto2/src/test/java/isep/lei/esinf/project2/MainTest.java
         */
        @Test
        public void testExercicio2Correto() {
                System.out.println("exercicio2Correto");
                List<MaxMinAverageDayNum> result = main.exercicio2("150", "156");
                assertEquals(3, result.size());
        }

        /**
         * Test case for the exercicio3 method in the Main class, using a list of trip
         * IDs.
         * The test verifies if the method returns the expected origin and destination
         * coordinates for each trip ID.
         */
        @Test
        public void testExercicio3List() {
                LinkedList<String> tripIDS = new LinkedList<>();
                tripIDS.add("2213");
                tripIDS.add("2294");
                tripIDS.add("2003");
                tripIDS.add("1389");
                tripIDS.add("768");
                tripIDS.add("1844");
                tripIDS.add("1438");
                tripIDS.add("1368");
                tripIDS.add("2552");

                LinkedList<TripOriginDest> output = main.exercicio3(tripIDS);

                Iterator<TripOriginDest> iterable = output.iterator();

                TripOriginDest tripOriginDest = iterable.next();
                assertEquals("2213", tripOriginDest.getTripID());
                assertEquals(Optional.of(42.28457278),
                                Optional.ofNullable(tripOriginDest.getOriginLatitude()));
                assertEquals(Optional.of(-83.74321417),
                                Optional.ofNullable(tripOriginDest.getOriginLongitude()));
                assertEquals(Optional.of(42.29456361),
                                Optional.ofNullable(tripOriginDest.getDestinationLatitude()));
                assertEquals(Optional.of(-83.70448778),
                                Optional.ofNullable(tripOriginDest.getDestinationLongitude()));

                tripOriginDest = iterable.next();
                assertEquals("2294", tripOriginDest.getTripID());
                assertEquals(Optional.of(42.29039778),
                                Optional.ofNullable(tripOriginDest.getOriginLatitude()));
                assertEquals(Optional.of(-83.71835944),
                                Optional.ofNullable(tripOriginDest.getOriginLongitude()));
                assertEquals(Optional.of(42.27822722),
                                Optional.ofNullable(tripOriginDest.getDestinationLatitude()));
                assertEquals(Optional.of(-83.74609722),
                                Optional.ofNullable(tripOriginDest.getDestinationLongitude()));

                tripOriginDest = iterable.next();
                assertEquals("2003", tripOriginDest.getTripID());
                assertEquals(Optional.of(42.26920056),
                                Optional.ofNullable(tripOriginDest.getOriginLatitude()));
                assertEquals(Optional.of(-83.73446917),
                                Optional.ofNullable(tripOriginDest.getOriginLongitude()));
                assertEquals(Optional.of(42.27401139),
                                Optional.ofNullable(tripOriginDest.getDestinationLatitude()));
                assertEquals(Optional.of(-83.74820694),
                                Optional.ofNullable(tripOriginDest.getDestinationLongitude()));

                tripOriginDest = iterable.next();
                assertEquals("1389", tripOriginDest.getTripID());
                assertEquals(Optional.of(42.27770444),
                                Optional.ofNullable(tripOriginDest.getOriginLatitude()));
                assertEquals(Optional.of(-83.69887889),
                                Optional.ofNullable(tripOriginDest.getOriginLongitude()));
                assertEquals(Optional.of(42.317075),
                                Optional.ofNullable(tripOriginDest.getDestinationLatitude()));
                assertEquals(Optional.of(-83.67928361),
                                Optional.ofNullable(tripOriginDest.getDestinationLongitude()));

                tripOriginDest = iterable.next();
                assertEquals("768", tripOriginDest.getTripID());
                assertEquals(Optional.of(42.27414444),
                                Optional.ofNullable(tripOriginDest.getOriginLatitude()));
                assertEquals(Optional.of(-83.77658167),
                                Optional.ofNullable(tripOriginDest.getOriginLongitude()));
                assertEquals(Optional.of(42.28183806),
                                Optional.ofNullable(tripOriginDest.getDestinationLatitude()));
                assertEquals(Optional.of(-83.79059806),
                                Optional.ofNullable(tripOriginDest.getDestinationLongitude()));

                tripOriginDest = iterable.next();
                assertEquals("1844", tripOriginDest.getTripID());
                assertEquals(Optional.of(42.229295),
                                Optional.ofNullable(tripOriginDest.getOriginLatitude()));
                assertEquals(Optional.of(-83.73894528),
                                Optional.ofNullable(tripOriginDest.getOriginLongitude()));
                assertEquals(Optional.of(42.24369667),
                                Optional.ofNullable(tripOriginDest.getDestinationLatitude()));
                assertEquals(Optional.of(-83.73907278),
                                Optional.ofNullable(tripOriginDest.getDestinationLongitude()));

                tripOriginDest = iterable.next();
                assertEquals("1438", tripOriginDest.getTripID());
                assertEquals(Optional.of(42.23036583),
                                Optional.ofNullable(tripOriginDest.getOriginLatitude()));
                assertEquals(Optional.of(-83.69972917),
                                Optional.ofNullable(tripOriginDest.getOriginLongitude()));
                assertEquals(Optional.of(42.30757889),
                                Optional.ofNullable(tripOriginDest.getDestinationLatitude()));
                assertEquals(Optional.of(-83.68361222),
                                Optional.ofNullable(tripOriginDest.getDestinationLongitude()));
        }

        // Este teste requere uma versão curta do ficheiro de trips

        /**
         * Test for the method exercicio3 with a list of trip IDs.
         * It tests if the method returns the correct origin and destination coordinates
         * for each trip ID.
         * 
         * @see Main#exercicio3(LinkedList)
         */
        @Test
        public void testExercicio3Interval() {
                LinkedList<String> tripIDS = new LinkedList<>();
                tripIDS.add("2003");
                tripIDS.add("2552");

                LinkedList<TripOriginDest> output = main.exercicio3(tripIDS);

                Iterator<TripOriginDest> iterable = output.iterator();

                TripOriginDest tripOriginDest = iterable.next();
                assertEquals("2003", tripOriginDest.getTripID());
                assertEquals(Optional.of(42.26920056),
                                Optional.ofNullable(tripOriginDest.getOriginLatitude()));
                assertEquals(Optional.of(-83.73446917),
                                Optional.ofNullable(tripOriginDest.getOriginLongitude()));
                assertEquals(Optional.of(42.27401139),
                                Optional.ofNullable(tripOriginDest.getDestinationLatitude()));
                assertEquals(Optional.of(-83.74820694),
                                Optional.ofNullable(tripOriginDest.getDestinationLongitude()));

                tripOriginDest = iterable.next();
                assertEquals("2213", tripOriginDest.getTripID());
                assertEquals(Optional.of(42.28457278),
                                Optional.ofNullable(tripOriginDest.getOriginLatitude()));
                assertEquals(Optional.of(-83.74321417),
                                Optional.ofNullable(tripOriginDest.getOriginLongitude()));
                assertEquals(Optional.of(42.29456361),
                                Optional.ofNullable(tripOriginDest.getDestinationLatitude()));
                assertEquals(Optional.of(-83.70448778),
                                Optional.ofNullable(tripOriginDest.getDestinationLongitude()));

                tripOriginDest = iterable.next();
                assertEquals("2294", tripOriginDest.getTripID());
                assertEquals(Optional.of(42.29039778),
                                Optional.ofNullable(tripOriginDest.getOriginLatitude()));
                assertEquals(Optional.of(-83.71835944),
                                Optional.ofNullable(tripOriginDest.getOriginLongitude()));
                assertEquals(Optional.of(42.27822722),
                                Optional.ofNullable(tripOriginDest.getDestinationLatitude()));
                assertEquals(Optional.of(-83.74609722),
                                Optional.ofNullable(tripOriginDest.getDestinationLongitude()));
        }

        /**
         * Test case for the method exercicio4() in the Main class with normal input.
         * It tests if the method returns the expected output for a given input.
         */
        @Test
        public void testExercício4NormalCase() {
                System.out.println("testExercício4 Normal Case");
                List<Integer> input = new ArrayList<>(Arrays.asList(10, 11, 140, 156, 161, 169, 180));

                Map<Integer, String> output = main.exercicio4(input);

                assertEquals("2213", output.get(10));
                assertEquals("2294", output.get(11));
                assertEquals("2003", output.get(140));
                assertEquals("1389", output.get(156));
                assertEquals("768", output.get(161));
                assertEquals("1844", output.get(169));
                assertEquals("1438", output.get(180));
        }

        /**
         * Test case for the method exercicio4 with an invalid vehicle input.
         * The input list contains invalid vehicle IDs.
         * Expects an IllegalArgumentException to be thrown.
         */
        @Test
        public void testExercicio4InvalidVehicle() {
                /*
                 * 0,1,3,4,6,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,
                 * 35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60
                 * ,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,
                 * 86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,109
                 * ,...
                 */
                System.out.println("testExercício4 Invalid Vehicle");
                List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 3, 4, 6, 13, 14, 14, 16, 24));

                assertThrows(IllegalArgumentException.class, () -> main.exercicio4(input));
        }

        /**
         * Test case for the method exercicio4 when there are no trips for a given
         * vehicle.
         */
        @Test
        public void testExercicio4VehicleWithNoTrips() {
                /*
                 * 2,5,7,8,12,110,116,119,120,123,125,126,129,130,131,133,137,138,142,143,145,
                 * 148,150,153,155,159,160,162,164,165,167,172,176,179,187,190,191,193,202,207,
                 * 211,212,214,215,216,222,225,234,237,238,240,241,246,247,250,251,254,255,257,
                 * 258,260,262,264,265,272,273,274,278,280,288,289,291,292,293,298,300,302,306,
                 * 308,312,315,318,321,324,325,328,329,330,332,333,337,344,345,346,348,354,357,
                 * 359,360,368,376,378,380,386,394,402,403,404,407,409,413,414,426,428,432,433,
                 * 434,435,440,448,454,460,461,473,474,476,477,483,485,486,487,489,494,500,502,
                 * 503,504,505,506,516,517,519,522,527,535,539,543,548,552,555,558,571,573,577,
                 * 578,580,587,591,592,599,602,610,616,618,624,625,630,9,379,431,453,497,545,
                 */
                System.out.println("testExercício4 Vehicle With No Trips");
                List<Integer> input = new ArrayList<>(Arrays.asList(2, 5, 7, 8, 12, 110));

                Map<Integer, String> output = main.exercicio4(input);

                assertEquals(" No trips found", output.get(2));
                assertEquals(" No trips found", output.get(5));
                assertEquals(" No trips found", output.get(7));
                assertEquals(" No trips found", output.get(8));
                assertEquals(" No trips found", output.get(12));
                assertEquals(" No trips found", output.get(110));
        }

        /**
         * Tests the method exercicio4 with an empty list as input.
         */
        @Test
        public void testExercicio4EmptyList() {
                System.out.println("testExercício4 Empty List");
                List<Integer> input = new ArrayList<>();

                assertThrows(IllegalArgumentException.class, () -> main.exercicio4(input));

        }

        /**
         * Tests the method exercicio4 with a null list as input.
         */
        @Test
        public void testExercicio4NullList() {
                System.out.println("testExercício4 Null List");
                List<Integer> input = null;

                assertThrows(Throwable.class, () -> main.exercicio4(input));

        }

        /**
         * Tests the exercicio5 method of the Main class.
         * This method tests if the method is able to return a list of TimeStamp objects
         * given four coordinates (latitude and longitude) in the correct order.
         */
        @Test
        public void testExercicio5() {
                System.out.println("testExercício 5");
                List<TimeStamp> output = main.exercicio5("48.28457278", "-80.74321417", "43.29456361", "-86.70448778");
                for (TimeStamp timeStamp : output) {
                        System.out.println(timeStamp.toString());
                }
        }

        /**
         * Tests the closest trip calculation between two given coordinates.
         * It prints the resulting time stamps to the console.
         */
        @Test
        public void testExercicio5ClosestTrip() {
                System.out.println("testExercício 5 ClosestTrip");
                List<TimeStamp> output = main.exercicio5("48.28457278", "-80.74321417", "43.29456361", "-86.70448778");
                for (TimeStamp timeStamp : output) {
                        System.out.println(timeStamp.toString());
                }
        }

        /**
         * Tests the method exercicio5 with wrong input format.
         * Expects an IllegalArgumentException to be thrown.
         */
        @Test
        public void testExercicio5WrongFormat() {
                System.out.println("testExercicio 5 WrongFormat");
                assertThrows(IllegalArgumentException.class, () -> {
                        main.exercicio5("a", "-80.74321417", "43.29456361", "-86.70448778");
                });
        }

        /**
         * Test case for the method exercicio5 with invalid coordinates.
         * It should throw an IllegalArgumentException.
         */
        @Test
        public void testExercicio5InvalidCoordinates() {
                System.out.println("testExercicio 5 InvalidCoordinates");
                assertThrows(IllegalArgumentException.class, () -> {
                        main.exercicio5("190.28457278", "-80.74321417", "43.29456361", "-86.70448778");
                });
        }

        /**
         * Test case for the method exercicio6() in the Main class.
         * It tests the method with a specific input and prints the output to the
         * console.
         * The input consists of four coordinates and a number of decimal places to
         * round to.
         */
        @Test
        public void testExercicio6() {
                System.out.println("testExercício 6");
                List<String> output = main.exercicio6("-90.00", "-180.00", "90.00", "180.00", 2);
                System.out.println("\n" + output.toString());
        }

        /**
         * Tests the method exercicio6 with wrong format input.
         * Expects an IllegalArgumentException to be thrown.
         */
        @Test
        public void testExercicio6WrongFormat() {
                System.out.println("testExercicio 6 WrongFormat");
                assertThrows(IllegalArgumentException.class, () -> {
                        main.exercicio6("a", "-80.74321417", "43.29456361", "-86.70448778", 2);
                });
        }

        /**
         * Tests the method exercicio6 with negative value for N parameter.
         */
        @Test
        public void testExercicio6NegTopN() {
                System.out.println("testExercicio 6 NegTopN");
                assertThrows(IllegalArgumentException.class, () -> {
                        main.exercicio6("-90.00", "-80.74321417", "43.29456361", "-86.70448778", -3);
                });
        }

        /**
         * Tests the method exercicio6 with invalid coordinates.
         * Expects an IllegalArgumentException to be thrown.
         */
        @Test
        public void testExercicio6InvalidCoordinates() {
                System.out.println("testExercicio 6 InvalidCoordinates");
                assertThrows(IllegalArgumentException.class, () -> {
                        main.exercicio6("-200.00", "-80.74321417", "92.29456361", "-86.70448778", 3);
                });
        }

        /**
         * Tests the method exercicio6() with a limit value smaller than the number of
         * occurrences of each location.
         * The expected output is a list of the top N locations with the highest number
         * of occurrences, within the given coordinates.
         */
        @Test
        public void testExercicio6TopNBiggerThanLimit() {
                System.out.println("testExercicio 6 TopNBiggerThanLimit");
                List<String> output = main.exercicio6("-90.00", "-180.00", "90.00", "180.00", 200);
                System.out.println("\n" + output.toString());
        }

}