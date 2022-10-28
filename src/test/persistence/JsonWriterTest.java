// Citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistence;

import model.Pet;
import model.Shelter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            Shelter shelter = new Shelter();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Shelter shelter = new Shelter();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShelter.json");
            writer.open();
            writer.write(shelter);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyShelter.json");
            shelter = reader.read();
            assertEquals(0, shelter.getNumOfPetInShelter());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Shelter shelter = new Shelter();
            shelter.addPet(new Pet("Jerry", "mouse", "male", 2, false));
            shelter.addPet(new Pet("Tom", "cat", "male", 3, true));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShelter.json");
            writer.open();
            writer.write(shelter);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralShelter.json");
            shelter = reader.read();
            List<Pet> pets = shelter.getListOfPets();
            assertEquals(2, pets.size());
            checkPet("Jerry", "mouse", "male", 2, false, pets.get(0));
            checkPet("Tom", "cat", "male", 3, true, pets.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
