package isep.lei.esinf.project2;

import java.util.*;
import java.util.stream.Collectors;

import isep.lei.esinf.project2.Import.Import;
import isep.lei.esinf.project2.domain.MaxMinAverageDayNum;
import isep.lei.esinf.project2.domain.domainAVL.DayNum;
import isep.lei.esinf.project2.domain.domainAVL.TimeStamp;
import isep.lei.esinf.project2.domain.domainAVL.TripID;
import isep.lei.esinf.project2.domain.trip.Coordinates;
import isep.lei.esinf.project2.domain.trip.Trip;
import isep.lei.esinf.project2.domain.domainAVL.Vehicle;
import isep.lei.esinf.project2.domain.domainKDTree.TripKD;
import isep.lei.esinf.project2.domain.trip.TripOriginDest;
import isep.lei.esinf.project2.domain.trip.TripSummary;
import isep.lei.esinf.project2.struct.AVL;
import isep.lei.esinf.project2.struct.KDTree.KDTree;

public class Main {

    private final List<Trip> TRIPS;
    private final List<Vehicle> VEHICLES;
    private final AVL<Vehicle> VEHICLE_AVL;
    private final AVL<TripKD> TRIP_KD_AVL;

    /**
     * This class represents the main class of the project.
     * It initializes the TRIPS, VEHICLES, VEHICLE_AVL and TRIP_KD_AVL attributes.
     */
    public Main() {
        Import importData = new Import();
        this.TRIPS = importData.importTrips();
        this.VEHICLES = importData.mergeImportedVehicles();
        this.VEHICLE_AVL = mainTree();
        this.TRIP_KD_AVL = mainKDTree();
    }

    /**
     * Returns the list of trips.
     *
     * @return the list of trips.
     */
    public List<Trip> getTrips() {
        return TRIPS;
    }

    /**
     * Returns a list of all vehicles.
     *
     * @return the list of vehicles
     */
    public List<Vehicle> getVehicles() {
        return VEHICLES;
    }

    /**
     * This class represents a summary of a trip, containing information such as
     * average speed, standard deviation of speed,
     * number of trip records, origin, destination and total time.
     */
    public TripSummary exercicio1(Integer vehicleID, String tripId) {

        vehicleVerify(List.of(vehicleID));
        if (tripId.isEmpty() || tripId.equals(null))
            throw new IllegalArgumentException("TripID cannot be empty");
        if (!VEHICLE_AVL.contains(new Vehicle(vehicleID))) // if the vehicle have no trips
            throw new IllegalArgumentException("Vehicle ID: " + vehicleID + " does not have any trips");

        TripID searchTripID = new TripID(tripId);
        Vehicle searchVehicle = VEHICLE_AVL.find(new Vehicle(vehicleID));
        TripSummary output = new TripSummary();

        searchVehicle.getDayNumAVL().inOrder().forEach(dayNum -> {

            if (dayNum.getTripAVL().contains(searchTripID)) {

                TripID searchTrip = dayNum.getTripAVL().find(searchTripID); // get the trip
                AVL<TimeStamp> timeStampTree = searchTrip.getTimeStampAVL(); // get the tree of timeStamps
                List<Double> speed = new ArrayList<>();

                timeStampTree.inOrder().forEach(timeStamp -> {
                    speed.add(timeStamp.getFuelExpendures().getSpeed()); // get all speed records
                });

                TimeStamp lastValue = timeStampTree.greatestElement();
                Double averageSpeed = getAverageSpeed(speed);

                output.setAverageSpeed(averageSpeed);
                output.setStandardDeviationSpeed(getStandardDeviationSpeed(speed, averageSpeed));
                output.setTripRecordsNum(timeStampTree.countAllNodes());
                output.setOrigin(timeStampTree.smallestElement().getCoordenates());
                output.setDestination(lastValue.getCoordenates());
                output.setTotalTime(lastValue.getTimestamp());
            }
        });
        if (output.getDestination() == null)
            throw new IllegalArgumentException("TripID: " + tripId + " does not exist");

        return output;

    }

    /**
     * The Double class wraps a value of the primitive type double in an object.
     * An object of type Double contains a single field whose type is double.
     * In addition, this class provides several methods for converting a double to a
     * String and a String to a double, as well as other constants and methods
     * useful when dealing with a double.
     */
    private Double getStandardDeviationSpeed(List<Double> speed, Double averageSpeed) {
        Double output = 0.0;
        for (Double s : speed)
            output += Math.pow(s - averageSpeed, 2); // (speedRecord - avergeSpeed) ^ 2

        output = Math.sqrt(output / speed.size()); // sqrt( ouput / number of records)

        return output;
    }

