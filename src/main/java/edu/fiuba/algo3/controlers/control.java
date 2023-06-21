package edu.fiuba.algo3.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class control implements Initializable {
    protected boolean open = false;
    public abstract void initialize(URL url, ResourceBundle resourceBundle);


}