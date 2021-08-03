package mania.src.enemies;

import mania.src.Enemy;
import mania.src.LoopMania;
import mania.src.Position;

public class Slime extends Enemy {

    private double health = 15;
    private double damage = 5;
    private double defence = 0;
    private double experience = 5;
    private int gold = 5;

    public Slime(Position position) {
        super(position);
        setDamage(damage);
        setHealth(health);
        setDefence(defence);
        setExperience(experience);
        setGold(gold);
        addItem(LoopMania.randItem());
    }

}