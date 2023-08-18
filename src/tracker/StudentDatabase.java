package tracker;

import tracker.enums.CommandType;
import tracker.enums.CourseType;

import java.util.Scanner;
import java.util.TreeMap;

public class StudentDatabase {
    private Scanner scanner;
    private UserInterface userInterface = null;
    private CourseDatabase courseDatabase = null;
    private TreeMap<String, Student> students;
    private boolean isStudentCreated = false;
    private boolean isAddingStudentsFinished = false;
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

    public void findStudent() {
        while (!isFindFinished) {
            String inputString = scanner.nextLine();
            if (inputString.equals(CommandType.BACK.getCommandPrompt())) {
                isFindFinished = true;
                userInterface.processCommand(CommandType.BACK);
                return;
            }
            Student student = students.get(inputString);
            if (student != null) {
                TreeMap<CourseType, Integer> points = courseDatabase.retrieveIndividualStudentProgress(student);
                System.out.printf("%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n", student.getId(),
                        points.get(CourseType.JAVA),
                        points.get(CourseType.DSA),
                        points.get(CourseType.DATABASES),
                        points.get(CourseType.SPRING));
            } else {
                System.out.printf("No student is found for id=%s\n", student.getId());
            }
        }
    }

    public void listStudents() {
        if (getStudents().size() > 0) {
            System.out.println("Students:");
            getStudents().values()
                    .stream()
                    .forEach(student -> System.out.println(student.getId()));
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

    public void setCourseDatabase(CourseDatabase courseDatabase) {
        this.courseDatabase = courseDatabase;
    }
}
