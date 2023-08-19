package tracker.utilities;

import tracker.Course;

import java.util.Comparator;

public class CourseNumEnrolledStudentsComparator implements Comparator<Course> {
    @Override
    public int compare(Course course1, Course course2) {
        return Integer.compare(course1.getNumEnrolledStudents(), course2.getNumEnrolledStudents());
    }
}
