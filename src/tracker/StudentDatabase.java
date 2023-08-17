package tracker;

import tracker.enums.CommandType;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentDatabase {
    private Scanner scanner;
    private UserInterface userInterface = null;
    private Map<String, Student> students;
    private boolean isStudentCreated = false;
    private boolean isAddingStudentsFinished = false;

    public StudentDatabase(Scanner scanner) {
        this.students = new HashMap<>();
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
            Student newStudent = new Student(studentCredentials);
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
                    students.put(newStudent.getFullName(), newStudent);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        isStudentCreated = false;
        isAddingStudentsFinished = false;
    }

    public Map<String, Student> getStudents() {
        return students;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }
}
