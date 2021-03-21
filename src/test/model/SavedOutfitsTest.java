package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SavedOutfitsTest {
    ClothingItem item;
    List<ClothingItem> items;
    Outfit testOutfit;
    SavedOutfits savedOutfits;

    @BeforeEach
    void setupOutfits() {
        item = new ClothingItem("Item", "red", "pants");
        items = new ArrayList<>();
        items.add(item);
        savedOutfits = new SavedOutfits();

        testOutfit = new Outfit("test", items);
    }

    @Test
    void testAddOutfitSuccessful() {
        assertTrue(savedOutfits.addOutfit(testOutfit));
        assertEquals(1, savedOutfits.numSavedOutfits());
    }

    @Test
    void testAddOutfitUnsuccessful() {
        savedOutfits.addOutfit(testOutfit);

        assertFalse(savedOutfits.addOutfit(testOutfit));
        assertEquals(1, savedOutfits.numSavedOutfits());
    }

    @Test
    void testRemoveOutfitEmpty() {
        assertFalse(savedOutfits.removeOutfit(testOutfit));
        assertEquals(0, savedOutfits.numSavedOutfits());
    }

    @Test
    void testRemoveOutfitSuccessful() {
        savedOutfits.addOutfit(testOutfit);

        assertTrue(savedOutfits.removeOutfit(testOutfit));
        assertEquals(0, savedOutfits.numSavedOutfits());
    }

    @Test
    void testRemoveOutfitUnsuccessful() {
        Outfit testOutfit2 = new Outfit("test 2", items);

        savedOutfits.addOutfit(testOutfit);
        assertFalse(savedOutfits.removeOutfit(testOutfit2));
        assertEquals(1, savedOutfits.numSavedOutfits());
    }

    @Test
    void testAllOutfitNames() {
        savedOutfits.addOutfit(testOutfit);

        Outfit testOutfit2 = new Outfit("test 2", items);
        savedOutfits.addOutfit(testOutfit2);

        assertEquals(2, savedOutfits.allOutfitNames().size());
        assertEquals("test", savedOutfits.allOutfitNames().get(0));
        assertEquals("test 2", savedOutfits.allOutfitNames().get(1));
    }

    @Test
    void testNumSavedOutfitsEmpty() {
        assertEquals(0, savedOutfits.numSavedOutfits());
    }

    @Test
    void testNumSavedOutfitsMany() {
        savedOutfits.addOutfit(testOutfit);
        Outfit testOutfit2 = new Outfit("test 2", items);
        savedOutfits.addOutfit(testOutfit2);

        assertEquals(2, savedOutfits.numSavedOutfits());
    }

    @Test
    void testGetOutfitFromNameOneItem() {
        savedOutfits.addOutfit(testOutfit);
        assertEquals(items, savedOutfits.getOutfitFromName("test"));
    }

    @Test
    void testGetOutfitFromNameManyItems() {
        ClothingItem item2 = new ClothingItem("Item 2", "red", "shirts");
        testOutfit.addItemToOutfit(item2);
        ClothingItem item3 = new ClothingItem("Item 3", "blue", "dresses");
        testOutfit.addItemToOutfit(item3);

        List<ClothingItem> list = new ArrayList<>();
        list.add(item);
        list.add(item2);
        list.add(item3);

        savedOutfits.addOutfit(testOutfit);
        assertEquals(list, savedOutfits.getOutfitFromName("test"));
    }

    @Test
    void testGetOutfitFromNameNoOutfit() {
        testOutfit.addItemToOutfit(item);
        List<ClothingItem> list = new ArrayList<>();
        savedOutfits.addOutfit(testOutfit);

        assertEquals(list, savedOutfits.getOutfitFromName("Fail"));
    }

    @Test
    void testGetOutfitEmpty() {
        List<Outfit> outfits = savedOutfits.getOutfits();
        assertEquals(0, outfits.size());
    }

    @Test
    void testGetOutfitMany() {
        ClothingItem item2 = new ClothingItem("Item2", "red", "pants");
        List<ClothingItem> items2 = new ArrayList<>();
        items2.add(item2);
        Outfit testOutfit2 = new Outfit("test 2", items2);

        savedOutfits.addOutfit(testOutfit);
        savedOutfits.addOutfit(testOutfit2);

        List<Outfit> outfits = savedOutfits.getOutfits();
        assertEquals(2, outfits.size());
        assertEquals(testOutfit, outfits.get(0));
        assertEquals(testOutfit2, outfits.get(1));
    }
}
