package ec.edu.espol.controllers;

import ec.edu.espol.grupo_10.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecondaryController {

    @FXML
    private Button secondaryButton;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}