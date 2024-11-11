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

    @FXML
    private TextField nombreTxt;
    @FXML
    private TextField apellidosTxt;
    @FXML
    private TextField correoTxt;
    @FXML
    private PasswordField contrasenaTxt;
    @FXML
    private Button confirmarBtn;
    @FXML
    private Button volverBtn;

    private UsuarioDAO usuarioDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioDAO = new UsuarioDAO(HibernateUtil.getSessionFactory());
    }

    @FXML
    public void setVolverBtn(ActionEvent actionEvent) {
        HelloApplication.loadFXML("hello-view.fxml", "Inicio Sesión");
    }

    @FXML
    public void setConfirmarBtn(ActionEvent actionEvent) {

        String nombre = nombreTxt.getText();
        String apellidos = apellidosTxt.getText();
        String correo = correoTxt.getText();
        String contrasena = contrasenaTxt.getText();

        if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            System.out.println("Por favor, completa todos los campos.");
            return;
        }


        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellidos(apellidos);
        nuevoUsuario.setEmail(correo);
        nuevoUsuario.setContrasena(contrasena);


        usuarioDAO.save(nuevoUsuario);


        System.out.println("Usuario registrado correctamente.");

        HelloApplication.loadFXML("hello-view.fxml", "Inicio Sesión");
    }
}
