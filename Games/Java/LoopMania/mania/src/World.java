package mania.src;

import java.util.ArrayList;
import java.util.List;

import mania.src.enemies.Zombie;

public class World {
    
    private int rows = 10;
    private int cols = 10;
    private Entity[][] grid = new Entity[rows][cols];

    public World() {;}

    public World(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public boolean isEmpty(Position position) {
        return grid[position.getRow()][position.getCol()] == null;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Entity[][] getGrid() {
        return grid;
    }

    public boolean addEntity(Entity entity, Position position) {
        if (isEmpty(position) && !outOfBounds(position)) {
            grid[position.getRow()][position.getCol()] = entity;
            return true;
        }
        return false;
    }

    public void clearPosition(Position position) {
        if (!outOfBounds(position)) {
            grid[position.getRow()][position.getCol()] = null;
        }
    }

    public boolean moveEntity(Entity entity, Position position) {
        if (!outOfBounds(position) && isEmpty(position)) {
            // Set the entity's new position
            grid[position.getRow()][position.getCol()] = entity;
            // Clear their old position
            grid[entity.getPosition().getRow()][entity.getPosition().getCol()] = null;
            entity.setPosition(position);
            return true;
        }
        return false;
    }

    public boolean outOfBounds(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return true;
        }
        return false;
    }

    public Entity getEntity(Position position) {
        return grid[position.getRow()][position.getCol()];
    }

    public void showWorld() {
        System.out.print("  ");
        for (int i = 0; i < cols; i++) {System.out.print(i+" ");}
        System.out.println("");
        for (int row = 0; row < rows; row++) {
            System.out.print(row+" ");
            for (int col = 0; col < cols; col++) {
                Position curPos = new Position(row, col);
                if (!isEmpty(curPos)) {
                    if (getEntity(curPos) instanceof Character) {
                        System.out.print("c ");
                    } else if (getEntity(curPos) instanceof Enemy) {
                        if (getEntity(curPos) instanceof Zombie) {
                            System.out.print("z ");
                        } else {
                            System.out.print("e ");
                        }
                    } else {
                        System.out.print("o ");
                    }
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println("");
        }
    }

    public List<Position> getAvailablePositions() {
        List<Position> positions = new ArrayList<Position>();
        for (int row = 0; row < rows; row ++) {
            for (int col = 0; col < cols; col ++) {
                Position position = new Position(row, col);
                if (isEmpty(position)) {positions.add(position);}
            }
        }
        return positions;
    }

}
