package edu.fiuba.algo3.modelo.parser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class JSONreader {

    public JSONObject getObject(String fileName, String fileRelativeSource) {
        JSONObject error = new JSONObject();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileRelativeSource));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject mapaObject = (JSONObject) jsonObject.get(fileName);
            return mapaObject;

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ParseException e) {
        }
        return error;
    }
}
