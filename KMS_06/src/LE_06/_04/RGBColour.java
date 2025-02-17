package LE_06._04;

import java.awt.*;
import java.util.Scanner;

public class RGBColour implements Colour {
    private int r, g, b;

    @Override
    public void getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter RGB Values (e.g., 255 123 53: ");
        if (sc.hasNextInt()) r = sc.nextInt();
        if (sc.hasNextInt()) g = sc.nextInt();
        if (sc.hasNextInt()) b = sc.nextInt();
    }

    @Override
    public boolean isValid() {
        return (r >= 0 && r <= 255) && (g >= 0 && g <= 255) && (b >= 0 && b <= 255);
    }

    @Override
    public CMYColour toCMY() {

        double c = (255 - r) / 255.0 * 100;
        double m = (255 - g) / 255.0 * 100;
        double y = (255 - b) / 255.0 * 100;
        return new CMYColour(c, m, y);
    }
}
