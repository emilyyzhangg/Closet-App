package model;

import java.util.ArrayList;
import java.util.List;

public class Closet {

    // EFFECTS: constructs an empty collection of clothing items
    public Closet() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds a new clothing item to the collection of clothes in the closet then returns true. If item of
    // clothing is a duplicate (same name), do nothing and return false
    public boolean addItem(ClothingItem item) {
        return false; //stub
    }

    // MODIFIES: this
    // EFFECTS: removes the item of clothing and return true. if item doesn't exist in closet, then return false.
    public boolean removeItem(ClothingItem item) {
        return false; //stub
    }

    // EFFECTS: returns the list of all clothing items in the closet; the list returned can be empty
    public List<ClothingItem> allItems() {
        return null;
    }

    // EFFECTS: returns the list of the names of all clothing items in the closet; the list returned can be empty
    public List<String> allItemsNames() {
        return null;
    }

    // EFFECTS: returns the list of all clothing items in the closet of a given colour; the list returned can be empty
    public List<ClothingItem> allItemsOfColour(String colour) {
        return null;
    }

    // EFFECTS: returns the list of all clothing items in the closet in the given category; the list returned can
    // be empty
    public List<ClothingItem> allItemsOfCategory(String size) {
        return null;
    }

    // EFFECTS: returns the number of all items currently in the closet
    public int numTotalItems() {
        return 0; //stub
    }

    // EFFECTS: returns the number of items of a given colour
    public int numItemsOfColour(String colour) {
        return 0;
    }

    // EFFECTS: returns the number of items in a given category
    public int numItemsInCategory(String category) {
        return 0;
    }

    // REQUIRES: string is a non-zero length
    // MODIFIES: this
    // EFFECTS: changes name associated with the clothing item, returns true. If name is same as another item,
    // return false
    public boolean changeItemName(ClothingItem item, String newName) {
        //stub
        return false;
    }

    // REQUIRES: string is a non-zero length
    // MODIFIES: this
    // EFFECTS: changes the colour associated with the clothing item
    public void changeItemColour(ClothingItem item, String newColour) {
        //stub
    }

    // REQUIRES: string is a non-zero length
    // MODIFIES: this
    // EFFECTS: changes the category associated with the clothing item
    public void changeItemCategory(ClothingItem item, String newCategory)  {
        // stub
    }

}
