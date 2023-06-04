package edu.fiuba.algo3.modelo.parser;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EnemiesParser {
    public JSONArray getArray(String fileName, String fileRelativeSource) {
        JSONArray error = new JSONArray();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileRelativeSource));
            JSONArray jsonArray = (JSONArray) obj;
            //JSONArray strategyObject = jsonObject.get();
            return jsonArray;

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ParseException e) {
        }
        return error;
    }
}
