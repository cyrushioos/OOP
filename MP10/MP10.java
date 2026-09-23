package com.charm2.ret;

import java.util.Scanner;

class Student {
    private String studentId;
    private String fullName;

    public Student(String studentId, String fullName) {
        this.studentId = studentId;
        this.fullName = fullName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }
}

class Course {
    private String courseCode;
    private String title;
    private int capacity;

    public Course(String courseCode, String title, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.capacity = capacity;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getCapacity() {
        return capacity;
    }
}

class Enrollment {
    private Student student;
    private Course course;
    private int grade;
    private static int enrollmentCount = 0;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.grade = -1;
        enrollmentCount++;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public int getGrade() {
        return grade;
    }

    public boolean setGrade(int grade) {
        if (grade < 0 || grade > 100) {
            return false;
        }
        this.grade = grade;
        return true;
    }

    public String getStatus() {
        if (grade == -1) {
            return "NOT YET GRADED";
        }
        return grade >= 75 ? "PASSED" : "FAILED";
    }

    public static int getEnrollmentCount() {
        return enrollmentCount;
    }
}

public class MP10 {
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("UNIVERSITY ENROLLMENT AND PERFORMANCE SYSTEM");

        int studentCount = readInt("Number of students: ", 1, 100);
        Student[] students = new Student[studentCount];
        for (int i = 0; i < students.length; i++) {
            System.out.println("\nStudent " + (i + 1));
            String id = readUniqueStudentId(students, i);
            String name = readNonEmpty("Full name: ");
            students[i] = new Student(id, name);
        }

        int courseCount = readInt("\nNumber of courses: ", 1, 100);
        Course[] courses = new Course[courseCount];
        int maximumEnrollments = 0;
        for (int i = 0; i < courses.length; i++) {
            System.out.println("\nCourse " + (i + 1));
            String code = readUniqueCourseCode(courses, i);
            String title = readNonEmpty("Course title: ");
            int capacity = readInt("Capacity: ", 1, studentCount);
            courses[i] = new Course(code, title, capacity);
            maximumEnrollments += capacity;
        }

        Enrollment[] enrollments = new Enrollment[maximumEnrollments];
        int usedEnrollments = 0;
        int choice;

        do {
            displayMenu();
            choice = readInt("Choice: ", 0, 5);

            if (choice == 1) {
                usedEnrollments = enrollStudent(students, courses, enrollments,
                        usedEnrollments);
            } else if (choice == 2) {
                assignGrade(students, courses, enrollments, usedEnrollments);
            } else if (choice == 3) {
                printStudentReport(students, enrollments, usedEnrollments);
            } else if (choice == 4) {
                printCourseRoster(courses, enrollments, usedEnrollments);
            } else if (choice == 5) {
                printSystemSummary(students, courses, enrollments, usedEnrollments);
            } else {
                System.out.println("Program ended.");
            }
        } while (choice != 0);
    }

    private static void displayMenu() {
        System.out.println("\nMENU");
        System.out.println("1 - Enroll Student");
        System.out.println("2 - Assign/Update Grade");
        System.out.println("3 - Student Report");
        System.out.println("4 - Course Roster");
        System.out.println("5 - System Summary");
        System.out.println("0 - Exit");
    }

    private static int enrollStudent(Student[] students, Course[] courses,
                                     Enrollment[] enrollments, int used) {
        String studentId = readNonEmpty("Student ID: ");
        Student student = findStudent(students, studentId);
        if (student == null) {
            System.out.println("Enrollment rejected: student does not exist.");
            return used;
        }

        String courseCode = readNonEmpty("Course code: ");
        Course course = findCourse(courses, courseCode);
        if (course == null) {
            System.out.println("Enrollment rejected: course does not exist.");
            return used;
        }

        if (findEnrollment(enrollments, used, studentId, courseCode) != null) {
            System.out.println("Enrollment rejected: duplicate enrollment.");
            return used;
        }

        if (countCourseEnrollments(enrollments, used, courseCode)
                >= course.getCapacity()) {
            System.out.println("Enrollment rejected: course is at capacity.");
            return used;
        }

        enrollments[used] = new Enrollment(student, course);
        System.out.println("Enrollment successful.");
        return used + 1;
    }

    private static void assignGrade(Student[] students, Course[] courses,
                                    Enrollment[] enrollments, int used) {
        String studentId = readNonEmpty("Student ID: ");
        if (findStudent(students, studentId) == null) {
            System.out.println("Grade rejected: student does not exist.");
            return;
        }

        String courseCode = readNonEmpty("Course code: ");
        if (findCourse(courses, courseCode) == null) {
            System.out.println("Grade rejected: course does not exist.");
            return;
        }

        Enrollment enrollment = findEnrollment(enrollments, used,
                studentId, courseCode);
        if (enrollment == null) {
            System.out.println("Grade rejected: enrollment does not exist.");
            return;
        }

        int grade = readInt("Grade (0-100): ", 0, 100);
        if (enrollment.setGrade(grade)) {
            System.out.println("Grade assigned/updated successfully.");
        }
    }

