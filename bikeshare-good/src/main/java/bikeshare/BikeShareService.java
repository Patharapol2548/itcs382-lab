package bikeshare;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BikeShareService {
    private final Map<String, Deque<String>> stationBikes = new HashMap<>();
    private final Map<String, ActiveRental> activeRentals = new HashMap<>();
    private final RideRepository repository;

    private record ActiveRental(String bike, MemberType memberType) {}

    public BikeShareService(RideRepository repository) {
        this.repository = repository;
        stationBikes.put("Library", new ArrayDeque<>(List.of("B1", "B2")));
        stationBikes.put("Canteen", new ArrayDeque<>(List.of("B3", "B4")));
        stationBikes.put("Dorm", new ArrayDeque<>(List.of("B5", "B6")));
    }

    public Optional<String> rentBike(String memberId, MemberType memberType, String station) {
        Deque<String> bikes = stationBikes.get(station);
        if (bikes == null || bikes.isEmpty()) {
            return Optional.empty();
        }
        String bike = bikes.poll();
        activeRentals.put(memberId, new ActiveRental(bike, memberType));
        return Optional.of(bike);
    }

    public Optional<RideRecord> returnBike(String memberId, String station, int minutes) {
        ActiveRental rental = activeRentals.remove(memberId);
        if (rental == null) {
            return Optional.empty();
        }
        Deque<String> bikes = stationBikes.computeIfAbsent(station, k -> new ArrayDeque<>());
        bikes.add(rental.bike());

        double fee = rental.memberType().calculateFee(minutes);
        RideRecord record = new RideRecord(rental.bike(), minutes, fee);
        repository.save(record);
        return Optional.of(record);
    }
}
