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
        this.setLabel(new JLabel("Enter name: "), 48, 40, 400, 20);
        this.setTextField(nameField,150, 40, 300, 20);

        this.setLabel(new JLabel("Enter species: "), 48, 70, 400, 20);
        this.setTextField(speciesField, 150, 70, 200, 20);

        this.setLabel(new JLabel("Enter sex(enter male or female): "), 48, 100, 400, 20);
        this.setTextField(sexField, 250, 100, 300, 20);

        this.setLabel(new JLabel("Enter age: "), 48, 130, 400, 20);
        this.setTextField(ageField, 150, 130, 300, 20);

        this.setLabel(new JLabel("Is the pet available for adoption?(enter true or false) "),
                48, 160, 400, 20);
        this.setTextField(isAvailableField, 48, 190, 300, 20);

        JButton finishButton = new JButton("Finish");
        finishButton.setBounds(310,300,100,20);
        add(finishButton);
        finishButton.setActionCommand(FINISH_ACTION);
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.darkGray);
    }

    // Modifies: this
    // Effects: A helper method that adds a label to the window with given coordinate and size
    public void setLabel(JLabel label, int x, int y, int width, int height) {
        label.setBounds(x, y, width, height);
        add(label);
        label.setForeground(Color.darkGray);
    }

    // Modifies: this
    // Effects: A helper method that adds a text field to the window with given coordinate and size
    public void setTextField(JTextField tf, int x, int y, int width, int height) {
        tf = new JTextField(30);
        tf.setBounds(x, y, width, height);
        add(tf);
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
