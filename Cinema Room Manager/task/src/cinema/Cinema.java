package cinema;

import java.util.Scanner;

/**
 * Application for a cinema theatre where people can get tickets, reserve seats, and enjoy their movie night.
 */
public class Cinema {
    private static Scanner parse = new Scanner(System.in);
    private static int totalPurchasedTickets = 0;
    private static int currentIncome = 0;

    public static void main(String[] args) {
        //1 Read rows and seats the theatre.
        System.out.println("Enter the number of rows:");
        final int ROW = parse.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int SEATS = parse.nextInt();
        char[][] theatre = makeTheatre(ROW, SEATS);
        // Show menu
        showMenu(theatre);

    }

    /**
     * Show some statistics
     */
    public static void statistics(char[][] theatre) {
        int totalTickets = theatre.length * theatre[0].length;
        System.out.println("Number of purchased tickets: " + totalPurchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", (float) (totalPurchasedTickets * 100) / totalTickets);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + calculateProfit(theatre.length, theatre[0].length));
    }

    /**
     * Ask the user for their seat, calculate the price and update seat the theatre
     */
    public static void buyTicket(char[][] theatre) {
        boolean askAgain = false;
        int rowUser, seatUser;
        do {
            //2 Ask for the seat.
            askAgain = false;
            System.out.println("Enter a row number:");
            rowUser = parse.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatUser = parse.nextInt();
            //Cheking the ticket if it has not been sold
            if (!isWithinLimits(theatre, rowUser - 1, seatUser - 1)) {
                System.out.println("\nWrong input!\n");
                askAgain = true;
            } else if (isPurchasedSeat(theatre, rowUser, seatUser)) {
                System.out.println("\nThat ticket has already been purchased!\n");
                askAgain = true;
            }

        } while (askAgain);

        // Calculate price ticket
        int price = calculatePrice(theatre, rowUser);
        System.out.println("Ticket price: $" + price);
        totalPurchasedTickets++;
        currentIncome += price;
        //3 Print again theatre but now with seats occupied.
        theatre[rowUser - 1][seatUser - 1] = 'B';
    }

    private static boolean isWithinLimits(char[][] theatre, int rowUser, int seatUser) {
        return (rowUser >= 0 && rowUser < theatre.length) && (seatUser >= 0 && seatUser < theatre[0].length);
    }

    private static boolean isPurchasedSeat(char[][] theatre, int rowUser, int seatUser) {
        return theatre[rowUser - 1][seatUser - 1] == 'B';
    }

    /**
     * Show the menu.
     */
    public static void showMenu(char[][] theatre) {
        int answer;
        do {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            answer = parse.nextInt();
            System.out.println();
            switch (answer) {
                case 1:
                    printSeats(theatre);
                    break;
                case 2:
                    buyTicket(theatre);
                    break;
                case 3:
                    statistics(theatre);
            }
        } while (answer != 0);
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
     * Build the theatre
     *
     * @return theatre que representa una matriz.
     */
    public static char[][] makeTheatre(int row, int colum) {
        char[][] theatre = new char[row][colum];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                theatre[i][j] = 'S';
            }
        }
        return theatre;
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