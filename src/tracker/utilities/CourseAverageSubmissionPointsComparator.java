package tracker.utilities;

import tracker.Course;

import java.util.Comparator;

public class CourseAverageSubmissionPointsComparator implements Comparator<Course> {
    @Override
    public int compare(Course course1, Course course2) {
        return Double.compare(course1.getAverageSubmissionPoints(), course2.getAverageSubmissionPoints());
    }
}
