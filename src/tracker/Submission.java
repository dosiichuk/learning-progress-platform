package tracker;

import tracker.enums.CourseType;

import java.util.TreeMap;

public class Submission {
    private String studentId;
    private TreeMap<CourseType, Integer> points;
    private TreeMap<String, Student> students;

    public Submission(String inputString, TreeMap<String, Student> students) {
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
                Integer coursePoints = Integer.parseInt(parsedString[i + 1]);
                if (coursePoints < 0) {
                    throw new RuntimeException("Incorrect points format.");
                }
                points.put(CourseType.values()[i], coursePoints);
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

    public TreeMap<CourseType, Integer> getPoints() {
        return points;
    }

    public String getStudentId() {
        return studentId;
    }
}
