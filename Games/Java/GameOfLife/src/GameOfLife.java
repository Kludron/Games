import java.util.ArrayList;
import java.util.List;

public class GameOfLife {

    public static final int rows = 25;
    public static final int cols = 25;

    private int round = 0;

    private boolean[][] board;

    public GameOfLife() {
        board = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = false;
            }
        }
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
        System.out.println(new String(new char[rows+2]).replace('\0', '#'));
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
        System.out.println(new String(new char[rows+2]).replace('\0', '#'));
    }

    public boolean isAlive(int row, int col) {
        return board[row][col];
    }

    public void start() throws InterruptedException {
        while (true) {
            Thread.sleep(250);
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

    public static void main(String[] args) throws InterruptedException {
        GameOfLife game = new GameOfLife();
        // Create a Blinker
        // game.ensureAlive(2, 2);
        // game.ensureAlive(3, 2);
        // game.ensureAlive(4, 2);
        // Create a Glider
        game.ensureAlive(3, 1);
        game.ensureAlive(3, 2);
        game.ensureAlive(3, 3);
        game.ensureAlive(2, 3);
        game.ensureAlive(1, 2);
        game.start();
    }
}
