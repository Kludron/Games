package mania.src;

import java.util.List;

public class LoopMania {

    private World map;
    private Character character;
    private List<Enemy> enemies;

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

}