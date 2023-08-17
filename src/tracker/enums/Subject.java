package tracker.enums;

public enum Subject {
    JAVA("Java"),
    DSA("DSA"),
    DATABASES("Databases"),
    SPRING("Spring");

    private String title;

    Subject(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
