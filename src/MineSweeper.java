import java.util.Random;
import java.util.Scanner;

public class MineSweeper { // Form 5
    private int row;
    private int col;
    private int[][] board;
    private boolean[][] revealed;
    private int mines;
    private int safeTiles = 0;
    private int safeTilesTotal;
    Scanner scanner = new Scanner(System.in);

    public MineSweeper(int row, int col) {
        int totalTiles;

        this.row = row;
        this.col = col;
        totalTiles = row * col;
        this.mines = totalTiles / 4; // Calculate the number of mines
        this.safeTilesTotal = totalTiles - mines;
        this.board = new int[row][col];
        this.revealed = new boolean[row][col];
        initializeBoard();
        printBoard();
        gamePlay();
    }

    public void initializeBoard() { // Form 8 // Initializing the board, randomly placing the mines and filling in the rest as safe tiles.

        while (mines > 0) { // Here we get random coordinates for the mines and place them onto the board.
            Random random = new Random();
            int randomRow = random.nextInt(row);
            int randomCol = random.nextInt(col);

            if (board[randomRow][randomCol] != -1) {
                board[randomRow][randomCol] = -1;
                mines--;
            }
        }
        for (int i = 0; i < row; i++) { // Form 12 // Calculate the total of neighboring mines
            for (int j = 0; j < col; j++) {
                if (board[i][j] != -1) {
                    int neighboringMines = 0;
                    neighboringMines += northCheck(i, j) + southCheck(i, j) + westCheck(i, j) + eastCheck(i, j)
                            + northeastCheck(i, j) + southeastCheck(i, j) + northwestCheck(i, j) + southwestCheck(i, j);
                    board[i][j] = neighboringMines; // Form 12 // Print the total number of neighboring mines onto the chosen coordinate
                }
            }
        }
    }

    // Starting checks for surrounding/neighboring mines
    private int northCheck(int currentRow, int currentColumn) { // Check if there is a row above.
        int north = currentRow - 1;

        if (north < 0) {
            return 0; // If there isn't a row above, there can't be a mine.
        } else {
            if (board[north][currentColumn] == -1) { // If it's -1, there is a mine.
                return 1;
            } else {
                return 0;
            }
        }
    }

    private int southCheck(int currentRow, int currentColumn) {
        int south = currentRow + 1;

        if (south >= row) {
            return 0;
        } else {
            if (board[south][currentColumn] == -1) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private int eastCheck(int currentRow, int currentColumn) {
        int east = currentColumn + 1;

        if (east >= col) {
            return 0;
        } else {
            if (board[currentRow][east] == -1) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private int westCheck(int currentRow, int currentColumn) {
        int west = currentColumn - 1;

        if (west < 0) {
            return 0;
        } else {
            if (board[currentRow][west] == -1) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private int northeastCheck(int currentRow, int currentColumn) {
        int north = currentRow - 1;
        int east = currentColumn + 1;

        if ((north < 0) || (east >= col)) { // Combining the logic from northCheck and eastCheck
            return 0;
        } else {
            if (board[north][east] == -1) {
                return 1;
            }
        }
        return 0;
    }

    private int northwestCheck(int currentRow, int currentColumn) {
        int north = currentRow - 1;
        int west = currentColumn - 1;

        if ((north < 0) || (west < 0)) {
            return 0;
        } else {
            if (board[north][west] == -1) {
                return 1;
            }
        }
        return 0;
    }

    private int southeastCheck(int currentRow, int currentColumn) {
        int south = currentRow + 1;
        int east = currentColumn + 1;

        if ((south >= row) || (east >= col)) {
            return 0;
        } else {
            if (board[south][east] == -1) {
                return 1;
            }
        }
        return 0;
    }

    private int southwestCheck(int currentRow, int currentColumn) {
        int south = currentRow + 1;
        int west = currentColumn - 1;

        if ((south >= row) || (west < 0)) {
            return 0;
        } else {
            if (board[south][west] == -1) {
                return 1;
            }
        }
        return 0;
    } // Ending checks for surrounding/neighboring mines

    public void printBoard() { // Here we print the solution including the flags.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == -1) {
                    System.out.print("*  ");
                } else {
                    System.out.print(board[i][j] + "  ");
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
    }

    // This is where the actual gameplay starts.
    // The game continues until the player reveals all safe tiles or hits a mine.
    public void gamePlay() {
        int rowNumber; // Used for input validation below
        int columnNumber; // Used for input validation below

        while (safeTiles != safeTilesTotal) { // Form 11
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (!revealed[i][j]) {
                        System.out.print("-  ");
                    } else {
                        if (board[i][j] == -1) {
                            System.out.print("*");
                        } else {
                            System.out.print(board[i][j] + "  ");
                        }
                    }
                }
                System.out.println();
            }
            // Getting coordinates from the user and validating the input
            while (true) {
                System.out.print("Please provide the row: "); // Form 9
                int inputRowNumber = scanner.nextInt() - 1;

                System.out.print("Please provide the column: ");
                int inputColumnNumber = scanner.nextInt() - 1;

                if (inputRowNumber + 1 <= row && inputRowNumber + 1 >= 1
                        && inputColumnNumber + 1 <= col && inputColumnNumber + 1 >= 1) { // Form 10 // Input validation to check if the row and column inputs are within range
                    rowNumber = inputRowNumber;
                    columnNumber = inputColumnNumber;

                    if (revealed[rowNumber][columnNumber]) { // Form 10 // Input validation to check if the tile has already been revealed or not
                        System.out.println("That tile is already revealed, please pick another tile.");
                    } else {
                        break; // Break out of the loop when the input is valid and tile is not revealed
                    }
                } else {
                    System.out.println("Please make sure to stay within the map."); // Form 10
                }
            }

            revealed[rowNumber][columnNumber] = true;
            safeTiles++;

            if (board[rowNumber][columnNumber] == -1) { // Form 13 // The player loses the game if they hit a mine.
                printBoard();
                System.out.println("Sorry, you've lost!"); // Form 15
                scanner.close();
                break;
            } else {
                if (safeTiles == safeTilesTotal) { // Form 14 // The player wins if the player reveals all the safe tiles.
                    printBoard();
                    System.out.println("You won!"); // Form 15
                    scanner.close();
                    break;
                }
            }
        }
    }
}
