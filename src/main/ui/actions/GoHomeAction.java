package ui.actions;

import ui.ClosetAppUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Citation: code adapted from the Java tutorials in the Oracle help desk
// Represents an action that allows the user to go back to the home page
public class GoHomeAction extends AbstractAction {
    private ClosetAppUI closetAppUI;
    private JPanel panel;

    // EFFECTS: constructs a go back button in the given panel
    GoHomeAction(ClosetAppUI closetAppUI, JPanel panel) {
        super("Go Back");
        this.closetAppUI = closetAppUI;
        this.panel = panel;
    }

    // MODIFIES: this
    // EFFECTS: reads action performed and brings user back home
    @Override
    public void actionPerformed(ActionEvent e) {
        closetAppUI.goBackHome(panel);
    }
}
