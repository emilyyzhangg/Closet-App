package ui.actions;

import javax.swing.*;
import java.awt.*;

// Citation: code adapted from the Java tutorials in the Oracle help desk
// Represents a scrollable panel to display items and outfits
public class ScrollablePanel extends JPanel implements Scrollable {

    // EFFECTS: constructs a new scrollable panel
    public ScrollablePanel() {
        super();
    }

    // MODIFIES: this
    // EFFECTS: sets preferred size of the scrollable panel to 760x450
    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return new Dimension(760, 450);
    }


    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;
    }

    // MODIFIES: this
    // EFFECTS: allows scrollable panel to be same width as viewport
    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    // MODIFIES: this
    // EFFECTS: allows scrollable panel to be same height as viewport
    @Override
    public boolean getScrollableTracksViewportHeight() {
        return true;
    }
}
