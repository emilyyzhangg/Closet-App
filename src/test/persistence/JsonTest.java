package persistence;

import model.ClothingItem;
import model.Outfit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonTest {
    protected void checkItem(String name, String colour, String category, ClothingItem item) {
        assertEquals(name, item.getName());
        assertEquals(colour, item.getColour());
        assertEquals(category, item.getCategory());
    }

    protected void checkOutfit(String name, List<ClothingItem> items, Outfit outfit) {
        assertEquals(name, outfit.getOutfitName());

        assertEquals(items.size(), outfit.displayOutfit().size());
        for (ClothingItem i : outfit.displayOutfit()) {
            assertTrue(items.contains(i));
        }
    }
}