    /**
     * The Double class wraps a value of the primitive type double in an object.
     * An object of type Double contains a single field whose type is double.
     */
    private Double getAverageSpeed(List<Double> speed) {
        Double output = 0.0;

        for (Double s : speed)
            output += s;

        return output / speed.size();
    }

    /**
     * Processes a vehicle for a given trip, updating the AVL tree of vehicles with
     * the corresponding day number.
     * 
     * @param var        an integer representing the number of days before the
     *                   current trip to search for the vehicle
     * @param trip       the trip to process the vehicle for
     * @param dayNumAVL  the AVL tree of day numbers
     * @param vehicleAVL the AVL tree of vehicles
     */
    private void processVehicle(int var, Trip trip, AVL<DayNum> dayNumAVL, AVL<Vehicle> vehicleAVL) {
        for (Vehicle vehicle : VEHICLES) {
            if (vehicle.getVehID() == Integer.parseInt(TRIPS.get(TRIPS.indexOf(trip) - var).getVehId())) {

                Vehicle searchVeh = new Vehicle(
                        vehicle.getVehID(),
                        vehicle.getVehType(),
                        vehicle.getVehicleClass(),
                        vehicle.getEngineConfig(),
                        vehicle.getTransmission(),
                        vehicle.getDriveWheels(),
                        vehicle.getGeneralizedWeight(),
                        dayNumAVL);

                if (vehicleAVL.contains(searchVeh)) {
                    vehicleAVL.find(searchVeh).getDayNumAVL()
                            .insert(new DayNum((TRIPS.get(TRIPS.indexOf(trip) - var).getDayNum())));
                } else {
                    vehicleAVL.insert(searchVeh);
                }
                return;
            }
        }
    }

    /**
     * Returns a list of MaxMinAverageDayNum objects representing the maximum,
     * minimum and average number of vehicles available for each day number between
     * dayNum1 and dayNum2 (inclusive).
     * 
     * @param dayNum1 the first day number to consider
     * @param dayNum2 the last day number to consider
     * @return a list of MaxMinAverageDayNum objects representing the maximum,
     *         minimum and average number of vehicles available for each day number
     *         between dayNum1 and dayNum2 (inclusive)
     */
    public List<MaxMinAverageDayNum> exercicio2(String dayNum1, String dayNum2) {
        List<MaxMinAverageDayNum> resultListMaxMinAvg = new ArrayList<>();
        collectStatistics(VEHICLE_AVL.getRoot(), dayNum1, dayNum2, resultListMaxMinAvg);
        return resultListMaxMinAvg;
    }

    /**
     * Collects statistics from a given AVL tree of vehicles, within a range of day
     * numbers, and adds the results to a list of MaxMinAverageDayNum objects.
     * Throws IllegalArgumentException if dayNum1 and dayNum2 are both 0, are equal,
     * if dayNum2 is smaller than dayNum1, or if either dayNum contains a comma or a
     * letter.
     * 
     * @param root                the root node of the AVL tree of vehicles
     * @param dayNum1             the starting day number of the range
     * @param dayNum2             the ending day number of the range
     * @param resultListMaxMinAvg the list of MaxMinAverageDayNum objects to which
     *                            the results will be added
     */
    private void collectStatistics(AVL.Node<Vehicle> root, String dayNum1, String dayNum2,
            List<MaxMinAverageDayNum> resultListMaxMinAvg) {
        if (root == null) {
            return; // Base case
        }
        if (dayNum1.equals("0") && dayNum2.equals("0")) {
            throw new IllegalArgumentException("DayNum1 and DayNum2 cannot be 0");
        }
        if (dayNum1.equals(dayNum2)) {
            throw new IllegalArgumentException("DayNum1 and DayNum2 cannot be equal");
        }
        if (Integer.parseInt(dayNum2) < Integer.parseInt(dayNum1)) {
            throw new IllegalArgumentException("DayNum2 cannot be smaller than DayNum1");
        }
        if (dayNum1.contains(",") || dayNum2.contains(",")) {
            throw new IllegalArgumentException("DayNum1 and DayNum2 cannot contain commas");
        }
        if (!dayNum1.matches("[0-9]+") || !dayNum2.matches("[0-9]+")) {
            throw new IllegalArgumentException("DayNum1 and DayNum2 cannot contain letters");
        }
        Vehicle vehicle = root.getElement();
        if (vehicle != null) {
            AVL<DayNum> dayNumAVL = vehicle.getDayNumAVL();
            collectStatisticsFromDayNum(dayNumAVL.getRoot(), dayNum1, dayNum2, resultListMaxMinAvg,
                    vehicle.getVehType());
        }
        // Recursively traverse the tree
        collectStatistics(root.getLeft(), dayNum1, dayNum2, resultListMaxMinAvg);
        collectStatistics(root.getRight(), dayNum1, dayNum2, resultListMaxMinAvg);
    }

