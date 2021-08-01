package mania.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mania.src.items.Sword;

public class LoopMania {

    private World map;
    private Character character;
    private List<Enemy> enemies = new ArrayList<Enemy>();

    public LoopMania(World map, Character character) {
        this.map = map;
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void addEnemy(Enemy enemy) {
        if (!enemies.contains(enemy)) {enemies.add(enemy);}
    }

    public void removeEnemy(Enemy enemy) {
        if (enemies.contains(enemy)) {enemies.remove(enemy);}
    }

    public void fight(Entity fighterA, Entity fighterB) {
        while (fighterA.isAlive() && fighterB.isAlive()) {
            fighterA.attack(fighterB);
            if (fighterB.isAlive()) {fighterB.attack(fighterA);}
        }
        if (!fighterA.isAlive()) {
            if (fighterA instanceof Enemy) {
                enemies.remove(fighterA);
            }
        } 
        if (!fighterB.isAlive()) {
            if (fighterB instanceof Enemy) {
                enemies.remove(fighterB);
            }
        } 
    }

    public static Item randItem() {
        Random random = new Random();
        int randNum = random.nextInt(100);

        if (randNum < 10) {
            return new Sword();
        }
        return null;
    }

    public void moveEntity(Entity entity, Position position) {
        if (map.isEmpty(position)) {map.moveEntity(entity, position);}
        else {
            fight(entity, map.getEntity(position));
            if (entity != null) {map.moveEntity(entity, position);}
        }
    }

}