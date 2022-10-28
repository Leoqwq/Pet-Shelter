package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a pet with species, sex, age and color.
public class Pet implements Writable {
    private String name;    //color of the pet
    private String species;     // species of the pet
    private String sex;     // sex of the pet, either "Male" or "Female"
    private int age;     // age of the pet, age >= 0
    private boolean isAvailable;     // whether a pet is available for adoption or not


    // REQUIRES: age is non-negative, sex is either "male" or "female"
    // EFFECTS: name of the pet is set to color given in the parameter;
    //          sex of the pet is set to pet given in the parameter;
    //          age of the pet is set to age given in the parameter which is a non-negative integer;
    //          species of the pet is set to species given in the parameter;
    //          isAvailable is set to false by default
    public Pet(String name, String species, String sex, int age, boolean isAvailable) {
        this.name = name;
        this.species = species;
        this.sex = sex;
        this.age = age;
        this.isAvailable = isAvailable;
    }

    // MODIFIES: this
    // EFFECTS: Changes a pet to available for adoption if it is not available, otherwise do nothing.
    public void markAsAvailable() {
        isAvailable = true;
    }

    // EFFECTS: Returns a string that contains all the information about a pet
    public String getPetInfo() {
        if (isAvailable == true) {
            return name + " is a " + age + " years old " + sex + " " +  species + ". (available for adoption)";
        } else {
            return name + " is a " + age + " years old " + sex + " " + species + ". (unavailable for adoption)";
        }
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("species", species);
        json.put("sex", sex);
        json.put("age", age);
        json.put("isAvailable", isAvailable);
        return json;
    }
}
