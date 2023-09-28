import java.util.Random;
import java.util.Scanner;

public class Minesweeper { // Form 5
    private int row;
    private int col;
    private int[][] board;
    private boolean[][] revealed;
    private int mines;
    private int safeTiles = 0;
    private int safeTilesTotal = 0;
    Scanner scanner = new Scanner(System.in);

    public Minesweeper(int row, int col) {
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
    private int northCheck(int mevcutSatır, int mevcutSütun) { // Check if there is a row above.
        int kuzey = mevcutSatır - 1;

        if (kuzey < 0) {
            return 0; // If there isn't a row above, there can't be a mine.
        } else {
            if (board[kuzey][mevcutSütun] == -1) { // If it's -1, there is a mine.
                return 1;
            } else {
                return 0;
            }
        }
    }

    private int southCheck(int mevcutSatır, int mevcutSütun) { // Check if there is a row below.
        int guney = mevcutSatır + 1;

        if (guney >= row) {
            return 0;
        } else {
            if (board[guney][mevcutSütun] == -1) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private int eastCheck(int mevcutSatır, int mevcutSütun) { // Check if there is a column to the right.
        int dogu = mevcutSütun + 1;

        if (dogu >= col) {
            return 0;
        } else {
            if (board[mevcutSatır][dogu] == -1) {
                return 1;
            } else {
                return 0;
            }
        }
    }


    private int westCheck(int mevcutSatır, int mevcutSütun) { // Check if there is a column to the left.
        int batı = mevcutSütun - 1;

        if (batı < 0) {
            return 0;
        } else {
            if (board[mevcutSatır][batı] == -1) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private int northeastCheck(int mevcutSatır, int mevcutSütun) {
        int kuzey = mevcutSatır - 1;
        int dogu = mevcutSütun + 1;
        if ((kuzey < 0) || (dogu >= col)) { // Combining the logic from northCheck and eastCheck
            return 0;
        } else {
            if (board[kuzey][dogu] == -1) {
                return 1;
            }
        }
        return 0;
    }

    private int northwestCheck(int mevcutSatır, int mevcutSütun) {
        int kuzey = mevcutSatır - 1;
        int batı = mevcutSütun - 1;
        if ((kuzey < 0) || (batı < 0)) {
            return 0;
        } else {
            if (board[kuzey][batı] == -1) {
                return 1;
            }
        }
        return 0;
    }

    private int southeastCheck(int mevcutSatır, int mevcutSütun) {
        int guney = mevcutSatır + 1;
        int dogu = mevcutSütun + 1;
        if ((guney >= row) || (dogu >= col)) {
            return 0;
        } else {
            if (board[guney][dogu] == -1) {
                return 1;
            }
        }
        return 0;
    }

    private int southwestCheck(int mevcutSatır, int mevcutSütun) {
        int guney = mevcutSatır + 1;
        int batı = mevcutSütun - 1;
        if ((guney >= row) || (batı < 0)) {
            return 0;
        } else {
            if (board[guney][batı] == -1) {
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
                    if (revealed[i][j] == false) {
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
