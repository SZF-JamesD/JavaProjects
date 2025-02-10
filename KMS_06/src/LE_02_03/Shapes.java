package LE_02_03;

import java.util.Scanner;

public class Shapes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the type of shape:\n1: Rectangle\n2: Equilateral Triangle\n3: Circle\n");
        int type = sc.nextInt();

        switch (type) {
            case 1:
                System.out.println("Enter the length of rectangle (cm): ");
                double rec_len = sc.nextDouble();
                System.out.println("Enter the width of rectangle (cm): ");
                double rec_wid = sc.nextDouble();
                double rec_area = rec_len * rec_wid;
                System.out.println("Base area of the rectangle is " + rec_area + "cm²");
                System.out.println("Enter the height of cuboid (cm): ");
                double rec_height = sc.nextDouble();
                System.out.println("Volume of the cuboid is " + (rec_area * rec_height) + "cm³");
                break;
            case 2:
                System.out.println("Enter the length of equilateral triangle (cm): ");
                double tri_area = (Math.sqrt(3) / 4) * Math.pow(sc.nextDouble(), 2);
                System.out.println("Base area of the equilateral triangle is: " + tri_area + "cm²");
                System.out.println("Enter the height of the triangular prism (cm): ");
                double tri_vol = tri_area * sc.nextDouble();
                System.out.println("Volume of triangular prism is: " + tri_vol + "cm³");
                break;
            case 3:
                System.out.println("Enter the radius of a circle (cm): ");
                double circle_area = (Math.PI * Math.pow(sc.nextDouble(), 2));
                System.out.println("Base area of the circle is: " + circle_area + "cm²");
                System.out.println("Enter the height of the cylinder (cm): ");
                double cylinder_vol = circle_area * sc.nextDouble();
                System.out.println("Volume of a cylinder is: " + cylinder_vol + "cm³");
                break;
        }
    }
}