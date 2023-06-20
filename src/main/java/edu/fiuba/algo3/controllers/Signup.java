package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.player.Player;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

import java.io.IOException;


public class Signup {
    @FXML
    private TextField usernameField;



    @FXML
    private void handleSignup() throws IOException {
        String username = usernameField.getText();
        if (username.isEmpty()) {
            System.out.println("Debes completar el nombre de usuario antes de registrarte.");
        } else {
            Player player = new Player(username);
            App.algodefense.setPlayer(player);
            App.setRoot("board");
            System.out.println("Registro exitoso");
        }
    }

}
