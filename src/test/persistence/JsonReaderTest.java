package persistence;

import model.Closet;
import model.ClothingItem;
import model.Outfit;
import model.SavedOutfits;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
Tests for JsonReader Class
Citation: code taken and modified from JsonReaderTest class in JsonSerializationDemo
 */

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Closet cl = reader.readCloset();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCloset() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCloset.json");
        try {
            Closet cl = reader.readCloset();
            assertEquals(0, cl.numTotalItems());
        } catch (IOException e) {
            fail("Couldn't read from that file");
        }
    }

    @Test
    void testReaderNormalCloset() {
        JsonReader reader = new JsonReader("./data/testReaderNormalCloset.json");
        try {
            Closet cl = reader.readCloset();
            assertEquals(2, cl.numTotalItems());

            assertEquals("jean jacket", cl.allItemsNames().get(0));
            assertEquals(1, cl.numItemsOfColour("black"));
            assertEquals(1, cl.numItemsInCategory("jackets"));

            assertEquals("sundress", cl.allItemsNames().get(1));
            assertEquals(1, cl.numItemsOfColour("yellow"));
            assertEquals(1, cl.numItemsInCategory("dresses"));

            List<ClothingItem> items = cl.allItems();
            checkItem("jean jacket", "black", "jackets", items.get(0));
            checkItem("sundress", "yellow", "dresses", items.get(1));

        } catch (IOException e) {
            fail("Couldn't read from that file");
        }
    }

    @Test
    void testReaderEmptySavedOutfits() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySavedOutfits.json");

        try {
            SavedOutfits so = reader.readSavedOutfits();
            assertEquals(0, so.numSavedOutfits());
        } catch (IOException e) {
            fail("Couldn't read from that file");
        }
    }

    @Test
    void testReaderNormalSavedOutfits() {
        try {
            JsonReader reader = new JsonReader("./data/testReaderNormalSavedOutfits.json");
            SavedOutfits so = reader.readSavedOutfits();
            assertEquals(2, so.numSavedOutfits());
            assertEquals("cottage", so.allOutfitNames().get(0));
            assertEquals("punk", so.allOutfitNames().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
