package edu.fiuba.algo3;
import edu.fiuba.algo3.controllers.Signup;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;

import java.awt.*;
import javafx.scene.text.Font;
import java.io.IOException;
import java.util.Objects;


/**
 * JavaFX App
 */
public class App extends Application {

    public static final AlgoDefense algodefense = new AlgoDefense();
    private static Scene scene;

   @Override
    public void start(Stage primaryStage) throws Exception {
       Logger.get();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        scene = new Scene(loadFXML("signup"),bounds.getMaxX(), bounds.getMaxY());
        scene.getStylesheets().add(getClass().getResource("/edu.fiuba.algo3/styles.css").toExternalForm());
        primaryStage.setTitle("Sign-up");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/logo.png"))));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/edu.fiuba.algo3/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void main(String[] args) {
        launch();
    }

}