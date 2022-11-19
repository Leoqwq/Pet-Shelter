// Citations: https://docs.oracle.com/javase/tutorial/uiswing/;
//            https://stackoverflow.com;
//            https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html#zz-3.
//            https://javatpoint.com

package ui;

import model.Pet;
import model.PetNotAvailableException;
import model.PetNotFoundException;
import model.Shelter;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represents a simple text pet shelter application
public class PetShelterApp {
    private static final String JSON_STORE = "./data/shelter.json";

    private Shelter shelter;
    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Effects: Initializes the shelter, scanner JsonWriter and JsonReader objects.
    public PetShelterApp() {
        shelter = new Shelter();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        run();
    }

    // Effects: Repeatedly display the menu until the user enters '8' which represents the quit option.
    private void run() {
        int command;

        while (true) {
            displayMenu();
            command = input.nextInt();
            input.nextLine();

            if (command == 8) {
                System.out.println("Thank you for using pet shelter app!");
                break;
            } else {
                proceedCommand(command);
            }
            System.out.println("------------------------------------------------------------------------------");
        }
    }

    // Effects: Prints out the option menu.
    private void displayMenu() {
        System.out.println("Welcome to pet shelter, please select an option.");
        System.out.println("[1] Adopt pet");
        System.out.println("[2] Add Pet");
        System.out.println("[3] Mark a pet as available");
        System.out.println("[4] View a list of pet in the shelter");
        System.out.println("[5] View the number of pets in the shelter");
        System.out.println("[6] Save shelter to file");
        System.out.println("[7] Load shelter from file");
        System.out.println("[8] Quit");
        System.out.println("------------------------------------------------------------------------------");
    }

    // Requires: command is an integer between 1 and 7.
    // Modifies: this
    // Effects: Adopts the pet with given name when the user enters '1';
    //          Adds the pet to the shelter with given information when the user enters '2';
    //          Marks a pet to be available with given name when the user enters '3';
    //          Otherwise, call proceedCommand2() for option 4 - 7;
    private void proceedCommand(int command) {
        if (command == 1) {
            System.out.print("Please enter the name of the pet that you want to adopt: ");
            String name = input.nextLine();
            try {
                shelter.adopt(name);
            } catch (PetNotAvailableException e) {
                System.out.println(e.getMessage());
            } catch (PetNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else if (command == 2) {
            Pet pet = askForPetInfo();
            shelter.addPet(pet);
            System.out.println(pet.getName() + " has been added to the shelter successfully!");
        } else if (command == 3) {
            System.out.print("Please enter the name of the pet that you want to mark as available: ");
            String name = input.nextLine();
            System.out.println(shelter.markPetAsAvailable(name));
        } else {
            proceedCommand2(command);
        }
    }

    // Requires: command is an integer between 4 and 7
    // Effects: Prints out a list of pets in the shelter when the user enters '4';
    //          Prints out the shelter information when the user enters '5';
    //          Saves the shelter to file when the user enters '6';
    //          Loads the shelter from file when the user enters '7'.
    private void proceedCommand2(int command) {
        if (command == 4) {
            System.out.println(shelter.viewTheListOfPet());
        } else if (command == 5) {
            System.out.println(shelter.getShelterInfo());
        } else if (command == 6) {
            saveShelter();
        } else if (command == 7) {
            loadShelter();
        }
    }

    // Effects: Returns a new Pet with the given information
    private Pet askForPetInfo() {
        System.out.print("Please enter the name of the pet: ");
        String name = input.nextLine();
        System.out.print("Please enter the species of the pet: ");
        String species = input.nextLine();
        System.out.print("Please enter the sex of the pet(male or female): ");
        String sex = input.nextLine();
        System.out.print("Please enter the age of the pet: ");
        int age = input.nextInt();
        System.out.print("Is it ready for adoption?(true or false) ");
        boolean isAvailable = input.nextBoolean();

        return new Pet(name, species, sex, age, isAvailable);
    }

    // EFFECTS: saves the workroom to file
    private void saveShelter() {
        try {
            jsonWriter.open();
            jsonWriter.write(shelter);
            jsonWriter.close();
            System.out.println("Saved shelter to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadShelter() {
        try {
            shelter = jsonReader.read();
            System.out.println("Loaded shelter from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
