package mania.src.enemies;

import mania.src.Enemy;
import mania.src.LoopMania;
import mania.src.Position;

public class Ant extends Enemy {

    private double health = 100;
    private double damage = 75;
    private double defence = 80;
    private double experience = 100;
    private int gold = 75;

    public Ant(Position position) {
        super(position);
        setDamage(damage);
        setHealth(health);
        setDefence(defence);
        setExperience(experience);
        setGold(gold);
        addItem(LoopMania.randItem(0.8));
    }

}