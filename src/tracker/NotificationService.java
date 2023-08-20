package tracker;

import tracker.enums.CourseType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NotificationService {
    private int notificationCounter = 0;
    private CourseDatabase courseDatabase;
    private Map<CourseType, Course> courseMap;
    private TreeMap<CourseType, List<Student>> studentsWhoWereNotifiedGroupedByCourse;
    private List<Student> studentsWhoWereNotified;

    public NotificationService(CourseDatabase courseDatabase) {
        this.courseDatabase = courseDatabase;
        this.courseMap = CourseDatabase.getCourseMap();
        this.studentsWhoWereNotifiedGroupedByCourse = new TreeMap<>();
        for (CourseType courseType: CourseType.values()) {
            studentsWhoWereNotifiedGroupedByCourse.put(courseType, new ArrayList<>());
        }
        this.studentsWhoWereNotified = new ArrayList<>();
    }

    public void notifyStudentsAboutCourseCompletion() {
        for (Map.Entry<CourseType, Course> courseEntry: courseMap.entrySet()) {
            List<Student> studentsWhoCompletedTheCourse = courseEntry.getValue().getStudentsWhoCompletedTheCourse();
            studentsWhoCompletedTheCourse.stream().forEach(student -> {
                if (!studentsWhoWereNotifiedGroupedByCourse.get(courseEntry.getKey()).contains(student)) {
                    sendEmailToStudent(student, courseEntry.getKey());
                    studentsWhoWereNotifiedGroupedByCourse.get(courseEntry.getKey()).add(student);
                    if (!studentsWhoWereNotified.contains(student)) {
                        studentsWhoWereNotified.add(student);
                        notificationCounter++;
                    }
                }
            });
        }
        System.out.printf("Total %d students have been notified.\n", notificationCounter);
        notificationCounter = 0;
    }

    private void sendEmailToStudent(Student student, CourseType courseType) {
        System.out.printf("To: %s\n", student.getEmail());
        System.out.printf("Re: Your Learning Progress\n");
        System.out.printf("Hello, %s! You have accomplished our %s course!\n", student.getFullName(), courseType.getTitle());
    }
}