    /**
     * Collects statistics from a given range of DayNum nodes in an AVL tree, based
     * on a given vehicle type.
     * 
     * @param root                the root node of the AVL tree to be searched
     * @param dayNum1             the starting day number of the range
     * @param dayNum2             the ending day number of the range
     * @param resultListMaxMinAvg the list to store the resulting
     *                            MaxMinAverageDayNum objects
     * @param vehicleType         the type of vehicle to filter the statistics by
     */
    private void collectStatisticsFromDayNum(AVL.Node<DayNum> root, String dayNum1, String dayNum2,
            List<MaxMinAverageDayNum> resultListMaxMinAvg, String vehicleType) {
        if (root == null) {
            return; // Base case
        }
        DayNum dayNum = root.getElement();
        if (dayNum != null) {
            AVL<TripID> tripIDAVL = dayNum.getTripAVL();
            collectStatisticsFromTripID(tripIDAVL.getRoot(), dayNum1, dayNum2, resultListMaxMinAvg, vehicleType);
        }
        collectStatisticsFromDayNum(root.getLeft(), dayNum1, dayNum2, resultListMaxMinAvg, vehicleType);
        collectStatisticsFromDayNum(root.getRight(), dayNum1, dayNum2, resultListMaxMinAvg, vehicleType);
    }

    /**
     * Collects statistics from a given AVL tree of TripID objects, within a given
     * range of days, and adds them to a list of MaxMinAverageDayNum objects.
     * 
     * @param root                the root node of the AVL tree of TripID objects
     * @param dayNum1             the starting day number of the range
     * @param dayNum2             the ending day number of the range
     * @param resultListMaxMinAvg the list of MaxMinAverageDayNum objects to add the
     *                            statistics to
     * @param vehicleType         the type of vehicle to consider for the statistics
     */
    private void collectStatisticsFromTripID(AVL.Node<TripID> root, String dayNum1, String dayNum2,
            List<MaxMinAverageDayNum> resultListMaxMinAvg, String vehicleType) {
        if (root == null) {
            return; // Base case
        }
        TripID tripID = root.getElement();
        if (tripID != null) {
            AVL<TimeStamp> timeStampAVL = tripID.getTimeStampAVL();
            collectStatisticsFromTimeStamp(timeStampAVL.getRoot(), dayNum1, dayNum2, resultListMaxMinAvg,
                    vehicleType);
        }
        collectStatisticsFromTripID(root.getLeft(), dayNum1, dayNum2, resultListMaxMinAvg, vehicleType);
        collectStatisticsFromTripID(root.getRight(), dayNum1, dayNum2, resultListMaxMinAvg, vehicleType);
    }

