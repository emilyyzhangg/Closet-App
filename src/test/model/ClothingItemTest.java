package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClothingItemTest {
    private ClothingItem testItem;

    @BeforeEach
    void setup() {
        testItem = new ClothingItem("Test", "Red", "Pants");
    }

    @Test
    void testClothingItemConstructor() {
        assertEquals("Test", testItem.getName());
        assertEquals("Red", testItem.getColour());
        assertEquals("Pants", testItem.getCategory());
    }

    @Test
    void testGetName() {
        assertEquals("Test", testItem.getName());
    }

    @Test
    void testGetColour() {
        assertEquals("Red", testItem.getColour());
    }

    @Test
    void testGetCategory() {
        assertEquals("Pants", testItem.getCategory());
    }

    @Test
    void testToString() {
        assertEquals("Name: Test, Colour: Red, Category: Pants", testItem.toString());
    }
}
