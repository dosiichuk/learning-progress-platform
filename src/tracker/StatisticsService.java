package tracker;

import tracker.enums.CourseType;
import tracker.utilities.CourseNumEnrolledStudentsComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class StatisticsService {
    private UserInterface userInterface;
    private StudentDatabase studentDatabase;
    private CourseDatabase courseDatabase;
    private Scanner scanner;
    private Map<CourseType, Course> courseMap;
    private int maxNumEnrolledStudents = 0;
    private int minNumEnrolledStudents = 0;
    private int maxNumSubmissions = 0;
    private int minNumSubmissions = 0;
    private double highestAverageSubmissionPoints = 0;
    private double lowestAverageSubmissionPoints = 0;
    private List<String> mostPopularCourses = new ArrayList<>();
    private List<String> leastPopularCourses = new ArrayList<>();
    private List<String> highestActivityCourses = new ArrayList<>();
    private List<String> lowestActivityCourses = new ArrayList<>();
    private List<String> easiestCourses = new ArrayList<>();
    private List<String> hardestCourses = new ArrayList<>();


    public StatisticsService(UserInterface userInterface, StudentDatabase studentDatabase, CourseDatabase courseDatabase, Scanner scanner) {
        this.userInterface = userInterface;
        this.studentDatabase = studentDatabase;
        this.courseDatabase = courseDatabase;
        this.scanner = scanner;
        this.courseMap = CourseDatabase.getCourseMap();
    }

    public void showIndividualCourseStatistics() {

    }

    public void showAllCoursesSummary() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        processCourseData();
        rankCourses();
        System.out.printf("Most popular: %s\n", mostPopularCourses.size() == 0 ? "n/a" : String.join(", ", mostPopularCourses));
        System.out.printf("Least popular: %s\n", leastPopularCourses.size() == 0 ? "n/a" : String.join(", ", leastPopularCourses));
        System.out.printf("Highest activity: %s\n", highestActivityCourses.size() == 0 ? "n/a" : String.join(", ", highestActivityCourses));
        System.out.printf("Lowest activity: %s\n", lowestActivityCourses.size() == 0 ? "n/a" : String.join(", ", lowestActivityCourses));
        System.out.printf("Easiest course: %s\n", easiestCourses.size() == 0 ? "n/a" : String.join(", ", easiestCourses));
        System.out.printf("Hardest course: %s\n", hardestCourses.size() == 0 ? "n/a" : String.join(", ", hardestCourses));
    }

    public void processCourseData() {
        for (CourseType courseType: CourseType.values()) {
            Course course = courseMap.get(courseType);
            Map<Student, Map<String, Integer>> enrolledStudentsProgressMap = course.getEnrolledStudentsProgressMap();
            int numEnrolledStudents = enrolledStudentsProgressMap.size();
            int numSubmissions = calculateNumberOfSubmissions(course);
            double averageSubmissionPoints = calculateAverageSubmissionPoints(course);
            course.setAverageSubmissionPoints(averageSubmissionPoints);
            course.setNumEnrolledStudents(numEnrolledStudents);
            course.setNumSubmissions(numSubmissions);
        }
    }

    public void rankCourses() {
        courseMap.values().stream().sorted(new CourseNumEnrolledStudentsComparator().reversed()).forEach(course -> {
            if (course.getNumEnrolledStudents() >= maxNumEnrolledStudents && course.getNumEnrolledStudents() > 0) {
                maxNumEnrolledStudents = course.getNumEnrolledStudents();
                course.setMostPopular(true);
            } else if (course.getNumEnrolledStudents() <= minNumEnrolledStudents && course.getNumSubmissions() > 0 && !course.isMostPopular()) {
                minNumEnrolledStudents = course.getNumEnrolledStudents();
                course.setLeastPopular(true);
            }
        });
        courseMap.values().stream().sorted(new CourseNumEnrolledStudentsComparator().reversed()).forEach(course -> {
            if (course.getNumEnrolledStudents() >= maxNumEnrolledStudents && course.getNumEnrolledStudents() > 0) {
                maxNumEnrolledStudents = course.getNumEnrolledStudents();
                course.setMostPopular(true);
            } else if (course.getNumEnrolledStudents() <= minNumEnrolledStudents && course.getNumSubmissions() > 0 && !course.isMostPopular()) {
                minNumEnrolledStudents = course.getNumEnrolledStudents();
                course.setLeastPopular(true);
            }
        });
        courseMap.values().stream().sorted(new CourseNumEnrolledStudentsComparator().reversed()).forEach(course -> {
            if (course.getNumEnrolledStudents() >= maxNumEnrolledStudents && course.getNumEnrolledStudents() > 0) {
                maxNumEnrolledStudents = course.getNumEnrolledStudents();
                course.setMostPopular(true);
            } else if (course.getNumEnrolledStudents() <= minNumEnrolledStudents && course.getNumSubmissions() > 0 && !course.isMostPopular()) {
                minNumEnrolledStudents = course.getNumEnrolledStudents();
                course.setLeastPopular(true);
            }
        });
        courseMap.values().stream().forEach(course -> {
            if (course.isMostPopular()) {
                mostPopularCourses.add(course.getCourseTitle());
            } else if (course.isLeastPopular()) {
                leastPopularCourses.add(course.getCourseTitle());
            } else if (course.isEasiest()) {
                easiestCourses.add(course.getCourseTitle());
            } else if (course.isHardest()) {
                hardestCourses.add(course.getCourseTitle());
            } else if (course.isHighestActivity()) {
                highestActivityCourses.add(course.getCourseTitle());
            } else if (course.isLowestActivity()) {
                lowestActivityCourses.add(course.getCourseTitle());
            }
        });
    }

    public double calculateAverageSubmissionPoints(Course course) {
        Map<Student, Map<String, Integer>> enrolledStudentsProgressMap = course.getEnrolledStudentsProgressMap();
        Integer totalPoints = 0;
        Integer totalSubmissions = 0;
        for (Map.Entry<Student, Map<String, Integer>> studentMapEntry: enrolledStudentsProgressMap.entrySet()) {
            totalPoints += studentMapEntry.getValue().get("points");
            totalSubmissions += studentMapEntry.getValue().get("submissions");
        }
        if (totalSubmissions == 0) {
            return 0;
        }
        return totalPoints / totalSubmissions;
    }

    public Integer calculateNumberOfSubmissions(Course course) {
        Map<Student, Map<String, Integer>> enrolledStudentsProgressMap = course.getEnrolledStudentsProgressMap();
        Integer totalSubmissions = 0;
        for (Map.Entry<Student, Map<String, Integer>> studentMapEntry: enrolledStudentsProgressMap.entrySet()) {
            totalSubmissions += studentMapEntry.getValue().get("submissions");
        }
        return totalSubmissions;
    }


}
