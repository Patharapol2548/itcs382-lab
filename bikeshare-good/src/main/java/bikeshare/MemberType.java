package bikeshare;

public enum MemberType {
    STUDENT(2.0),
    STAFF(3.0);

    private final double ratePerMinute;

    MemberType(double ratePerMinute) {
        this.ratePerMinute = ratePerMinute;
    }

    public double calculateFee(int minutes) {
        return minutes * ratePerMinute;
    }

    public static MemberType fromString(String text) {
        for (MemberType type : values()) {
            if (type.name().equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }
}
