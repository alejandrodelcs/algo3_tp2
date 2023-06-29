module edu.fiuba.algo3 {
    requires javafx.controls;
    requires java.datatransfer;
    requires java.desktop;
    requires json.simple;
    requires javafx.fxml;
    requires javafx.media;

    exports edu.fiuba.algo3 to javafx.fxml, javafx.graphics;
    exports edu.fiuba.algo3.modelo;
    opens edu.fiuba.algo3.controllers to javafx.fxml;
}