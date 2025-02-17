package LE_06._04;


public class ColourConverter {
    public static void main(String[] args) {
        Colour colour = ColourFactory.selectColourMode();
        colour.getInput();

        if (colour.isValid()) {
            CMYColour cmy = colour.toCMY();
            cmy.display();
        } else {
            System.out.println("Invalid colour input.");
        }
    }
}