    private static void printStudentReport(Student[] students,
                                           Enrollment[] enrollments, int used) {
        String studentId = readNonEmpty("Student ID: ");
        Student student = findStudent(students, studentId);
        if (student == null) {
            System.out.println("Report unavailable: student does not exist.");
            return;
        }

        System.out.println("\nSTUDENT REPORT");
        System.out.println("ID: " + student.getStudentId());
        System.out.println("Name: " + student.getFullName());

        int courseTotal = 0;
        int gradeTotal = 0;
        int gradedCount = 0;
        for (int i = 0; i < used; i++) {
            Enrollment current = enrollments[i];
            if (current.getStudent().getStudentId().equalsIgnoreCase(studentId)) {
                courseTotal++;
                String gradeText = current.getGrade() == -1
                        ? "N/A" : String.valueOf(current.getGrade());
                System.out.printf("%-10s %-25s Grade: %-3s Status: %s%n",
                        current.getCourse().getCourseCode(),
                        current.getCourse().getTitle(), gradeText,
                        current.getStatus());
                if (current.getGrade() != -1) {
                    gradeTotal += current.getGrade();
                    gradedCount++;
                }
            }
        }

        if (courseTotal == 0) {
            System.out.println("No enrolled courses.");
        }
        if (gradedCount > 0) {
            System.out.printf("Average of graded courses: %.2f%n",
                    (double) gradeTotal / gradedCount);
        } else {
            System.out.println("Average of graded courses: N/A");
        }
    }

    private static void printCourseRoster(Course[] courses,
                                          Enrollment[] enrollments, int used) {
        String courseCode = readNonEmpty("Course code: ");
        Course course = findCourse(courses, courseCode);
        if (course == null) {
            System.out.println("Roster unavailable: course does not exist.");
            return;
        }

        System.out.println("\nCOURSE ROSTER");
        System.out.println("Course: " + course.getCourseCode() + " - "
                + course.getTitle());
        int rosterCount = 0;
        for (int i = 0; i < used; i++) {
            Enrollment current = enrollments[i];
            if (current.getCourse().getCourseCode().equalsIgnoreCase(courseCode)) {
                rosterCount++;
                String gradeText = current.getGrade() == -1
                        ? "N/A" : String.valueOf(current.getGrade());
                System.out.printf("%-10s %-25s Grade: %-3s Status: %s%n",
                        current.getStudent().getStudentId(),
                        current.getStudent().getFullName(), gradeText,
                        current.getStatus());
            }
        }
        if (rosterCount == 0) {
            System.out.println("No enrolled students.");
        }
        System.out.println("Occupied slots: " + rosterCount + "/"
                + course.getCapacity());
    }

    private static void printSystemSummary(Student[] students, Course[] courses,
                                           Enrollment[] enrollments, int used) {
        int gradedCount = 0;
        for (int i = 0; i < used; i++) {
            if (enrollments[i].getGrade() != -1) {
                gradedCount++;
            }
        }

        System.out.println("\nSYSTEM SUMMARY");
        System.out.println("Total students: " + students.length);
        System.out.println("Total courses: " + courses.length);
        System.out.println("Total enrollments: "
                + Enrollment.getEnrollmentCount());
        System.out.println("Graded enrollments: " + gradedCount);
    }

    private static Student findStudent(Student[] students, String id) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null
                    && students[i].getStudentId().equalsIgnoreCase(id)) {
                return students[i];
            }
        }
        return null;
    }

    private static Course findCourse(Course[] courses, String code) {
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] != null
                    && courses[i].getCourseCode().equalsIgnoreCase(code)) {
                return courses[i];
            }
        }
        return null;
    }

    private static Enrollment findEnrollment(Enrollment[] enrollments, int used,
                                             String studentId, String courseCode) {
        for (int i = 0; i < used; i++) {
            Enrollment current = enrollments[i];
            if (current.getStudent().getStudentId().equalsIgnoreCase(studentId)
                    && current.getCourse().getCourseCode()
                    .equalsIgnoreCase(courseCode)) {
                return current;
            }
        }
        return null;
    }

    private static int countCourseEnrollments(Enrollment[] enrollments, int used,
                                              String courseCode) {
        int count = 0;
        for (int i = 0; i < used; i++) {
            if (enrollments[i].getCourse().getCourseCode()
                    .equalsIgnoreCase(courseCode)) {
                count++;
            }
        }
        return count;
    }

    private static String readUniqueStudentId(Student[] students, int used) {
        while (true) {
            String id = readNonEmpty("Student ID: ");
            boolean duplicate = false;
            for (int i = 0; i < used; i++) {
                if (students[i].getStudentId().equalsIgnoreCase(id)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                return id;
            }
            System.out.println("That student ID already exists.");
        }
    }

    private static String readUniqueCourseCode(Course[] courses, int used) {
        while (true) {
            String code = readNonEmpty("Course code: ");
            boolean duplicate = false;
            for (int i = 0; i < used; i++) {
                if (courses[i].getCourseCode().equalsIgnoreCase(code)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                return code;
            }
            System.out.println("That course code already exists.");
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = INPUT.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Input cannot be blank.");
        }
    }

    private static int readInt(String prompt, int minimum, int maximum) {
        while (true) {
            System.out.print(prompt);
            String value = INPUT.nextLine().trim();
            try {
                int number = Integer.parseInt(value);
                if (number >= minimum && number <= maximum) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
                // The message below handles both non-integers and out-of-range input.
            }
            System.out.println("Enter a whole number from " + minimum
                    + " to " + maximum + ".");
        }
    }
}
