import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    private int row;
    private int col;
    private int[][] board;
    private boolean[][] revealed;
    private int mines;
    private int safeTiles = 0;
    private int safeTilesTotal = 0;


    // Constructor for the Minesweeper class
    public Minesweeper(int row, int col) {
        int totalTiles;

        this.row = row;
        this.col = col;
        totalTiles = row * col;
        this.mines = totalTiles / 4;// Calculate the number of mines
        this.safeTilesTotal = totalTiles - mines;
        this.board = new int[row][col];
        this.revealed = new boolean[row][col];
        initializeBoard();
        printBoard();
        gamePlay();
    }

    public void initializeBoard() { // Initializing the board, randomly placing the mines and filling in the rest as safe tiles.
        // Here we randomly assign and place mines to the board
        while (mines > 0) {
            Random random = new Random();
            int randomRow = random.nextInt(row);
            int randomCol = random.nextInt(col);

            if (board[randomRow][randomCol] != -1) {
                board[randomRow][randomCol] = -1;
                mines--;
            }
        }
        for (int i = 0; i < row; i++) { // Here we check every tile for surrounding/neighboring mines
            for (int j = 0; j < col; j++) {
                if (board[i][j] != -1) {
                    int neighboringMines = 0;
                    neighboringMines += northCheck(i, j) + southCheck(i, j) + westCheck(i, j) + eastCheck(i, j)
                            + northeastCheck(i, j) + southeastCheck(i, j) + northwestCheck(i, j) + southwestCheck(i, j);
                    board[i][j] = neighboringMines;
                }
            }
        }
    }
    // Methods to check for surrounding/neighboring mines
    private int northCheck(int mevcutSatır, int mevcutSütun) {
        int kuzey = mevcutSatır - 1;

        // Check if there is a row above.
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
    private int southCheck(int mevcutSatır, int mevcutSütun) {
        int guney = mevcutSatır + 1;

        // Check if there is a row below.
        if (guney >= row) {
            return 0; // If there isn't a row below, there can't be a mine.
        } else {
            if (board[guney][mevcutSütun] == -1) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    private int eastCheck(int mevcutSatır, int mevcutSütun) {
        int dogu = mevcutSütun + 1;

        // Check if there is a column to the right.
        if (dogu >= col) {
            return 0; // If there isn't a column to the right, there can't be a mine.
        } else {
            if (board[mevcutSatır][dogu] == -1) {
                return 1;
            } else {
                return 0;
            }
        }
    }
   private int westCheck(int mevcutSatır, int mevcutSütun) {
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
        if ((kuzey < 0) || (batı < 0)) { // Combining the logic from northCheck and westCheck
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
        if ((guney >= row) || (dogu >= col)) { // Combining the logic from southCheck and eastCheck
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
        if ((guney >= row) || (batı < 0)) { // Combining the logic from southCheck and westCheck
            return 0;
        } else {
            if (board[guney][batı] == -1) {
                return 1;
            }
        }
        return 0;
    }
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

        while (safeTiles != safeTilesTotal) {
            Scanner scanner = new Scanner(System.in);
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
            // Input validation to check if the row and column inputs are within range
            while (true) {
                System.out.print("Please provide the row: ");
                int inputRowNumber = scanner.nextInt() - 1;

                System.out.print("Please provide the column: ");
                int inputColumnNumber = scanner.nextInt() - 1;

                if (inputRowNumber + 1 <= row && inputRowNumber + 1 >= 1
                        && inputColumnNumber + 1 <= col && inputColumnNumber + 1 >= 1) {
                    rowNumber = inputRowNumber;
                    columnNumber = inputColumnNumber;

                    if (revealed[rowNumber][columnNumber]) {
                        System.out.println("That tile is already revealed, please pick another tile.");
                    } else {
                        break; // Break out of the loop when the input is valid and tile is not revealed
                    }
                } else {
                    System.out.println("Please make sure to stay within the map.");
                }
            }

            revealed[rowNumber][columnNumber] = true;
            safeTiles++;

            if (board[rowNumber][columnNumber] == -1) { // The player loses the game if they hit a mine.
                printBoard();
                System.out.println("Sorry, you've lost!");
                break;
            } else {
                if (safeTiles == safeTilesTotal) { // The player wins if the player reveals all the safe tiles.
                    printBoard();
                    System.out.println("You won!");
                    break;
                }
            }
        }
    }
}
