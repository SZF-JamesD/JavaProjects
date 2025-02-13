package LE_05._01;

import java.awt.Color;
import java.util.Scanner;
public class Colours {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String colour;
        int red, green, blue;
        //input hex code, get RGB numbers
        while (true) {
            System.out.println("Enter a colour hex code using 0-9 and A-F (e.g. AA1922): ");
            try {
                colour = sc.nextLine();
                colour = colour.toUpperCase().trim();
                red = Integer.valueOf(colour.substring(0,2), 16);
                green = Integer.valueOf(colour.substring(2,4), 16);
                blue = Integer.valueOf(colour.substring(4,6), 16);
                System.out.printf("RGB values for %S.\nRed = %d, Green = %d, Blue = %d\n", colour, red, green, blue);
                break;
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }
    }
}