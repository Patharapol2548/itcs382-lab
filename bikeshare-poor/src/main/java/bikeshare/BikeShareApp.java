package bikeshare;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BikeShareApp {
    public static Map<String, List<String>> stations = new HashMap<>();
    public static Map<String, String> activeRentals = new HashMap<>();
    public static Map<String, Integer> activeMemberTypes = new HashMap<>();
    public static String csvFile = "rides.csv";

    static {
        stations.put("Library", new ArrayList<>(List.of("B1", "B2")));
        stations.put("Canteen", new ArrayList<>(List.of("B3", "B4")));
        stations.put("Dorm", new ArrayList<>(List.of("B5", "B6")));
    }

    public static String updateStationBike(String station, String bike, boolean isReturning) {
        List<String> bikes = stations.get(station);
        if (bikes == null) {
            return null;
        }
        if (isReturning) {
            bikes.add(bike);
            return bike;
        } else {
            if (bikes.isEmpty()) {
                return null;
            }
            return bikes.remove(0);
        }
    }

    public static void finishRide(String bike, int memberType, int minutes, boolean saveToFile) {
        int rate = (memberType == 1) ? 2 : 3;
        double fee = (double) minutes * rate;
        System.out.println("Receipt: Bike: " + bike + ", Minutes: " + minutes + ", Fee: " + fee + " baht");
        if (saveToFile) {
            try (FileWriter writer = new FileWriter(csvFile, true)) {
                writer.write(bike + "," + minutes + "," + fee + "\n");
            } catch (IOException e) {
                System.err.println("Failed to write to file: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Commands: rent <memberId> <type: 1=student, 2=staff> <station>, return <memberId> <station> <minutes>, quit");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(" ");
                String command = parts[0];

                if ("quit".equalsIgnoreCase(command)) {
                    break;
                } else if ("rent".equalsIgnoreCase(command)) {
                    if (parts.length < 4) {
                        System.out.println("Usage: rent <memberId> <type 1-2> <station>");
                        continue;
                    }
                    String memberId = parts[1];
                    int memberType = Integer.parseInt(parts[2]);
                    String station = parts[3];

                    String bike = updateStationBike(station, null, false);
                    if (bike == null) {
                        System.out.println("No bike available at " + station);
                    } else {
                        activeRentals.put(memberId, bike);
                        activeMemberTypes.put(memberId, memberType);
                        System.out.println("Rented bike " + bike + " to member " + memberId + " at " + station);
                    }
                } else if ("return".equalsIgnoreCase(command)) {
                    if (parts.length < 4) {
                        System.out.println("Usage: return <memberId> <station> <minutes>");
                        continue;
                    }
                    String memberId = parts[1];
                    String station = parts[2];
                    int minutes = Integer.parseInt(parts[3]);

                    String bike = activeRentals.remove(memberId);
                    if (bike == null) {
                        System.out.println("No active rental found for member " + memberId);
                        continue;
                    }
                    Integer memberType = activeMemberTypes.remove(memberId);
                    if (memberType == null) {
                        memberType = 1;
                    }

                    updateStationBike(station, bike, true);
                    finishRide(bike, memberType, minutes, true);
                } else {
                    System.out.println("Unknown command: " + command);
                }
            }
        }
    }
}
