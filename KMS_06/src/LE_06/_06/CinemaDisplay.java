package LE_06._06;

public class CinemaDisplay {
    public static void printSeatingArrangement(CinemaHall hall) {
        char[][] seats = hall.getSeats();
        System.out.println("Cinema Hall Layout");
        for (int i = 0; i < seats.length; i++) {
            System.out.print("Row " + (i + 1) + ": ");
            for (char seat : seats[i]) {
                System.out.print(seat + " ");
            }
            System.out.println();
        }
    }

    public static void printFreeSeats(CinemaHall hall) {
        for (int i = 0; i < hall.getSeats().length; i++) {
            int freeSeats = hall.getFreeSeatsInRow(i);
            System.out.println(freeSeats + " free seats in row " + (i + 1));
        }
    }

    public static void printOccupancy(CinemaHall hall) {
        System.out.printf("Occupancy Rate: %.2f%%\n", hall.getOccupancyRate());
    }
}