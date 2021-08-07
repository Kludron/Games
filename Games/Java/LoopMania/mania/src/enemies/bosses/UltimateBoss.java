package mania.src.enemies.bosses; 

import mania.src.LoopMania;
import mania.src.Position;
import mania.src.enemies.Boss;

public class UltimateBoss extends Boss {

    private double health = 1000;
    private double damage = 500;
    private double defence = 150;
    private double experience = 500;
    private int gold = 200;

    public UltimateBoss(Position position) {
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