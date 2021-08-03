package mania.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {

    private List<Item> items = new ArrayList<Item>();
    private Character character;

    private boolean isOpen = true;

    private int MAX_SHOP_SIZE = 4; // Number of items in the shop

    public Shop(Character character) {
        this.character = character;
        for (int i = 0; i < MAX_SHOP_SIZE; i++) {
            Item item = LoopMania.randItem();
            if (item != null) {
                items.add(item);
            }
        }
    }

    public void openShop() {
        Scanner input = new Scanner(System.in);
        String helpPanel = "Help [h] | ShowShopItems [i] | ShowCharacterItems [c] | Purchase [p] | Sell [s] | CloseShop [q]";
        System.out.println(helpPanel);
        while (isOpen()) {
            System.out.print("shop> ");
            String command = input.nextLine();
            switch (command.toLowerCase().strip()) {
                case "h":
                    System.out.println(helpPanel);
                    break;
                case "i":
                    for (int item = 0; item < items.size(); item++) {
                        System.out.println(item + " - " + items.get(item).getClass().getName() + ": " + items.get(item).getBuyPrice());
                    }
                    break;
                case "c":
                    System.out.println("Gold = " + character.getGold());
                    for (Item item : character.getItems()) {
                        System.out.println(item.getClass().getSimpleName().substring(15) + ": " + item.getBuyPrice());
                    }
                    break;
                case "p":
                    System.out.println("Gold = " + character.getGold());
                    System.out.print("Which item would you like to purchase? [0-"+(items.size()-1)+"]: ");
                    try {
                        int itemNum = Integer.parseInt(input.nextLine());
                        purchase(itemNum);
                    } catch (Exception e) {
                        System.out.println("An error occurred while purchasing the item...");
                    }
                    break;
                case "s":
                    System.out.println("Gold = " + character.getGold());
                    System.out.print("Which item would you like to sell? [0-"+(character.getItems().size()-1)+"]: ");
                    try {
                        int itemNum = Integer.parseInt(input.nextLine());
                        sell(itemNum);
                    } catch (Exception e) {
                        System.out.println("An error occurred while selling the item...");
                    }
                    break;
                case "q":
                    closeShop();
                    break;
                default:
                    System.out.println("Command not found.");
                    System.out.println(helpPanel);
            }
        }
        System.out.println("Thank you for visiting the shop!");
        input.close();
    }

    public void closeShop() {
        isOpen = false;
    }

    public boolean isOpen() {
        return isOpen;
    }
    
    public void purchase(int itemNumber) {
        if (itemNumber < items.size() && character.getGold() >= items.get(itemNumber).getBuyPrice()) {
            character.addItem(items.get(itemNumber));
            character.setGold(character.getGold()-items.get(itemNumber).getBuyPrice());
            System.out.println("Purchase Successful");
        } else {
            System.out.println("Purchase Unsuccessful");
        }
    }

    public void sell(int itemNumber) {
        List<Item> characterItems = character.getItems();
        if (itemNumber < characterItems.size() && !character.isEquipped(characterItems.get(itemNumber))) {
            character.removeItem(characterItems.get(itemNumber));
            character.setGold(character.getGold() + characterItems.get(itemNumber).getSellPrice());
            System.out.println("Sale Successful");
        } else {
            System.out.println("Sale Unsuccessful");
        }
    }

}