    /**
     * Collects statistics from a given AVL tree node of TimeStamp elements, within
     * a given range of day numbers, for a given vehicle type.
     * Calculates the maximum, minimum and average day numbers for the given vehicle
     * type and adds them to the given list of MaxMinAverageDayNum objects.
     * If the given list already contains a MaxMinAverageDayNum object for the given
     * vehicle type, updates its statistics instead of adding a new object.
     * 
     * @param root                the root node of the AVL tree to collect
     *                            statistics from
     * @param dayNum1             the lower bound of the day number range to
     *                            consider
     * @param dayNum2             the upper bound of the day number range to
     *                            consider
     * @param resultListMaxMinAvg the list of MaxMinAverageDayNum objects to add or
     *                            update the statistics to
     * @param vehicleType         the type of vehicle to consider
     */
    private void collectStatisticsFromTimeStamp(AVL.Node<TimeStamp> root, String dayNum1, String dayNum2,
            List<MaxMinAverageDayNum> resultListMaxMinAvg, String vehicleType) {
        if (root == null) {
            return; // Base case
        }
        TimeStamp timeStamp = root.getElement();
        if (timeStamp != null) {
            MaxMinAverageDayNum dayNumStats = new MaxMinAverageDayNum(vehicleType);
            if (resultListMaxMinAvg.stream().noneMatch(e -> e.getVehicleType().equals(vehicleType))) {
                resultListMaxMinAvg.add(calculateMaxMinAvg(dayNumStats, timeStamp));
            } else {
                // update list find with some vehicleType
                resultListMaxMinAvg.stream().filter(e -> e.getVehicleType().equals(vehicleType))
                        .forEach(e -> calculateMaxMinAvg(e, timeStamp));
            }
        }
        collectStatisticsFromTimeStamp(root.getLeft(), dayNum1, dayNum2, resultListMaxMinAvg, vehicleType);
        collectStatisticsFromTimeStamp(root.getRight(), dayNum1, dayNum2, resultListMaxMinAvg, vehicleType);
    }

    /**
     * A class representing the statistics of a day, including the maximum and
     * minimum values of vehicle speed, absolute load, and outside air temperature,
     * as well as their averages.
     */
    public MaxMinAverageDayNum calculateMaxMinAvg(MaxMinAverageDayNum dayNumStats, TimeStamp timeStamp) {
        // Calculate max of Vehicle Speed
        if (timeStamp.getFuelExpendures().getSpeed() > dayNumStats.getMaxVehicleSpeed()) {
            dayNumStats.setMaxVehicleSpeed(timeStamp.getFuelExpendures().getSpeed());
        }
        // Calculate min of Vehicle Speed
        if (timeStamp.getFuelExpendures().getSpeed() < dayNumStats.getMinVehicleSpeed()) {
            dayNumStats.setMinVehicleSpeed(timeStamp.getFuelExpendures().getSpeed());
        }
        // Calculate max of Absolute Load
        if (Double.parseDouble(timeStamp.getFuelExpendures().getLoad()) > dayNumStats.getMaxAbsoluteLoad()) {
            dayNumStats.setMaxAbsoluteLoad(Double.parseDouble(timeStamp.getFuelExpendures().getLoad()));
        }
        // Calculate min of Absolute Load
        if (Double.parseDouble(timeStamp.getFuelExpendures().getLoad()) < dayNumStats.getMinAbsoluteLoad()) {
            dayNumStats.setMinAbsoluteLoad(Double.parseDouble(timeStamp.getFuelExpendures().getLoad()));
        }
        // Calculate max of Outside Air Temperature
        if (Double.parseDouble(timeStamp.getFuelExpendures().getOat()) > dayNumStats.getMaxOutsideAirTemperature()) {
            dayNumStats.setMaxOutsideAirTemperature(Double.parseDouble(timeStamp.getFuelExpendures().getOat()));
        }
        // Calculate min of Outside Air Temperature
        if (Double.parseDouble(timeStamp.getFuelExpendures().getOat()) < dayNumStats.getMinOutsideAirTemperature()) {
            dayNumStats.setMinOutsideAirTemperature(Double.parseDouble(timeStamp.getFuelExpendures().getOat()));
        }
        // Calculate avg of Vehicle Speed
        if (dayNumStats.getAvgVehicleSpeed() == 0) {
            dayNumStats.setAvgVehicleSpeed(timeStamp.getFuelExpendures().getSpeed());
        } else {
            dayNumStats.setAvgVehicleSpeed((dayNumStats.getAvgVehicleSpeed() +
                    Double.parseDouble(String.valueOf(timeStamp.getFuelExpendures().getSpeed()))) / 2);
        }
        // Calculate avg of Absolute Load
        if (dayNumStats.getAvgAbsoluteLoad() == 0) {
            dayNumStats.setAvgAbsoluteLoad(Double.parseDouble(timeStamp.getFuelExpendures().getLoad()));
        } else {
            dayNumStats.setAvgAbsoluteLoad((dayNumStats.getAvgAbsoluteLoad() +
                    Double.parseDouble(timeStamp.getFuelExpendures().getLoad())) / 2);
        }
        // Calculate avg of Outside Air Temperature
        if (dayNumStats.getAvgOutsideAirTemperature() == 0) {
            dayNumStats.setAvgOutsideAirTemperature(Double.parseDouble(timeStamp.getFuelExpendures().getOat()));
        } else {
            dayNumStats.setAvgOutsideAirTemperature((dayNumStats.getAvgOutsideAirTemperature() +
                    Double.parseDouble(timeStamp.getFuelExpendures().getOat())) / 2);
        }
        return dayNumStats;
    }

