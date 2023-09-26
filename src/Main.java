import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        do {
            System.out.print("Satır sayısını girin (en az 3): ");
            row = scanner.nextInt();
            System.out.print("Sütun sayısını girin (en az 3): ");
            col = scanner.nextInt();

            if (row < 3 || col < 3) {
                System.out.println("Satır ve sütun sayıları en az 3 olmalıdır. Lütfen tekrar deneyin.");
            }
        } while (row < 3 || col < 3);

        Minesweeper game = new Minesweeper (row, col);

        scanner.close();
    }
}
