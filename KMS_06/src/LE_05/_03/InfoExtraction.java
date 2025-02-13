package LE_05._03;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoExtraction {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

        String pattern = "^The article (.+?) was ordered by (.+?)$";
        Pattern r = Pattern.compile(pattern);

        while (true) {
            try{
                System.out.println("Enter the order string (format: The article [product name] was ordered by [customer name]: ");
                String input = sc.nextLine();

                Matcher m = r.matcher(input);

                if (m.matches()) {
                    String product = m.group(1);
                    String customer = m.group(2);

                    System.out.println("\n✅ Valid Input");
                    System.out.println("Article: " + product);
                    System.out.println("Customer: " + customer);
                    break;
                } else {
                    System.out.println("\n❌ Invalid input! Please try again.\n");
                }

            } catch (Exception e){
                    System.out.println("⚠ An error occurred while processing the input: " + e.getMessage());
                }

            }
        }catch (Exception e) {
                System.out.println("❌ Failed to initialize scanner: " + e.getMessage());}
    }
}