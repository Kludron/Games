package mania.src;

public class Position {

    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    
    // @Override
    public boolean equals(Position position) {
        if (row == position.getRow() && col == position.getCol() && position.getClass() == this.getClass()) {return true;}
        return false;
    }

}
