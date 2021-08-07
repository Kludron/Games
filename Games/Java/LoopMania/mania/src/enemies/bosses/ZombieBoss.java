package mania.src.enemies.bosses;

import mania.src.LoopMania;
import mania.src.Position;
import mania.src.enemies.Boss;

public class ZombieBoss extends Boss {

    private double health = 200;
    private double damage = 50;
    private double defence = 20;
    private double experience = 150;
    private int gold = 80;

    public ZombieBoss(Position position) {
        super(position);
        setDamage(damage);
        setHealth(health);
        setDefence(defence);
        setExperience(experience);
        setGold(gold);
        addItem(LoopMania.randItem(1));
        addItem(LoopMania.randItem(1));
        addItem(LoopMania.randItem(0.5));
    }

}