package ui.actions;

import model.ClothingItem;
import model.Outfit;
import ui.ClosetAppUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

// Represents an action that allows the user to view their saved outfits
public class SavedOutfitsAction extends AbstractAction {
    private final ClosetAppUI closetAppUI;
    private JPanel savedOutfitsPanel;
    private final Font buttonFont;
    private final Font outfitButtonFont;

    // EFFECTS: constructs a new saved outfits action
    public SavedOutfitsAction(ClosetAppUI closetAppUI) {
        super("View Your Saved Outfits");
        this.closetAppUI = closetAppUI;
        buttonFont = new Font("Serif", Font.PLAIN, 14);
        outfitButtonFont = new Font("Serif", Font.PLAIN, 30);
    }

    // MODIFIES: this
    // EFFECTS: reads action performed and creates a new panel
    @Override
    public void actionPerformed(ActionEvent e) {
        savedOutfitsPanel = new JPanel(null);
        setSavedOutfitsPanel();
    }

    // MODIFIES: this
    // EFFECTS: sets up the saved outfits panel with the home button, outfits, and title
    private void setSavedOutfitsPanel() {
        JButton homeButton = new JButton(new GoHomeAction(closetAppUI, savedOutfitsPanel));
        homeButton.setFont(buttonFont);
        savedOutfitsPanel.add(homeButton).setBounds(5, 5, 120, 30);
        savedOutfitsPanel.add(homeButton);

        JLabel title = new JLabel("Click on the outfit you wish to view: ");
        title.setFont(new Font("Serif", Font.PLAIN, 20));
        title.setBounds(270, 35, 400, 40);
        savedOutfitsPanel.add(title);
        savedOutfitsPanel.setBackground(new Color(238, 130, 133, 255));

        displayOutfitButtons();
        closetAppUI.changePanel(savedOutfitsPanel);
    }

    // MODIFIES: this
    // EFFECTS: displays all the outfits in saved outfits as a list of buttons
    private void displayOutfitButtons() {
        List<Outfit> outfits = closetAppUI.getSavedOutfitsAsList();

        JPanel outfitButtonsPanel = new JPanel();
        outfitButtonsPanel.setBackground(new Color(239, 174, 196));
        outfitButtonsPanel.setBounds(20, 80, 760, 450);
        savedOutfitsPanel.add(outfitButtonsPanel);

        for (Outfit outfit : outfits) {
            JButton button = new JButton(new ShowOutfitAction(outfit));
            button.setFont(outfitButtonFont);
            outfitButtonsPanel.add(button);
        }
    }

    // Represents the action taken when an outfit button is pressed, displays the outfit
    public class ShowOutfitAction extends AbstractAction {
        private Outfit outfit;
        private final List<ClothingItem> items;
        private JPanel itemsPanel;
        private JPanel accessoriesPanel;
        private JPanel shirtsPanel;
        private JPanel jacketsPanel;
        private JPanel pantsPanel;
        private JPanel skirtsPanel;
        private JPanel dressesPanel;
        private JPanel shoesPanel;

        // EFFECTS: constructs a show outfit action
        ShowOutfitAction(Outfit outfit) {
            super(outfit.getOutfitName());
            this.outfit = outfit;
            this.items = outfit.displayOutfit();
            itemsPanel = new JPanel();
        }

        // MODIFIES: this
        // EFFECTS: reads action performed and displays the items in the outfit
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel itemPanel = new JPanel(null);
            itemPanel.setBackground(new Color(245, 218, 227));
            setItemPanel(itemPanel);

            savedOutfitsPanel.removeAll();
            savedOutfitsPanel.repaint();
            savedOutfitsPanel.revalidate();
            savedOutfitsPanel.add(itemPanel);
            savedOutfitsPanel.repaint();
            savedOutfitsPanel.revalidate();

            for (ClothingItem item : items) {
                try {
                    getImageFromFile(item.getColour(), item.getCategory(), itemPanel);
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(itemPanel, "Sorry, something went wrong!");
                }
            }
        }

        // MODIFIES: this
        // EFFECTS: sets up the panel with items on it
        private void setItemPanel(JPanel itemPanel) {
            itemPanel.setBounds(0, 0, 800, 600);

            JButton homeButton = new JButton(new GoHomeAction(closetAppUI, savedOutfitsPanel));
            homeButton.setFont(buttonFont);
            itemPanel.add(homeButton).setBounds(5, 5, 120, 30);
            itemPanel.add(homeButton);

            renderAccessoriesBox(itemPanel);
            renderShirtsBox(itemPanel);
            renderJacketsBox(itemPanel);
            renderPantsBox(itemPanel);
            renderSkirtsBox(itemPanel);
            renderDressesBox(itemPanel);
            renderShoesBox(itemPanel);
        }

        // MODIFIES: this
        // EFFECTS: renders the panel with accessories
        private void renderAccessoriesBox(JPanel itemPanel) {
            accessoriesPanel = new JPanel();
            accessoriesPanel.setBounds(300, 20, 300, 90);
            accessoriesPanel.setBackground(Color.WHITE);
            itemPanel.add(accessoriesPanel);

            JLabel accessoriesLabel = new JLabel("Accessories:");
            itemPanel.add(accessoriesLabel).setBounds(200, 20, 180, 25);
        }

        // MODIFIES: this
        // EFFECTS: renders the panel with shirts
        private void renderShirtsBox(JPanel itemPanel) {
            shirtsPanel = new JPanel();
            shirtsPanel.setBounds(90, 140, 280, 120);
            shirtsPanel.setBackground(Color.WHITE);
            itemPanel.add(shirtsPanel);

            JLabel shirtsLabel = new JLabel("Shirts:");
            itemPanel.add(shirtsLabel).setBounds(40, 140, 180, 25);
        }

