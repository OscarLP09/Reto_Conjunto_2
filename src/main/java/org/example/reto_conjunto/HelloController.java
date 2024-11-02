package org.example.reto_conjunto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField usuarioTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private Button registerBtn;
    @FXML
    private Button loginBtn;

    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        System.exit(0);
    }
}
