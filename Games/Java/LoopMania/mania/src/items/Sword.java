package mania.src.items;

import mania.src.Item;

public class Sword extends Item {
    
    private double damage = 10;
    private int buyPrice = 50;
    private int sellPrice = buyPrice / 2;

    public Sword() {
        setDamage(damage);
        setSellPrice(sellPrice);
        setBuyPrice(buyPrice);
    }

}
