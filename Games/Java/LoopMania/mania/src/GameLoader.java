package mania.src;

import mania.src.enemies.Zombie;

public class GameLoader {
    
    public static void main(String[] args) {

        World map = new World();
        Character character = new Character(map, new Position(0, 0));
        LoopMania game = new LoopMania(map, character);
        Zombie enemy = new Zombie(map, new Position(1, 1));

        game.addEnemy(enemy);

        System.out.println(character.getHealth());
        game.moveEntity(enemy, character.getPosition());
        System.out.println(character.getHealth());
        // Enemy is dead so the health should be the same
        game.moveEntity(enemy, character.getPosition());
        System.out.println(character.getHealth());

    }

}
