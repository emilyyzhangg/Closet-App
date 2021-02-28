package model;

import java.util.List;

// Represents an outfit with a list of clothing items
public class Outfit {
    List<ClothingItem> clothes;
    String name;

    // EFFECTS: constructs a saved outfit with associated name and clothing items
    public Outfit(String outfitName, List<ClothingItem> items) {
        name = outfitName;
        clothes = items;
    }

    // MODIFIES: this
    // EFFECTS: adds given clothing item to the outfit and return true, if item of clothing is a
    // duplicate, do nothing and return false
    public boolean addItemToOutfit(ClothingItem item) {
        if (!this.clothes.contains(item)) {
            this.clothes.add(item);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes given clothing item to the outfit, then produces true. If clothing item isn't found in
    // outfit, do nothing and return false
    public boolean removeItemInOutfit(ClothingItem item) {
        if (this.clothes.contains(item)) {
            this.clothes.remove(item);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns outfit name
    public String getOutfitName() {
        return name;
    }

    // EFFECTS: returns list of clothing items in outfit, returns empty list if no items in outfit
    public List<ClothingItem> displayOutfit() {
        return this.clothes;
    }
}
