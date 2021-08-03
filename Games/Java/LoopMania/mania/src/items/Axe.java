package mania.src.items;

import mania.src.Item;

public class Axe extends Item {
    
    private double damage = 15;
    private int buyPrice = 35;
    private int sellPrice = buyPrice / 2;

    public Axe() {
        setDamage(damage);
        setSellPrice(sellPrice);
        setBuyPrice(buyPrice);
    }

}