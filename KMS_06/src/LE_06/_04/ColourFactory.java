package LE_06._04;

import java.util.Scanner;

public class ColourFactory {
    public static Colour selectColourMode() {
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.print("Select colour mode: 1 for RGB, 2 for CSS: ");

            if (sc.hasNextInt()){
                int mode = sc.nextInt();
                if (mode == 1){
                    return new RGBColour();
                }
                else if (mode == 2){
                    return new CSSColour();
                } else{
                    System.out.println("Invalid input, please enter 1 or 2.");
                    sc.next();
                }
            } else {
                System.out.println("Invalid input, please enter 1 or 2.");
                sc.next();
            }
        }
    }
}
