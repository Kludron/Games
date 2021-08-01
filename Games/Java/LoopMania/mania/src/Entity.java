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

    public Entity(World map, Position position) {
        map.addEntity(this, position);
        this.position = position;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setDefence(double defence) {
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

    public double getDefence() {
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
                addItem(droppedItem);
            }
            // Transfer gold
            setGold(getGold()+opponent.getGold());
            // Transfer experience
            setExperience(getExperience()+opponent.getExperience());
        }
    }

}
