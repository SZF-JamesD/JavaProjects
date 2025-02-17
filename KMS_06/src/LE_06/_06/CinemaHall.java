package LE_06._06;


import java.util.Random;
import java.util.Scanner;

public class CinemaHall {
    private char[][] seats; // X = occupied, O = free
    private int rows;
    private int cols;

    public CinemaHall(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        seats = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = 'O';
            }
        }
    }

    public void occupyRandomSeats(int amount) {
        Random rand = new Random();
        int occupied = 0;

        while (occupied < amount) {
            int row = rand.nextInt(rows);
            int col = rand.nextInt(cols);

            if (seats[row][col] == 'O') {
                seats[row][col] = 'X';
                occupied++;
            }
        }
    }

    public  void selectSeat(CinemaHall hall) {
        Scanner sc = new Scanner(System.in);
        int row, col;

        while (true) {
            System.out.print("Enter row: ");
            row = sc.nextInt() - 1;

            System.out.print("Enter col: ");
            col = sc.nextInt() - 1;

            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                System.out.println("❌ Invalid seat, please choose another seat.");
                continue;
            }

            if (seats[row][col] == 'O') {
                seats[row][col] = 'X';
                System.out.println("✅ Seat booked successfully at" + (row + 1) + ":" + (col + 1));
                break;
            } else {
                System.out.println("❌ Seat is already occupied, please choose another seat.");
            }
        }
    }


    public int getFreeSeatsInRow(int row) {
        int freeSeats = 0;
        for (int j = 0; j < cols; j++) {
            if (seats[row][j] == 'O') {
                freeSeats++;
            }
        }
        return freeSeats;
    }

    public double getOccupancyRate() {
        int occupiedSeats = 0;
        int totalSeats = rows * cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (seats[i][j] == 'X') {
                    occupiedSeats++;
                }
            }
        }
        return (occupiedSeats / (double) totalSeats) * 100;
    }

    public char[][] getSeats() {
        return seats;
    }
}