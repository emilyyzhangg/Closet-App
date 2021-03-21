package ui.actions;

import model.Closet;
import model.SavedOutfits;
import ui.ClosetAppUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Citation: code adapted from the Java tutorials in the Oracle help desk
// Represents an action that allows the user to save their closet
public class SaveClosetAction extends AbstractAction {
    ClosetAppUI closetAppUI;
    Closet closet;
    SavedOutfits savedOutfits;
    JPanel panel;

    // Constructs a new save closet action
    public SaveClosetAction(ClosetAppUI closetAppUI, JPanel panel) {
        super("Save changes");
        this.closetAppUI = closetAppUI;
        this.closet = closetAppUI.getCloset();
        this.savedOutfits = closetAppUI.getSavedOutfits();
        this.panel = panel;
    }

    // MODIFIES: this
    // EFFECTS: reads action performed and saves closet
    @Override
    public void actionPerformed(ActionEvent e) {
        closetAppUI.saveCloset();
        JOptionPane.showMessageDialog(panel, "Successfully saved your closet!");
    }
}
