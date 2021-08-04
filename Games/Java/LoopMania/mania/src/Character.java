package mania.src;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;

public class Character extends Entity {
    
    private double health = 100;
    private double damage = 5;
    private double defence = 0;
    private double experience = 0;
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

    @Override
    public String toString() {
        return "=========="
            + "\nHealth: "+ getHealth()
            + "\nExperience: "+ getExperience()
            + "\nGold: "+ getGold()
            + "\nDamage: " + getDamage()
            + "\nDefence: " + getDefence()
            + "\nItems:" + getStringItems()
            + "\n==========";
    }

    public boolean isEquipped(Item item) {
        return equippedItems.contains(item);
    }

    public String getStringItems() {
        String result = "[";
        for (int i = 0; i < getItems().size(); i++) {
            Item item = getItems().get(i);
            if (item != null) {result += getItems().get(i).getName();};
            if (i < getItems().size()-1) {result += ", ";}
        }
        return result + "]";
    }

}
