package mania.src;

public abstract class Enemy extends Entity {

    /**
     * Basic constructor for enemy type
     * @param position Position: Starting position
     */
    public Enemy(Position position) {
        super(position);
    }

    @Override
    public String toString() {
        return "=========="
            + "\nType: " + this.getClass().getSimpleName()
            + "\nHealth: " + getHealth()
            + "\nDamage: " + getDamage()
            + "\n==========\n";
    }

}
