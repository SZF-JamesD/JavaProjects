package LE_06._04;

import java.awt.*;
import java.util.Scanner;

public class CSSColour implements Colour {
    private String cssCode;

    @Override
    public void getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the CSS code (e.g., #80FF40: ");
        cssCode = sc.next();
    }

    @Override
    public boolean isValid() {
        return cssCode.matches("^#([0-9a-fA-F]{3,8})$");
    }

    @Override
    public CMYColour toCMY() {
        int r = 0, g= 0, b = 0;

        if (cssCode.length() == 4 || cssCode.length() == 5) {
            r = Integer.parseInt(cssCode.substring(1, 2).repeat(2), 16);
            g = Integer.parseInt(cssCode.substring(2, 3).repeat(2), 16);
            b = Integer.parseInt(cssCode.substring(3, 4).repeat(2), 16);
        }
        else if (cssCode.length() == 7 || cssCode.length() == 9) {
            r = Integer.parseInt(cssCode.substring(1, 3), 16);
            g = Integer.parseInt(cssCode.substring(3, 5), 16);
            b = Integer.parseInt(cssCode.substring(5, 7), 16);
        }

        double c = (255 -r) / 255.0 * 100;
        double m = (255 -g) / 255.0 * 100;
        double y = (255 -b) / 255.0 * 100;
        return new CMYColour(c, m ,y);
    }
}
