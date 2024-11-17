/**
 * Controlador principal de la aplicación que gestiona la pantalla de inicio de sesión y la navegación al registro.
 * Implementa la interfaz {@link Initializable} para inicializar los elementos de la UI al cargar la vista.
 * Proporciona funcionalidades de inicio de sesión y redirección al registro de nuevos usuarios.
 */
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
import lombok.Getter;
import lombok.Setter;
import org.example.reto_conjunto.dao.UsuarioDAO;
import org.example.reto_conjunto.models.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    /**
     * Campo de texto para ingresar el correo electrónico del usuario.
     */
    @FXML
    private TextField usuarioTxt;

    /**
     * Campo de texto para ingresar la contraseña del usuario.
     */
    @FXML
    private PasswordField passwordTxt;

    /**
     * Botón para redirigir al formulario de registro de nuevos usuarios.
     */
    @FXML
    private Button registerBtn;

    /**
     * Botón para realizar la acción de inicio de sesión.
     */
    @FXML
    private Button loginBtn;

    /**
     * Objeto de acceso a datos para gestionar la validación de usuarios en la base de datos.
     */
    private UsuarioDAO usuarioDAO;

    /**
     * Método de inicialización que se ejecuta al cargar la vista.
     * Inicializa el objeto {@link UsuarioDAO} con la sesión de Hibernate.
     *
     * @param url               la URL de inicialización.
     * @param resourceBundle    el recurso asociado a la inicialización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioDAO = new UsuarioDAO(HibernateUtil.getSessionFactory());
    }

    /**
     * Clase interna para gestionar la sesión del usuario.
     */
    public class Sesion {
        /**
         * ID del usuario que ha iniciado sesión.
         */
        @Setter
        @Getter
        private static int usuarioId;
    }

    /**
     * Maneja el evento de clic en el botón de registro y redirige a la vista de registro de usuario.
     *
     * @param actionEvent el evento de acción que desencadena el método.
     */
    @FXML
    public void setRegisterBtn(ActionEvent actionEvent) {
        HelloApplication.loadFXML("Register_view.fxml", "Registro");
    }

    /**
     * Maneja el evento de clic en el botón de inicio de sesión.
     * Valida las credenciales del usuario, muestra alertas correspondientes y, en caso de éxito,
     * redirige a la pantalla de gestión de películas.
     *
     * @param actionEvent el evento de acción que desencadena el método.
     */
    @FXML
    public void setLoginBtn(ActionEvent actionEvent) {
        String email = usuarioTxt.getText();
        String password = passwordTxt.getText();

        // Valida las credenciales del usuario
        Usuario user = usuarioDAO.validateUsuario(email, password);

        if (user != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Correcto");
            alert.setHeaderText(null);
            alert.setContentText("Inicio de sesión exitoso. ¡Bienvenido!");
            alert.showAndWait();

            try {
                // Carga la vista de gestión de películas al iniciar sesión correctamente
                FXMLLoader loader = new FXMLLoader(getClass().getResource("peliculas-controller.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) usuarioTxt.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de Carga");
                alerta.setHeaderText(null);
                alerta.setContentText("No se pudo cargar la nueva pantalla.");
                alerta.showAndWait();
            }
        } else {
            // Muestra una alerta de error si las credenciales no son válidas
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("El email o la contraseña son incorrectos. Inténtalo de nuevo.");
            alert.showAndWait();
        }
    }
}
