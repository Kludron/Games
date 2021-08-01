package mania.src;

public class Character extends Entity {
    
    private double health = 100;
    private double damage = 5;
    private double defence = 0;
    private double experience = 0;
    private int gold = 0;

    public Character() {
        super();
        super.setDamage(damage);
        super.setHealth(health);
        super.setDefence(defence);
        super.setExperience(experience);
        super.setGold(gold);
    }

}
