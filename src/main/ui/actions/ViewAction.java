package ui.actions;

import model.ClothingItem;
import ui.ClosetAppUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

// Citation: code adapted from the Java tutorials in the Oracle help desk
// Represents an action that allows the user to view an item
public class ViewAction extends AbstractAction {

    private JButton homeButton;
    private ClosetAppUI closetAppUI;
    private JPanel viewPanel;
    private List<ClothingItem> itemList = null;
    private final Font itemButtonFont;

    // Constructs the action completed when dress button is pushed
    public ViewAction(ClosetAppUI closetAppUI) {
        super("View Items in Your Closet");
        this.closetAppUI = closetAppUI;
        itemButtonFont = new Font("Serif", Font.PLAIN, 30);
    }

    // MODIFIES: this
    // EFFECTS: creates a new panel
    @Override
    public void actionPerformed(ActionEvent e) {
        viewPanel = new JPanel(null);
        setViewPanel();
        closetAppUI.changePanel(viewPanel);
    }

    // MODIFIES: this
    // EFFECTS: sets up the view panel with the home button, title, and items
    private void setViewPanel() {
        homeButton = new JButton(new GoHomeAction(closetAppUI, viewPanel));
        homeButton.setBounds(5, 5, 120, 30);
        viewPanel.add(homeButton);
        itemList = closetAppUI.getItemsInCloset();
        displayItems(itemList);

        viewPanel.setBackground(new Color(97, 184, 227));

        JLabel chooseLabel = new JLabel("Click on the item you wish to view!");
        chooseLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        chooseLabel.setBounds(250, 35, 300, 30);
        viewPanel.add(chooseLabel);
    }

    // MODIFIES: this
    // EFFECTS: displays the items as a list of JButtons
    private void displayItems(List<ClothingItem> items) {
        ScrollablePanel itemsPanel = new ScrollablePanel();

        for (ClothingItem i : items) {
            JButton button = new JButton(new ShowItemAction(i));
            button.setFont(itemButtonFont);
            itemsPanel.add(button);
        }

        itemsPanel.setBounds(20, 80, 760, 450);
        itemsPanel.setBackground(new Color(136, 201, 232));
        viewPanel.add(itemsPanel);
        setScrollPanel(itemsPanel);

        closetAppUI.changePanel(viewPanel);
    }

    // MODIFIES: this, itemsPanel
    // EFFECTS: Sets up a scrollPane to display items
    private void setScrollPanel(JPanel itemsPanel) {
        JScrollPane scrollPane = new JScrollPane(itemsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setPreferredSize(new Dimension(760, 450));
        scrollPane.setViewportView(itemsPanel);
        scrollPane.setBounds(20, 80, 760, 450);
        viewPanel.add(scrollPane);
    }

    // Represents an action that displays a clothing item image
    public class ShowItemAction extends AbstractAction {
        ClothingItem item;

        // EFFECTS: constructs a show item action
        public ShowItemAction(ClothingItem i) {
            super(i.getName());
            this.item = i;
        }

        // MODIFIES: this
        // EFFECTS: renders the JOptionPane with the item image
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel itemPanel = new JPanel();
            try {
                getImageFromFile(item.getColour(), item.getCategory(), itemPanel);

            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(itemPanel, "Sorry, something went wrong!");
            }
            viewPanel.add(itemPanel);
        }

        // EFFECTS: gets the correct image from file based on the category of the item
        private void getImageFromFile(String colour, String category, JPanel panel) throws IOException {
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
            setBackgroundColourOfImage(colour, image, panel);
        }

        // EFFECTS: renders the background colour panel based on the colour of the item
        private void setBackgroundColourOfImage(String colour, Image image, JPanel panel) {
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

            showItemImage(image,colourBackground, panel);
        }

        // MODIFIES: this, JPanel
        // EFFECTS: renders the JOptionPane with the item image and colour background
        private void showItemImage(Image image, JPanel colour, JPanel panel) {
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            colour.add(imageLabel);

            JOptionPane.showMessageDialog(panel, colour);

        }

    }
}

