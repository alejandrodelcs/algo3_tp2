package edu.fiuba.algo3;
import edu.fiuba.algo3.controllers.Signup;
import edu.fiuba.algo3.modelo.AlgoDefense;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    public static final AlgoDefense algodefense = new AlgoDefense();
    private static Scene scene;

   @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(loadFXML("signup"));
        scene.getStylesheets().add(getClass().getResource("/edu.fiuba.algo3/styles.css").toExternalForm());
        primaryStage.setTitle("Sign-up");
        primaryStage.setScene(scene);
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