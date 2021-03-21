package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OutfitTest {
    private ClothingItem testItem;
    private ClothingItem testItem2;
    private ClothingItem testItem3;
    private Outfit testOutfit;
    private List<ClothingItem> outfitClothes;

    @BeforeEach
    void setup() {
        testItem = new ClothingItem("Test", "Red", "Pants");
        testItem2 = new ClothingItem("Test 2", "Pink", "Shirt");
        testItem3 = new ClothingItem("Test 3", "White", "Shoes");
        outfitClothes = new ArrayList<>();
        testOutfit = new Outfit("test outfit", outfitClothes);
    }

    @Test
    void testOutfitConstructor() {
        assertEquals("test outfit", testOutfit.getOutfitName());
        assertEquals(0, testOutfit.displayOutfit().size());
    }

    @Test
    void testAddItemToOutfitEmptySucceed() {
        assertTrue(testOutfit.addItemToOutfit(testItem3));
        assertEquals(1, testOutfit.displayOutfit().size());
        assertEquals(testItem3, testOutfit.displayOutfit().get(0));
    }

    @Test
    void testAddItemToOutfitNotEmptySucceed() {
        testOutfit.addItemToOutfit(testItem);
        testOutfit.addItemToOutfit(testItem2);

        assertTrue(testOutfit.addItemToOutfit(testItem3));
        assertEquals(3, testOutfit.displayOutfit().size());
        assertEquals(testItem, testOutfit.displayOutfit().get(0));
        assertEquals(testItem2, testOutfit.displayOutfit().get(1));
        assertEquals(testItem3, testOutfit.displayOutfit().get(2));

    }

    @Test
    void testAddItemToOutfitOutfitDuplicate() {
        testOutfit.addItemToOutfit(testItem);
        testOutfit.addItemToOutfit(testItem2);

        assertFalse(testOutfit.addItemToOutfit(testItem));
        assertEquals(2, testOutfit.displayOutfit().size());
        assertEquals(testItem, testOutfit.displayOutfit().get(0));
        assertEquals(testItem2, testOutfit.displayOutfit().get(1));
    }

    @Test
    void testRemoveItemInOutfitEmpty() {
        assertFalse(testOutfit.removeItemInOutfit(testItem));
        assertEquals(0, testOutfit.displayOutfit().size());
    }

    @Test
    void testRemoveItemInOutfitSucceed() {
        testOutfit.addItemToOutfit(testItem);
        testOutfit.addItemToOutfit(testItem2);

        assertTrue(testOutfit.removeItemInOutfit(testItem2));
        assertEquals(1, testOutfit.displayOutfit().size());
        assertEquals(testItem, testOutfit.displayOutfit().get(0));
    }

    @Test
    void testRemoveItemNotFound() {
        testOutfit.addItemToOutfit(testItem);
        testOutfit.addItemToOutfit(testItem2);

        assertFalse(testOutfit.removeItemInOutfit(testItem3));
        assertEquals(2, testOutfit.displayOutfit().size());
        assertEquals(testItem, testOutfit.displayOutfit().get(0));
        assertEquals(testItem2, testOutfit.displayOutfit().get(1));
    }

    @Test
    void testGetOutfitName() {
        assertEquals("test outfit", testOutfit.getOutfitName());
    }

    @Test
    void testDisplayOutfitEmpty() {
        assertEquals(0, testOutfit.displayOutfit().size());
    }

    @Test
    void testDisplayOutfitOneItem() {
        testOutfit.addItemToOutfit(testItem);

        assertEquals(1, testOutfit.displayOutfit().size());
        assertEquals(testItem, testOutfit.displayOutfit().get(0));
    }

    @Test
    void testDisplayOutfitManyItems() {
        testOutfit.addItemToOutfit(testItem);
        testOutfit.addItemToOutfit(testItem2);
        testOutfit.addItemToOutfit(testItem3);

        assertEquals(3, testOutfit.displayOutfit().size());
        assertEquals(testItem, testOutfit.displayOutfit().get(0));
        assertEquals(testItem2, testOutfit.displayOutfit().get(1));
        assertEquals(testItem3, testOutfit.displayOutfit().get(2));
    }
}
