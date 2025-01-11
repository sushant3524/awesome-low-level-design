import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CourseRegistrationSystem {

    Database database;

    List<Course> browseCourses(String courseCode) {

    }

    public class RegisterService {
        Map<Course, List<Student>> registeredStudents;

        public boolean registerCourse(Student student, Course course) {
            synchronized (course) {
                List<Student> students = registeredStudents.computeIfAbsent(course, c -> new ArrayList<>());
                if (students.size() < course.capacity) {
                    students.add(student);
                    student.courseIds.add(course.courseCode);
                    return true;
                }
                return false;
            }
            Objects.hash()
        }
    }

    public class Database {
        Map<String, Student> students;
        Map<String, Course> courses;

        public Database() {
            students = new ConcurrentHashMap<>();
            courses = new ConcurrentHashMap<>();
        }

        public List<Course> searchCourse(String query) {

        }

        public Student getStudent(String studentId) {
            return students.get(studentId);
        }
        public Map<String, Student> getStudents() {
            return students;
        }

        public void setStudents(Map<String, Student> students) {
            this.students = students;
        }

        public Map<String, Course> getCourses() {
            return courses;
        }

        public void setCourses(Map<String, Course> courses) {
            this.courses = courses;
        }
    }

    public class Student {
        String studentId;
        String name;
        List<String> courseIds;
    }

    public class Course {
        String courseCode;
        String courseName;
        String instructor;
        int capacity;
    }
}
