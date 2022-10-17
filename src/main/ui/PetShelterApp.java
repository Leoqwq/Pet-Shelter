package ui;

import model.Pet;
import model.Shelter;
import java.util.Scanner;

public class PetShelterApp {
    private Shelter shelter;
    private Scanner input;

    public PetShelterApp() {
        shelter = new Shelter();
        input = new Scanner(System.in);
        run();
    }

    private void run() {
        int command;

        while (true) {
            displayMenu();
            command = input.nextInt();
            input.nextLine();

            if (command == 6) {
                System.out.println("Thank you for using pet shelter app!");
                break;
            } else {
                proceedCommand(command);
            }
            System.out.println("------------------------------------------------------------------------------");
        }
    }

    private void displayMenu() {
        System.out.println("Welcome to pet shelter, please select an option.");
        System.out.println("[1] Adopt pet");
        System.out.println("[2] Add Pet");
        System.out.println("[3] Mark a pet as available");
        System.out.println("[4] View a list of pet in the shelter");
        System.out.println("[5] View the number of pets in the shelter and the number of adopted pets");
        System.out.println("[6] Quit");
        System.out.println("------------------------------------------------------------------------------");
    }

    private void proceedCommand(int command) {
        if (command == 1) {
            System.out.print("Please enter the name of the pet that you want to adopt: ");
            String name = input.nextLine();
            System.out.println(shelter.adopt(name));
        } else if (command == 2) {
            Pet pet = askForPetInfo();
            shelter.addPet(pet);
            System.out.println(pet.getName() + " has been added to the shelter successfully!");
        } else if (command == 3) {
            System.out.print("Please enter the name of the pet that you want to mark as available: ");
            String name = input.nextLine();
            System.out.println(shelter.markPetAsAvailable(name));
        } else if (command == 4) {
            System.out.println(shelter.viewTheListOfPet());
        } else if (command == 5) {
            System.out.println(shelter.getShelterInfo());
        }
    }

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
}
