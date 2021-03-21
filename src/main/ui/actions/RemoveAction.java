package ui.actions;

import model.ClothingItem;
import ui.ClosetAppUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

// Citation: code adapted from the Java tutorials in the Oracle help desk
// Represents an action that takes the user to a page to delete a clothing item
public class RemoveAction extends AbstractAction {
    private final ClosetAppUI closetAppUI;
    private JPanel removePanel;
    private final Font buttonFont;
    private final Font itemButtonFont;

    // EFFECTS: constructs the action completed when dress button is pushed
    public RemoveAction(ClosetAppUI closetAppUI) {
        super("Remove a Clothing Item From Your Closet");
        this.closetAppUI = closetAppUI;
        buttonFont = new Font("Serif", Font.PLAIN, 14);
        itemButtonFont = new Font("Serif", Font.PLAIN, 30);
    }

    // MODIFIES: this
    // EFFECTS: reads action performed and creates a new JPanel
    @Override
    public void actionPerformed(ActionEvent e) {
        removePanel = new JPanel(null);
        setRemovePanel();
    }

    // MODIFIES: this
    // EFFECTS: sets up the remove items panel
    private void setRemovePanel() {
        closetAppUI.changePanel(removePanel);

        JButton homeButton = new JButton(new GoHomeAction(closetAppUI, removePanel));
        homeButton.setFont(buttonFont);
        homeButton.setBounds(5, 5, 120, 30);
        removePanel.add(homeButton);
        removePanel.setBackground(new Color(116, 158, 91));

        JLabel label = new JLabel("Click on the item you wish to remove");
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        label.setBounds(270, 35, 400, 40);
        removePanel.add(label);

        List<ClothingItem> itemsInCloset = closetAppUI.getItemsInCloset();
        ScrollablePanel itemsPanel = new ScrollablePanel();

        for (ClothingItem i : itemsInCloset) {
            JButton itemButton = new JButton(new RemoveItemAction(i));
            itemButton.setSize(100, 30);
            itemButton.setFont(itemButtonFont);
            itemsPanel.add(itemButton);
        }
        itemsPanel.setBounds(20, 80, 760, 450);
        itemsPanel.setBackground(new Color(178, 219, 149));
        removePanel.add(itemsPanel);

        setScrollPanel(itemsPanel);
        closetAppUI.changePanel(removePanel);
    }

    // MODIFIES: this, itemsPanel
    // EFFECTS: Sets up a scrollPane to display items
    private void setScrollPanel(JPanel itemsPanel) {
        JScrollPane scrollPane = new JScrollPane(itemsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setPreferredSize(new Dimension(760, 450));
        scrollPane.setViewportView(itemsPanel);
        scrollPane.setBounds(20, 80, 760, 450);
        removePanel.add(scrollPane);
    }

    // Represents an action that removes an item
    protected class RemoveItemAction extends AbstractAction {
        ClothingItem item;

        // Constructs the remove item action
        RemoveItemAction(ClothingItem item) {
            super(item.getName());
            this.item = item;
        }

        // MODIFIES: this
        // EFFECTS: reads action performed and removes item
        @Override
        public void actionPerformed(ActionEvent e) {
            closetAppUI.removeItemFromCloset(item);
            JOptionPane.showMessageDialog(removePanel, "Item successfully removed!");
            closetAppUI.goBackHome(removePanel);
        }
    }
}


