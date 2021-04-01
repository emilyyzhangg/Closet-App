package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a clothing item with a name, colour, and category
public class ClothingItem implements Writable {
    private String name;
    private String colour;
    private String category;

    // REQUIRES: name, colour, and categories are all strings with non-zero length. Also requires name is not a
    // duplicate of existing clothing item name
    // EFFECTS: constructs a clothing item with an associated name, colour, and category
    public ClothingItem(String clothingName, String clothingColour, String clothingCategory) {
        name = clothingName;
        colour = clothingColour;
        category = clothingCategory;
    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public String getCategory() {
        return category;
    }

    // EFFECTS: produces a string representation of the clothing item
    public String toString() {
        return "Name: " + name + ", Colour: " + colour + ", Category: " + category;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("colour", colour);
        json.put("category", category);
        return json;
    }
}
