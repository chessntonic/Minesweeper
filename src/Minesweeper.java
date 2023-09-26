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
        this.row = row;
        this.col = col;
        this.mines = (row * col) / 4; // Calculate the number of mines
        this.board = new int[row][col];
        this.revealed = new boolean[row][col];
        initializeBoard();
        printBoard();
        gamePlay();
    }

    public void initializeBoard() {
        Random random = new Random();
        // Here we assign and place mines to the board
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (random.nextInt(4) == 0 && mines > 0) {
                    board[i][j] = -1; // Place a mine
                    mines--;
                } else {
                    board[i][j] = 0; // No mine
                }
            }
        }
        // Randomizer needs to be corrected
        /*for (int i = 0; i < mines; i++) {
            random.nextInt

        }*/

        for (int i = 0; i < row; i++) { // Here we check every tile for mines
            for (int j = 0; j < col; j++) {
                if (board[i][j] != -1) {
                    int neighboringMines = 0;
                    neighboringMines += northCheck(i, j) + southCheck(i, j) + westCheck(i, j) + eastCheck(i, j)
                            + northeastCheck(i, j) + southeastCheck(i, j) + northwestCheck(i, j) + southwestCheck(i, j);
                    board[i][j] = neighboringMines;
                    safeTilesTotal += neighboringMines;
                }
            }
        }
    }
    // Methods to check for surrounding mines
    private int northCheck(int mevcutSatır, int mevcutSütun) {
        int kuzey = mevcutSatır - 1;

        // KUZEYDEKİ İNDİS GERÇEKTEN VAR MI?
        if (kuzey < 0) {
            return 0;
        }
        // KUZEYDEKİ İNDİS GERÇEKMİŞ
        // MADEM ÖYLE SÖYLE BAKALIM
        // MAYIN MIYMIŞ?
        else { // kuzeydekiİndeks >= 0
            if (board[kuzey][mevcutSütun] == -1) { // -1 ise mayındır
                return 1;
            } else {
                return 0;
            }
        }
    }
    private int southCheck(int mevcutSatır, int mevcutSütun) {
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
    private int eastCheck(int mevcutSatır, int mevcutSütun) {
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
        if ((kuzey < 0) || (dogu >= col)) {
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
    public void printBoard() { // Here we print the solution including the flags
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
    public void gamePlay() {
        while (safeTiles != safeTilesTotal) { // The game continues until all safe tiles have been opened or the player hits a mine
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

            System.out.println("Please provide the row: ");
            int rowNumber = scanner.nextInt()-1;
            System.out.println("Please provide the column: ");
            int columnNumber = scanner.nextInt()-1;

            revealed[rowNumber][columnNumber] = true;
            safeTiles += board[rowNumber][columnNumber];

            if (board[rowNumber][columnNumber] == -1) {
                printBoard();
                System.out.println("Sorry, you've lost!");
                break;
            } else {
                if (safeTiles == safeTilesTotal) {
                    printBoard();
                    System.out.println("You won!");
                    break;
                }
            }
        }
    }
}
