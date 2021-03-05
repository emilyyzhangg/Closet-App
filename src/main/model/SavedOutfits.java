package model;

import jdk.nashorn.api.scripting.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a list of outfits
public class SavedOutfits implements Writable {
    List<Outfit> savedOutfits;

    // EFFECTS: constructs an empty collection of outfits
    public SavedOutfits() {
        this.savedOutfits = new ArrayList<>();
    }

    // REQUIRES: outfit already exists
    // MODIFIES: this
    // EFFECTS: adds outfit to list of saved outfits and produces true if it is not a duplicate; else does
    // nothing and returns false
    public boolean addOutfit(Outfit outfit) {
        if (savedOutfits.contains(outfit)) {
            return false;
        } else {
            this.savedOutfits.add(outfit);
            return true;
        }
    }

    // REQUIRES: outfit already exists
    // MODIFIES: this
    // EFFECTS: removes outfit from list of save outfits if there and produces true; else false
    public boolean removeOutfit(Outfit outfit) {
        if (savedOutfits.contains(outfit)) {
            savedOutfits.remove(outfit);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: list of saved outfits is not empty
    // EFFECTS: returns list of names of all saved outfits
    public List<String> allOutfitNames() {
        List<String> names = new ArrayList<>();

        for (Outfit o : savedOutfits) {
            names.add(o.getOutfitName());
        }
        return names;
    }

    // EFFECTS: returns number of saved outfits
    public Integer numSavedOutfits() {
        return savedOutfits.size();
    }

    // REQUIRES: no two outfits have the same name
    // EFFECTS: returns list of clothing items given the name of the outfit
    public List<ClothingItem> getOutfitFromName(String name) {
        List<ClothingItem> clothesInOutfit = new ArrayList<>();

        for (Outfit o : savedOutfits) {
            if (o.getOutfitName().equals(name)) {
                clothesInOutfit = o.displayOutfit();
            }
        }
        return clothesInOutfit;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("outfits", outfitsToJson());
        return json;
    }

    // EFFECTS: returns outfits in this list of saved outfits as a JSON array
    private JSONArray outfitsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Outfit o : savedOutfits) {
            jsonArray.put(o.toJson());
        }

        return jsonArray;
    }
}
