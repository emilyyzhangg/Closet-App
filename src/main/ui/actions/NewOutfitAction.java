package ui.actions;

import model.ClothingItem;
import ui.ClosetAppUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

// Represents an action that takes user to page to create a new outfit
public class NewOutfitAction extends AbstractAction {
    private final ClosetAppUI closetAppUI;
    private JPanel newOutfitPanel;
    private final List<String> nameList;
    private final Font buttonFont;
    private final Font stringFont;

    // EFFECTS: constructs a new outfit action panel
    public NewOutfitAction(ClosetAppUI closetAppUI) {
        super("Create a New Outfit");
        this.closetAppUI = closetAppUI;
        nameList = new ArrayList<>();
        buttonFont = new Font("Serif", Font.PLAIN, 14);
        stringFont = new Font("Serif", Font.PLAIN, 16);
    }

    // MODIFIES: this
    // EFFECTS: reads action performed and sets up the new panel
    @Override
    public void actionPerformed(ActionEvent e) {
        newOutfitPanel = new JPanel(null);
        setNewOutfitPanel();
        closetAppUI.changePanel(newOutfitPanel);
    }

    // MODIFIES: this
    // EFFECTS: sets up the panel
    private void setNewOutfitPanel() {
        JLabel title = new JLabel("Create a new outfit:");
        title.setFont(new Font("Serif", Font.PLAIN, 20));
        title.setBounds(320, 35, 400, 40);
        newOutfitPanel.add(title);
        JButton homeButton = new JButton(new GoHomeAction(closetAppUI, newOutfitPanel));
        homeButton.setFont(buttonFont);
        newOutfitPanel.add(homeButton).setBounds(5, 5, 120, 30);
        newOutfitPanel.setBackground(new Color(167, 116, 212));

        setOutfitName();
        addItemToOutfit();
    }

    // MODIFIES: this
    // EFFECTS: creates a JLabel and JTextField for the user to input the outfit name
    private void setOutfitName() {
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 80, 80, 25);
        nameLabel.setFont(stringFont);
        newOutfitPanel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(170, 80, 165, 25);
        nameText.setFont(stringFont);
        newOutfitPanel.add(nameText);

        setSubmitButton(newOutfitPanel, nameText);
    }

    // MODIFIES: this
    // EFFECTS: creates a field for users to add items to the outfit
    private void addItemToOutfit() {
        JLabel itemsLabel = new JLabel("Click on an item to add it to your outfit:");
        itemsLabel.setFont(stringFont);
        itemsLabel.setBounds(100, 110, 300, 25);
        newOutfitPanel.add(itemsLabel);

        JPanel itemsPanel = new JPanel();
        itemsPanel.setBackground(new Color(183, 147, 217));
        itemsPanel.setBounds(100, 130, 600, 350);
        newOutfitPanel.add(itemsPanel);

        List<ClothingItem> items = closetAppUI.getItemsInCloset();
        for (ClothingItem i : items) {
            JCheckBox box = new JCheckBox(new ItemSelectorAction(i));
            box.setFont(new Font("Serif", Font.PLAIN, 30));
            itemsPanel.add(box);
        }
    }

    // Represents a checkbox that represents an item in the closet
    public class ItemSelectorAction extends AbstractAction {
        String name;

        // Constructs the checkbox corresponding to i
        ItemSelectorAction(ClothingItem i) {
            super(i.getName());
            name = i.getName();
        }

        // MODIFIES: this
        // EFFECTS: reads action performed and adds the item to the outfit
        @Override
        public void actionPerformed(ActionEvent e) {
            nameList.add(name);
            JOptionPane.showMessageDialog(newOutfitPanel, name + " was successfully added to outfit!");
        }
    }

    // MODIFIES: this, addPanel
    // EFFECTS: adds submit button to create a new clothing item
    private void setSubmitButton(JPanel addPanel, JTextField nameText) {
        JButton submitButton = new JButton(new SubmitAction(nameText));
        submitButton.setFont(buttonFont);
        submitButton.setBounds(100, 500, 80, 25);
        addPanel.add(submitButton);
    }

    // Represents a submit button that creates a new clothing item
    public class SubmitAction extends AbstractAction {
        JTextField nameText;

        // EFFECTS: constructs the submit button
        SubmitAction(JTextField nameText) {
            super("submit");
            this.nameText = nameText;
        }

        // MODIFIES: this
        // EFFECTS: reads action performed and acts accordingly
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            try {
                if (s.equals("submit")) {
                    String name = nameText.getText();

                    if (name.equals("")) {
                        JOptionPane.showMessageDialog(newOutfitPanel, "Name cannot be empty!");
                    } else if (nameList.size() == 0) {
                        JOptionPane.showMessageDialog(newOutfitPanel, "Outfit cannot be empty!");
                    } else {
                        closetAppUI.createNewOutfit(name, nameList);
                        JOptionPane.showMessageDialog(newOutfitPanel, "Outfit was successfully saved!");

                        closetAppUI.goBackHome(newOutfitPanel);
                    }
                }
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(newOutfitPanel, "Please try again!");
            }
        }
    }
}
