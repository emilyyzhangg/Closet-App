package ui;

import model.Closet;
import model.ClothingItem;
import model.Outfit;
import model.SavedOutfits;
import persistence.JsonWriter;
import ui.actions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// Citation: code adapted from the Java tutorials in the Oracle help desk
// Represents the GUI of the closet app
public class ClosetAppUI extends JFrame {
    private static JFrame frame;
    private static JPanel panel;
    private Closet closet = new Closet();
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private SavedOutfits savedOutfits = new SavedOutfits();
    private final Font buttonFont;
    private static final String JSON_STORE_CLOSET = "./data/Closet.json";
    private static final String JSON_STORE_SAVED_OUTFITS = "./data/SavedOutfits.json";
    private final JsonWriter jsonWriterCloset;
    private final JsonWriter jsonWriterSavedOutfits;

    // MODIFIES: this
    // EFFECTS: constructor that sets up button panel
    public ClosetAppUI() {
        jsonWriterCloset = new JsonWriter(JSON_STORE_CLOSET);
        jsonWriterSavedOutfits = new JsonWriter(JSON_STORE_SAVED_OUTFITS);

        frame = new JFrame("Closet App");
        JLabel label = new JLabel("Welcome to Your Closet!");
        JDesktopPane desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        panel = new JPanel(null);
        panel.setBackground(new Color(204, 204, 255));

        label.setFont(new Font("Serif", Font.PLAIN, 28));
        label.setBounds((WIDTH - 300) / 2, 30, 300, 30);
        panel.add(label);

        buttonFont = new Font("Serif", Font.PLAIN, 16);

        initializeButtons();

        frame.add(panel);
        frame.setSize(WIDTH, HEIGHT);
        frame.repaint();

        frame.setVisible(true);
    }

    // MODIFIES: this
    // Creates all the buttons to be added to the home page
    private void initializeButtons() {
        List<JButton> buttonList = new ArrayList<>();

        JButton addButton = new JButton(new AddAction(this));
        buttonList.add(addButton);
        JButton removeButton = new JButton(new RemoveAction(this));
        buttonList.add(removeButton);
        JButton viewItemsButton = new JButton(new ViewAction(this));
        buttonList.add(viewItemsButton);
        JButton newOutfitButton = new JButton(new NewOutfitAction(this));
        buttonList.add(newOutfitButton);
        JButton savedOutfitsButton = new JButton(new SavedOutfitsAction(this));
        buttonList.add(savedOutfitsButton);
        JButton saveButton = new JButton(new SaveClosetAction(this, panel));
        buttonList.add(saveButton);
        JButton loadButton = new JButton(new LoadClosetAction(this, panel));
        buttonList.add(loadButton);

        changeFontOfButtons(buttonList);

        panel.add(addButton).setBounds((WIDTH - 300) / 2, 90, 300, 50);
        panel.add(removeButton).setBounds((WIDTH - 300) / 2, 150, 300, 50);
        panel.add(viewItemsButton).setBounds((WIDTH - 300) / 2, 210, 300, 50);
        panel.add(newOutfitButton).setBounds((WIDTH - 300) / 2, 270, 300, 50);
        panel.add(savedOutfitsButton).setBounds((WIDTH - 300) / 2, 330, 300, 50);
        panel.add(loadButton).setBounds((WIDTH - 300) / 2, 390, 300, 50);
        panel.add(saveButton).setBounds((WIDTH - 300) / 2, 450, 300, 50);
    }

    // MODIFIES: this
    // EFFECTS: changes the font of the JButtons in the list
    private void changeFontOfButtons(List<JButton> buttonList) {
        for (JButton b : buttonList) {
            b.setFont(buttonFont);
        }
    }

    // MODIFIES: this, currentPanel
    // EFFECTS: GUI moves from currentPanel to the home page
    public void goBackHome(JPanel currentPanel) {
        frame.remove(currentPanel);
        frame.repaint();
        frame.revalidate();

        frame.add(panel);
        frame.repaint();
        frame.revalidate();
    }

    // MODIFIES: this, newPanel
    // EFFECTS: GUI moves from home page to the newPanel
    public void changePanel(JPanel newPanel) {
        frame.remove(panel);
        frame.repaint();
        frame.revalidate();

        frame.add(newPanel);
        frame.repaint();
        frame.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: sets inputs as closet and saved outfits
    public void updateCloset(Closet closet, SavedOutfits savedOutfits) {
        this.closet = closet;
        this.savedOutfits = savedOutfits;
    }

    // MODIFIES: this
    // EFFECTS: writes changes in closet and saved outfits to JSON
    public void saveCloset() {
        try {
            jsonWriterCloset.open();
            jsonWriterCloset.writeCloset(this.closet);
            jsonWriterCloset.close();

            jsonWriterSavedOutfits.open();
            jsonWriterSavedOutfits.writeOutfits(this.savedOutfits);
            jsonWriterSavedOutfits.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(panel, "Sorry, something went wrong!");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a clothing item to the closet
    public void addClothingItem(ClothingItem item) {
        closet.addItem(item);
    }

    // EFFECTS: returns the closet
    public Closet getCloset() {
        return closet;
    }

    // EFFECTS: returns the list of items in the closet
    public List<ClothingItem> getItemsInCloset() {
        return closet.allItems();
    }

    // MODIFIES: this
    // EFFECTS: removes the given item from the closet
    public void removeItemFromCloset(ClothingItem item) {
        closet.removeItem(item);
    }

    // MODIFIES: this
    // EFFECTS: creates a new outfit in saved outfits with given name and items, display an error panel if a clothing
    // item isn't found
    public void createNewOutfit(String name, List<String> itemList) {
        List<ClothingItem> itemsInOutfit = new ArrayList<>();

        for (String itemName : itemList) {
            try {
                ClothingItem item = closet.getItemFromName(itemName);
                itemsInOutfit.add(item);
            } catch (NoSuchElementException e) {
                JOptionPane.showMessageDialog(panel, "Sorry, something went wrong!");
            }
        }

        Outfit outfit = new Outfit(name, itemsInOutfit);
        savedOutfits.addOutfit(outfit);
    }

    // EFFECTS: returns saved outfits
    public SavedOutfits getSavedOutfits() {
        return savedOutfits;
    }

    // EFFECTS: returns saved outfits as a list of outfits
    public List<Outfit> getSavedOutfitsAsList() {
        return savedOutfits.getOutfits();
    }


    // Represents action to be taken when user clicks desktop
    private class DesktopFocusAction extends MouseAdapter {
        // EFFECTS: initializes mouse
        @Override
        public void mouseClicked(MouseEvent e) {
            ClosetAppUI.this.requestFocusInWindow();
        }
    }

    // MODIFIES: this
    // EFFECTS: constructs a closet app GUI
    public static void main(String[] args) {
        new ClosetAppUI();
    }
}