    /**
     * Returns a linked list of TripOriginDest objects based on a linked list of
     * trip IDs.
     * If the linked list of trip IDs has a size different than 2, it obtains the
     * trips and fills the output list.
     * Otherwise, it fills a new linked list with the missing trip ID and then
     * obtains the trips and fills the output list.
     * 
     * @param tripIDs a linked list of trip IDs
     * @return a linked list of TripOriginDest objects
     */
    public LinkedList<TripOriginDest> exercicio3(LinkedList<String> tripIDs) {
        LinkedList<TripOriginDest> output = new LinkedList<>();
        Map<String, TripOriginDest> tripOriginDestMap = new LinkedHashMap<>();
        if (tripIDs.size() != 2) {
            obtainTrips(tripIDs, tripOriginDestMap);
            fillTripOriginDestList(output, tripOriginDestMap);
        } else {
            LinkedList<String> filledTripIDs = fillTripList(tripIDs);
            obtainTrips(filledTripIDs, tripOriginDestMap);
            fillTripOriginDestList(output, tripOriginDestMap);
        }
        return output;
    }

    /**
     * Fills a list of trip IDs with the IDs of all trips in the AVL tree that have
     * an ID between the first and last IDs of the given list.
     * 
     * @param tripIDs the list of trip IDs to use as reference
     * @return a new list of trip IDs with the IDs of all trips in the AVL tree that
     *         have an ID between the first and last IDs of the given list, sorted
     *         in ascending order
     */
    private LinkedList<String> fillTripList(LinkedList<String> tripIDs) {
        LinkedList<String> output = new LinkedList<>();
        Iterable<TripKD> tripsInOrder = TRIP_KD_AVL.inOrder();
        for (TripKD tripKD : tripsInOrder) {
            if ((tripKD.getTripID().compareTo(tripIDs.getFirst()) > 0)
                    && (tripKD.getTripID().compareTo(tripIDs.getLast()) < 0)) {
                output.add(tripKD.getTripID());
            }
        }
        output.addFirst(tripIDs.getFirst());
        output.addLast(tripIDs.getLast());
        Collections.sort(output);
        return output;
    }

    /**
     * Obtains the origin and destination coordinates of a list of trips, given
     * their IDs.
     * 
     * @param tripIDs           The list of trip IDs.
     * @param tripOriginDestMap The map where the origin and destination coordinates
     *                          will be stored.
     */
    private void obtainTrips(LinkedList<String> tripIDs, Map<String, TripOriginDest> tripOriginDestMap) {
        for (String tripid : tripIDs) {
            Iterable<TripKD> tripsInOrder = TRIP_KD_AVL.inOrder();
            for (TripKD tripKD : tripsInOrder) {
                if ((Objects.equals(tripKD.getTripID(), tripid)) && !tripOriginDestMap.containsKey(tripid)) {
                    Coordinates origin = new Coordinates();
                    Coordinates dest = new Coordinates();
                    int max = 0;
                    int min = Integer.MAX_VALUE;
                    for (TimeStamp timeStamp : tripKD.getTimeStampKDTree().inOrder()) {
                        if (timeStamp.getTimestamp() < min) {
                            min = timeStamp.getTimestamp();
                            origin = timeStamp.getCoordenates();
                        }
                        if (timeStamp.getTimestamp() > max) {
                            max = timeStamp.getTimestamp();
                            dest = timeStamp.getCoordenates();
                        }
                    }
                    TripOriginDest tripOriginDest = new TripOriginDest(origin.getLatitude(), origin.getLongitude(),
                            dest.getLatitude(), dest.getLongitude());
                    tripOriginDestMap.put(tripKD.getTripID(), tripOriginDest);
                    break;
                }
            }
        }
    }

