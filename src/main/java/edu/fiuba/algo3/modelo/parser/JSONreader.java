package edu.fiuba.algo3.modelo.parser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class JSONreader {
    public JSONObject getObject() {
        JSONObject error = new JSONObject();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("C:\\Users\\ruymo\\uba\\ALGO3\\TP2\\algo3_tp2\\src\\main\\java\\edu\\fiuba\\algo3\\modelo\\Parser\\mapa.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject mapaObject = (JSONObject) jsonObject.get("Mapa");
            //System.out.print(mapaObject.toString());
            /*
            for (Object key : mapaObject.keySet()) {
                JSONArray rowArray = (JSONArray) mapaObject.get(key);
                for (Object element : rowArray) {
                    System.out.print(element.toString());
                }
                System.out.println();
            }

             */
            return mapaObject;

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ParseException e) {
        }
        return error;
    }
}