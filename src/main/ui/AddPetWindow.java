// Citations: https://docs.oracle.com/javase/tutorial/uiswing/;
//            https://stackoverflow.com;
//            https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html#zz-3.
//            https://javatpoint.com

package ui;

import model.Pet;
import model.Shelter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Represents a window that allows the user to add a pet to the shelter by entering pet's information.
public class AddPetWindow extends JFrame implements ActionListener {
    private JTextField nameField;
    private JTextField speciesField;
    private JTextField sexField;
    private JTextField ageField;
    private JTextField isAvailableField;

    private Shelter shelter;
    private static final String FINISH_ACTION = "FINISH_ACTION";

    // Effects: Creates a new AddPet window that has all the labels, buttons and the background image set up.
    public AddPetWindow(Shelter shelter) {
        super("Add a pet to the shelter");
        this.shelter = shelter;
        this.setWindow();
        this.setBackgroundImage();
        this.setLabelsFieldsButtons();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // Modifies: this
    // Effects: Creates a new window.
    private void setWindow() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 400));
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
    private void setLabelsFieldsButtons() {
        JLabel nameFieldLabel = new JLabel("Enter name: ");
        nameFieldLabel.setBounds(48, 40, 400, 20);
        add(nameFieldLabel);
        nameFieldLabel.setForeground(Color.darkGray);

        nameField = new JTextField(30);
        nameField.setBounds(150, 40, 300, 20);
        add(nameField);

        JLabel speciesFieldLabel = new JLabel("Enter species: ");
        speciesFieldLabel.setBounds(48, 70, 400, 20);
        add(speciesFieldLabel);
        speciesFieldLabel.setForeground(Color.darkGray);

        speciesField = new JTextField(30);
        speciesField.setBounds(150, 70,300,20);
        add(speciesField);

        JLabel sexFieldLabel = new JLabel("Enter sex(enter male or female): ");
        sexFieldLabel.setBounds(48,100,400,20);
        add(sexFieldLabel);
        sexFieldLabel.setForeground(Color.darkGray);

        sexField = new JTextField(30);
        sexField.setBounds(250,100,300,20);
        add(sexField);

        JLabel ageFieldLabel = new JLabel("Enter age: ");
        ageFieldLabel.setBounds(48,130,400,20);
        add(ageFieldLabel);
        ageFieldLabel.setForeground(Color.darkGray);

        ageField = new JTextField(30);
        ageField.setBounds(150,130,300,20);
        add(ageField);

        JLabel isAvailableFieldLabel = new JLabel("Is the pet available for adoption?(enter true or false) ");
        isAvailableFieldLabel.setBounds(48,160,400,20);
        add(isAvailableFieldLabel);
        isAvailableFieldLabel.setForeground(Color.darkGray);

        isAvailableField = new JTextField(30);
        isAvailableField.setBounds(48,190,300,20);
        add(isAvailableField);

        JButton finishButton = new JButton("Finish");
        finishButton.setBounds(310,300,100,20);
        add(finishButton);
        finishButton.setActionCommand(FINISH_ACTION);
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.darkGray);
    }

    // Modifies: this
    // Effects: Add the pet to the shelter when the user click the 'finish' button.
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(FINISH_ACTION)) {
            String name = nameField.getText();
            String species = speciesField.getText();
            String sex = sexField.getText();
            int age = Integer.parseInt(ageField.getText());
            boolean isAvailable = Boolean.parseBoolean(isAvailableField.getText());
            shelter.addPet(new Pet(name, species, sex, age, isAvailable));
            dispose();
            JOptionPane.showMessageDialog(null, name
                    + " has been added to the shelter successfully!");
        }
    }
}