    /**
     * Fills a LinkedList with TripOriginDest objects from a Map of TripOriginDest
     * objects.
     * Each TripOriginDest object's tripID is set to its corresponding key in the
     * Map.
     * 
     * @param output            the LinkedList to be filled with TripOriginDest
     *                          objects
     * @param tripOriginDestMap the Map of TripOriginDest objects to be used to fill
     *                          the LinkedList
     */
    private void fillTripOriginDestList(LinkedList<TripOriginDest> output,
            Map<String, TripOriginDest> tripOriginDestMap) {
        tripOriginDestMap.forEach((key, value) -> {
            value.setTripID(key);
            output.add(value);
        });
    }

    /**
     * Returns a map with the vehicle ID as key and the ID of the trip with the
     * longest distance as value.
     * If the vehicle has no trips, the value will be " No trips found".
     * 
     * @param searchingVehicles List of vehicle IDs to search for.
     * @return Map with the vehicle ID as key and the ID of the trip with the
     *         longest distance as value.
     */
    public Map<Integer, String> exercicio4(List<Integer> searchingVehicles) {
        Map<Integer, String> output = new HashMap<>();
        vehicleVerify(searchingVehicles); // verify if the list is valid

        for (Integer vehicle : searchingVehicles) { // loop through the list of vehicles

            if (!VEHICLE_AVL.contains(new Vehicle(vehicle))) { // if the vehicle have no trips
                output.put(vehicle, " No trips found");
                continue;
            }
            Vehicle vehicleSearch = VEHICLE_AVL.find(new Vehicle(vehicle)); // get the vehicle
            Map<Double, String> tripDistance = new HashMap<>();

            vehicleSearch.getDayNumAVL().inOrder().forEach(daynum -> { // loop through the list of daynum
                daynum.getTripAVL().inOrder().forEach(trip -> { // loop through the list of trips

                    Coordinates firstCoordenates = trip.getTimeStampAVL().smallestElement().getCoordenates();
                    Coordinates lastCoordenates = trip.getTimeStampAVL().greatestElement().getCoordenates();

                    tripDistance.put(firstCoordenates.distance(lastCoordenates), trip.getId()); // get distance
                });

            });
            output.put(vehicle, tripDistance.get(Collections.max(tripDistance.keySet()))); // get the max distance
        }

        return output;

    }

    /**
     * Verifies if the given list of vehicle IDs exists in the VEHICLES list.
     * Throws an IllegalArgumentException if the list is empty or if any of the IDs
     * does not exist.
     *
     * @param searchingVehicles the list of vehicle IDs to be verified
     * @throws IllegalArgumentException if the list is empty or if any of the IDs
     *                                  does not exist
     */
    private void vehicleVerify(List<Integer> searchingVehicles) {
        if (searchingVehicles.isEmpty())
            throw new IllegalArgumentException("List of vehicles cannot be empty");

        searchingVehicles.forEach(vehicle -> {
            if (VEHICLES.stream().noneMatch(e -> e.getVehID() == vehicle)) {
                throw new IllegalArgumentException("Vehicle ID: " + vehicle + " does not exist");
            }
        });
    }

    /**
     * Finds the trip with the closest origin and destination coordinates to the
     * given lookup coordinates.
     * 
     * @param xOriginLookUp the x coordinate of the origin lookup
     * @param yOriginLookUp the y coordinate of the origin lookup
     * @param xDestLookUp   the x coordinate of the destination lookup
     * @param yDestLookUp   the y coordinate of the destination lookup
     * @return the list of timestamps of the closest trip, or null if no trip is
     *         found
     * @throws IllegalArgumentException if any of the coordinates is invalid
     */
    public List<TimeStamp> exercicio5(String xOriginLookUp, String yOriginLookUp, String xDestLookUp,
            String yDestLookUp) {

        checkCoordsString(xOriginLookUp, yOriginLookUp, xDestLookUp, yDestLookUp);
        verifyCoordinates(Double.parseDouble(xOriginLookUp), Double.parseDouble(yOriginLookUp));
        verifyCoordinates(Double.parseDouble(xDestLookUp), Double.parseDouble(yDestLookUp));
        Double minDistance = Double.MAX_VALUE;
        List<TimeStamp> closestTrip = null;
        Iterable<TripKD> tripsInOrder = TRIP_KD_AVL.inOrder();
        Coordinates originLookup = new Coordinates(xOriginLookUp, yOriginLookUp);
        Coordinates destLookup = new Coordinates(xDestLookUp, yDestLookUp);
        for (TripKD tripKD : tripsInOrder) {
            KDTree<TimeStamp> timeStampKDTree = tripKD.getTimeStampKDTree();
            List<TimeStamp> timeStampInOrder = new ArrayList<>();
            Iterator<TimeStamp> iterator = timeStampKDTree.inOrder().iterator();
            while (iterator.hasNext()) {
                timeStampInOrder.add(iterator.next());
            }
            timeStampInOrder.sort(Comparator.comparingInt(TimeStamp::getTimestamp));
            Coordinates tripOrigin = timeStampInOrder.get(0).getCoordenates();
            Coordinates tripDest = timeStampInOrder.get(timeStampInOrder.size() - 1).getCoordenates();

            Double originDist = tripOrigin.distance(originLookup);
            Double destDist = tripDest.distance(destLookup);

            if ((originDist + destDist) < minDistance) {
                minDistance = originDist + destDist;
                closestTrip = timeStampInOrder;
            }
        }

        return closestTrip;
    }

