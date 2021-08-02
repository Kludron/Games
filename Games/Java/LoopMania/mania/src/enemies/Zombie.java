package mania.src.enemies;

import mania.src.Enemy;
import mania.src.LoopMania;
import mania.src.Position;
import mania.src.World;

public class Zombie extends Enemy {

    private double health = 25;
    private double damage = 10;
    private double defence = 0;
    private double experience = 10;
    private int gold = 5;

    public Zombie(Position position) {
        super(position);
        super.setDamage(damage);
        super.setHealth(health);
        super.setDefence(defence);
        super.setExperience(experience);
        super.setGold(gold);
        super.addItem(LoopMania.randItem());
    }

}