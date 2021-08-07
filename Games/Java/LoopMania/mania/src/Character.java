package mania.src;

import java.util.ArrayList;
import java.util.List;

public class Character extends Entity {
    
    private double maxHealth = 100;
    private double health = maxHealth;
    private double damage = 5;
    private double defence = 0;
    private double experience = 0;
    private int level = 0;
    private int gold = 0;

    private List<Item> equippedItems = new ArrayList<Item>();

    /**
     * Basic constructor for Character
     * @param position Position: Starting position
     */
    public Character(Position position) {
        super(position);
        super.setDamage(damage);
        super.setHealth(health);
        super.setDefence(defence);
        super.setExperience(experience);
        super.setGold(gold);
    }

    /**
     * Equips an item
     * @param item Item: Item to equip
     */
    public void equipItem(Item item) {
        item.wrapper(this);
        equippedItems.add(item);
        removeItem(item);
    }

    /**
     * Unequips an item
     * @param item Item: Item to unequip
     */
    public void unequipItem(Item item) {
        // TODO: Ensure unequipping this item won't kill them
        if (!equippedItems.contains(item)) {System.out.println("Item not found.");}
        item.unwrap(this);
        equippedItems.remove(item);
        addItem(item);
    }

    /**
     * Gets a list of all the characters equipped items
     * @return List<Item>: Equipped Items
     */
    public List<Item> getEquippedItems() {
        return equippedItems;
    }

    @Override
    public String toString() {
        return "=========="
            + "\nLevel: " + getLevel() 
            + "\nHealth: " + getHealth()
            + "\nExperience: " + getExperience()
            + "\nGold: " + getGold()
            + "\nDamage: " + getDamage()
            + "\nDefence: " + getDefence()
            + "\nItems:" + getStringItems(getItems())
            + "\nEquipped:" + getStringItems(getEquippedItems())
            + "\n==========\n";
    }

    public int getLevel() {
        return level;
    }

    /**
     * Checks whether or not the given item is equipped
     * @param item Item: The item to check
     * @return boolean: Is item equipped?
     */
    public boolean isEquipped(Item item) {
        return equippedItems.contains(item);
    }

    /**
     * Get the items as a string
     * @param items List<Item>: List of items to conver to string
     * @return String: List of items in string form
     */
    public String getStringItems(List<Item> items) {
        String result = "[";
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item != null) {result += items.get(i).getName();};
            if (i < items.size()-1) {result += ", ";}
        }
        return result + "]";
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void levelUp() {
        level ++;
        setMaxHealth(getMaxHealth()+20);
        setHealth(getMaxHealth());
        super.setExperience(0);   
    }

    @Override
    public void setExperience(double experience) {
        if (experience >= (1000 + level*200)) {levelUp();}
        else {super.setExperience(experience);}
    }

}
