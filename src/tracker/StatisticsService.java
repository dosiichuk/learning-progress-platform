package tracker;

import java.util.Scanner;

public class StatisticsService {
    private UserInterface userInterface;
    private StudentDatabase studentDatabase;
    private CourseDatabase courseDatabase;
    private Scanner scanner;

    public StatisticsService(UserInterface userInterface, StudentDatabase studentDatabase, CourseDatabase courseDatabase, Scanner scanner) {
        this.userInterface = userInterface;
        this.studentDatabase = studentDatabase;
        this.courseDatabase = courseDatabase;
        this.scanner = scanner;
    }

    public void showOverallCourseStatistics() {

    }

    public void showAllCoursesSummary() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
    }


}
