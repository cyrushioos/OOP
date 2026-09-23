package com.charm2.ret;
import java.util.Scanner;

class GradeStudent {
    private String studentID;
    private String fullName;
    private int[] scores;

    // Shared by all Student objects
    private static int objectCount = 0;

    public GradeStudent(String studentID, String fullName, int[] scores) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.scores = scores;

        objectCount++;
    }

    public double calculateAverage() {
        int total = 0;

        for (int score : scores) {
            total += score;
        }

        return (double) total / scores.length;
    }

    public String getStatus() {
        if (calculateAverage() >= 75) {
            return "PASSED";
        }

        return "FAILED";
    }

    public String getStudentID() {
        return studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public static int getObjectCount() {
        return objectCount;
    }

    public void display() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Full Name: " + fullName);
        System.out.printf("Average: %.2f%n", calculateAverage());
        System.out.println("Status: " + getStatus());
    }
}

public class MP1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int studentCount;

        do {
            System.out.print("Enter number of students (1-10): ");
            studentCount = input.nextInt();

            if (studentCount < 1 || studentCount > 10) {
                System.out.println("Please enter a number from 1 to 10.");
            }
        } while (studentCount < 1 || studentCount > 10);

        input.nextLine(); // Remove the leftover newline

        GradeStudent[] students = new GradeStudent[studentCount];

        // Requirements 2-4: Enter each student's information
        for (int i = 0; i < studentCount; i++) {
            System.out.println("\nStudent " + (i + 1));

            System.out.print("Enter student ID: ");
            String studentID = input.nextLine();

            System.out.print("Enter full name: ");
            String fullName = input.nextLine();

            int[] scores = new int[3];

            for (int j = 0; j < scores.length; j++) {
                do {
                    System.out.print("Enter score " + (j + 1) + " (0-100): ");
                    scores[j] = input.nextInt();

                    if (scores[j] < 0 || scores[j] > 100) {
                        System.out.println("Invalid score. Please try again.");
                    }
                } while (scores[j] < 0 || scores[j] > 100);
            }

            input.nextLine(); // Remove newline before the next name

            students[i] = new GradeStudent(studentID, fullName, scores);
        }

        // Requirement 6: Display every student
        System.out.println("\n===== STUDENT RECORDS =====");

        for (GradeStudent student : students) {
            System.out.println();
            student.display();
        }

        // Requirement 7: Find the highest average
        GradeStudent highestStudent = students[0];

        for (int i = 1; i < students.length; i++) {
            if (students[i].calculateAverage()
                    > highestStudent.calculateAverage()) {
                highestStudent = students[i];
            }
        }

        System.out.println("\n===== HIGHEST AVERAGE =====");
        highestStudent.display();

        // Requirement 8: Display number of Student objects
        System.out.println("\nStudent objects created: "
                + GradeStudent.getObjectCount());

        input.close();
    }
}
