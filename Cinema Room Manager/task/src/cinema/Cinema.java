package cinema;

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        final int ROW = 7, SEATS = 8;
        char[][] theathe =  makeTheathe(ROW, SEATS);
        printSeats(theathe, ROW, SEATS);
    }


    /**
     * Build the theathe
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
    /*
     * Visualize the seating arrangement by printing the scheme to the console.
     */
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