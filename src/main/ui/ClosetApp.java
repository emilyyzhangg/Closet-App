package ui;

import model.Closet;
import model.ClothingItem;
import model.Outfit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Closet Application
public class ClosetApp {
    private Closet closet;
    private Outfit display;
    private List<ClothingItem> clothes;
    private Scanner input;


    // MODIFIES: this
    // EFFECTS: processes user input to run closet app
    private void runCloset() {
        boolean keepGoing = true;

        init();

        while (keepGoing) {
            displayHomeMenu();
            String command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes command given by the user
    private void processCommand(String command) {
        if (command.equals("d")) {
            getDressed(display);
        } else if (command.equals("a")) {
            addToCloset();
        } else if (command.equals("r")) {
            removeFromCloset();
        } else if (command.equals("v")) {
            viewItemsInCloset();
        } else if (command.equals("n")) {
            createOutfit();
        } else if (command.equals("o")) {
            viewSavedOutfit();
        } else {
            System.out.println("Sorry, that's not a valid input. Please try again!");
        }

    }

    // MODIFIES: this
    // EFFECTS: initializes closet and current outfit
    private void init() {
        closet = new Closet();
        clothes = new ArrayList<>();
        display = new Outfit("Today's Outfit", clothes);
        input = new Scanner(System.in);
    }

    // EFFECTS: displays home menu for users to interact with
    private void displayHomeMenu() {
        System.out.println("\nWelcome to your virtual closet!");
        System.out.println("\tEnter 'd' to get dressed");
        System.out.println("\tEnter 'a' to add a clothing item to your closet");
        System.out.println("\tEnter 'r' to remove a clothing item from you closet");
        System.out.println("\tEnter 'v' to view all the items in your closet");
        System.out.println("\tEnter 'n' to create and save a new outfit");
        System.out.println("\tEnter 'o' to view all your saved outfits");
        System.out.println("\tEnter 'q' to quit");
    }

    // MODIFIES: this
    // EFFECTS: creates a new clothing item and adds it to the closet if possible (name not a duplicate)
    private void addToCloset() {
        System.out.println("Add a New Clothing Item to Your Closet:");

        String name = getClothingName();
        String colour = getClothingColour();
        String category = getClothingCategory();

        ClothingItem newItem = new ClothingItem(name, colour, category);

        closet.addItem(newItem);
    }

    // EFFECTS: prompts user for the name of a new clothing item and returns the given string
    private String getClothingName() {
        String command = input.next();

        System.out.println("Please enter the name of your item: ");
        String name = command;

        while (!closet.isValidName(name)) {
            System.out.println("Sorry, another item in your closet has the same name");
            System.out.println("Please try again with a different name.");
            getClothingName();
        }
        return name;
    }

    // EFFECTS: prompts user for the colour of a new clothing items and returns the given string
    private String getClothingColour() {
        System.out.println("\nTo set the colour of your item, please enter one of the following: ");
        System.out.println("\tred");
        System.out.println("\torange");
        System.out.println("\tyellow");
        System.out.println("\tgreen");
        System.out.println("\tblue");
        System.out.println("\tpurple");
        System.out.println("\tblack");
        System.out.println("\twhite");
        System.out.println("\tbrown");

        String command = input.next();
        command = command.toLowerCase();

        while (!isValidColour(command)) {
            System.out.println("Sorry, that's not a valid colour. Please try again");
            getClothingColour();
        }
        return command;
    }

    // EFFECTS: returns true if user input matches one of the valid colours. if not, returns false
    private boolean isValidColour(String command) {
        return command.equals("red") || command.equals("orange") || command.equals("yellow") || command.equals("green")
                || command.equals("blue") || command.equals("purple") || command.equals("black")
                || command.equals("white") || command.equals("brown");
    }

    // EFFECTS: prompts user for the category of a new clothing items and returns the given string
    private String getClothingCategory() {
        System.out.println("\nTo set the category of your item, please enter one of the following: ");
        System.out.println("\taccessories");
        System.out.println("\tshirts");
        System.out.println("\tjackets");
        System.out.println("\tpants");
        System.out.println("\tskirts");
        System.out.println("\tdresses");
        System.out.println("\tshoes");

        String command = input.next();
        command = command.toLowerCase();

        while (!isValidCategory(command)) {
            System.out.println("Sorry, that's not a valid category. Please try again.");
            getClothingCategory();
        }
        return command;
    }

    // EFFECTS: returns true if the user input matches one of the valid colours; else returns false
    private boolean isValidCategory(String command) {
        return command.equals("accessories") || command.equals("shirts") || command.equals("jackets")
                || command.equals("pants") || command.equals("skirts") || command.equals("dresses")
                || command.equals("shoes");
    }

    // MODIFIES: this
    // EFFECTS: removes item from closet if it exists, else do nothing
    public void removeFromCloset() {
        String command = input.next();
        ClothingItem itemRemoved;

        System.out.println(closet.allItemsNames());
        System.out.println("Please input the name of the item you want to remove: ");

        if (closet.allItemsNames().contains(command)) {
            itemRemoved = closet.getItemFromName(command);
            closet.removeItem(itemRemoved);
        } else {
            System.out.println("Sorry, there is no item in your closet with that name. Please try again.");
            removeFromCloset();
        }
    }

    // EFFECTS: returns the names of all of the items in the closet
    public void viewItemsInCloset() {
        if (closet.numTotalItems() == 0) {
            System.out.println("Your closet is currently empty.");
        } else {
            System.out.println(closet.allItemsNames());
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new outfit
    public void createOutfit() {
        String command = input.next();
        List<ClothingItem> clothes = new ArrayList<>();

        System.out.println("Please enter the name of this outfit: ");
        String name = command;

        while (addItemToOutfit(clothes)) {
            addItemToOutfit(clothes);
        }
        Outfit newOutfit = new Outfit(name, clothes);
    }

    // MODIFIES: this
    // EFFECTS: adds an item of clothing to an outfit
    public boolean addItemToOutfit(List<ClothingItem> clothes) {
        String command = input.next();

        System.out.println(closet.allItemsNames());
        System.out.println("Please enter the name of the item you wish to add: ");
        System.out.println("When done, enter 'q'.");

        if (command.equals("q")) {
            return false;
        } else if (closet.allItemsNames().contains(command)) {
            clothes.add(closet.getItemFromName(command));
            System.out.println("Item successfully added to closet.");
            return true;
        } else {
            System.out.println("Sorry, this item doesn't exist. Please try again.");
            return false;
        }
    }

    // EFFECTS: returns a the names of the clothing items in the saved outfit with given name;
    public void viewSavedOutfit() {
        System.out.println("Please enter the name of the outfit you wish to view");


    }

    // MODIFIES: this
    // EFFECTS: displays daily outfit and allows user to add and remove items from it
    private void getDressed(Outfit outfit) {
        System.out.println("");
    }






}
