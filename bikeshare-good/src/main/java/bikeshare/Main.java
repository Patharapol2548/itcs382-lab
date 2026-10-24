package bikeshare;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RideRepository repository = new CsvRideRepository("rides.csv");
        BikeShareService service = new BikeShareService(repository);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Commands: rent <memberId> <student|staff> <station>, return <memberId> <station> <minutes>, quit");
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
                    handleRent(service, parts);
                } else if ("return".equalsIgnoreCase(command)) {
                    handleReturn(service, parts);
                } else {
                    System.out.println("Unknown command: " + command);
                }
            }
        }
    }

    private static void handleRent(BikeShareService service, String[] parts) {
        if (parts.length < 4) {
            System.out.println("Usage: rent <memberId> <student|staff> <station>");
            return;
        }
        String memberId = parts[1];
        MemberType type = MemberType.fromString(parts[2]);
        if (type == null) {
            System.out.println("Invalid member type. Allowed: student, staff");
            return;
        }
        String station = parts[3];

        Optional<String> bike = service.rentBike(memberId, type, station);
        if (bike.isPresent()) {
            System.out.println("Rented bike " + bike.get() + " to member " + memberId + " at " + station);
        } else {
            System.out.println("No available bike at " + station);
        }
    }

    private static void handleReturn(BikeShareService service, String[] parts) {
        if (parts.length < 4) {
            System.out.println("Usage: return <memberId> <station> <minutes>");
            return;
        }
        String memberId = parts[1];
        String station = parts[2];
        int minutes;
        try {
            minutes = Integer.parseInt(parts[3]);
        } catch (NumberFormatException e) {
            System.out.println("Minutes must be a valid integer.");
            return;
        }

        Optional<RideRecord> record = service.returnBike(memberId, station, minutes);
        if (record.isPresent()) {
            System.out.println(record.get().formatReceipt());
        } else {
            System.out.println("No active rental found for member " + memberId);
        }
    }
}
