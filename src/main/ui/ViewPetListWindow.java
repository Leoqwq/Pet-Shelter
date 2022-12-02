// Citations: https://docs.oracle.com/javase/tutorial/uiswing/;
//            https://stackoverflow.com;
//            https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html#zz-3.
//            https://javatpoint.com

package ui;

import model.Event;
import model.EventLog;
import model.Pet;
import model.Shelter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a ViewPetList window that has the information of all pets in the shelter; as well as a button
// that allows the user to mark a selected pet as available and a button to view the total number of pets in
// the shelter.
public class ViewPetListWindow extends JFrame implements ActionListener {
    private DefaultTableModel tableModel;
    private JTable table;
    private Shelter shelter;
    private static final String MARK_AS_AVAILABLE_ACTION = "MARK_AS_AVAILABLE_ACTION";
    private static final String VIEW_PET_NUMBER_ACTION = "VIEW_PET_NUMBER_ACTION";


    // Effects: Creates a new ViewPetList window that has a table of pets in the shelter as well as
    // labels and buttons set up.
    public ViewPetListWindow(Shelter shelter) {
        this.shelter = shelter;
        final String[] columnLabels = new String[] {
                "NO.",
                "Name",
                "Species",
                "Sex",
                "Age",
                "Is Available"
        };
        tableModel = new DefaultTableModel(null, columnLabels) {};
        table = new JTable(tableModel);
        this.populateTableRows();

        add(new JScrollPane(table));
        this.setButtons();
        setTitle("Pet List");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // Modifies: this
    // Effects: Display a table of pets in the shelter with their information.
    private void populateTableRows() {
        for (int i = 0; i < shelter.getListOfPets().size(); i++) {
            Pet pet = shelter.getListOfPets().get(i);
            Object[] tableRow = new Object[] {
                    i + 1, // index column
                    pet.getName(), // name column
                    pet.getSpecies(), // species column
                    pet.getSex(), // sex column
                    pet.getAge(), // age column
                    pet.getIsAvailable(), // isAvailable column
            };
            tableModel.addRow(tableRow);
        }
    }

    // Modifies: this
    // Effects: Sets up 2 buttons; one allows the user to mark a selected pet as available for adoption and one to view
    //          the total number of pets in the shelter.
    public void setButtons() {
        JButton addItemButton = new JButton(("Mark the selected pet as available for adoption"));
        add(addItemButton);
        addItemButton.setActionCommand(MARK_AS_AVAILABLE_ACTION);
        addItemButton.addActionListener(this);
        addItemButton.setForeground(Color.darkGray);

        JButton markItemAsDoneButton = new JButton("View the total number of pets in the shelter");
        add(markItemAsDoneButton);
        markItemAsDoneButton.setActionCommand(VIEW_PET_NUMBER_ACTION);
        markItemAsDoneButton.addActionListener(this);
        markItemAsDoneButton.setForeground(Color.darkGray);
    }

    // Modifies: this
    // Effects: Marks a selected pet in the table as available for adoption when the user click the
    //          "Mark the selected pet as available for adoption" button, throw an error message when
    //          nothing is being selected;
    //          Displays the total number of pets in the shelter when the user click the
    //          "View the total number of pets in the shelter" button.
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.equals(MARK_AS_AVAILABLE_ACTION)) {
            int selectedRowIndex = table.getSelectedRow();
            if (selectedRowIndex == -1) {
                JOptionPane.showMessageDialog(null,
                        "Please select a pet to mark as available.");
                return;
            }

            shelter.markPetAsAvailable(shelter.getListOfPets().get(selectedRowIndex).getName());
            table.setValueAt(true, selectedRowIndex, 5);
        } else if (action.equals(VIEW_PET_NUMBER_ACTION)) {
            EventLog.getInstance().logEvent(new Event("Viewed number of pet in the shelter."));
            JOptionPane.showMessageDialog(null,
                    "The total number of pets in the shelter is: " + shelter.getNumOfPetInShelter());
        }
    }
}

