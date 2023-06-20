package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class Board {
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("board");
    }
}
