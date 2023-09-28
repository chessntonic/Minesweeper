import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        do { // A board of 2x2 would be too small for the game, so we kindly ask the user to provide at least three rows and columns.
             // Form 7
            System.out.print("How many rows would you like to have? (Please enter at least 3): ");
            row = scanner.nextInt();
            System.out.print("How many columns would you like to have? (Please enter at least 3): ");
            col = scanner.nextInt();

            if (row < 3 || col < 3) {
                System.out.println("You need at least three rows and columns. Please try again.");
            }
        } while (row < 3 || col < 3);

        Minesweeper game = new Minesweeper (row, col);

        scanner.close();
    }
}
