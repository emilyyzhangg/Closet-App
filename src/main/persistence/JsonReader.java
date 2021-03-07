package persistence;

import model.Closet;
import model.ClothingItem;
import model.Outfit;
import model.SavedOutfits;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
Represents a reader that reads from JSON representation of closet and saved outfits stored in file
Citation: code obtained and modified from JsonReader class in JsonSerializationDemo
 */

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads closet from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Closet readCloset() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        return parseCloset(jsonObject);
    }

    // EFFECTS: reads closet from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SavedOutfits readSavedOutfits() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        return parseSavedOutfits(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses closet from JSON object and returns it
    private Closet parseCloset(JSONObject jsonObject) {
        Closet cl = new Closet();
        addItemsToCloset(cl, jsonObject);
        return cl;
    }

    // MODIFIES: cl
    // EFFECTS: parses items from JSON object and adds them to closet
    private void addItemsToCloset(Closet cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");

        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(cl, nextItem);
        }
    }

    // MODIFIES: cl
    // EFFECTS: parses item from JSON object and adds it to closet
    private void addItem(Closet cl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String colour = jsonObject.getString("colour");
        String category = jsonObject.getString("category");

        ClothingItem item = new ClothingItem(name, colour, category);
        cl.addItem(item);
    }

    // EFFECTS: parses saved outfits from JSON object and returns it
    private SavedOutfits parseSavedOutfits(JSONObject jsonObject) {
        SavedOutfits so = new SavedOutfits();
        addOutfitsToSavedOutfits(so, jsonObject);
        return so;
    }

    // MODIFIES: so
    // EFFECTS: parses outfits from JSON object and adds them to saved outfits
    private void addOutfitsToSavedOutfits(SavedOutfits so, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("outfits");

        for (Object json : jsonArray) {
            JSONObject nextOutfit = (JSONObject) json;
            addOutfit(so, nextOutfit);
        }
    }

    // MODIFIES: so
    // EFFECTS: parses outfits from JSON object and adds it to saved outfit
    private void addOutfit(SavedOutfits so, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray jsonArray = jsonObject.getJSONArray("outfitItems");
        List<ClothingItem> items = new ArrayList<>();

        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItemToOutfit(items, nextItem);
        }

        Outfit outfit = new Outfit(name, items);
        so.addOutfit(outfit);
    }

    // MODIFIES: items
    // EFFECTS: parses item from JSON object and adds it to saved outfit
    private void addItemToOutfit(List<ClothingItem> items, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String colour = jsonObject.getString("colour");
        String category = jsonObject.getString("category");

        ClothingItem item = new ClothingItem(name, colour, category);
        items.add(item);
    }
}
