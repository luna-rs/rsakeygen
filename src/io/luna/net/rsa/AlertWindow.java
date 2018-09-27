package io.luna.net.rsa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * A Javafx application that opens an alert window detailing the result of the key computation.
 *
 * @author lare96 <http://github.com/lare96>
 */
public final class AlertWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Dummy scene, never actually displayed.
        primaryStage.setScene(new Scene(new HBox(), 300, 300));

        List<String> parameters = getParameters().getRaw();
        if (parameters.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "RSA Keys have successfully been generated.", ButtonType.OK);
            alert.setTitle("RSAKeyGen");
            alert.setHeaderText("Success!");
            alert.showAndWait();
        } else {
            writeError(parameters.get(0));

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "RSA Keys could not be generated. Please see log.err for more info.",
                    ButtonType.OK);
            alert.setTitle("RSAKeyGen");
            alert.setHeaderText("Warning!");
            alert.showAndWait();
        }
    }

    /**
     * Writes an error to the error log.
     *
     * @param e The error to write.
     */
    private void writeError(String e) {
        Path errorLog = Paths.get("log.err");
        try {
            Files.deleteIfExists(errorLog);
            Files.write(errorLog, e.getBytes());
        } catch (IOException ignored) {

        }
    }
}