package tracker;

import tracker.enums.CommandType;
import tracker.enums.Subject;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class StudentDatabase {
    private Scanner scanner;
    private UserInterface userInterface = null;
    private TreeMap<String, Student> students;
    private boolean isStudentCreated = false;
    private boolean isAddingStudentsFinished = false;
    private boolean isPointsAdded = false;
    private boolean isAddingPointsFinished = false;
    private boolean isFindFinished = false;

    public StudentDatabase(Scanner scanner) {
        this.students = new TreeMap<>();
        this.scanner = scanner;
    }

    public Student createStudent() throws Exception {
        try {
            String studentCredentials = scanner.nextLine();
            if (studentCredentials.equals(CommandType.BACK.getCommandPrompt())) {
                isStudentCreated = true;
                isAddingStudentsFinished = true;
                userInterface.processCommand(CommandType.BACK);
                return null;
            }
            Student newStudent = new Student(studentCredentials, getStudents());
            isStudentCreated = true;
            return newStudent;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void addStudent() {
        while (!isStudentCreated || !isAddingStudentsFinished) {
            try {
                Student newStudent = createStudent();
                if (newStudent != null) {
                    students.put(newStudent.getId(), newStudent);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        isStudentCreated = false;
        isAddingStudentsFinished = false;
    }

    public PointsRecord createPoints() throws Exception {
        try {
            String inputString = scanner.nextLine();
            if (inputString.equals(CommandType.BACK.getCommandPrompt())) {
                isPointsAdded = true;
                isAddingPointsFinished = true;
                userInterface.processCommand(CommandType.BACK);
                return null;
            }
            PointsRecord newPointsRecord = new PointsRecord(inputString, getStudents());
            isPointsAdded = true;
            return newPointsRecord;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void addPoints() {
        while (!isPointsAdded || !isAddingPointsFinished) {
            try {
                PointsRecord newPointsRecord = createPoints();
                if (newPointsRecord != null) {
                    Student studentToUpdate = students.get(newPointsRecord.getStudentId());
                    studentToUpdate.setPoints(newPointsRecord);
                    System.out.println("Points updated.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        isPointsAdded = false;
        isAddingPointsFinished = false;
    }

    public void findStudent() {
        while (!isFindFinished) {
            String studentId = scanner.nextLine();
            if (studentId.equals(CommandType.BACK.getCommandPrompt())) {
                isFindFinished = true;
                userInterface.processCommand(CommandType.BACK);
                return;
            }
            Student student = students.get(studentId);
            if (student != null) {
                TreeMap<Subject, Integer> points = student.getPoints().getPoints();
                System.out.printf("%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n", studentId,
                        points.get(Subject.JAVA),
                        points.get(Subject.DSA),
                        points.get(Subject.DATABASES),
                        points.get(Subject.SPRING));
            } else {
                System.out.printf("No student is found for id=%s\n", studentId);
            }
        }
    }

    public void listStudents() {
        if (getStudents().size() > 0) {
            System.out.println("Students:");
            for (Student student: getStudents().values()) {
                System.out.println(student.getId());
            }
        } else {
            System.out.println("No students found");
        }
    }

    public TreeMap<String, Student> getStudents() {
        return students;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }
}
