package LE_06._06;


public class CinemaApp {
    public static void main(String[] args) {
        CinemaHall hall = new CinemaHall(5,8);

        hall.occupyRandomSeats(20);

        CinemaDisplay.printSeatingArrangement(hall);
        CinemaDisplay.printFreeSeats(hall);
        CinemaDisplay.printOccupancy(hall);

        hall.selectSeat(hall);

        CinemaDisplay.printSeatingArrangement(hall);
        CinemaDisplay.printFreeSeats(hall);
        CinemaDisplay.printOccupancy(hall);

    }




}