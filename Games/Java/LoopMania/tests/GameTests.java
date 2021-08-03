package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import mania.src.Character;
import mania.src.Enemy;
import mania.src.GameLoader;
import mania.src.LoopMania;
import mania.src.Position;
import mania.src.World;
import mania.src.enemies.Zombie;

public class GameTests {

    @Test
    public void Tests() {
        World map = new World();
        Character character = new Character(new Position(0, 0));
        LoopMania game = new LoopMania(map, character);

        assertEquals((double) 100, character.getHealth());

        Enemy enemy = new Zombie(new Position(1, 1));
        game.addEnemy(enemy);
        
        game.moveEntity(character, new Position(1, 1));

        assertEquals(character.getPosition().getRow(), 1);
        assertEquals(character.getPosition().getCol(), 1);

        assertEquals(character.getHealth(), (double) 60);

    }    

}
