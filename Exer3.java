import java.util.Scanner;

public class Exer3 {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        
    System.out.print("Enter Brand: ");
    String brand = scanner.nextLine();
        
    System.out.print("Enter Model: ");
    String model = scanner.nextLine();

    System.out.print("Enter Plate Number: ");
    String plateNumber = scanner.nextLine();

    System.out.print("Enter Manufacturing Year: ");
    int manufacturingYear = scanner.nextInt();

    System.out.print("Enter Engine Displacement: ");
    double engineDisplacement = scanner.nextDouble();

    scanner.close();
    
    System.out.println("Brand: " + brand);
    System.out.println("Model: " + model);
    System.out.println("Plate: " + plateNumber);
    System.out.println("Year: " + manufacturingYear);
    System.out.println("Engine: " + engineDisplacement + " L");
}
}