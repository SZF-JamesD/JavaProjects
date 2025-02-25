package Utils;

import java.util.regex.Pattern;

public class ValidationUtil {

    public static boolean isNonEmptyString(String input) {
        return input != null && !input.trim().isEmpty();
    }


    public static boolean isAlphanumeric(String input) {
        if (input == null) return false;
        return Pattern.matches("^[a-zA-Z0-9 ]+$", input);
    }


    public static boolean isPositiveInt(int number) {
        return number > 0;
    }


    public static boolean isNonNegativeDouble(double number) {
        return number >= 0;
    }


    public static boolean isValidYear(int year) {
        return year >= 1886 && year <= 2100;
    }


    public static boolean isValidChar(char input, char... validOptions) {
        for (char option : validOptions) {
            if (input == option) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidVehicleChoice(int choice) {
        return choice >= 1 && choice <= 4;
    }
}
