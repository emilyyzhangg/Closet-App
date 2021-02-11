package model;

import java.util.List;

public class Outfit {

    // EFFECTS: constructs a saved outfit with associated name and clothing items
    public Outfit(String name, List<ClothingItem> items) {}

    // MODIFIES: this
    // EFFECTS: adds given clothing item to the outfit and return true, if item of clothing is a
    // duplicate, do nothing and return false
    public boolean addItemToOutfit(ClothingItem item) {
        return true;// stub
    }

    // MODIFIES: this
    // EFFECTS: removes given clothing item to the outfit, then produces true. If clothing item isn't found in
    // outfit, do nothing and return false
    public boolean removeItemInOutfit(ClothingItem item) {
        return false;// stub
    }

    // EFFECTS: returns outfit name
    public String getOutfitName() {
        return null; // stub
    }

    // EFFECTS: returns list of clothing items in outfit, returns empty list if no items in outfit
    public List<ClothingItem> displayOutfit() {
        return null; // stub
    }
}
