import java.util.ArrayList;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolled;

    public Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
    }
 
    public boolean isAvailable() {
        return enrolled < capacity;
    }
    public boolean enroll() {
        if (isAvailable()) {
            enrolled++;
            return true;
        }
        return false;
    }
    public void removeStudent() {
        if (enrolled > 0) {
            enrolled--;
        }
    }

    @Override
    public String toString() {
        return courseCode + ": " + title + " (" + description + ") - Available Slots: " + (capacity - enrolled);
    }
}

class Student {
    int studentId;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
    public boolean registerForCourse(Course course) {
        if (course.enroll()) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }
    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.removeStudent();
            registeredCourses.remove(course);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder studentInfo = new StringBuilder();
        studentInfo.append("Student ID: ").append(studentId).append(", Name: ").append(name).append("\nRegistered Courses:\n");
        if (registeredCourses.isEmpty()) {
            studentInfo.append("No courses registered.");
        } else {
            for (Course course : registeredCourses) {
                studentInfo.append(course.courseCode).append(": ").append(course.title).append("\n");
            }
        }
        return studentInfo.toString();
    }
}
public class courseRegistrationSystem {
    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        addCourse("CS101", "Introduction to Programming", "Learn the basics of programming using Java.", 3);
        addCourse("CS102", "Data Structures", "Learn about data structures like arrays, stacks, queues, etc.", 2);
        addCourse("CS103", "Web Development", "Learn the basics of web development with HTML, CSS, and JavaScript.", 4);
        addStudent(1, "Alice");
        addStudent(2, "Bob");
        startSystem();
    }
    private static void addCourse(String courseCode, String title, String description, int capacity) {
        courses.add(new Course(courseCode, title, description, capacity));
    }
    private static void addStudent(int studentId, String name) {
        students.add(new Student(studentId, name));
    }
    private static void displayCourses() {
        System.out.println("\n--- Available Courses ---");
        for (Course course : courses) {
            System.out.println(course);
        }
    }
    private static void registerStudentForCourse(Student student) {
        displayCourses();
        System.out.print("Enter the course code to register: ");
        String courseCode = sc.nextLine();
        Course course = findCourseByCode(courseCode);
        if (course != null && student.registerForCourse(course)) {
            System.out.println("Successfully registered for " + course.title);
        } else {
            System.out.println("Failed to register for the course. Either the course doesn't exist or it's full.");
        }
    }
    private static void dropCourse(Student student) {
        System.out.println(student);
        System.out.print("Enter the course code to drop: ");
        String courseCode = sc.nextLine();
        Course course = findCourseByCode(courseCode);
        if (course != null && student.dropCourse(course)) {
            System.out.println("Successfully dropped the course " + course.title);
        } else {
            System.out.println("Failed to drop the course. Either you haven't registered for it or the course doesn't exist.");
        }
    }
    private static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.courseCode.equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }  
    private static void startSystem() {
        System.out.println("Welcome to the Course Registration System!");
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        sc.nextLine();
        Student student = findStudentById(studentId);
        if (student != null) {
            System.out.println("Welcome " + student.name);
            boolean exit = false;
            while (!exit) {
                System.out.println("\n1. Register for a course");
                System.out.println("2. Drop a course");
                System.out.println("3. View registered courses");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine(); 
                switch (choice) {
                    case 1:
                        registerStudentForCourse(student);
                        break;
                    case 2:
                        dropCourse(student);
                        break;
                    case 3:
                        System.out.println(student);
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.studentId == studentId) {
                return student;
            }
        }
        return null;
    }
}
