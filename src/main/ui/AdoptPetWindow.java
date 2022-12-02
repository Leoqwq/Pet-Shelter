// Citations: https://docs.oracle.com/javase/tutorial/uiswing/;
//            https://stackoverflow.com;
//            https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html#zz-3.
//            https://javatpoint.com

package ui;

import model.PetNotAvailableException;
import model.PetNotFoundException;
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

// Represents a window that allows user to adopt a pet in the shelter by entering the pet's name.
public class AdoptPetWindow extends JFrame implements ActionListener {
    private JTextField nameField;
    private static final String FINISH_ACTION = "FINISH_ACTION";

    private Shelter shelter;

    // Creates a new AdoptPet window that has all the labels, buttons and background image set up.
    public AdoptPetWindow(Shelter shelter) {
        super("Adopt Pet");
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
    // Effects: Creates a new window
    private void setWindow() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 300));
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
    private void setLabelsFieldsButtons() {
        JLabel nameFieldLabel = new JLabel("Enter name of the pet that you want to adopt: ");
        nameFieldLabel.setBounds(48, 40, 400, 20);
        add(nameFieldLabel);
        nameFieldLabel.setForeground(Color.darkGray);

        nameField = new JTextField(30);
        nameField.setBounds(50, 70, 300, 20);
        add(nameField);

        JButton finishButton = new JButton("Finish");
        finishButton.setBounds(310,210,100,20);
        add(finishButton);
        finishButton.setActionCommand(FINISH_ACTION);
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.darkGray);
    }

    // Modifies: this
    // Effects: Adopt a pet in the shelter with given name when the user click the 'finish' button.
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.equals(FINISH_ACTION)) {
            try {
                shelter.adopt(nameField.getText());
                JOptionPane.showMessageDialog(null, nameField.getText()
                        + " has been adopted successfully!");
            } catch (PetNotAvailableException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            } catch (PetNotFoundException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
            dispose();
        }
    }
}
