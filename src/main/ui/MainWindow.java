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
    private static final String MARK_PET_AVAILABLE_ACTION = "MARK_PET_AVAILABLE_ACTION";
    private static final String VIEW_PET_LIST_ACTION = "VIEW_PET_LIST_ACTION";
    private static final String VIEW_PET_NUM_ACTION = "VIEW_PET_NUM_ACTION";
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
            BufferedImage backgroundImage = ImageIO.read(new File("src/main/ui/images/PetShelter.png"));
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

        JButton adoptPetButton = new JButton("Adopt Pet");
        adoptPetButton.setBounds(BUTTON_POSITION, 40, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(adoptPetButton);
        adoptPetButton.setActionCommand(ADOPT_PET_ACTION);
        adoptPetButton.addActionListener(this);
        adoptPetButton.setForeground(Color.black);

        JButton addPetButton = new JButton("Add Pet");
        addPetButton.setBounds(BUTTON_POSITION, 80, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(addPetButton);
        addPetButton.setActionCommand(ADD_PET_ACTION);
        addPetButton.addActionListener(this);
        addPetButton.setForeground(Color.black);

        JButton viewPetListButton = new JButton("View Pet List");
        viewPetListButton.setBounds(BUTTON_POSITION, 160, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(viewPetListButton);
        viewPetListButton.setActionCommand(VIEW_PET_LIST_ACTION);
        viewPetListButton.addActionListener(this);
        viewPetListButton.setForeground(Color.black);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(BUTTON_POSITION, 240, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(saveButton);
        saveButton.setActionCommand(SAVE_ACTION);
        saveButton.addActionListener(this);
        saveButton.setForeground(Color.black);

        JButton loadButton = new JButton("Load");
        loadButton.setBounds(BUTTON_POSITION, 280, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(loadButton);
        loadButton.setActionCommand(LOAD_ACTION);
        loadButton.addActionListener(this);
        loadButton.setForeground(Color.black);

        JButton quitAppButton = new JButton("Quit Pet Shelter Application");
        quitAppButton.setBounds(BUTTON_POSITION, 360, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(quitAppButton);
        quitAppButton.setActionCommand(QUIT_ACTION);
        quitAppButton.addActionListener(this);
        quitAppButton.setForeground(Color.black);
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
