package ui.actions;

import model.Closet;
import model.SavedOutfits;
import persistence.JsonReader;
import ui.ClosetAppUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

// Citation: code adapted from the Java tutorials in the Oracle help desk
// Represents an action that allows the user to write the closet to JSON
public class LoadClosetAction extends AbstractAction {
    ClosetAppUI closetAppUI;
    Closet closet;
    SavedOutfits savedOutfits;
    JPanel panel;

    private static final String JSON_STORE_CLOSET = "./data/Closet.json";
    private static final String JSON_STORE_SAVED_OUTFITS = "./data/SavedOutfits.json";
    private final JsonReader jsonReaderCloset;
    private final JsonReader jsonReaderSavedOutfits;

    // EFFECTS: constructs a JButton that allows the user to write the closet to JSON
    public LoadClosetAction(ClosetAppUI closetAppUI, JPanel panel) {
        super("Load Closet");
        this.closetAppUI = closetAppUI;
        this.closet = closetAppUI.getCloset();
        this.savedOutfits = closetAppUI.getSavedOutfits();
        this.panel = panel;

        jsonReaderCloset = new JsonReader(JSON_STORE_CLOSET);
        jsonReaderSavedOutfits = new JsonReader(JSON_STORE_SAVED_OUTFITS);
    }

    // MODIFIES: this
    // EFFECTS: reads action performed and loads closet
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            closet = jsonReaderCloset.readCloset();
            savedOutfits = jsonReaderSavedOutfits.readSavedOutfits();
            JOptionPane.showMessageDialog(panel, "Successfully loaded your closet!");
            closetAppUI.updateCloset(closet, savedOutfits);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(panel, "Sorry, something went wrong!");
        }
    }
}
