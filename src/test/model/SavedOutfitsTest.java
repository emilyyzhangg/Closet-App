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
}
