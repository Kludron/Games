package mania.src;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import mania.src.items.*;

public class LoopMania {

    private World map;
    private Character character;
    private List<Enemy> enemies = new ArrayList<Enemy>();

    public LoopMania(World map, Character character) {
        this.map = map;
        this.character = character;
        map.addEntity(character, character.getPosition());
    }

    public LoopMania(World map) {
        this.map = map;
    }

    public void addCharacter(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void addEnemy(Enemy enemy) {
        if (!enemies.contains(enemy)) {
            enemies.add(enemy);
            map.addEntity(enemy, enemy.getPosition());
        }
    }

    public void removeEnemy(Enemy enemy) {
        if (enemies.contains(enemy)) {enemies.remove(enemy);}
    }

    public void fight(Entity fighterA, Entity fighterB) {
        Scanner input = new Scanner(System.in);
        System.out.println("Fighter 1:\n" + fighterA.toString() + "Fighter 2:\n" + fighterB.toString());
        System.out.print("Fight? [Y/N]: ");
        try {
            String answer = input.nextLine().strip().toLowerCase();
            if (answer.equals("y")) {;}
            else {return;}
        } catch (NoSuchElementException e) {
            return;
        }
        while (fighterA.isAlive() && fighterB.isAlive()) {
            fighterA.attack(fighterB);
            if (fighterB.isAlive()) {fighterB.attack(fighterA);}
        }
        if (!fighterA.isAlive()) {
            if (fighterA instanceof Enemy) {
                enemies.remove(fighterA);
                map.clearPosition(fighterA.getPosition());
            }
        } 
        if (!fighterB.isAlive()) {
            if (fighterB instanceof Enemy) {
                enemies.remove(fighterB);
                map.clearPosition(fighterB.getPosition());
            }
        }
    }

    public static Item randItem(double spawnChance) {
        int numItems = 4;
        Random random = new Random();

        int chance = 10 * numItems + (int)(10 * (1-spawnChance) * numItems);
        int randNum = random.nextInt(chance);

        if (randNum < 10) {return new Sword();}
        else if (randNum < 20) {return new Axe();}
        else if (randNum < 30) {return new HealthPotion();}
        else if (randNum < 40) {return new Shield();}
        else {return null;}

    }

    public void moveEntity(Entity entity, Position position) {
        if (entity.getPosition().equals(position)) {return;} // Not moving
        if (map.isEmpty(position)) {map.moveEntity(entity, position);}
        else {
            fight(entity, map.getEntity(position));
            // If they fled
            if (entity != null && !map.isEmpty(position)) {return;}
            if (entity != null) {map.moveEntity(entity, position);}
        }
    }

}