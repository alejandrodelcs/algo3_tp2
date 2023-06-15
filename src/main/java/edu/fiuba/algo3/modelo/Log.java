package edu.fiuba.algo3.modelo;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    String filePath;

    SimpleDateFormat dateFormat;
    String formattedDate;

    String appFile;

    public Log(String appFile) {
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        this.formattedDate = dateFormat.format(new Date());
        this.filePath = "src\\main\\java\\edu\\fiuba\\algo3\\modelo\\app.log";
        this.appFile = appFile;
    }


    public void logToFile(String message) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            String formatLog = "[" + lastID() + "] " + "[" + formattedDate + "]" + " ==> " + appFile + " : " + message;
            writer.write(formatLog);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String lastID(){
        File file = new File(this.filePath);
        int lastID = readLastID(file);
        return Integer.toString(lastID + 1);
    }

    private static int readLastID(File file) {
        int lastID = -1;
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("[") && line.contains("]")) {
                    String idString = line.substring(1, line.indexOf("]"));
                    if (idString.matches("\\d+")) {
                        lastID = Integer.parseInt(idString);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastID;
    }
}

