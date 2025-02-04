package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ShelterTest {
    Pet maple = new Pet("Maple", "dog", "male", 1, true);
    Pet tom = new Pet("Tom", "cat", "male", 3, true);
    Pet jerry = new Pet("Jerry", "mouse", "male", 2, false);

    Shelter testShelter1;
    Shelter testShelter2;

    @BeforeEach
    public void setup() {
        testShelter1 = new Shelter();
        testShelter2 = new Shelter();
        testShelter1.addPet(maple);
        testShelter1.addPet(tom);
        testShelter1.addPet(jerry);
    }

    @Test
    public void testConstructor() {
        assertEquals(3, testShelter1.getListOfPets().size());
        assertTrue(testShelter1.getListOfPets().contains(maple));
        assertTrue(testShelter1.getListOfPets().contains(tom));
        assertTrue(testShelter1.getListOfPets().contains(jerry));
    }

    @Test
    public void testAdopt() {
        // Test adopt available pet
        try {
            String result1 = testShelter1.adopt("Tom");
            assertEquals("Tom has been successfully adopted!", result1);
            assertEquals(2, testShelter1.getListOfPets().size());
        } catch (PetNotFoundException e) {
            fail();
        } catch (PetNotAvailableException e) {
            fail();
        }

        // Test adopt unavailable pet
        try {
            testShelter1.adopt("Jerry");
        } catch (PetNotAvailableException e) {
            assertEquals("Sorry, Jerry is currently unavailable for adoption...",
                    e.getMessage());
            assertEquals(2, testShelter1.getListOfPets().size());
        } catch (PetNotFoundException e) {
            fail();
        }

        // Test adopt pet that is not in the shelter
        try {
            testShelter2.adopt("Tom");
        } catch (PetNotFoundException e) {
            assertEquals("Sorry, we couldn't find the pet in the shelter", e.getMessage());
            assertEquals(0, testShelter2.getListOfPets().size());
        } catch (PetNotAvailableException e) {
            fail();
        }
    }

    @Test
    public void testAddPet() {
        testShelter2.addPet(tom);
        testShelter2.addPet(jerry);

        assertEquals(2, testShelter2.getListOfPets().size());
        assertTrue(testShelter2.getListOfPets().contains(tom));
        assertTrue(testShelter2.getListOfPets().contains(jerry));
    }

    @Test
    public void testMarkPetAsAvailable() {
        // Test mark an unavailable pet as available
        String result1 = testShelter1.markPetAsAvailable("Jerry");

        assertEquals("Jerry has been successfully marked as available!", result1);
        assertTrue(testShelter1.getListOfPets().get(2).getIsAvailable());

        // Test mark a pet that is not in the shelter
        String result2 = testShelter2.markPetAsAvailable("Jerry");
        assertEquals("Sorry, we couldn't find Jerry in the shelter, please try again...", result2);
    }

    @Test
    public void testViewTheListOfPet() {
        // Test view a list with 3 pets
        String result1 = testShelter1.viewTheListOfPet();
        String expected = "List of pets: " +
                "\nMaple is a 1 years old male dog. (available for adoption)" +
                "\nTom is a 3 years old male cat. (available for adoption)" +
                "\nJerry is a 2 years old male mouse. (unavailable for adoption)";

        assertEquals(expected, result1);

        // Test view an empty list of pets
        String result2 = testShelter2.viewTheListOfPet();

        assertEquals("The shelter is empty...", result2);
    }

    @Test
    public void testGetShelterInfo() {
        // Test get shelter info with 2 pets in the shelter and 1 adopted record
        String result1 = testShelter1.getShelterInfo();
        String expected1 = "The number of pets in the shelter: 3";

        assertEquals(expected1, result1);

        // Test get an empty shelter info
        String result2 = testShelter2.getShelterInfo();
        String expected2 = "The number of pets in the shelter: 0";

        assertEquals(expected2, result2);
    }

    @Test
    public void testGetListOfPets() {
        ArrayList<Pet> expected = new ArrayList<>();
        expected.add(maple);
        expected.add(tom);
        expected.add(jerry);

        assertEquals(expected, testShelter1.getListOfPets());
    }

}
