package hw_12.file_logger;

public enum SizeUnit {
    B(1L),
    KB(1024L),
    MB(1024L * 1024),
    GB(1024L * 1024 * 1024);

    private final long multiplier;

    SizeUnit(long multiplier) {
        this.multiplier = multiplier;
    }

    public static SizeUnit fromSuffix(String suffix) {
        String unit = suffix.trim().replaceAll("[\\d_]", "").trim().toUpperCase();
        return unit.isEmpty() ? B : SizeUnit.valueOf(unit);
    }

    public long toBytes(long size) {
        return size * multiplier;
    }

    public long toBytes(String size) {
        return Long.parseLong(size.trim().replaceAll("\\D+", "").replaceAll("_", "")) * multiplier;
    }
}
