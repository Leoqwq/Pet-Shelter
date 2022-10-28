// Citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistence;

import model.Pet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPet(String name, String species, String sex, int age, boolean isAvailable, Pet pet) {
        assertEquals(name, pet.getName());
        assertEquals(species, pet.getSpecies());
        assertEquals(sex, pet.getSex());
        assertEquals(age, pet.getAge());
        assertEquals(isAvailable, pet.getIsAvailable());
    }
}
