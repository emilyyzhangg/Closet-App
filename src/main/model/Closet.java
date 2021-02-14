package model;

import java.util.ArrayList;
import java.util.List;

public class Closet {

    private List<ClothingItem> clothes;

    // EFFECTS: constructs an empty collection of clothing items
    public Closet() {
        this.clothes = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new clothing item to the collection of clothes in the closet then returns true. If item of
    // clothing is a duplicate (same name), do nothing and return false
    public boolean addItem(ClothingItem item) {
        List<String> clothingNames = new ArrayList<>();

        for (ClothingItem c : this.clothes) {
            clothingNames.add(c.getName());
        }

        if (clothingNames.contains(item.getName())) {
            return false;
        } else {
            this.clothes.add(item);
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the item of clothing and return true. if item doesn't exist in closet, then return false.
    public boolean removeItem(ClothingItem item) {
        if (this.clothes.contains(item)) {
            clothes.remove(item);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns the list of all clothing items in the closet; the list returned can be empty
    public List<ClothingItem> allItems() {
        return this.clothes;
    }

    // EFFECTS: returns the list of the names of all clothing items in the closet; the list returned can be empty
    public List<String> allItemsNames() {
        List<String> names = new ArrayList<>();

        for (ClothingItem c : this.clothes) {
            names.add(c.getName());
        }

        return names;
    }

    // EFFECTS: returns the list of all clothing items in the closet of a given colour;
    public List<ClothingItem> allItemsOfColour(String colour) {
        List<ClothingItem> items = new ArrayList<>();

        for (ClothingItem c : this.clothes) {
            if (c.getColour().equals(colour)) {
                items.add(c);
            }
        }
        return items;
    }

    // EFFECTS: returns the list of all clothing items in the closet in the given category; the list returned can
    // be empty
    public List<ClothingItem> allItemsOfCategory(String category) {
        List<ClothingItem> items = new ArrayList<>();

        for (ClothingItem c : this.clothes) {
            if (c.getCategory().equals(category)) {
                items.add(c);
            }
        }
        return items;
    }

    // EFFECTS: returns the number of all items currently in the closet
    public int numTotalItems() {
        return this.clothes.size();
    }

    // EFFECTS: returns the number of items of a given colour
    public int numItemsOfColour(String colour) {
        int numItems = 0;

        for (ClothingItem c : this.clothes) {
            if (c.getColour().equals(colour)) {
                numItems++;
            }
        }
        return numItems;
    }

    // EFFECTS: returns the number of items in a given category
    public int numItemsInCategory(String category) {
        int numItems = 0;

        for (ClothingItem c : this.clothes) {
            if (c.getCategory().equals(category)) {
                numItems++;
            }
        }
        return numItems;
    }

    // REQUIRES: string is a non-zero length
    // MODIFIES: this
    // EFFECTS: changes name associated with the clothing item, returns true. If name is same as another item or item
    // doesn't exist in closet, return false
    public boolean changeItemName(ClothingItem item, String newName) {
        ClothingItem newItem = new ClothingItem(newName, item.getColour(), item.getCategory());

        List<String> names = new ArrayList<>();
        for (ClothingItem c : this.clothes) {
            names.add(c.getName());
        }

        if (this.clothes.contains(item) && !names.contains(newName)) {
            this.clothes.set(this.clothes.indexOf(item), newItem);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: string is a non-zero length
    // MODIFIES: this
    // EFFECTS: changes the colour associated with the clothing item if found and produces true; else produces false
    public boolean changeItemColour(ClothingItem item, String newColour) {
        ClothingItem newItem = new ClothingItem(item.getName(), newColour, item.getCategory());

        if (this.clothes.contains(item)) {
            this.clothes.set(this.clothes.indexOf(item), newItem);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: string is a non-zero length
    // MODIFIES: this
    // EFFECTS: changes the category associated with the clothing item if found and produces true; else produces false
    public boolean changeItemCategory(ClothingItem item, String newCategory)  {
        ClothingItem newItem = new ClothingItem(item.getName(), item.getColour(), newCategory);

        if (this.clothes.contains(item)) {
            this.clothes.set(this.clothes.indexOf(item), newItem);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns true if given string is not the same as the name of an item in the closet; else false
    public boolean isValidName(String name) {
        List names = new ArrayList<>();

        for (ClothingItem c : this.clothes) {
            names.add(c.getName());
        }
        return !names.contains(name);
    }

    // REQUIRES: item with given name exists in the closet
    // EFFECTS: returns item with given name
    public ClothingItem getItemFromName(String name) {
        ClothingItem choice = null;

        for (ClothingItem c : clothes) {
            if (c.getName().equals(name)) {
                choice = c;
            }
        }
        return choice;
    }
}
