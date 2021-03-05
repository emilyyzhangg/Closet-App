package persistence;

import com.oracle.javafx.jmx.json.JSONReader;
import model.Closet;
import model.ClothingItem;
import model.Outfit;
import model.SavedOutfits;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Closet cl = new Closet();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCloset() {
        try {
            Closet cl = new Closet();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCloset.json");
            writer.open();
            writer.writeCloset(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCloset.json");
            cl = reader.readCloset();
            assertEquals(0, cl.numTotalItems());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNormalCloset() {
        try {
            Closet cl = new Closet();
            cl.addItem(new ClothingItem("jeans", "black", "pants"));
            cl.addItem(new ClothingItem("dress", "red", "dresses"));
            JsonWriter writer = new JsonWriter("./data/testWriterNormalCloset.json");
            writer.open();
            writer.writeCloset(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNormalCloset.json");
            cl = reader.readCloset();
            assertEquals(2, cl.numTotalItems());
            assertEquals("jeans", cl.allItemsNames().get(0));
            assertEquals(1, cl.numItemsOfColour("black"));
            assertEquals(1, cl.numItemsInCategory("pants"));
            assertEquals("dress", cl.allItemsNames().get(1));
            assertEquals(1, cl.numItemsOfColour("red"));
            assertEquals(1, cl.numItemsInCategory("dresses"));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

    @Test
    void testWriterEmptySavedOutfits() {
        try {
            SavedOutfits so = new SavedOutfits();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySavedOutfits.json");
            writer.open();
            writer.writeOutfits(so);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySavedOutfits.json");
            so = reader.readSavedOutfits();
            assertEquals(0, so.numSavedOutfits());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNormalSavedOutfits() {
        try {
            SavedOutfits so = new SavedOutfits();
            List<ClothingItem> itemsOne = new ArrayList<>();
            List<ClothingItem> itemsTwo = new ArrayList<>();

            itemsOne.add(new ClothingItem("dress", "white", "dresses"));
            itemsOne.add(new ClothingItem("heels", "blue", "shoes"));
            itemsTwo.add(new ClothingItem("jean jacket", "black", "jackets"));
            Outfit of = new Outfit("cottage", itemsOne);
            Outfit ofSecond = new Outfit("punk", itemsTwo);
            so.addOutfit(of);
            so.addOutfit(ofSecond);

            JsonWriter writer = new JsonWriter("./data/testWriterNormalSavedOutfits.json");
            writer.open();
            writer.writeOutfits(so);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNormalSavedOutfits.json");
            so = reader.readSavedOutfits();
            assertEquals(2, so.numSavedOutfits());
            assertEquals("cottage", so.allOutfitNames().get(0));
            assertEquals("punk", so.allOutfitNames().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
