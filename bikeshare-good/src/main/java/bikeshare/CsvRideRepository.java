package bikeshare;

import java.io.FileWriter;
import java.io.IOException;

public class CsvRideRepository implements RideRepository {
    private final String filePath;

    public CsvRideRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(RideRecord ride) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(ride.bike() + "," + ride.minutes() + "," + ride.fee() + "\n");
        } catch (IOException e) {
            System.err.println("Failed to save ride to " + filePath + ": " + e.getMessage());
        }
    }
}
