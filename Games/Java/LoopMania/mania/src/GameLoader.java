package mania.src;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import mania.src.enemies.*;
import mania.src.enemies.bosses.*;

public class GameLoader {

    private int round = 0;
    private World map = new World();
    private Character character = new Character(map.getAvailablePositions().get(0));
    private LoopMania game = new LoopMania(map, character);

    private int ENEMY_SPAWN_ROUND = 1;
    private int BOSS_SPAWN_ROUND = 20;

    private String startPrompt = "Welcome to The Game!";
    private String controlsPrompt = "Help [h] | PrintMap [p] | PrintStats [c] | Move [m] | Shop [s] | Equip [e] | Unequip [u] | DropItem [d] | Exit [x / q]";

    public boolean playRound() {
        if (!character.isAlive()) {
            System.out.println("Game Over!");
            return false;
        }
        if (round % ENEMY_SPAWN_ROUND == 0) {game.addEnemy(generateRandomEnemy());}
        if (round % BOSS_SPAWN_ROUND == 0 && round > 0) {game.addEnemy(generateRandomBoss());}
        round ++;
        return true;
    }

    public Enemy generateRandomEnemy() {
        Random random = new Random();
        int enemyTypes = 4;
        int randNumber = random.nextInt(enemyTypes*12);
        List<Position> positions = map.getAvailablePositions();
        Position position = positions.get(random.nextInt(positions.size()));

        if (randNumber < 10) {
            return new Zombie(position);
        } else if (randNumber < 20) {
            return new Spider(position);
        } else if (randNumber < 30) {
            return new Ant(position);
        } else {
            return new Slime(position);
        }
    }

    public Boss generateRandomBoss() {
        Random random = new Random();
        int bossTypes = 2;
        int randNumber = random.nextInt(bossTypes*10);
        List<Position> positions = map.getAvailablePositions();
        Position position = positions.get(random.nextInt(positions.size()));

        if (randNumber < 10) {
            return new ZombieBoss(position);
        } else {
            return new UltimateBoss(position);
        }
    }

    public boolean parseCommand(String command) {

        String[] commandValue = command.toLowerCase().strip().split(" ");

        String errorMessage = "Character Unchanged.";

        switch (commandValue[0]) {
            case "h":
                System.out.println(controlsPrompt);
                return true;
            case "s":
                Shop shop = new Shop(character);
                shop.openShop();
                while (shop.isOpen()){;}
                return true;
            case "p":
                map.showWorld();
                return true;
            case "c":
                System.out.println(character.toString());
                return true;
            case "m":
                try {
                    int row = Integer.valueOf(commandValue[1]);
                    int col = Integer.valueOf(commandValue[2]);
                    game.moveEntity(character, new Position(row, col));
                    return playRound();
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Move usage: m [row] [col]\n" + errorMessage);
                    return true;
                }
            case "e":
                try {
                    int itemNum = Integer.parseInt(commandValue[1]);
                    character.equipItem(character.getItems().get(itemNum));
                    System.out.println("Equip Successful!");
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Equip usage: e [item_number]\n" + errorMessage);
                }
                return true;
            case "u":
                try {
                    int itemNum = Integer.parseInt(commandValue[1]);
                    character.unequipItem(character.getEquippedItems().get(itemNum));
                    System.out.println("Unequip Successful!");
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Unequip usage: u [item_number]\n" + errorMessage);
                }
                return true;
            case "d":
                try {
                    int itemNum = Integer.parseInt(commandValue[1]);
                    character.removeItem(character.getItems().get(itemNum));
                    System.out.println("Drop Successful!");
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Drop item usage: d [item_number]\n" + errorMessage);
                }
                return true;
            case "q":
            case "x":
                return false;
            default:
                System.out.println("Command not found.");
                System.out.println(controlsPrompt);
                return true;
        }
    }

    public boolean prompt() {
        Scanner input = new Scanner(System.in);
        System.out.print("> ");
        try {
            String command = input.nextLine();
            return parseCommand(command);
        } catch (NoSuchElementException e) {
            return true;
        }
     }

    public void beginGame() {
        // Starting prompt
        System.out.println(startPrompt);
        System.out.println("Game modes in development...");
        System.out.println(controlsPrompt);
        playRound();
        while(prompt()){;}
        System.out.println("Thank you for playing the game!");
    }

    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.beginGame();
    }

}
