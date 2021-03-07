package persistence;

import model.ClothingItem;
import model.Outfit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
Citation: code taken and modified from JsonTest class in JsonSerializationDemo
 */
public class JsonTest {
    protected void checkItem(String name, String colour, String category, ClothingItem item) {
        assertEquals(name, item.getName());
        assertEquals(colour, item.getColour());
        assertEquals(category, item.getCategory());
    }
}
