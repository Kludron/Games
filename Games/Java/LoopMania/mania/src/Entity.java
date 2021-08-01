import java.util.*;

public abstract class Entity implements Fight {
    
    private double health;
    private double defence;
    private List<Item> items = new ArrayList<Item>();
    private int gold;
    private double experience;
    private double damage;

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDamage() {
        return damage;
    }

    public int getDefence() {
        return defence;
    }

    public double getExperience() {
        return experience;
    }

    public int getGold() {
        return gold;
    }

    public double getHealth() {
        return health;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        if (!items.contains(item)) {items.add(item);}
    }

    public Item removeItem(Item item) {
        if (items.contains(item)) {return items.remove(item);}
        return null;
    }

    @Override
    public void takeDamage(double damage) {
        setHealth(Math.max(0, getHealth() - (Math.max(0, damage - getDefence()))));
    }

    @Override
    public void attack(Entity target) {
        target.takeDamage(getDamage());
        if (!target.isAlive()) {
            // Transfer items
            for (Item droppedItem : target.getItems()) {
                addItem(target.removeItem(droppedItem));
            }
            // Transfer gold
            setGold(getGold()+target.getGold());
            // Transfer experience
            setExperience(getExperience()+target.getExperience());
        }
    }

    public boolean isAlive() {
        return health > (double) 0;
    }

}
