package bikeshare;

public record RideRecord(String bike, int minutes, double fee) {
    public String formatReceipt() {
        return String.format("Receipt: Bike: %s, Minutes: %d, Fee: %.1f baht", bike, minutes, fee);
    }
}
