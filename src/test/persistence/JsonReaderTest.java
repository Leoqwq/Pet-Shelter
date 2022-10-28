// Citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistence;

import model.Pet;
import model.Shelter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Shelter shelter = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyShelter.json");
        try {
            Shelter shelter = reader.read();
            assertEquals(0, shelter.getNumOfPetInShelter());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            Shelter shelter = reader.read();
            List<Pet> pets = shelter.getListOfPets();
            assertEquals(2, pets.size());
            checkPet("Tom", "cat", "male", 3, true, pets.get(0));
            checkPet("Jerry", "mouse", "male", 2, false, pets.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
