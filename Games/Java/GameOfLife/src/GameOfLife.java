import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameOfLife {

    private int minRows = 5;
    private int minCols = 5;
    private int rows = 10;
    private int cols = 10;

    private int round = 0;

    private boolean[][] board;

    public GameOfLife() {
        gameInit();
    }

    public void ensureAlive(int row, int col) {
        if (!outOfBounds(row, col)) {
            board[row][col] = true;
        }
    }

    public void ensureDead(int row, int col) {
        if (!outOfBounds(row, col)) {
            board[row][col] = false;
        }
    }

    public boolean outOfBounds(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return true;
        } else{
            return false;
        }
    }

    public void tick() {
        boolean[][] nextBoard = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                List<Boolean> neighbours = getNeighbours(row, col);
                int liveNeighbours = 0;
                for (boolean neighbour : neighbours) {
                    if (neighbour) {
                        liveNeighbours++;
                    }
                }
                // Any live cell with fewer than two live neighbours dies, as if by underpopulation.
                if (liveNeighbours < 2 && isAlive(row, col)) {
                    nextBoard[row][col] = false;
                // Any live cell with two or three live neighbours lives on to the next generation.
                } else if (liveNeighbours < 4 && isAlive(row, col)) {
                    nextBoard[row][col] = true;
                // Any live cell with more than three live neighbours dies, as if by overpopulation.
                } else if (liveNeighbours > 3 && isAlive(row, col)) {
                    nextBoard[row][col] = false;
                // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                } else if (liveNeighbours == 3) {
                    nextBoard[row][col] = true;
                }
            }
        }
        round ++;
        board = nextBoard;
    }

    public List<Boolean> getNeighbours(int row, int col) {
        List<Boolean> neighbours = new ArrayList<>();
        
        // Add neighbours above
        if (!outOfBounds(row, col-1)) {
            int newCol = col-1;
            // Top Left
            if (!outOfBounds(row-1, newCol)) { neighbours.add(board[row-1][newCol]); }
            else { neighbours.add(board[row+rows-1][newCol]); }
            // Top
            if (!outOfBounds(row, newCol)) { neighbours.add(board[row][newCol]); }
            // Top Right
            if (!outOfBounds(row+1, newCol)) { neighbours.add(board[row+1][newCol]); }
            else { neighbours.add(board[row-rows+1][newCol]); }
        // If wrap around required
        } else {
            int newCol = col+cols-1;
            // Top Left
            if (!outOfBounds(row-1, col)) { neighbours.add(board[row-1][newCol]); }
            else { neighbours.add(board[row+rows-1][newCol]); }
            // Top
            if (!outOfBounds(row, col)) { neighbours.add(board[row][newCol]); }
            // Top Right
            if (!outOfBounds(row+1, col)) { neighbours.add(board[row+1][newCol]); }
            else { neighbours.add(board[row-rows+1][newCol]); }
        }
        // Add neighours on this row
        if (!outOfBounds(row, col)) {
            int newCol = col;
            // Left
            if (!outOfBounds(row-1, newCol)) { neighbours.add(board[row-1][newCol]); }
            else { neighbours.add(board[row+rows-1][newCol]); }
            // Right
            if (!outOfBounds(row+1, newCol)) { neighbours.add(board[row+1][newCol]); }
            else { neighbours.add(board[row-rows+1][newCol]); }
        }
        // Add neighbours below
        if (!outOfBounds(row, col+1)) {
            int newCol = col+1;
            // Bottom Left
            if (!outOfBounds(row-1, newCol)) { neighbours.add(board[row-1][newCol]); }
            else { neighbours.add(board[row+rows-1][newCol]); }
            // Bottom
            if (!outOfBounds(row, newCol)) { neighbours.add(board[row][newCol]); }
            // Bottom Right
            if (!outOfBounds(row+1, newCol)) { neighbours.add(board[row+1][newCol]); }
            else { neighbours.add(board[row-rows+1][newCol]); }
        // If wrap around required
        } else {
            int newCol = col-cols+1;
            // Bottom Left
            if (!outOfBounds(row-1, col)) { neighbours.add(board[row-1][newCol]); }
            else { neighbours.add(board[row+rows-1][newCol]); }
            // Bottom
            if (!outOfBounds(row, col)) { neighbours.add(board[row][newCol]); }
            // Bottom Right
            if (!outOfBounds(row+1, col)) { neighbours.add(board[row+1][newCol]); }
            else { neighbours.add(board[row-rows+1][newCol]); }
        }

        return neighbours;
    }

    public void printBoard() {
        System.out.println(new String(new char[cols+2]).replace('\0', '#'));
        for (int row = 0; row < rows; row++) {
            System.out.print("#");
            for (int col = 0; col < cols; col++) {
                if (board[row][col]) {
                    System.out.print("o");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("#\n");
        }
        System.out.println(new String(new char[cols+2]).replace('\0', '#'));
    }

    public boolean isAlive(int row, int col) {
        return board[row][col];
    }

    public void start() throws InterruptedException {
        while (true) {
            Thread.sleep(100);
            tick();
            clearScreen();
            printTitle();
            printBoard();
            printRound();
        }
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public void printTitle() {
        System.out.println("Conways Game Of Life");
    }

    public void printRound() {
        System.out.println("Round: " + round);
    }

    public void gameInit() {

        System.out.print("Enter the number of rows (default = 10): ");
        Scanner scanner = new Scanner(System.in);
        try {
            int newRows = Integer.parseInt(scanner.nextLine());
            if (newRows >= minRows) {
                rows = newRows;
            } else {
                rows = minRows;
            }
        } catch (NumberFormatException e) {;}
        System.out.print("Enter the number of columns (default = 10): ");
        try {
            int newCols = Integer.parseInt(scanner.nextLine());
            if (newCols >= minCols) {
                cols = newCols;
            } else {
                cols = minCols;
            }
        } catch (NumberFormatException e) {;}

        board = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = false;
            }
        }

        System.out.print("What life would you like to start?[Blinker, Glider, Toad, Lightweight Spaceship]: ");
        String input = scanner.nextLine();
        switch (input.toLowerCase()) {
            case "blinker" :
                ensureAlive(2, 1);
                ensureAlive(2, 2);
                ensureAlive(2, 3);
                break;
            case "glider" :
                ensureAlive(0, 2);
                ensureAlive(2, 1);
                ensureAlive(2, 2);
                ensureAlive(2, 3);
                ensureAlive(1, 3);
                break;
            case "toad" :
                ensureAlive(2, 1);
                ensureAlive(2, 2);
                ensureAlive(2, 3);
                ensureAlive(1, 0);
                ensureAlive(1, 1);
                ensureAlive(1, 2);
                break;
            case "lightweight spaceship" :
                ensureAlive(1, 1);
                ensureAlive(3, 1);
                ensureAlive(4, 2);
                ensureAlive(4, 3);
                ensureAlive(4, 4);
                ensureAlive(4, 5);
                ensureAlive(3, 5);
                ensureAlive(2, 5);
                ensureAlive(1, 4);
                break;
            case "gosper glider gun" :
            default :
                ensureAlive(1, 1);
                ensureAlive(1, 2);
                ensureAlive(2, 1);
                ensureAlive(4, 4);
                ensureAlive(3, 4);
                ensureAlive(4, 3);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        GameOfLife game = new GameOfLife();
        game.start();
    }
}
