package cinema;

import java.util.Scanner;

/**
 * Application for a cinema theatre where people can get tickets, reserve seats, and enjoy their movie night.
 */
public class Cinema {
    private static Scanner parse = new Scanner(System.in);

    public static void main(String[] args) {
        //1 Read rows and seats the theater.
        System.out.println("Enter the number of rows:");
        final int ROW = parse.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int SEATS = parse.nextInt();
        char[][] theatre = makeTheathe(ROW, SEATS);
        printSeats(theatre);


        //2 Ask for the seat.
        System.out.println("Enter a row number:");
        int rowUser = parse.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatUser = parse.nextInt();

        // Calculate price ticket
        System.out.println("Ticket price: $" + calculatePrice(theatre, rowUser));
        //3 Print again theater but now with seats occupied.
        theatre[rowUser - 1][seatUser - 1] = 'B';

        printSeats(theatre);
    }
    /**
     * Calculate the prize the one ticket.
     * Depending of rules: if not more than 60 the ticket is 10 dollars
     * else is more than 60 then front half 10 and the back half is 8
     *
     * @param row user.
     * @parama colum user.
     */
    public static int calculatePrice(char[][] cinema, int row) {
        int numSeats = cinema.length * cinema[0].length;
        if (numSeats < 60)
            return 10;
        else {
            // Divede rows
            int halfRows = cinema.length / 2;
            // Determinate seat of user.
            if (row <= halfRows) {
                return 10;
            }
        }
        return 8;
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

    /**
     * Build the theathe
     *
     * @return theathe que representa una matriz.
     */
    public static char[][] makeTheathe(int row, int colum) {
        char[][] theathe = new char[row][colum];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                theathe[i][j] = 'S';
            }
        }
        return theathe;
    }

    /**
     * Visualize the seating arrangement by printing the scheme to the console.
     * Take advantage of object oriented programming.
     */
    public static void printSeats(char[][] theatre) {
        System.out.println("Cinema:");
        // First row numbers
        System.out.print(" ");
        for (int i = 1; i <= theatre[0].length; i++) {
            System.out.printf(" %d", i);
        }
        System.out.println();
        // Second part scenery
        for (int i = 0; i < theatre.length; i++) { // return rows
            System.out.printf("%d", i + 1);
            for (int j = 0; j < theatre[i].length; j++) { //return columns
                System.out.print(" " + theatre[i][j]);
            }
            System.out.println("");
        }
    }
}