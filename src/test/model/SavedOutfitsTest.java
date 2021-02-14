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

        testOutfit = new Outfit("Test", items);
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
        Outfit testOutfit2 = new Outfit("Test 2", items);

        savedOutfits.addOutfit(testOutfit);
        assertFalse(savedOutfits.removeOutfit(testOutfit2));
        assertEquals(1, savedOutfits.numSavedOutfits());
    }

    @Test
    void testAllOutfitNames() {
        savedOutfits.addOutfit(testOutfit);

        Outfit testOutfit2 = new Outfit("Test 2", items);
        savedOutfits.addOutfit(testOutfit2);

        assertEquals(2, savedOutfits.allOutfitNames().size());
        assertEquals("Test", savedOutfits.allOutfitNames().get(0));
        assertEquals("Test 2", savedOutfits.allOutfitNames().get(1));
    }

    @Test
    void testNumSavedOutfitsEmpty() {
        assertEquals(0, savedOutfits.numSavedOutfits());
    }

    @Test
    void testNumSavedOutfitsMany() {
        savedOutfits.addOutfit(testOutfit);
        Outfit testOutfit2 = new Outfit("Test 2", items);
        savedOutfits.addOutfit(testOutfit2);

        assertEquals(2, savedOutfits.numSavedOutfits());
    }

    @Test
    void testGetOutfitFromNameOneItem() {
        savedOutfits.addOutfit(testOutfit);
        assertEquals(items, savedOutfits.getOutfitFromName("Test"));
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
        assertEquals(list, savedOutfits.getOutfitFromName("Test"));
    }

    @Test
    void testGetOutfitFromNameNoOutfit() {
        testOutfit.addItemToOutfit(item);
        List<ClothingItem> list = new ArrayList<>();
        savedOutfits.addOutfit(testOutfit);

        assertEquals(list, savedOutfits.getOutfitFromName("Fail"));
    }
}
