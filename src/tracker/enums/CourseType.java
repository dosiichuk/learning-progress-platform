package tracker.enums;

public enum CourseType {
    JAVA("Java", 600),
    DSA("DSA", 400),
    DATABASES("Databases", 480),
    SPRING("Spring", 550);

    private String title;
    private int maxPoints;

    CourseType(String title, int maxPoints) {
        this.title = title;
        this.maxPoints = maxPoints;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}
