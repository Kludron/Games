package mania.src.enemies;

import mania.src.Enemy;
import mania.src.LoopMania;
import mania.src.Position;

public class Zombie extends Enemy {

    private double health = 45;
    private double damage = 15;
    private double defence = 2;
    private double experience = 10;
    private int gold = 10;

    public Zombie(Position position) {
        super(position);
        setDamage(damage);
        setHealth(health);
        setDefence(defence);
        setExperience(experience);
        setGold(gold);
        addItem(LoopMania.randItem(0.5));
    }

}