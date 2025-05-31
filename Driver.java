import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException {
        // Load polynomials from poly1.txt and poly2.txt
        Polynomial p1 = new Polynomial(new File("poly1.txt"));
        Polynomial p2 = new Polynomial(new File("poly2.txt"));

        // Multiply them
        Polynomial product = p1.multiply(p2);

        // Save the result to product.txt
        product.saveToFile("product.txt");

        // Display message
        System.out.println("Polynomial product saved to product.txt");
    }
}