package tracker;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
    String fullName;
    String firstName;
    String lastName;
    String email;

    public Student(String studentCredentials) throws Exception {
        parseStudentCredentials(studentCredentials);
        boolean isFirstNameValid = verifyName(firstName);
        boolean isLastNameValid = verifyName(lastName);
        boolean isEmailValid = verifyEmail(email);
        if (!isFirstNameValid) {
            throw new RuntimeException("Incorrect first name.");
        }
        if (!isLastNameValid) {
            throw new RuntimeException("Incorrect last name.");
        }
        if (!isEmailValid) {
            throw new RuntimeException("Incorrect email.");
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
//                !CustomRegex.verifyThePresenceOfForbiddenCharacters(name) &&
//                CustomRegex.verifyTheCorrectnessOfHyphenUse(name) &&
//                CustomRegex.verifyTheCorrectnessOfApostropheUse(name) &&
//                CustomRegex.verifyTheCorrectnessOfSpaceUse(name);
    }

    public boolean verifyEmail(String email) {
        return CustomRegex.verifyEmail(email);
    }

    public String getFullName() {
        return String.join(" ", getFirstName(), getLastName());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
