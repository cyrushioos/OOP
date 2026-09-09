
import java.util.Scanner;

public class Exer2 {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        
        // Collect all inputs first
    System.out.print("Enter Item Name: ");
    String name = scanner.nextLine();
        
    System.out.print("Enter Category: ");
    String category = scanner.nextLine();

    System.out.print("Enter Price: ");
    double price = scanner.nextDouble();

    System.out.print("Enter Serving size: ");
    int servingSize = scanner.nextInt();

    scanner.close();
    
    System.out.println("Name: " + name);
    System.out.println("Category: " + category);
    System.out.println("Price: " + price);
    System.out.println("Serving Size: " + servingSize);
}
}