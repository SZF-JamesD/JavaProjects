package LE_06._04;

public class CMYColour {
    private double c, m, y;

    public CMYColour(double c, double m, double y) {
        this.c = c;
        this.m = m;
        this.y = y;
    }

    public void display() {
        System.out.printf("CMY: C=%.2f%%  M=%.2f%%  Y=%.2f%%\n", c, m, y);
    }
}
