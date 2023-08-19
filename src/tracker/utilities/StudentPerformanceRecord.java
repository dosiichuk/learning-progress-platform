package tracker.utilities;

public class StudentPerformanceRecord {
    private String id;
    private int points;

    public StudentPerformanceRecord(String id, int points) {
        this.id = id;
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }
}
