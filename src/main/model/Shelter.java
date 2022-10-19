package model;

import java.util.ArrayList;

// Represents a list of pets that are in the shelter
public class Shelter {
    private ArrayList<Pet> listOfPet;
    private int numOfAdopted;

    // EFFECTS: Creates a new listOfPet and set the initial value of numOfAdopted to 0.
    public Shelter() {
        listOfPet = new ArrayList<Pet>();
        numOfAdopted = 0;
    }

    // MODIFIES: this
    // EFFECTS: Removes a pet from listOfPet with given name in the parameter, increases numOfAdopted by 1 and
    //          returns a string represents successful adoption; if the pet is not found in the list,
    //          do nothing and returns a string represents pet not found.
    public String adopt(String name) {
        for (Pet pet : listOfPet) {
            if (pet.getName().equals(name)) {
                if (pet.getIsAvailable()) {
                    listOfPet.remove(pet);
                    numOfAdopted++;
                    return pet.getName() + " has been successfully adopted!";
                } else {
                    return "Sorry, " + pet.getName() + " is currently unavailable for adoption...";
                }
            }
        }
        return "Sorry, we couldn't find " + name + " in the shelter, please try again...";
    }

    // MODIFIES: this
    // EFFECTS: Adds a pet to listOfPet
    public void addPet(Pet pet) {
        listOfPet.add(pet);
    }

    // MODIFIES: pet
    // EFFECTS: Calls markAsAvailable() for a given pet and returns a string represents successfully marked;
    //          If the pet is not found in the list, do nothing and returns a string represents not found.
    public String markPetAsAvailable(String name) {
        for (Pet pet : listOfPet) {
            if (pet.getName().equals(name)) {
                pet.markAsAvailable();
                return name + " has been successfully marked as available!";
            }
        }
        return "Sorry, we couldn't find " + name + " in the shelter, please try again...";
    }

    // EFFECTS: returns "The shelter is empty..." if there is no pet in listOfPet; Otherwise returns a string
    //          represents information of every pet in the list.
    public String viewTheListOfPet() {
        if (listOfPet.size() == 0) {
            return "The shelter is empty...";
        } else {
            String output = "List of pets: ";
            for (Pet pet : listOfPet) {
                output += "\n" + pet.getPetInfo();
            }
            return output;
        }
    }

    // EFFECTS: returns a string that shows the number of pets in the shelter and the number of adopted pets.
    public String getShelterInfo() {
        return "The number of pets in the shelter: " + listOfPet.size()
                + "\nThe number of adopted pets: " + getNumOfAdopted();
    }

    public ArrayList<Pet> getListOfPets() {
        return listOfPet;
    }

    public int getNumOfAdopted() {
        return numOfAdopted;
    }
}
