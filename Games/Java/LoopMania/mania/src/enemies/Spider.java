package mania.src.enemies;

import mania.src.Enemy;
import mania.src.LoopMania;
import mania.src.Position;

public class Spider extends Enemy {

    private double health = 75;
    private double damage = 30;
    private double defence = 15;
    private double experience = 30;
    private int gold = 20;

    public Spider(Position position) {
        super(position);
        setDamage(damage);
        setHealth(health);
        setDefence(defence);
        setExperience(experience);
        setGold(gold);
        addItem(LoopMania.randItem(0.8));
    }

}