    /**
     * Returns a list of the top N trips with the highest distance between two given
     * coordinates.
     * Throws an IllegalArgumentException if topN is negative or if any of the
     * coordinates is invalid.
     * 
     * @param xOriginLookUp the latitude of the origin coordinate
     * @param yOriginLookUp the longitude of the origin coordinate
     * @param xDestLookUp   the latitude of the destination coordinate
     * @param yDestLookUp   the longitude of the destination coordinate
     * @param topN          the number of top trips to return
     * @return a list of the top N trips with the highest distance between the two
     *         given coordinates
     */
    public List<String> exercicio6(String xOriginLookUp, String yOriginLookUp, String xDestLookUp, String yDestLookUp,
            int topN) {
        if (topN < 0) {
            throw new IllegalArgumentException("TopN cannot be negative");
        }
        checkCoordsString(xOriginLookUp, yOriginLookUp, xDestLookUp, yDestLookUp);
        verifyCoordinates(Double.parseDouble(xOriginLookUp), Double.parseDouble(yOriginLookUp));
        verifyCoordinates(Double.parseDouble(xDestLookUp), Double.parseDouble(yDestLookUp));
        List<String> output = new ArrayList<>();
        Iterable<TripKD> tripsInOrder = TRIP_KD_AVL.inOrder();
        Coordinates originLookup = new Coordinates(xOriginLookUp, yOriginLookUp);
        Coordinates destLookup = new Coordinates(xDestLookUp, yDestLookUp);
        Map<TripKD, Double> tripDistance = new HashMap<>();
        for (TripKD tripKD : tripsInOrder) {
            Double distance = 0.0;
            KDTree<TimeStamp> timeStampKDTree = tripKD.getTimeStampKDTree();
            List<TimeStamp> timeStampInRange = timeStampKDTree.rangeSearch(originLookup.getLatitude(),
                    originLookup.getLongitude(), destLookup.getLatitude(), destLookup.getLongitude());
            timeStampInRange.sort(Comparator.comparingInt(TimeStamp::getTimestamp));
            if (timeStampInRange.size() == timeStampKDTree.countAllNodes()) {
                for (int i = 0; i < timeStampInRange.size() - 1; i++) {
                    TimeStamp timeStamp = timeStampInRange.get(i);
                    distance += timeStamp.getCoordenates().distance(timeStampInRange.get(i + 1).getCoordenates());
                }
                System.out.println(tripKD.getTripID() + " -> " + distance);
            }

            tripDistance.put(tripKD, distance);
        }

        List<TripKD> tripDistanceList = tripDistance.entrySet().stream()
                .sorted(Map.Entry.<TripKD, Double>comparingByValue().reversed())
                .limit(topN)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        output = tripDistanceList.stream().map(TripKD::getTripID).collect(Collectors.toList());
        return output;

    }

    /**
     * Checks if the given coordinates are valid doubles.
     * 
     * @param xOriginLookUp the x coordinate of the origin
     * @param yOriginLookUp the y coordinate of the origin
     * @param xDestLookUp   the x coordinate of the destination
     * @param yDestLookUp   the y coordinate of the destination
     * @throws IllegalArgumentException if any of the given coordinates is not a
     *                                  valid double
     */
    private void checkCoordsString(String xOriginLookUp, String yOriginLookUp, String xDestLookUp, String yDestLookUp) {
        try {
            Double.valueOf(xOriginLookUp);
            Double.valueOf(yOriginLookUp);
            Double.valueOf(xDestLookUp);
            Double.valueOf(yDestLookUp);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid double value: " + e.getMessage());
        }
    }

