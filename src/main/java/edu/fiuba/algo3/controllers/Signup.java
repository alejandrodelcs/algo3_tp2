package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.exceptions.InvalidJSONArray;
import edu.fiuba.algo3.modelo.exceptions.InvalidPlayersName;
import edu.fiuba.algo3.modelo.player.Player;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javafx.scene.input.KeyEvent;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;


public class Signup {
    @FXML
    private TextField usernameField;
    @FXML
    private Button playButton;
    @FXML
    private void handleSignup() throws IOException {
        String username = usernameField.getText();

        if (username.isEmpty()) {
            usernameField.setStyle("-fx-border-color: red");
            usernameField.setPromptText("You must fill out your name first");
            System.out.println("You must fill out your name first");
        } else {
            try {
                Player player = new Player(username);
                App.algodefense.setPlayer(player);
                App.setRoot("board");
                System.out.println("Successful Register");
            } catch (InvalidPlayersName invalidPlayersName) {

                usernameField.setText("");
                usernameField.setStyle("-fx-border-color: red");
                usernameField.setPromptText("Name must be at least 6 characters long");
                System.out.println("Name must be at least 6 characters long");
            }

        }
    }

    @FXML
    private void handleEnterPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            playButton.fire();
        }
    }

}
