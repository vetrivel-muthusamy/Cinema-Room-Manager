package cinema;

import java.util.Scanner;

/**
 * Application for a cinema theatre where people can get tickets, reserve seats, and enjoy their movie night.
 */
public class Cinema {
    private static Scanner parse = new Scanner(System.in);

    public static void main(String[] args) {
        // final int ROW = 7, SEATS = 8;
        // char[][] theathe =  makeTheathe(ROW, SEATS);
        // printSeats(theathe, ROW, SEATS);
        System.out.println("Enter the number of rows:");
        int rows = parse.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = parse.nextInt();
        System.out.println("Total income:\n$" + calculateProfit(rows, seats));

    }

    /**
     * Calculate the profit from all the sold tickets depending on the number of available seats.
     *
     * @return profit
     */
    public static int calculateProfit(int rows, int seats) {
        int totalSeats, profit = 0;
        totalSeats = rows * seats;
        if (totalSeats < 60) {
            profit = totalSeats * 10;
        } else if (totalSeats >= 60) {
            int frontHalf = rows / 2;
            int backHalf = rows - frontHalf;
            profit = frontHalf * seats * 10 + backHalf * seats * 8;
        }
        return profit;

    }

    //Build the theathe
    public static char[][] makeTheathe(int row, int colum) {
        char[][] theathe = new char[row][colum];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                theathe[i][j] = 'S';
            }
        }
        return theathe;
    }

    //Visualize the seating arrangement by printing the scheme to the console.
    public static void printSeats(char[][] theathe, int row, int colum) {
        System.out.println("Cinema:");
        // First row numbers
        System.out.print(" ");
        for (int i = 1; i <= 8; i++) {
            System.out.printf(" %d", i);
        }
        System.out.println();
        // Second part scenenary
        for (int i = 0; i < row; i++) {
            System.out.printf("%d", i + 1);
            for (int j = 0; j < colum; j++) {
                System.out.print(" " + theathe[i][j]);
            }
            System.out.println("");
        }
    }
}