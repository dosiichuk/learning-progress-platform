package tracker;

import tracker.enums.CourseType;

import java.util.Map;
import java.util.Scanner;

public class Tracker implements Runnable {
    private Scanner scanner;
    private UserInterface userInterface;
    private StudentDatabase studentDatabase;
    private CourseDatabase courseDatabase;
    private StatisticsService statisticsService;
    private NotificationService notificationService;

    public Tracker() {
        System.out.println("Learning Progress Tracker");
        this.scanner = new Scanner(System.in);
        this.studentDatabase = new StudentDatabase(scanner);
        this.courseDatabase = new CourseDatabase(scanner);
        this.notificationService = new NotificationService(courseDatabase);
        this.userInterface = new UserInterface(scanner, studentDatabase, courseDatabase, notificationService);
        this.studentDatabase.setUserInterface(userInterface);
        this.courseDatabase.setUserInterface(userInterface);
        this.statisticsService = new StatisticsService(userInterface,studentDatabase, courseDatabase, scanner);
        userInterface.setStatisticsService(statisticsService);
    }

    @Override
    public void run() {
        while(true) {
            userInterface.getAndProcessCommand();
        }
    }
}
