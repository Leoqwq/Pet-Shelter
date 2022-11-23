// Citations: https://docs.oracle.com/javase/tutorial/uiswing/;
//            https://stackoverflow.com;
//            https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html#zz-3.
//            https://javatpoint.com

package ui;

import model.Shelter;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the main window of the Pet Shelter Application
public class MainWindow extends JFrame implements ActionListener {
    private static final int BUTTON_POSITION = 100;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 20;
    private static final String ADOPT_PET_ACTION = "ADOPT_PET_ACTION";
    private static final String ADD_PET_ACTION = "ADD_PET_ACTION";
    private static final String VIEW_PET_LIST_ACTION = "VIEW_PET_LIST_ACTION";
    private static final String SAVE_ACTION = "SAVE_ACTION";
    private static final String LOAD_ACTION = "LOAD_ACTION";
    private static final String QUIT_ACTION = "QUIT_ACTION";
    private Shelter shelter;
    private ViewPetListWindow viewPetListWindow;
    private AddPetWindow addPetWindow;
    private AdoptPetWindow adoptPetWindow;

    private static final String JSON_STORE = "./data/shelter.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Effects: Creates a main window with all the labels, buttons and background image set up.
    public MainWindow() {
        shelter = new Shelter();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        this.setWindow();
        this.setBackgroundImage();
        this.setUpLabelsAndButtons();
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    // Modifies: this
    // Effects: Creates a new window
    private void setWindow() {
        setPreferredSize(new Dimension(500, 500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
    }

    // Modifies: this
    // Effects: Set up the background image of the window.
    private void setBackgroundImage() {
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("data/images/PetShelter.png"));
            setContentPane(new BackgroundImage(backgroundImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Modifies: this
    // Effects: Set up all the labels, buttons and text-fields for the window.
    private void setUpLabelsAndButtons() {
        JLabel selectOptionLabel = new JLabel("Please select an option: ", JLabel.CENTER);
        selectOptionLabel.setBounds(26, 10, 300, 20);
        add(selectOptionLabel);
        selectOptionLabel.setForeground(Color.black);

        this.setButton(new JButton("Adopt Pet"), ADOPT_PET_ACTION, 40);

        this.setButton(new JButton("Add Pet"), ADD_PET_ACTION, 80);

        this.setButton(new JButton("View Pet List"), VIEW_PET_LIST_ACTION, 160);

        this.setButton(new JButton("Save"), SAVE_ACTION, 240);

        this.setButton(new JButton("Load"), LOAD_ACTION, 280);

        this.setButton(new JButton("Quit Pet Shelter Application"), QUIT_ACTION, 360);
    }

    // Modifies: this
    // Effects: A helper method that adds a new button to the main window
    private void setButton(JButton button, String action, int y) {
        button.setBounds(BUTTON_POSITION, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(button);
        button.setActionCommand(action);
        button.addActionListener(this);
        button.setForeground(Color.black);
    }

    // EFFECTS: Saves the shelter to file
    private void saveShelter() {
        try {
            jsonWriter.open();
            jsonWriter.write(shelter);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,"Successfully saved shelter to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads the shelter from file
    private void loadShelter() {
        try {
            shelter = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Successfully loaded shelter from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
        }
    }

    // Modifies: this
    // Effects: Creates an AdoptPet window when the user click the 'Adopt Pet' button;
    //          Creates an AddPet window when the user click the 'Add Pet' button;
    //          Creates a ViewPetList window when the user click the 'View Pet List' button;
    //          Saves the shelter to file when the user click the 'Save' button;
    //          Loads the shelter from file when the user click the 'Load' button;
    //          Quits the application and closes the main window when the user click the 'Quit' button.
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.equals(ADOPT_PET_ACTION)) {
            adoptPetWindow = new AdoptPetWindow(shelter);
        } else if (action.equals(ADD_PET_ACTION)) {
            addPetWindow = new AddPetWindow(shelter);
        } else if (action.equals(VIEW_PET_LIST_ACTION)) {
            if (shelter.getNumOfPetInShelter() == 0) {
                JOptionPane.showMessageDialog(null, "The shelter is empty!");
            } else {
                viewPetListWindow = new ViewPetListWindow(shelter);
            }
        } else if (action.equals(SAVE_ACTION)) {
            saveShelter();
        } else if (action.equals(LOAD_ACTION)) {
            loadShelter();
        } else if (action.equals(QUIT_ACTION)) {
            dispose();
        }
    }

    // Effects: Create a new MainWindow Object
    public static void main(String[] args) {
        new MainWindow();
    }
}
