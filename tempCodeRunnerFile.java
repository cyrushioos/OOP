import java.util.Scanner;

public class Exer5 {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        
    System.out.print("Enter Brand Name: ");
    String brandname = scanner.nextLine();
        
    System.out.print("Enter Model: ");
    int model = scanner.nextInt();
    
    System.out.print("Enter RAM: ");
    int ram = scanner.nextInt();

    System.out.print("Enter Storage: ");
    int storage = scanner.nextInt();
    
    System.out.print("Enter Price: ");
    double price = scanner.nextDouble();
    scanner.close();

    System.out.println();
    System.out.println("Brand: " + brandname);
    System.out.println("Model: " + model);
    System.out.println("RAM: " + ram);
    System.out.println("Storage: " + storage);
    System.out.println("Price: PHP " + price);    
}
}