package org.example.reto_conjunto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.reto_conjunto.dao.UsuarioDAO;
import org.example.reto_conjunto.models.Usuario;

import java.io.IOException;
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

    private UsuarioDAO usuarioDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
          usuarioDAO = new UsuarioDAO(HibernateUtil.getSessionFactory());
    }

    @FXML
    public void setRegisterBtn(ActionEvent actionEvent) {
        HelloApplication.loadFXML("Register_view.fxml", "Registro");
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


            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("peliculas-controller.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) usuarioTxt.getScene().getWindow(); // Obtén la ventana actual
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Manejo básico de excepciones
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de Carga");
                alerta.setHeaderText(null);
                alerta.setContentText("No se pudo cargar la nueva pantalla.");
                alerta.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("El email o la contraseña son incorrectos. Inténtalo de nuevo.");
            alert.showAndWait();
        }
    }

}
