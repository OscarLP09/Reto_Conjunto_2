/**
 * Controlador para gestionar el proceso de registro de usuarios en la aplicación.
 * Implementa la interfaz {@link Initializable} para inicializar los elementos de la UI al cargar la vista.
 * Contiene métodos para manejar la interacción con la interfaz gráfica y gestionar la creación de nuevos usuarios.
 */
package org.example.reto_conjunto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.reto_conjunto.dao.UsuarioDAO;
import org.example.reto_conjunto.models.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    /**
     * Campo de texto para ingresar el nombre del usuario.
     */
    @FXML
    private TextField nombreTxt;

    /**
     * Campo de texto para ingresar los apellidos del usuario.
     */
    @FXML
    private TextField apellidosTxt;

    /**
     * Campo de texto para ingresar el correo electrónico del usuario.
     */
    @FXML
    private TextField correoTxt;

    /**
     * Campo de texto para ingresar la contraseña del usuario.
     */
    @FXML
    private PasswordField contrasenaTxt;

    /**
     * Botón para confirmar el registro de un nuevo usuario.
     */
    @FXML
    private Button confirmarBtn;

    /**
     * Botón para volver a la pantalla de inicio de sesión.
     */
    @FXML
    private Button volverBtn;

    /**
     * Objeto para acceder y manipular los datos de usuario en la base de datos.
     */
    private UsuarioDAO usuarioDAO;

    /**
     * Método de inicialización que se ejecuta al cargar la vista.
     * Inicializa la instancia de {@link UsuarioDAO} con la sesión de Hibernate.
     *
     * @param url               la URL de inicialización.
     * @param resourceBundle    el recurso asociado a la inicialización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioDAO = new UsuarioDAO(HibernateUtil.getSessionFactory());
    }

    /**
     * Método manejador para el evento de clic en el botón "Volver".
     * Carga la vista de inicio de sesión de la aplicación.
     *
     * @param actionEvent el evento de acción que desencadena el método.
     */
    @FXML
    public void setVolverBtn(ActionEvent actionEvent) {
        HelloApplication.loadFXML("hello-view.fxml", "Inicio Sesión");
    }

    /**
     * Método manejador para el evento de clic en el botón "Confirmar".
     * Valida los campos de entrada y registra un nuevo usuario si los datos son válidos.
     * Guarda el nuevo usuario en la base de datos y redirige a la pantalla de inicio de sesión.
     *
     * @param actionEvent el evento de acción que desencadena el método.
     */
    @FXML
    public void setConfirmarBtn(ActionEvent actionEvent) {
        String nombre = nombreTxt.getText();
        String apellidos = apellidosTxt.getText();
        String correo = correoTxt.getText();
        String contrasena = contrasenaTxt.getText();

        // Verifica si algún campo está vacío
        if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            System.out.println("Por favor, completa todos los campos.");
            return;
        }

        // Crea y guarda un nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellidos(apellidos);
        nuevoUsuario.setEmail(correo);
        nuevoUsuario.setContrasena(contrasena);

        usuarioDAO.save(nuevoUsuario);

        System.out.println("Usuario registrado correctamente.");

        // Carga la vista de inicio de sesión
        HelloApplication.loadFXML("hello-view.fxml", "Inicio Sesión");
    }
}
