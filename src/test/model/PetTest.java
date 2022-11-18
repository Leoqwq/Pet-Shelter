package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetTest {
    Pet testPet1;
    Pet testPet2;

    @BeforeEach
    public void setup() {
        testPet1 = new Pet("Tom", "cat", "male", 3, true);
        testPet2 = new Pet("Jerry", "mouse", "male", 2, false);
    }

    @Test
    public void testConstructor() {
        assertEquals("Tom", testPet1.getName());
        assertEquals("Tom", testPet1.getName());
        assertEquals("Tom", testPet1.getName());
        assertEquals("Tom", testPet1.getName());
        assertTrue(testPet1.getIsAvailable());
        assertFalse(testPet2.getIsAvailable());
    }

    @Test
    public void testMarkAsAvailable() {
        testPet1.markAsAvailable();
        testPet2.markAsAvailable();

        assertTrue(testPet1.getIsAvailable());
        assertTrue(testPet2.getIsAvailable());
    }

    @Test
    public void testGetPetInfo() {
        String info1 = "Tom is a 3 years old male cat. (available for adoption)";
        String info2 = "Jerry is a 2 years old male mouse. (unavailable for adoption)";

        assertEquals(info1, testPet1.getPetInfo());
        assertEquals(info2, testPet2.getPetInfo());
    }

    @Test
    public void testGetName() {
        assertEquals("Tom", testPet1.getName());
    }

    @Test
    public void testGetSpecies() {
        assertEquals("cat", testPet1.getSpecies());
    }

    @Test
    public void testGetSex() {
        assertEquals("male", testPet1.getSex());
    }

    @Test
    public void testGetAge() {
        assertEquals(3, testPet1.getAge());
    }

    @Test
    public void testGetIsAvailable() {
        assertTrue(testPet1.getIsAvailable());
    }


}
