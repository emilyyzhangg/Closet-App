package model;

import java.util.ArrayList;
import java.util.List;

public class SavedOutfits {
    List<Outfit> outfits;

    // EFFECTS: constructs an empty collection of outfits
    public SavedOutfits() {
        this.outfits = new ArrayList<>();
    }

    // REQUIRES: outfit already exists
    // MODIFIES: this
    // EFFECTS: adds outfit to list of saved outfits and produces true if it is not a duplicate; else does
    // nothing and returns false
    public boolean addOutfit(Outfit outfit) {
        if (outfits.contains(outfit)) {
            return false;
        } else {
            this.outfits.add(outfit);
            return true;
        }
    }

    // REQUIRES: outfit already exists
    // MODIFIES: this
    // EFFECTS: removes outfit from list of save outfits if there and produces true; else false
    public boolean removeOutfit(Outfit outfit) {
        if (outfits.contains(outfit)) {
            outfits.remove(outfit);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: list of saved outfits is not empty
    // EFFECTS: returns list of names of all saved outfits
    public List<String> allOutfitNames() {
        List<String> names = new ArrayList<>();

        for (Outfit o : outfits) {
            names.add(o.getOutfitName());
        }
        return names;
    }

    // EFFECTS: returns number of saved outfits
    public Integer numSavedOutfits() {
        return outfits.size();
    }
}