        // MODIFIES: this
        // EFFECTS: renders the panel with jackets
        private void renderJacketsBox(JPanel itemPanel) {
            jacketsPanel = new JPanel();
            jacketsPanel.setBounds(470, 140, 300, 120);
            jacketsPanel.setBackground(Color.WHITE);
            itemPanel.add(jacketsPanel);

            JLabel jacketsLabel = new JLabel("Jackets:");
            itemPanel.add(jacketsLabel).setBounds(400, 140, 180, 25);
        }

        // MODIFIES: this
        // EFFECTS: renders the panel with pants
        private void renderPantsBox(JPanel itemPanel) {
            pantsPanel = new JPanel();
            pantsPanel.setBounds(90, 290, 280, 120);
            pantsPanel.setBackground(Color.WHITE);
            itemPanel.add(pantsPanel);

            JLabel pantsLabel = new JLabel("Pants:");
            itemPanel.add(pantsLabel).setBounds(40, 290, 180, 25);
        }

        // MODIFIES: this
        // EFFECTS: renders the panel with skirts
        private void renderSkirtsBox(JPanel itemPanel) {
            skirtsPanel = new JPanel();
            skirtsPanel.setBounds(470, 290, 300, 120);
            skirtsPanel.setBackground(Color.WHITE);
            itemPanel.add(skirtsPanel);

            JLabel skirtsLabel = new JLabel("Skirts:");
            itemPanel.add(skirtsLabel).setBounds(400, 290, 180, 25);
        }

        // MODIFIES: this
        // EFFECTS: renders the panel with dresses
        private void renderDressesBox(JPanel itemPanel) {
            dressesPanel = new JPanel();
            dressesPanel.setBounds(100, 440, 280, 120);
            dressesPanel.setBackground(Color.WHITE);
            itemPanel.add(dressesPanel);

            JLabel dressesLabel = new JLabel("Dresses:");
            itemPanel.add(dressesLabel).setBounds(40, 440, 180, 25);
        }

        // MODIFIES: this
        // EFFECTS: renders the panel with shoes
        private void renderShoesBox(JPanel itemPanel) {
            shoesPanel = new JPanel();
            shoesPanel.setBounds(470, 440, 300, 120);
            shoesPanel.setBackground(Color.WHITE);
            itemPanel.add(shoesPanel);

            JLabel shoesLabel = new JLabel("Shoes:");
            itemPanel.add(shoesLabel).setBounds(400, 440, 180, 25);
        }

        // EFFECTS: gets an image from the file based on category
        private void getImageFromFile(String colour, String category, JPanel itemPanel) throws IOException {
            String imageName;

            if (category.equals("Accessories")) {
                imageName = "accessories.png";
            } else if (category.equals("Shirts")) {
                imageName = "shirt.png";
            } else if (category.equals("Jackets")) {
                imageName = "jacket.png";
            } else if (category.equals("Pants")) {
                imageName = "pants.png";
            } else if (category.equals("Skirts")) {
                imageName = "skirt.png";
            } else if (category.equals("Dresses")) {
                imageName = "dress.png";
            } else {
                imageName = "shoes.png";
            }

            Image image = ImageIO.read(new File("/Users/emilyzhang/CPSC210/project_m8t7n/images/"
                    + imageName));
            setBackgroundColourOfImage(colour, category, image);
        }

        // EFFECTS: creates a background based on the colour of the item
        private void setBackgroundColourOfImage(String colour, String category, Image image) {
            JPanel colourBackground = new JPanel();
            if (colour.equals("Red")) {
                colourBackground.setBackground(new Color(255, 101, 69));
            } else if (colour.equals("Orange")) {
                colourBackground.setBackground(new Color(255, 130, 63));
            } else if (colour.equals("Yellow")) {
                colourBackground.setBackground(new Color(255, 224, 113));
            } else if (colour.equals("Green")) {
                colourBackground.setBackground(new Color(26, 154, 60));
            } else if (colour.equals("Blue")) {
                colourBackground.setBackground(new Color(82, 225, 248));
            } else if (colour.equals("Purple")) {
                colourBackground.setBackground(new Color(165, 132, 248));
            } else if (colour.equals("Black")) {
                colourBackground.setBackground(new Color(55, 51, 52));
            } else if (colour.equals("Brown")) {
                colourBackground.setBackground(new Color(113, 48, 14));
            } else {
                colourBackground.setBackground(Color.WHITE);
            }

            showItemImage(image, category, colourBackground);
        }

        // MODIFIES: this, colourPanel
        // EFFECTS: displays the item image on top of the colourPanel
        private void showItemImage(Image image, String category, JPanel colourPanel) {
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            colourPanel.add(imageLabel);

            addImageLabelToCorrectPanel(category, colourPanel);
        }

        // MODIFIES: this
        // EFFECTS: displays the colourPanel in the correct parent panel
        private void addImageLabelToCorrectPanel(String category, JPanel colourPanel) {
            if (category.equals("Accessories")) {
                accessoriesPanel.add(colourPanel);
            } else if (category.equals("Shirts")) {
                shirtsPanel.add(colourPanel);
            } else if (category.equals("Jackets")) {
                jacketsPanel.add(colourPanel);
            } else if (category.equals("Pants")) {
                pantsPanel.add(colourPanel);
            } else if (category.equals("Skirts")) {
                skirtsPanel.add(colourPanel);
            } else if (category.equals("Dresses")) {
                dressesPanel.add(colourPanel);
            } else {
                shoesPanel.add(colourPanel);
            }
        }
    }
}
