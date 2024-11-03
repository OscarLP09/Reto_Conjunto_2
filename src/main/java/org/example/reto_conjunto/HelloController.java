package org.example.reto_conjunto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.reto_conjunto.dao.UsuarioDAO;
import org.example.reto_conjunto.models.Usuario;
import org.hibernate.SessionFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField usuarioTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button loginBtn;

    private UsuarioDAO usuarioDAO;

    public HelloController() {

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void setCancelBtn(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void setLoginBtn(ActionEvent actionEvent) {
        String email = usuarioTxt.getText();
        String password = passwordTxt.getText();

        Usuario user = usuarioDAO.validateUsuario(email, password);

        if (user != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Correcto");
            alert.setHeaderText(null);
            alert.setContentText("Inicio de sesión exitoso. ¡Bienvenido!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("El email o la contraseña son incorrectos. Inténtalo de nuevo.");
            alert.showAndWait();
        }
    }
}
