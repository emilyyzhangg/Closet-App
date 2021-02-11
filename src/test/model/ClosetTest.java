package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClosetTest {
    private Closet testCloset;
    private ClothingItem testItem;

    @BeforeEach
    void setup() {
        testCloset= new Closet();
        testItem = new ClothingItem("Test", "Red", "Pants");
    }

    @Test
    void testAddOneItem() {
        assertTrue(testCloset.addItem(testItem));
        assertEquals(1, testCloset.numTotalItems());
        assertEquals(1, testCloset.numItemsOfColour(testItem.getColour()));
        assertEquals(1, testCloset.numItemsInCategory(testItem.getCategory()));
    }

    @Test
    void testAddManyItems() {
        assertTrue(testCloset.addItem(testItem));
        ClothingItem testItem2 = new ClothingItem("Test 2", testItem.getColour(), "Shirts");
        assertTrue(testCloset.addItem(testItem2));
        ClothingItem testItem3 = new ClothingItem("Test 3", "Green", "Shoes");
        assertTrue(testCloset.addItem(testItem3));

        assertEquals(3, testCloset.numTotalItems());
        assertEquals(2, testCloset.numItemsOfColour(testItem.getColour()));
        assertEquals(1, testCloset.numItemsOfColour(testItem3.getColour()));
        assertEquals(1, testCloset.numItemsInCategory(testItem.getCategory()));
        assertEquals(1, testCloset.numItemsInCategory("Shirts"));
        assertEquals(1, testCloset.numItemsInCategory("Shoes"));
    }

    @Test
    void testAddItemDuplicateItem() {
        testCloset.addItem(testItem);
        assertFalse(testCloset.addItem(testItem));

        assertEquals(1, testCloset.numTotalItems());
        assertEquals(1, testCloset.numItemsOfColour(testItem.getColour()));
        assertEquals(1, testCloset.numItemsInCategory(testItem.getCategory()));
    }

    @Test
    void testAddItemDuplicateName() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test", "Blue", "Hat");
        assertFalse(testCloset.addItem(testItem2));

        assertEquals(1, testCloset.numTotalItems());
        assertEquals(1, testCloset.numItemsOfColour(testItem.getColour()));
        assertEquals(1, testCloset.numItemsInCategory(testItem.getCategory()));
    }

    @Test
    void testRemoveOnlyItem() {
        testCloset.addItem(testItem);

        assertTrue(testCloset.removeItem(testItem));
        assertEquals(0, testCloset.numTotalItems());
        assertEquals(0, testCloset.numItemsOfColour(testItem.getColour()));
        assertEquals(0, testCloset.numItemsInCategory(testItem.getCategory()));
    }

    @Test
    void testRemoveOneOfManyItems() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test 2", testItem.getColour(), "Shirts");
        testCloset.addItem(testItem2);
        ClothingItem testItem3 = new ClothingItem("Test 3", "Green", "Shoes");
        testCloset.addItem(testItem3);

        assertEquals(3, testCloset.numTotalItems());
        assertEquals(2, testCloset.numItemsOfColour(testItem.getColour()));
        assertEquals(1, testCloset.numItemsInCategory("Shirts"));

        assertTrue(testCloset.removeItem(testItem2));

        assertEquals(2, testCloset.numTotalItems());
        assertEquals(1, testCloset.numItemsOfColour(testItem.getColour()));
        assertEquals(0, testCloset.numItemsInCategory("Shirts"));
    }

    @Test
    void testRemoveEmptyCloset() {
        assertFalse(testCloset.removeItem(testItem));
        assertEquals(0, testCloset.numTotalItems());
    }

    @Test
    void testRemoveNotInCloset() {
        ClothingItem testItem2 = new ClothingItem("Test 2", "Red", "Shirts");
        testCloset.addItem(testItem2);
        ClothingItem testItem3 = new ClothingItem("Test 3", "Green", "Shoes");
        testCloset.addItem(testItem3);

        assertFalse(testCloset.removeItem(testItem));
    }

    @Test
    void testAllItemsEmpty() {
        List<ClothingItem> closet = testCloset.allItems();
        assertEquals(0, closet.size());
    }

    @Test
    void testAllItemsOne() {
        testCloset.addItem(testItem);
        List<ClothingItem> closet = testCloset.allItems();

        assertEquals(1, closet.size());
        assertEquals(testItem, closet.get(0));
    }

    @Test
    void testAllItemsMany() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test 2", "Red", "Shirts");
        testCloset.addItem(testItem2);
        ClothingItem testItem3 = new ClothingItem("Test 3", "Green", "Shoes");
        testCloset.addItem(testItem3);
        List<ClothingItem> closet = testCloset.allItems();

        assertEquals(3, closet.size());
        assertEquals(testItem, closet.get(0));
        assertEquals(testItem2, closet.get(1));
        assertEquals(testItem3, closet.get(2));
    }

    @Test
    void testAllItemsNamesEmpty() {
        List<String> closet = testCloset.allItemsNames();
        assertEquals(0, closet.size());
    }

    @Test
    void testAllItemsNamesOne() {
        testCloset.addItem(testItem);
        List<String> closet = testCloset.allItemsNames();

        assertEquals(1, closet.size());
        assertEquals("Test", closet.get(0));
    }

    @Test
    void testAllItemsNamesMany() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test 2", "Red", "Shirts");
        testCloset.addItem(testItem2);
        ClothingItem testItem3 = new ClothingItem("Test 3", "Green", "Shoes");
        testCloset.addItem(testItem3);
        List<String> closet = testCloset.allItemsNames();

        assertEquals(3, closet.size());
        assertEquals("Test", closet.get(0));
        assertEquals("Test 2", closet.get(1));
        assertEquals("Test 3", closet.get(2));
    }

    @Test
    void testAllItemsOfColourEmpty() {
        List<ClothingItem> closet = testCloset.allItemsOfColour("Red");

        assertEquals(0, closet.size());
    }

    @Test
    void testAllItemsOfColourFound() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test 2", "Red", "Shirts");
        testCloset.addItem(testItem2);
        ClothingItem testItem3 = new ClothingItem("Test 3", "Green", "Shoes");
        testCloset.addItem(testItem3);
        List<ClothingItem> closet = testCloset.allItemsOfColour("Red");

        assertEquals(2, closet.size());
        assertEquals(testItem, closet.get(0));
        assertEquals(testItem2, closet.get(1));
    }

    @Test
    void testAllItemsColourNotFound() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test 2", "Red", "Shirts");
        testCloset.addItem(testItem2);
        ClothingItem testItem3 = new ClothingItem("Test 3", "Green", "Shoes");
        testCloset.addItem(testItem3);
        List<ClothingItem> closet = testCloset.allItemsOfColour("Teal");

        assertEquals(0, closet.size());
    }

    @Test
    void testAllItemsInCategoryEmpty() {
        List<ClothingItem> closet = testCloset.allItemsOfCategory("Shirts");

        assertEquals(0, closet.size());
    }

    @Test
    void testAllItemsInCategoryNotFound() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test 2", "Red", "Shirts");
        testCloset.addItem(testItem2);
        ClothingItem testItem3 = new ClothingItem("Test 3", "Green", "Shoes");
        testCloset.addItem(testItem3);
        List<ClothingItem> closet = testCloset.allItemsOfCategory("Hats");

        assertEquals(0, closet.size());
    }

    @Test
    void testAllItemsInCategoryFound() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test 2", "Red", "Shirts");
        testCloset.addItem(testItem2);
        ClothingItem testItem3 = new ClothingItem("Test 3", "Green", "Pants");
        testCloset.addItem(testItem3);
        List<ClothingItem> closet = testCloset.allItemsOfCategory("Pants");

        assertEquals(2, closet.size());
        assertEquals(testItem, closet.get(0));
        assertEquals(testItem3, closet.get(1));
    }

    @Test
    void testNumTotalItemsEmptyCloset() {
        assertEquals(0, testCloset.numTotalItems());
    }

    @Test
    void testNumTotalItemsCloset() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test 2", "Red", "Shirts");
        testCloset.addItem(testItem2);
        ClothingItem testItem3 = new ClothingItem("Test 3", "Green", "Shoes");
        testCloset.addItem(testItem3);

        assertEquals(3, testCloset.numTotalItems());
    }

    @Test
    void testNumItemsOfColourEmpty() {
        assertEquals(0, testCloset.numItemsOfColour("Red"));
    }

    @Test
    void testNumItemsOfColourNone() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test 2", "Red", "Shirts");
        testCloset.addItem(testItem2);

        assertEquals(0, testCloset.numItemsOfColour("Black"));
    }

    @Test
    void testNumItemsOfColourOne() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test 2", "Purple", "Shirts");
        testCloset.addItem(testItem2);

        assertEquals(1, testCloset.numItemsOfColour("Purple"));
    }

    @Test
    void testNumItemsOfColourMany() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test 2", "Red", "Shirts");
        testCloset.addItem(testItem2);

        assertEquals(2, testCloset.numItemsOfColour("Red"));
    }

    @Test
    void testNumItemsCategoryEmpty() {
        assertEquals(0, testCloset.numItemsInCategory("Hats"));
    }

    @Test
    void testNumItemsCategoryOne() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test 2", "Purple", "Shirts");
        testCloset.addItem(testItem2);

        assertEquals(1, testCloset.numItemsInCategory(testItem.getCategory()));
    }

    @Test
    void testNumItemsCategoryMany() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test 2", "Purple", "Shirts");
        testCloset.addItem(testItem2);
        ClothingItem testItem3 = new ClothingItem("Test 3", "Red", "Shirts");
        testCloset.addItem(testItem3);

        assertEquals(2, testCloset.numItemsInCategory("Shirts"));
    }

    @Test
    void testChangeItemNameValid() {
        testCloset.addItem(testItem);

        assertTrue(testCloset.changeItemName(testItem, "New Name"));
        assertEquals("New Name", testCloset.allItems().get(0).getName());
    }

    @Test
    void testChangeItemNameNotValid() {
        testCloset.addItem(testItem);
        ClothingItem testItem2 = new ClothingItem("Test 2", "Purple", "Shirts");
        testCloset.addItem(testItem2);

        assertFalse(testCloset.changeItemName(testItem, "Test 2"));
        assertEquals("Test", testCloset.allItems().get(0).getName());
    }

    @Test
    void testChangeItemColourEmpty() {
        assertFalse(testCloset.changeItemColour(testItem, "Red"));
        assertEquals(0, testCloset.numTotalItems());
        assertEquals(0, testCloset.numItemsOfColour("Red"));
    }

    @Test
    void testChangeItemColourFound() {
        testCloset.addItem(testItem);

        assertTrue(testCloset.changeItemColour(testItem, "Blue"));
        assertEquals(1, testCloset.numTotalItems());
        assertEquals(1, testCloset.numItemsOfColour("Blue"));
        assertEquals(0, testCloset.numItemsOfColour("Red"));
    }

    @Test
    void testChangeItemColourNotFound() {
        testCloset.addItem(testItem);
        ClothingItem newItem = new ClothingItem("Purple Hat", "Purple", "Hat");

        assertFalse(testCloset.changeItemColour(newItem, "Red"));
        assertEquals(1, testCloset.numTotalItems());
        assertEquals(1, testCloset.numItemsOfColour("Red"));
        assertEquals(0, testCloset.numItemsOfColour("Purple"));
    }

    @Test
    void testChangeItemCategoryEmpty() {
        assertFalse(testCloset.changeItemCategory(testItem, "Scarves"));
        assertEquals(0, testCloset.numTotalItems());
        assertEquals(0, testCloset.numItemsInCategory("Scarves"));
    }

    @Test
    void testChangeItemCategoryFound() {
        testCloset.addItem(testItem);
        testCloset.changeItemCategory(testItem, "Purses");

        assertEquals(1, testCloset.numTotalItems());
        assertEquals(1, testCloset.numItemsInCategory("Purses"));
        assertEquals(0, testCloset.numItemsInCategory("Pants"));
    }

    @Test
    void testChangeItemCategoryNotFound() {
        testCloset.addItem(testItem);
        ClothingItem newItem = new ClothingItem("Purple Hat", "Purple", "Hat");

        assertFalse(testCloset.changeItemCategory(newItem, "Jorts"));
        assertEquals(1, testCloset.numTotalItems());
        assertEquals(1, testCloset.numItemsInCategory("Pants"));
        assertEquals(0, testCloset.numItemsInCategory("Jorts"));
    }

}