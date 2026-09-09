public class Student {
    private String fullName;
    private String studentNumber;
    private String program;
    private int yearLevel;
    private double gpa;

    // Constructor
    public Student(String fullName, String studentNumber, String program, int yearLevel, double gpa) {
        this.fullName = fullName;
        this.studentNumber = studentNumber;
        this.program = program;
        this.yearLevel = yearLevel;
        this.gpa = gpa;
    }

    // Getters and Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getStudentNumber() { return studentNumber; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }

    public int getYearLevel() { return yearLevel; }
    public void setYearLevel(int yearLevel) { this.yearLevel = yearLevel; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    // Display Method
    public void displayProfile() {
        System.out.println("Name: " + fullName);
        System.out.println("Student Number: " + studentNumber);
        System.out.println("Program: " + program);
        System.out.println("Year Level: " + yearLevel);
        System.out.println("GPA: " + gpa);
    }

    // Main entry point for execution
    public static void main(String[] args) {
        Student myProfile = new Student("Erwin Cyrus John Elecanal", "2026-00001", "Computer Science", 2, 1.25);
        myProfile.displayProfile();
    }
}