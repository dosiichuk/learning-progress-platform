package tracker;

import tracker.enums.CourseType;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Course {
    private CourseType courseType;
    private Map<Student, Map<String, Integer>> enrolledStudentsProgressMap;

    public Course(CourseType courseType, Map<Student, Map<String, Integer>> enrolledStudentsProgressMap) {

        this.courseType = courseType;
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

    public CourseType getCourseType() {
        return courseType;
    }

    public Map<Student, Map<String, Integer>> getEnrolledStudentsProgressMap() {
        return enrolledStudentsProgressMap;
    }
}
