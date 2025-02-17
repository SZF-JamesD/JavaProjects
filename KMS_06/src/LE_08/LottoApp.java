package LE_08;

import java.util.*;

public class LottoApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] lottoNumbers = getLottoNumbers(sc);
        System.out.println("Your lotto numbers: " + Arrays.toString(lottoNumbers));
        sc.close();
    }

    public static int getValidNumber(Scanner sc, Set<Integer> enteredNumbers) {
        while (true) {
            try {
                System.out.print("Enter a number (1-45): ");
                int number = sc.nextInt();
                if (number < 1 || number > 45) {
                    System.out.println("Number must be between 1 and 45. Try again.");
                } else if (!enteredNumbers.add(number)) {
                    System.out.println("Number is already in use. Try again.");
                } else {
                    return number;
                }
            } catch (InputMismatchException e){
                System.out.println("That's not a number. Please try again.");
                sc.next();
            }
        }
    }

    public static int[] getLottoNumbers(Scanner sc) {
        Set<Integer> enteredNumbers = new HashSet<>();

        while (enteredNumbers.size() < 6) {
            int number = getValidNumber(sc, enteredNumbers);
            System.out.println("Number accepted: " + number);
        }

        int[] lottoNumbers = enteredNumbers.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(lottoNumbers);
        return lottoNumbers;
    }
}
