package mania.src.items;

import mania.src.Item;

public class Shield extends Item {
    
    private double defence = 5;
    private int buyPrice = 50;
    private int sellPrice = buyPrice / 2;

    public Shield() {
        setDefence(defence);
        setSellPrice(sellPrice);
        setBuyPrice(buyPrice);
    }

}