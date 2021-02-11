package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void testOutfitConstructor() {
        outfitClothes.add(testItem);
        outfitClothes.add(testItem2);
        outfitClothes.add(testItem3);

        testOutfit = new Outfit("Test Outfit", outfitClothes);

        assertEquals("Test Outfit", testOutfit.getOutfitName());
        assertEquals(3, testOutfit.displayOutfit("Test Outfit").size());
        assertEquals(testItem, testOutfit.displayOutfit("Test Outfit").get(0));
        assertEquals(testItem2, testOutfit.displayOutfit("Test Outfit").get(1));
        assertEquals(testItem3, testOutfit.displayOutfit("Test Outfit").get(2));
    }

    @Test
    void testAddItemToOutfitSucceed() {
        outfitClothes.add(testItem);
        outfitClothes.add(testItem2);

        testOutfit = new Outfit("Test Outfit", outfitClothes);

        assertTrue(testOutfit.addItemToOutfit("Test Outfit", testItem3));
        assertEquals(3, testOutfit.displayOutfit("Test Outfit").size());
        assertEquals(testItem, testOutfit.displayOutfit("Test Outfit").get(0));
        assertEquals(testItem2, testOutfit.displayOutfit("Test Outfit").get(1));
        assertEquals(testItem3, testOutfit.displayOutfit("Test Outfit").get(2));
    }

    @Test
    void testAddItemToOutfitOutfitNotFound() {
        outfitClothes.add(testItem);
        outfitClothes.add(testItem2);

        testOutfit = new Outfit("Test Outfit", outfitClothes);

        assertFalse(testOutfit.addItemToOutfit("Test Outfit Wrong", testItem3));
        assertEquals(2, testOutfit.displayOutfit("Test Outfit").size());
        assertEquals(testItem, testOutfit.displayOutfit("Test Outfit").get(0));
        assertEquals(testItem2, testOutfit.displayOutfit("Test Outfit").get(1));
    }





}
