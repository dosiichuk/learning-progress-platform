package tracker;

import tracker.enums.CourseType;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Course {
    private final CourseType courseType;
    private final String courseTitle;
    private Map<Student, Map<String, Integer>> enrolledStudentsProgressMap;
    private int numEnrolledStudents;
    private int numSubmissions;
    private double averageSubmissionPoints;
    private boolean isMostPopular = false;
    private boolean isLeastPopular = false;
    private boolean isHighestActivity = false;
    private boolean isLowestActivity = false;
    private boolean isEasiest = false;
    private boolean isHardest = false;

    public Course(CourseType courseType, Map<Student, Map<String, Integer>> enrolledStudentsProgressMap) {
        this.courseType = courseType;
        this.courseTitle = courseType.getTitle();
        this.enrolledStudentsProgressMap = enrolledStudentsProgressMap;
    }

    public void updateEnrolledStudentsProgressMap(Student student, Integer points) {
        if (enrolledStudentsProgressMap.containsKey(student)) {
            Map<String, Integer> currentProgressRecord = enrolledStudentsProgressMap.get(student);
            currentProgressRecord.put("points", currentProgressRecord.get("points") + points);
            currentProgressRecord.put("submissions", currentProgressRecord.get("submissions") + 1);

            enrolledStudentsProgressMap.put(student, currentProgressRecord);
        } else {
            Map<String, Integer> currentProgressRecord = new HashMap<>();
            currentProgressRecord.put("points", points);
            currentProgressRecord.put("submissions", 1);
            enrolledStudentsProgressMap.put(student, currentProgressRecord);
        }
    }

    public static TreeMap<CourseType, Course> initCourses() {
        TreeMap<CourseType, Course> courseMap = new TreeMap<>();
        for (CourseType courseType: CourseType.values()) {
            Map<Student, Map<String, Integer>> enrolledStudentsProgressMap = new HashMap<>();
            Course newCourse = new Course(courseType, enrolledStudentsProgressMap);
            courseMap.put(courseType, newCourse);
        }
        return courseMap;
    }


    public Map<Student, Map<String, Integer>> getEnrolledStudentsProgressMap() {
        return enrolledStudentsProgressMap;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public int getNumEnrolledStudents() {
        return numEnrolledStudents;
    }

    public int getNumSubmissions() {
        return numSubmissions;
    }

    public double getAverageSubmissionPoints() {
        return averageSubmissionPoints;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public boolean isMostPopular() {
        return isMostPopular;
    }

    public boolean isLeastPopular() {
        return isLeastPopular;
    }

    public boolean isHighestActivity() {
        return isHighestActivity;
    }

    public boolean isLowestActivity() {
        return isLowestActivity;
    }

    public boolean isEasiest() {
        return isEasiest;
    }

    public boolean isHardest() {
        return isHardest;
    }

    public void setNumEnrolledStudents(int numEnrolledStudents) {
        this.numEnrolledStudents = numEnrolledStudents;
    }

    public void setNumSubmissions(int numSubmissions) {
        this.numSubmissions = numSubmissions;
    }

    public void setAverageSubmissionPoints(double averageSubmissionPoints) {
        this.averageSubmissionPoints = averageSubmissionPoints;
    }

    public void setMostPopular(boolean mostPopular) {
        isMostPopular = mostPopular;
    }

    public void setLeastPopular(boolean leastPopular) {
        isLeastPopular = leastPopular;
    }

    public void setHighestActivity(boolean highestActivity) {
        isHighestActivity = highestActivity;
    }

    public void setLowestActivity(boolean lowestActivity) {
        isLowestActivity = lowestActivity;
    }

    public void setEasiest(boolean easiest) {
        isEasiest = easiest;
    }

    public void setHardest(boolean hardest) {
        isHardest = hardest;
    }


}
