import java.util.Scanner;

public class Exer4 {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        
    System.out.print("Enter Guest Name: ");
    String guestname = scanner.nextLine();
        
    System.out.print("Enter Room Number: ");
    int Roomnumber = scanner.nextInt();
    
    System.out.print("Enter Number of nights: ");
    int numbernights = scanner.nextInt();

    System.out.print("Enter Room rate: ");
    double roomrate = scanner.nextDouble();


    scanner.close();
    System.out.println();
    System.out.println("Guest: " + guestname);
    System.out.println("Room: " + Roomnumber);
    System.out.println("Nights: " + numbernights);
    System.out.println("Room Rate: PHP " + roomrate);
    
}
}