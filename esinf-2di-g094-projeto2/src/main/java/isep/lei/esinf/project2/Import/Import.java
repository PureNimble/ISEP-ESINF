package isep.lei.esinf.project2.Import;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import isep.lei.esinf.project2.domain.trip.BatteryUsage;
import isep.lei.esinf.project2.domain.trip.Coordinates;
import isep.lei.esinf.project2.domain.trip.FuelExpendures;
import isep.lei.esinf.project2.domain.trip.FuelTrimBank;
import isep.lei.esinf.project2.domain.trip.Trip;
import isep.lei.esinf.project2.domain.domainAVL.TripID;
import isep.lei.esinf.project2.domain.domainAVL.Vehicle;

public class Import {

    public List<Vehicle> mergeImportedVehicles() {
        InputStream vedStaticHev = getClass().getClassLoader().getResourceAsStream("VED_Static_Data_ICE&HEV.csv");
        InputStream vedStaticEv = getClass().getClassLoader().getResourceAsStream("VED_Static_Data_PHEV&EV.csv");
        List<Vehicle> output = new ArrayList<>();
        output.addAll(importVehicles(vedStaticHev));
        output.addAll(importVehicles(vedStaticEv));

        return output;
    }

    private List<Vehicle> importVehicles(InputStream stream) {

        List<Vehicle> output = new ArrayList<>();
        Scanner sc = new Scanner(stream);
        sc.nextLine();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] split = line.split(",");
            Vehicle v = new Vehicle(Integer.parseInt(split[0]), split[1], split[2], split[3], split[4], split[5],
                    split[6]);
            output.add(v);
        }
        sc.close();

        return output;
    }

    public List<Trip> importTrips() {
        InputStream vedWeekTrips = getClass().getClassLoader().getResourceAsStream("VED_180404_week.csv");
        List<Trip> output = new ArrayList<>();
        Scanner sc = new Scanner(vedWeekTrips);
        sc.nextLine();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] split = line.split(",");

            TripID tIntro = new TripID(split[2]);

            BatteryUsage batteryUsage = new BatteryUsage(split[12], split[13], split[14], split[15], split[16],
                    split[17]);
            Coordinates coordenates = new Coordinates(split[4], split[5]);
            FuelExpendures fuelExpendures = new FuelExpendures(split[6], split[7], split[8], split[9], split[10],
                    split[11]);
            FuelTrimBank fuelTrimBank = new FuelTrimBank(split[18], split[19], split[20], split[21]);

            Trip t = new Trip(split[0], tIntro, batteryUsage, split[3], coordenates, fuelExpendures, fuelTrimBank,
                    split[1]);

            output.add(t);
        }
        sc.close();
        return output;
    }

}
