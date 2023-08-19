package tracker;

import tracker.enums.CommandType;
import tracker.enums.CourseType;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CourseDatabase {
    private Scanner scanner;
    private UserInterface userInterface;
    private static Map<CourseType, Course> courseMap;
    private boolean isBatchSubmissionInProgress = false;
    private boolean isIndividualSubmissionComplete = false;

    public CourseDatabase(Scanner scanner) {
        this.scanner = scanner;
        this.courseMap = Course.initCourses();
    }

    public Submission createSubmission() throws Exception {
        try {
            String inputString = scanner.nextLine();
            if (inputString.equals(CommandType.BACK.getCommandPrompt())) {
                isIndividualSubmissionComplete = true;
                isBatchSubmissionInProgress = true;
                userInterface.processCommand(CommandType.BACK);
                return null;
            }
            Submission newSubmission = new Submission(inputString, StudentDatabase.getStudents());
            isIndividualSubmissionComplete = true;
            return newSubmission;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void processSubmission() {
        while (!isIndividualSubmissionComplete || !isBatchSubmissionInProgress) {
            try {
                Submission newSubmission = createSubmission();
                if (newSubmission != null) {
                    Student studentToUpdate = StudentDatabase.getStudents().get(newSubmission.getStudentId());
                    updateStudentProgressMap(studentToUpdate, newSubmission);
                    System.out.println("Points updated.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        isIndividualSubmissionComplete = false;
        isBatchSubmissionInProgress = false;
    }

    public static TreeMap<CourseType, Integer> retrieveIndividualStudentProgress(Student student) {
        TreeMap<CourseType, Integer> studentProgress = new TreeMap<>();
        for (CourseType courseType: CourseType.values()) {
            Map<Student, Map<String, Integer>> enrolledStudents = courseMap.get(courseType).getEnrolledStudentsProgressMap();
            Integer searchedStudentProgress = enrolledStudents.get(student).get("points");
            if (searchedStudentProgress == null) {
                studentProgress.put(courseType, 0);
            } else {
                studentProgress.put(courseType, searchedStudentProgress);
            }
        }
        return studentProgress;
    }

    public void updateStudentProgressMap(Student student, Submission submission) {
        TreeMap<CourseType, Integer> submissionPoints = submission.getPoints();
        for (Map.Entry<CourseType, Course> courseEntry: courseMap.entrySet()) {
            if (submissionPoints.get(courseEntry.getKey()) > 0) {
                courseEntry.getValue().updateEnrolledStudentsProgressMap(student, submissionPoints.get(courseEntry.getKey()));
            }
        }
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public static Map<CourseType, Course> getCourseMap() {
        return courseMap;
    }
}
