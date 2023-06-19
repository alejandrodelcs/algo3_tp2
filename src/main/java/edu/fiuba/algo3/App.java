package edu.fiuba.algo3;
import edu.fiuba.algo3.controllers.Signup;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



/**
 * JavaFX App
 */
public class App extends Application {

    /*@Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }*/


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu.fiuba.algo3/signup.fxml"));
        Parent root = loader.load();

        // Obtener el controlador
        Signup signupController = loader.getController();

        // Configurar la escena
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/edu.fiuba.algo3/styles.css").toExternalForm());

        // Configurar el escenario
        primaryStage.setTitle("Signup");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}