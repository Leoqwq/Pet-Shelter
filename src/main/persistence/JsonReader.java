// Citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistence;

import model.Pet;
import model.Shelter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads shelter from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads shelter from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Shelter read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShelter(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses shelter from JSON object and returns it
    private Shelter parseShelter(JSONObject jsonObject) {
        Shelter shelter = new Shelter();
        addPets(shelter, jsonObject);
        return shelter;
    }

    // MODIFIES: shelter
    // EFFECTS: parses pets from JSON object and adds them to shelter
    private void addPets(Shelter shelter, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("pets");
        for (Object json : jsonArray) {
            JSONObject nextPet = (JSONObject) json;
            addPet(shelter, nextPet);
        }
    }

    // MODIFIES: shelter
    // EFFECTS: parses pet from JSON object and adds it to shelter
    private void addPet(Shelter wr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String species = jsonObject.getString("species");
        String sex = jsonObject.getString("sex");
        int age = jsonObject.getInt("age");
        boolean isAvailable = jsonObject.getBoolean("isAvailable");
        Pet pet = new Pet(name, species, sex, age, isAvailable);
        wr.addPet(pet);
    }
}
