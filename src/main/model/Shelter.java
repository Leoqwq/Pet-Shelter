package model;

import java.util.ArrayList;

// Represents a list of pets that are in the shelter
public class Shelter {
    private ArrayList<Pet> listOfPet;
    private int numOfAdopted;

    // REQUIRES:
    // EFFECTS:
    public Shelter() {
        listOfPet = new ArrayList<Pet>();
        numOfAdopted = 0;
    }

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

    public void addPet(Pet pet) {
        listOfPet.add(pet);
    }

    public String markPetAsAvailable(String name) {
        for (Pet pet : listOfPet) {
            if (pet.getName().equals(name)) {
                pet.markAsAvailable();
                return name + " has been successfully marked as available!";
            }
        }
        return "Sorry, we couldn't find " + name + " in the shelter, please try again...";
    }

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
