package mania.src;

public abstract class Item {
    
    private double damage = 0;
    private int buyPrice = 0;
    private int sellPrice = 0;
    private double defence = 0;
    private double health = 0;

    public double getDamage() {
        return damage;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public double getDefence() {
        return defence;
    }

    public double getHealth() {
        return health;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Character wrapper(Character character) {
        character.setDamage(character.getDamage()+getDamage());
        character.setDefence(character.getDefence()+getDefence());
        character.setHealth(character.getHealth()+getHealth());
        return character;
    }

    public Character unwrap(Character character) {
        character.setDamage(character.getDamage()-getDamage());
        character.setDefence(character.getDefence()-getDefence());
        character.takeDamage(getHealth());
        return character;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

}
