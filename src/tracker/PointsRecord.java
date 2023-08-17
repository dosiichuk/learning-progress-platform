package tracker;

import tracker.enums.Subject;

import java.util.TreeMap;

public class PointsRecord {
    private TreeMap<Subject, Integer> points;
    private String studentId;
    private TreeMap<String, Student> students;

    public PointsRecord(String inputString, TreeMap<String, Student> students) {
        this.students = students;
        this.points = new TreeMap<>();
        try {
            parseInputString(inputString);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void parseInputString(String inputString) throws RuntimeException {
        String[] parsedString = inputString.split(" ");
        studentId = parsedString[0];
        if (!verifyWhetherStudentExists(studentId)) {
            throw new RuntimeException(String.format("No student is found for id=%s.", studentId));
        }
        if (parsedString.length != 5) {
            throw new RuntimeException("Incorrect points format.");
        }
        try {
            for (int i = 0; i < 4; i++) {
                Integer subjectPoints = Integer.parseInt(parsedString[i + 1]);
                if (subjectPoints < 0) {
                    throw new RuntimeException("Incorrect points format.");
                }
                points.put(Subject.values()[i], subjectPoints);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Incorrect points format.");
        }
    }

    public boolean verifyWhetherStudentExists(String studentId) {
        for (String id: students.keySet()) {
            if (id.equals(studentId)) return true;
        }
        return false;
    }

    public TreeMap<Subject, Integer> getPoints() {
        return points;
    }

    public String getStudentId() {
        return studentId;
    }
}
