package src;

import java.util.List;

public class GameOfLife {

    public static final int rows = 10;
    public static final int cols = 10;

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
 
        board = nextBoard;
    }

    public List<Boolean> getNeighbours(int row, int col) {
        List<Boolean> neighbours = new ArrayList<>();
        
        // Add neighbours above
        if (!outOfBounds(row, col-1)) {
            int newCol = col-1;
            // Top Left
            if (!outOfBounds(row-1, newCol)) { neighbours.add(board[row-1][newCol].get()); }
            else { neighbours.add(board[row+numRows-1][newCol].get()); }
            // Top
            if (!outOfBounds(row, newCol)) { neighbours.add(board[row][newCol].get()); }
            // Top Right
            if (!outOfBounds(row+1, newCol)) { neighbours.add(board[row+1][newCol].get()); }
            else { neighbours.add(board[row-numRows+1][newCol].get()); }
        // If wrap around required
        } else {
            int newCol = col+numCols-1;
            // Top Left
            if (!outOfBounds(row-1, col)) { neighbours.add(board[row-1][newCol].get()); }
            else { neighbours.add(board[row+numRows-1][newCol].get()); }
            // Top
            if (!outOfBounds(row, col)) { neighbours.add(board[row][newCol].get()); }
            // Top Right
            if (!outOfBounds(row+1, col)) { neighbours.add(board[row+1][newCol].get()); }
            else { neighbours.add(board[row-numRows+1][newCol].get()); }
        }
        // Add neighours on this row
        if (!outOfBounds(row, col)) {
            int newCol = col;
            // Left
            if (!outOfBounds(row-1, newCol)) { neighbours.add(board[row-1][newCol].get()); }
            else { neighbours.add(board[row+numRows-1][newCol].get()); }
            // Right
            if (!outOfBounds(row+1, newCol)) { neighbours.add(board[row+1][newCol].get()); }
            else { neighbours.add(board[row-numRows+1][newCol].get()); }
        }
        // Add neighbours below
        if (!outOfBounds(row, col+1)) {
            int newCol = col+1;
            // Bottom Left
            if (!outOfBounds(row-1, newCol)) { neighbours.add(board[row-1][newCol].get()); }
            else { neighbours.add(board[row+numRows-1][newCol].get()); }
            // Bottom
            if (!outOfBounds(row, newCol)) { neighbours.add(board[row][newCol].get()); }
            // Bottom Right
            if (!outOfBounds(row+1, newCol)) { neighbours.add(board[row+1][newCol].get()); }
            else { neighbours.add(board[row-numRows+1][newCol].get()); }
        // If wrap around required
        } else {
            int newCol = col-numCols+1;
            // Bottom Left
            if (!outOfBounds(row-1, col)) { neighbours.add(board[row-1][newCol].get()); }
            else { neighbours.add(board[row+numRows-1][newCol].get()); }
            // Bottom
            if (!outOfBounds(row, col)) { neighbours.add(board[row][newCol].get()); }
            // Bottom Right
            if (!outOfBounds(row+1, col)) { neighbours.add(board[row+1][newCol].get()); }
            else { neighbours.add(board[row-numRows+1][newCol].get()); }
        }

        return neighbours;
    }

    public void printBoard() {
        System.out.println("#" * (rows + 2));
        for (int row = 0; row < rows; row++) {
            System.out.print("#")
            for (int col = 0; col < cols; col++) {
                if (board[row][col]) {
                    System.out.print("o");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("#\n")
        }
        System.out.println("#" * (rows + 2));
    }
}