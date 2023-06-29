package edu.fiuba.algo3.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class controler implements Initializable {


    public abstract void initialize(URL url, ResourceBundle resourceBundle);

    public Stage obtenerStageActual(Node nodo){
        return (Stage)(nodo.getScene().getWindow());
    }

}
