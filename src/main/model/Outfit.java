package model;

import java.util.List;

public class Outfit {

    // EFFECTS: constructs a saved outfit with associated name and clothing items
    public Outfit(String name, List<ClothingItem> items) {}

    // MODIFIES: this
    // EFFECTS: adds given clothing item to the outfit with given name and produces true. If outfit isn't found,
    // produce false
    public boolean addItemToOutfit(String outfitName, ClothingItem item) {
        return true;// stub
    }

    // MODIFIES: this
    // EFFECTS: removes given clothing item to the outfit with given name, then produces true. If either clothing item
    // or outfit aren't found produce false
    public boolean removeItemInOutfit() {
        return false;// stub
    }

    public String getOutfitName() {
        return null; // stub
    }

    // EFFECTS: returns list of clothing items in outfit with given name if found
    // if outfit with given name not found, produce empty list
    public List<ClothingItem> displayOutfit(String outfitName) {
        return null; // stub
    }
}
