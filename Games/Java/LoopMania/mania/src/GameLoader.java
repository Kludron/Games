package mania.src;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import mania.src.enemies.Slime;
import mania.src.enemies.Zombie;

public class GameLoader {

    private int round = 0;
    private World map = new World();
    private Character character = new Character(map.getAvailablePositions().get(0));
    private LoopMania game = new LoopMania(map, character);

    private int ENEMY_SPAWN_ROUND = 1;

    private String startPrompt = "Welcome to The Game!";
    private String controlsPrompt = "Help [h] | PrintMap [p] | PrintStats [c] | Move [m] | Shop [s] | Equip [e] | Unequip [u] | Exit [x / q]";

    public boolean playRound() {
        if (!character.isAlive()) {
            System.out.println("Game Over!");
            return false;
        }
        if (round % ENEMY_SPAWN_ROUND == 0) {game.addEnemy(generateRandomEnemy());}
        round ++;
        return true;
    }

    public Enemy generateRandomEnemy() {
        Random random = new Random();
        int enemyTypes = 2;
        int randNumber = random.nextInt(enemyTypes*10);
        List<Position> positions = map.getAvailablePositions();
        Position position = positions.get(random.nextInt(positions.size()));

        if (randNumber < 10) {
            return new Zombie(position);
        } else {
            return new Slime(position);
        }
    }

    public boolean prompt() {
        Scanner input = new Scanner(System.in);
        System.out.print("> ");
        try {
        String command = input.nextLine();
        switch (command.toLowerCase().strip()) {
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
            case "q":
            case "x":
                return false;
            case "m":
                System.out.print("Where would you like to move to? [row col]: ");
                try {
                    String[] nums = input.nextLine().split(" ");
                    int row = Integer.valueOf(nums[0]);
                    int col = Integer.valueOf(nums[1]);
                    game.moveEntity(character, new Position(row, col));
                } catch (Exception e) {
                    System.out.println("An error occurred while attempting to move the character.");
                }
                return playRound();
            case "e":
                System.out.print("Which item would you like to equip? [0-"+(character.getItems().size()-1)+"]: ");
                try {
                    int itemNum = Integer.parseInt(input.nextLine());
                    character.equipItem(character.getItems().get(itemNum));
                    System.out.println("Equip Successful");
                } catch (Exception e) {
                    System.out.println("An error occurred while trying to equip the item.");
                    System.out.println("Equip Unsuccessful");
                }
                return true;
            default:
                System.out.println("Command not found.");
                System.out.println(controlsPrompt);
                return true;
        }
        } catch (NoSuchElementException e) {
            return false;
        }
     }

    public void beginGame() {
        // Starting prompt
        System.out.println(startPrompt);
        System.out.println("Game modes in development...");
        System.out.println(controlsPrompt);
        while(prompt()){;}
        System.out.println("Thank you for playing the game!");
    }

    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.beginGame();
    }

}
