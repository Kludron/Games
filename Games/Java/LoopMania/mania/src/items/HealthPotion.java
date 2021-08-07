package mania.src.items;

import mania.src.Character;
import mania.src.Item;

public class HealthPotion extends Item {
    
    private double health = 20;
    private int buyPrice = 100;
    private int sellPrice = buyPrice / 2;

    public HealthPotion() {
        setHealth(health);
        setSellPrice(sellPrice);
        setBuyPrice(buyPrice);
    }

    @Override
    public Character wrapper(Character character) {
        character.setHealth(Math.min(character.getHealth()+getHealth(), character.getMaxHealth()));
        setHealth(0);
        return character;
    }

}