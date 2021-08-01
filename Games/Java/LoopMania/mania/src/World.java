package mania.src;

public class World {
    
    public static final int rows = 10;
    public static final int cols = 10;
    Entity[][] grid = new Entity[rows][cols];

    public boolean isEmpty(Position position) {
        return grid[position.getRow()][position.getCol()] == null;
    }

    public boolean addEntity(Entity entity, Position position) {
        if (isEmpty(position) && !outOfBounds(position)) {
            grid[position.getRow()][position.getCol()] = entity;
            return true;
        }
        return false;
    }

    public boolean moveEntity(Entity entity, Position position) {
        if (!outOfBounds(position) && isEmpty(position)) {
            grid[position.getRow()][position.getCol()] = entity;
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

}
