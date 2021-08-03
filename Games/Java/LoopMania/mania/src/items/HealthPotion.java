package mania.src.items;

import mania.src.Item;

public class HealthPotion extends Item {
    
    private double health = 20;
    private int buyPrice = 30;
    private int sellPrice = buyPrice / 2;

    public HealthPotion() {
        setHealth(health);
        setSellPrice(sellPrice);
        setBuyPrice(buyPrice);
    }

}