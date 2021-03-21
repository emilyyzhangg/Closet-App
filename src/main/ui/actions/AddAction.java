package ui.actions;

import model.ClothingItem;
import ui.ClosetAppUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Citation: code adapted from the Java tutorials in the Oracle help desk
// Represents an action that allows the user to create a new item
public class AddAction extends AbstractAction  {

    private final ClosetAppUI closetAppUI;
    private JPanel addPanel;
    private String colour;
    private String category;
    private final Font buttonFont;
    private final Font stringFont;

    // Constructs the action completed when dress button is pushed
    public AddAction(ClosetAppUI closetAppUI) {
        super("Add a Clothing Item to Your Closet");
        this.closetAppUI = closetAppUI;
        buttonFont = new Font("Serif", Font.PLAIN, 14);
        stringFont = new Font("Serif", Font.PLAIN, 16);
    }

    // MODIFIES: this
    // EFFECTS: creates a new panel
    @Override
    public void actionPerformed(ActionEvent e) {
        addPanel = new JPanel(null);
        setAddPanel();
    }

    // MODIFIES: this
    // EFFECTS: displays the panel to add a clothing item
    private void setAddPanel() {
        JButton homeButton = new JButton(new GoHomeAction(closetAppUI, addPanel));
        homeButton.setFont(buttonFont);
        addPanel.add(homeButton).setBounds(5, 5, 120, 30);
        addPanel.setBackground(new Color(255, 192, 203));

        setItemName();
        setItemColour();
        setItemCategory();

        closetAppUI.changePanel(addPanel);
    }

    // MODIFIES: this
    // EFFECTS: renders text box and returns name of clothing item entered by user
    private void setItemName() {
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(100, 60, 80, 25);
        nameLabel.setFont(stringFont);
        addPanel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(170, 60, 165, 25);
        nameLabel.setFont(stringFont);
        addPanel.add(nameText);

        setSubmitButton(addPanel, nameText);
    }

    // MODIFIES: this
    // EFFECTS: adds label and J combo box for clothing item colour
    private void setItemColour() {
        JLabel colourLabel = new JLabel("Colour: ");
        colourLabel.setFont(stringFont);
        colourLabel.setBounds(100, 90, 80, 25);
        String[] coloursString = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Black", "White", "Brown"};

        JComboBox<String> colourList = new JComboBox<>(coloursString);

        colourList.addActionListener(new ColourSelectorAction());

        colourList.setBounds(170, 90, 150, 25);
        addPanel.add(colourLabel);
        addPanel.add(colourList);
        colour = "Red";
    }

    // MODIFIES: this
    // EFFECTS: adds label and J combo box for clothing item category
    private void setItemCategory() {
        JLabel categoryLabel = new JLabel("Category: ");
        categoryLabel.setFont(stringFont);
        categoryLabel.setBounds(100, 120, 80, 25);
        String[] categoriesString = {"Accessories", "Shirts", "Jackets", "Pants", "Skirts", "Dresses", "Shoes"};

        JComboBox<String> categoriesList = new JComboBox<>(categoriesString);
        categoriesList.addActionListener(new CategorySelectorAction());
        categoriesList.setBounds(170, 120, 150, 25);
        addPanel.add(categoryLabel);
        addPanel.add(categoriesList);
        category = "Accessories";
    }

    // MODIFIES: this, addPanel
    // EFFECTS: adds submit button to create a new clothing item
    private void setSubmitButton(JPanel addPanel, JTextField nameText) {
        JButton submitButton = new JButton(new SubmitAction(nameText));
        submitButton.setFont(buttonFont);
        submitButton.setBounds(100, 240, 80, 25);
        addPanel.add(submitButton);
    }

    // Represents a combo box that allows the user to select the colour of their item
    public class ColourSelectorAction extends AbstractAction {
        // EFFECTS: constructs a new action listener that determines the colour chosen
        ColourSelectorAction() {
            super("Colour");
        }

        // EFFECTS: reads action performed and sets colour
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();
            colour = (String)cb.getSelectedItem();
        }
    }

    // Represents a combo box that allows the user to select the category of their item
    public class CategorySelectorAction extends AbstractAction {
        CategorySelectorAction() {
            super("Category");
        }

        // MODIFIES: this
        // EFFECTS: reads action performed and sets category
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();
            category = (String)cb.getSelectedItem();
        }
    }

    // Represents a button that when pressed, allows the user to create a new clothing item with the given information
    public class SubmitAction extends AbstractAction {
        JTextField nameText;

        // EFFECTS: constructs a submit button
        SubmitAction(JTextField nameText) {
            super("Submit");
            this.nameText = nameText;
        }

        // MODIFIES: this
        // EFFECTS: reads action performed and acts accordingly
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            try {
                if (s.equals("Submit")) {
                    String name = nameText.getText();

                    if (name.equals("")) {
                        JOptionPane.showMessageDialog(addPanel, "Name cannot be empty!");
                    } else {
                        ClothingItem item = new ClothingItem(name, colour, category);
                        closetAppUI.addClothingItem(item);
                        JOptionPane.showMessageDialog(addPanel, "Item successfully added: "
                                + "\nName: " + item.getName() + "\nColour: " + item.getColour() + "\nCategory: "
                                + item.getCategory());

                        closetAppUI.goBackHome(addPanel);
                    }
                }
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(addPanel, "Please try again!");
            }
        }
    }
}
