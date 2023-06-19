package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.player.Player;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;



public class Signup {
    @FXML
    private TextField usernameField;

    @FXML
    private Button signupButton;

    @FXML
    private void handleSignup() {
        String username = usernameField.getText();
        Player player = new Player(username);
        System.out.println("Registro exitoso");

    }
}
