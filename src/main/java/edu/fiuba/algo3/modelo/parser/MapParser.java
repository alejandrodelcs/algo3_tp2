package edu.fiuba.algo3.modelo.parser;
import edu.fiuba.algo3.modelo.exceptions.*;
import edu.fiuba.algo3.modelo.gameboard.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;


public class MapParser {
    public Plot[][] plots;
    private String fileName;
    private String fileRelativeSource;

    public MapParser(String file){
        FileHandler fileHandler = new FileHandler(file);
        this.fileName = "Mapa";
        this.fileRelativeSource = file;
    }

    public JSONObject getObject() {
        JSONObject error = new JSONObject();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileRelativeSource));
            if(obj instanceof JSONArray){
                throw new InvalidJSONObject();
            }
            JSONObject jsonObject = (JSONObject) obj;
            if(!jsonObject.containsKey("Mapa")){
                throw new InvalidMapFile();
            }
            return (JSONObject) jsonObject.get(fileName);
        } catch (ParseException | IOException ignored) {
        }
        return error;
    }

    public GameBoard initializeMap(){

        MapParser reader = new MapParser(fileRelativeSource);
        JSONObject mapaJsonObject =  reader.getObject();
        plots = new Plot[mapaJsonObject.keySet().size()][mapaJsonObject.values().size()];

        for (int i = 1; i <= mapaJsonObject.size(); i++) {
            String key = String.valueOf(i);
            JSONArray rowArray = (JSONArray) mapaJsonObject.get(key);
            int j = 0;
            for (Object element : rowArray) {
                String value = element.toString();
                switch (value) {
                    case "Tierra":
                        plots[i - 1][j] = new Dirt();
                        break;
                    case "Pasarela":
                        if(j == (mapaJsonObject.size()-1)){
                            plots[i - 1][j] = new FinishLine();
                        }
                        else{
                            plots[i - 1][j] = new Path();
                        }
                        break;
                    case "Rocoso":
                        plots[i - 1][j] = new Stone();
                        break;
                    default :
                        throw new InvalidPlot();
                }
                j++;
            }
        }
        return new GameBoard(plots);
    }
}
