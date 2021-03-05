package ui;

import model.Closet;
import model.ClothingItem;
import model.Outfit;
import model.SavedOutfits;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Closet Application
public class ClosetApp {
    private static final String JSON_STORE_CLOSET = "./data/Closet.json";
    private static final String JSON_STORE_SAVED_OUTFITS = "./data/SavedOutfits.json";
    private final JsonWriter jsonWriterCloset;
    private final JsonReader jsonReaderCloset;
    private final JsonWriter jsonWriterSavedOutfits;
    private final JsonReader jsonReaderSavedOutfits;

    private Closet closet;
    private Outfit display;
    private SavedOutfits savedOutfits;
    private Scanner input;

    // EFFECTS: runs the closet application
    public ClosetApp() {
        input = new Scanner(System.in);
        jsonWriterCloset = new JsonWriter(JSON_STORE_CLOSET);
        jsonReaderCloset = new JsonReader(JSON_STORE_CLOSET);

        jsonWriterSavedOutfits = new JsonWriter(JSON_STORE_SAVED_OUTFITS);
        jsonReaderSavedOutfits = new JsonReader(JSON_STORE_SAVED_OUTFITS);

        runCloset();
    }

    // MODIFIES: this
    // EFFECTS: processes user input to run closet app
    private void runCloset() {
        boolean keepGoing = true;

        init();

        while (keepGoing) {
            displayHomeMenu();
            String command = input.nextLine();
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
        switch (command) {
            case "d":
                getDressed(display);
                break;
            case "a":
                addToCloset();
                break;
            case "r":
                removeFromCloset();
                break;
            case "v":
                viewItemsInCloset();
                break;
            case "n":
                createOutfit();
                break;
            case "o":
                viewSavedOutfit();
                break;
            default:
                processCommandPersistence(command);
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: processes command given by the user
    private void processCommandPersistence(String command) {
        switch (command) {
            case "s":
                saveCloset();
                break;
            case "l":
                loadCloset();
                break;
            default:
                System.out.println("Sorry, that's not a valid input! Please try again.");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes closet and current outfit
    private void init() {
        closet = new Closet();
        List<ClothingItem> clothes = new ArrayList<>();
        display = new Outfit("Today's Outfit", clothes);
        savedOutfits = new SavedOutfits();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays home menu for users to interact with
    private void displayHomeMenu() {
        System.out.println("\nWelcome to your virtual closet!");
        System.out.println("\tEnter 'd' to get dressed");
        System.out.println("\tEnter 'a' to add a clothing item to your closet");
        System.out.println("\tEnter 'r' to remove a clothing item from your closet");
        System.out.println("\tEnter 'v' to view all the items in your closet");
        System.out.println("\tEnter 'n' to create and save a new outfit");
        System.out.println("\tEnter 'o' to view a saved outfit");
        System.out.println("\tEnter 's' save closet to file");
        System.out.println("\tEnter 'l' to load closet from file");
        System.out.println("\tEnter 'q' to quit");
    }

    // MODIFIES: this
    // EFFECTS: creates a new clothing item and adds it to the closet if possible (name not a duplicate)
    private void addToCloset() {
        String name = getClothingName();
        String colour = getClothingColour();
        String category = getClothingCategory();

        ClothingItem newItem = new ClothingItem(name, colour, category);

        closet.addItem(newItem);
        System.out.println("Successfully added item!");
    }

    // EFFECTS: prompts user for the name of a new clothing item and returns the given string
    private String getClothingName() {

        System.out.println("Please enter the name of your item: ");
        String command = input.nextLine();
        command = command.toLowerCase();

        if (!closet.isValidName(command)) {
            System.out.println("Sorry, another item in your closet has the same name");
            System.out.println("Please try again with a different name. \n");
            return getClothingName();
        }
        return command;
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

        String command = input.nextLine();
        command = command.toLowerCase();

        if (isValidColour(command)) {
            return command;
        } else {
            System.out.println("Sorry, that's not a valid colour. Please try again");
            return getClothingColour();
        }
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

        String command = input.nextLine();
        command = command.toLowerCase();

        if (isValidCategory(command)) {
            return command;
        } else {
            System.out.println("Sorry, that's not a valid category. Please try again.");
            return getClothingCategory();
        }
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
        ClothingItem itemRemoved;

        System.out.println("\nPlease input the name of the item you want to remove: ");
        System.out.println("\tTo view all items, enter 'v'");
        System.out.println("\tTo quit, enter 'q'");

        String command = input.nextLine();
        if (command.equals("v")) {
            showClothes(closet.allItems(), "all", "na");
            removeFromCloset();
        } else if (command.equals("q")) {
            System.out.println("Have a nice day!");
        } else if (closet.allItemsNames().contains(command)) {
            itemRemoved = closet.getItemFromName(command);
            closet.removeItem(itemRemoved);
            System.out.println("Item successfully removed!");
        } else {
            System.out.println("Sorry, there is no item in your closet with that name. Please try again.");
            removeFromCloset();
        }
    }

    // EFFECTS: returns the names of all of the items in the closet
    public void viewItemsInCloset() {
        if (this.closet.numTotalItems() == 0) {
            System.out.println("Your closet is currently empty.");
        } else {
            showClothes(closet.allItems(), "all", "na");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new outfit
    public void createOutfit() {
        List<ClothingItem> clothes = new ArrayList<>();
        boolean status = true;

        System.out.println("Please enter the name of this outfit: ");
        String command = input.nextLine();

        if (doesOutfitNameExist(command)) {
            System.out.println("Sorry, another outfit in your closet already has this name. Please try again.");
            createOutfit();
        } else {
            while (status) {
                status = addItemToOutfit(clothes);
            }
            Outfit newOutfit = new Outfit(command, clothes);
            savedOutfits.addOutfit(newOutfit);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds an item of clothing to an outfit
    public boolean addItemToOutfit(List<ClothingItem> clothes) {
        System.out.println("\n" + "Please enter the name of the item you wish to add: ");
        System.out.println("\tEnter 'v' to view all items in your closet");
        System.out.println("\tEnter 'q' When done");

        String command = input.nextLine();

        if (command.equals("v")) {
            showClothes(closet.allItems(), "all", "na");
            return true;
        } else if (command.equals("q")) {
            return false;
        } else if (closet.allItemsNames().contains(command)) {
            clothes.add(closet.getItemFromName(command));
            System.out.println("Item successfully added.");
            return true;
        } else {
            System.out.println("Sorry, this item doesn't exist. Please try again.");
            return true;
        }
    }

    // EFFECTS: returns the information of the clothing items in the saved outfit with given name;
    public void viewSavedOutfit() {
        List<ClothingItem> listItems;

        System.out.println();
        System.out.println("Please enter the name of the outfit you wish to view");
        System.out.println("\tEnter 'v' to view all outfit names");
        System.out.println("\tEnter 'q' to quit");
        String command = input.nextLine();
        command = command.toLowerCase();

        if (command.equals("v")) {
            allSavedOutfitNames();
            viewSavedOutfit();
        } else if (command.equals("q")) {
            System.out.println("Have a great day!");
        } else if (doesOutfitNameExist(command)) {
            listItems = savedOutfits.getOutfitFromName(command);
            for (ClothingItem c : listItems) {
                System.out.println("\nName: " + c.getName() + "\n\tColour: " + c.getColour()
                        + "\n\tCategory: " + c.getCategory());
            }
        } else {
            System.out.println("Sorry, the outfit you're looking for doesn't exist. Please try again.");
            viewSavedOutfit();
        }
    }

    // EFFECTS: returns the list of the names of all saved outfits
    public void allSavedOutfitNames() {
        StringBuilder listNames = new StringBuilder("Outfit names: ");

        for (String name : savedOutfits.allOutfitNames()) {
            name = name + ", ";
            listNames.append(name);
        }
        System.out.println(listNames);
    }

    // EFFECTS: returns true if outfit with given name exists in saved outfits
    public boolean doesOutfitNameExist(String command) {
        boolean result = false;

        if (savedOutfits.allOutfitNames().contains(command)) {
            result = true;
        }
        return result;
    }

    // MODIFIES: this
    // EFFECTS: allows user to add and remove items from outfit
    private void getDressed(Outfit outfit) {
        String command = getDressedHome();

        if (command.equals("see")) {
            showClothes(closet.allItems(), "all", command);
            getDressed(outfit);
        } else if (command.equals("colour") || command.equals("category")) {
            filterItems(command);
            getDressed(outfit);
        }  else if (command.equals("dress")) {
            addItemsDaily(outfit);
            getDressed(outfit);
        } else if (command.equals("view")) {
            viewDailyOutfit(outfit);
            getDressed(outfit);
        } else if (command.equals("q")) {
            System.out.println("Have a great day!");
        } else {
            System.out.println("Sorry, that's not a valid option. Please try again");
            getDressed(outfit);
        }
    }

    // EFFECTS: outputs options for the user to choose from in the get dressed window
    private String getDressedHome() {
        System.out.println("\nEnter 'dress' to get dressed.");
        System.out.println("\tEnter 'see' to view all the items in your closet");
        System.out.println("\tEnter 'colour' to filter your clothing items by colour");
        System.out.println("\tEnter 'category' to filter your clothing items by category");
        System.out.println("\tEnter 'view' to view your daily outfit");
        System.out.println("\tEnter 'q' to quit");
        String command = input.nextLine();
        command = command.toLowerCase();
        return command;
    }

    // EFFECTS: filters the clothing items in the closet by colour or category
    private void filterItems(String command) {
        if (command.equals("colour")) {
            showClothes(closet.allItems(), "colour", getColourFilter());
        } else if (command.equals("category")) {
            showClothes(closet.allItems(), "category", getCategoryFilter());
        }
    }

    // EFFECTS: displays the daily outfit for the user to see
    private void viewDailyOutfit(Outfit outfit) {
        showClothes(outfit.displayOutfit(), "all", "na");
        System.out.println("\nEnter 'a' to add an item to your outfit.");
        System.out.println("Enter 'q' to quit.");

        String command = input.nextLine();
        command = command.toLowerCase();

        if (command.equals("a")) {
            addItemsDaily(outfit);
        } else if (command.equals("q")) {
            System.out.println("Have a great day!");
        } else {
            System.out.println("Sorry, that's not a valid input. Please try again.");
            viewDailyOutfit(outfit);
        }
    }

    // EFFECTS: prompts user for a valid colour and returns it
    private String getColourFilter() {
        System.out.println("\nTo filter the items in your closet by colour, please enter one of the following: ");
        System.out.println("\tred");
        System.out.println("\torange");
        System.out.println("\tyellow");
        System.out.println("\tgreen");
        System.out.println("\tblue");
        System.out.println("\tpurple");
        System.out.println("\tblack");
        System.out.println("\twhite");
        System.out.println("\tbrown");

        String command = input.nextLine();
        command = command.toLowerCase();

        if (!isValidColour(command)) {
            System.out.println("Sorry, that's not a valid colour. Please try again");
            return getColourFilter();
        }
        return command;
    }

    // EFFECTS: prompts user for a valid category and returns it
    public String getCategoryFilter() {
        System.out.println("\nTo set the category of your item, please enter one of the following: ");
        System.out.println("\taccessories");
        System.out.println("\tshirts");
        System.out.println("\tjackets");
        System.out.println("\tpants");
        System.out.println("\tskirts");
        System.out.println("\tdresses");
        System.out.println("\tshoes");

        String command = input.nextLine();
        command = command.toLowerCase();

        if (!isValidCategory(command)) {
            System.out.println("Sorry, that's not a valid category. Please try again.");
            return getCategoryFilter();
        }
        return command;
    }

    // MODIFIES: this
    // EFFECTS: adds items to daily outfit
    private void addItemsDaily(Outfit outfit) {
        List<ClothingItem> items = new ArrayList<>();
        boolean status = true;

        while (status) {
            status = addItemToOutfit(items);
        }

        for (ClothingItem c : items) {
            outfit.addItemToOutfit(c);
        }
    }

    // REQUIRES: filter is one of: "all", "colour", "category"
    // EFFECTS: returns a list clothes and their respective colours and categories
    private void showClothes(List<ClothingItem> clothes, String filter, String colourOrCategory) {
        List<ClothingItem> result = new ArrayList<>();
        switch (filter) {
            case "all":
                result.addAll(clothes);
                break;
            case "colour":
            case "category":
                filterColour(clothes, colourOrCategory);
                break;
        }

        if (result.size() > 0) {
            for (ClothingItem i : result) {
                System.out.println("\nName: " + i.getName() + "\n\tColour: " + i.getColour() + "\n\tCategory: "
                        + i.getCategory());
            }
        }
    }

    // EFFECTS: shows all items of clothing in a list of clothing items with given colour
    private void filterColour(List<ClothingItem> clothes, String colourOrCategory) {
        List<ClothingItem> result = new ArrayList<>();

        for (ClothingItem c : clothes) {
            if (c.getColour().equals(colourOrCategory) || c.getCategory().equals(colourOrCategory)) {
                result.add(c);
            }
        }
        if (result.size() > 0) {
            for (ClothingItem i : result) {
                System.out.println("\nName: " + i.getName() + "\n\tColour: " + i.getColour() + "\n\tCategory: "
                        + i.getCategory());
            }
        } else {
            System.out.println("Sorry, this list is empty. Please try again");
        }
    }

    // EFFECTS: saves the closet and all saved outfits to file
    private void saveCloset() {
        try {
            jsonWriterCloset.open();
            jsonWriterCloset.writeCloset(closet);
            jsonWriterCloset.close();
            System.out.println("Successfully saved closet to " + JSON_STORE_CLOSET);

            jsonWriterSavedOutfits.open();
            jsonWriterSavedOutfits.writeOutfits(savedOutfits);
            jsonWriterSavedOutfits.close();
            System.out.println("Successfully saved closet to " + JSON_STORE_SAVED_OUTFITS);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_CLOSET);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the closet and all saved outfits from file
    private void loadCloset() {
        try {
            closet = jsonReaderCloset.readCloset();
            System.out.println("Loaded closet from" + JSON_STORE_CLOSET);
            savedOutfits = jsonReaderSavedOutfits.readSavedOutfits();
            System.out.println("Loaded outfits from" + JSON_STORE_SAVED_OUTFITS);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_CLOSET);
        }
    }
}
