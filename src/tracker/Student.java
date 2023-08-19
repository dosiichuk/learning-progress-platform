package tracker;
import tracker.enums.CourseType;
import tracker.utilities.CustomRegex;

import java.util.*;

public class Student implements Comparable<Student> {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private TreeMap<String, Student> students;

    public Student(String studentCredentials, TreeMap<String, Student> students) throws Exception {
        this.students = students;
        parseStudentCredentials(studentCredentials);
        id = generateStudentId();
        boolean isFirstNameValid = verifyName(firstName);
        boolean isLastNameValid = verifyName(lastName);
        boolean isEmailValid = verifyEmail(email);
        boolean isEmailInUse = verifyWhetherEmailInUse(email);
        if (!isFirstNameValid) {
            throw new RuntimeException("Incorrect first name.");
        }
        if (!isLastNameValid) {
            throw new RuntimeException("Incorrect last name.");
        }
        if (!isEmailValid) {
            throw new RuntimeException("Incorrect email.");
        }
        if(isEmailInUse) {
            throw new RuntimeException("This email is already taken.");
        }
        System.out.println("The student has been added.");
    }

    public void parseStudentCredentials(String studentCredentials) throws Exception {
        List<String> parsedStudentCredentials = new ArrayList<String>(Arrays.asList(studentCredentials.split(" ")));
        if (parsedStudentCredentials.size() < 3) {
            throw new Exception("Incorrect credentials.");
        }
        this.email = parsedStudentCredentials.get(parsedStudentCredentials.size() - 1);
        parsedStudentCredentials.remove(parsedStudentCredentials.size() - 1);
        this.firstName = parsedStudentCredentials.get(0);
        parsedStudentCredentials.remove(0);
        this.lastName = String.join(" ", parsedStudentCredentials);
    }

    public boolean verifyName(String name) {
        return CustomRegex.verifyName(name);
    }

    public boolean verifyEmail(String email) {
        return CustomRegex.verifyEmail(email);
    }

    public boolean verifyWhetherEmailInUse(String email) {
        for (Map.Entry<String, Student> studentEntry: students.entrySet()) {
            if (studentEntry.getValue().getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public String generateStudentId() {
        return String.valueOf(10000 + students.size());
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id) && email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public int compareTo(Student anotherStudent) {
        return this.getId().compareTo(anotherStudent.getId());
    }
}
