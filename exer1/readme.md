import java.util.Scanner;

public class Exer1 {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        
        // Collect all inputs first
    System.out.print("Enter name: ");
    String name = scanner.nextLine();
        
    System.out.print("Enter student number: ");
    String studentId = scanner.nextLine();

    System.out.print("Enter program: ");
    String program = scanner.nextLine();

    System.out.print("Enter year level: ");
    int yearLevel = scanner.nextInt();

    System.out.print("Enter GPA: ");
    double gpa = scanner.nextDouble();
    scanner.close();
    
    System.out.println("Name: " + name);
    System.out.println("Student Number: " + studentId);
    System.out.println("Program: " + program);
    System.out.println("Year Level: " + yearLevel);
    System.out.println("GPA: " + gpa);
}
}