    /**
     * Verifies if the given latitude and longitude coordinates are valid.
     * Throws an IllegalArgumentException if the coordinates are invalid.
     *
     * @param lat the latitude coordinate to be verified
     * @param lon the longitude coordinate to be verified
     * @throws IllegalArgumentException if the coordinates are invalid
     */
    private void verifyCoordinates(double lat, double lon) {
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
    }

    /**
     * Returns an AVL tree of vehicles with their respective trips, timestamps and
     * day numbers.
     * 
     * @return AVL tree of vehicles.
     */
    private AVL<Vehicle> mainTree() {
        AVL<Vehicle> vehicleAVL = new AVL<>();
        AVL<TimeStamp> timeStampAVL = new AVL<>();
        AVL<TripID> tripIDAVL = new AVL<>();
        AVL<DayNum> dayNumAVL = new AVL<>();
        int var = 1;
        String repeatedTrip = TRIPS.get(0).getTripId().getId();
        for (Trip trip : TRIPS) {
            // if new trip
            if (!trip.getTripId().getId().equals(repeatedTrip) || (TRIPS.indexOf(trip) == TRIPS.size() - 1)) {
                if (TRIPS.indexOf(trip) == TRIPS.size() - 1)
                    var = 0;
                processVehicle(var, trip, dayNumAVL, vehicleAVL);

                Vehicle searchVehicle = new Vehicle(Integer.valueOf(trip.getVehId()));
                if (vehicleAVL.contains(searchVehicle)
                        && vehicleAVL.find(searchVehicle).getDayNumAVL().contains(new DayNum(trip.getDayNum()))) {
                    Vehicle vehicle = vehicleAVL.find(searchVehicle);
                    dayNumAVL = vehicle.getDayNumAVL();
                } else {
                    dayNumAVL = new AVL<>();
                }
                tripIDAVL = new AVL<>();
                timeStampAVL = new AVL<>();
                repeatedTrip = trip.getTripId().getId();
            }

            TripID tripID = new TripID(trip.getTripId().getId());
            TimeStamp timeStamp = new TimeStamp(
                    trip.getTimestampString(),
                    tripID,
                    trip.getBatteryUsage(),
                    trip.getCoordenates(),
                    trip.getFuelExpendures(),
                    trip.getFuelTrimBank());

            if (tripIDAVL.contains(tripID)) {
                tripIDAVL.find(tripID).getTimeStampAVL().insert(timeStamp);
            } else {
                timeStampAVL.insert(timeStamp);
                tripID.setTimeStampAVL(timeStampAVL);
                tripIDAVL.insert(tripID);
            }
            DayNum dayNum = new DayNum(trip.getDayNum());
            if (!dayNumAVL.contains(dayNum)) {
                dayNum.setTripAVL(tripIDAVL);
                dayNumAVL.insert(dayNum);
            }
        }
        return vehicleAVL;
    }

    /**
     * Builds and returns an AVL tree of TripKD objects, where each TripKD object
     * contains a KDTree of TimeStamp objects.
     * The KDTree is built using the latitude and longitude coordinates of each
     * TimeStamp object.
     *
     * @return AVL tree of TripKD objects
     */
    public AVL<TripKD> mainKDTree() {
        AVL<TripKD> tripIDAvl = new AVL<>();
        VEHICLE_AVL.inOrder().forEach(vehicle -> {
            vehicle.getDayNumAVL().inOrder().forEach(daynum -> {
                daynum.getTripAVL().inOrder().forEach(tripID -> {
                    KDTree<TimeStamp> timeStampKDTree = new KDTree<>();
                    List<TimeStamp> timeStampsList = new ArrayList<>();
                    List<Double> xCoords = new ArrayList<>();
                    List<Double> yCoords = new ArrayList<>();

                    tripID.getTimeStampAVL().inOrder().forEach(timeStamp -> {
                        xCoords.add(timeStamp.getCoordenates().getLatitude());
                        yCoords.add(timeStamp.getCoordenates().getLongitude());
                        timeStampsList.add(timeStamp);
                    });

                    timeStampKDTree.insertByMedian(timeStampsList, xCoords, yCoords);
                    tripIDAvl.insert(new TripKD(tripID.getId(), timeStampKDTree));
                });
            });
        });
        return tripIDAvl;
    }
}