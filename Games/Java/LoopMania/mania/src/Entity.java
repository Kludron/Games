package mania.src;

import java.util.*;

public abstract class Entity implements Fight {
    
    private double health;
    private double defence;
    private List<Item> items = new ArrayList<Item>();
    private int gold;
    private double experience;
    private double damage;
    private Position position;

    /**
     * Basic constructor for an Entity
     * @param position Position: Starting position
     */
    public Entity(Position position) {
        this.position = position;
    }

    /**
     * Sets the entity's damage
     * @param damage double: Amount of Damage to deal
     */
    public void setDamage(double damage) {
        this.damage = damage;
    }

    /**
     * Sets the entity's defence
     * @param defence double: Amount of defence
     */
    public void setDefence(double defence) {
        this.defence = defence;
    }

    /**
     * Sets the entity's experience
     * @param experience double: Experience amount
     */
    public void setExperience(double experience) {
        this.experience = experience;
    }

    /**
     * Sets the entity's gold
     * @param gold int: Gold amount
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

    /**
     * Sets the entity's health
     * @param health double: Health amount
     */
    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * Gets the entity's damage
     * @return double: Damage amount
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Gets the entity's defence
     * @return double: Defence amount
     */
    public double getDefence() {
        return defence;
    }

    /**
     * 
     * @return
     */
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
        if (items.contains(item)) {
            if (items.remove(item)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void takeDamage(double damage) {
        setHealth(Math.max(0, getHealth() - (Math.max(0, damage - getDefence()))));
    }

    @Override
    public void attack(Entity target) {
        target.takeDamage(getDamage());
        if (!target.isAlive()) {getReward(target);}
    }

    public boolean isAlive() {
        return health > (double) 0;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void getReward(Entity opponent) {
        if (!opponent.isAlive()) {
            // Transfer items
            for (Item droppedItem : opponent.getItems()) {
                if (droppedItem != null) {addItem(droppedItem);}
            }
            // Transfer gold
            setGold(getGold()+opponent.getGold());
            // Transfer experience
            setExperience(getExperience()+opponent.getExperience());
        }
    }

}
