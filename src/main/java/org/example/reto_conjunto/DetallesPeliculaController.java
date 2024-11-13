package org.example.reto_conjunto;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.reto_conjunto.dao.CopiasDAO;
import org.example.reto_conjunto.models.PeliculasCopia;
import org.example.reto_conjunto.models.Copias;

import java.net.URL;
import java.util.ResourceBundle;

public class DetallesPeliculaController implements Initializable {

    @FXML
    private Label nom_peli;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField generoTxt;
    @FXML
    private TextField anhoTxt;
    @FXML
    private TextField directorTxt;
    @FXML
    private Spinner<Integer> cantidadSpinner;
    @FXML
    private ChoiceBox<String> estadoChoice;

    private CopiasDAO copiasDAO = new CopiasDAO(HibernateUtil.getSessionFactory());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Opciones de estado
        if (estadoChoice != null) {
            estadoChoice.getItems().addAll("disponible", "prestado", "en_mantenimiento", "no disponible");
        }

        // Desactivar los campos de texto que no se deben editar
        if (generoTxt != null) generoTxt.setEditable(false);
        if (anhoTxt != null) anhoTxt.setEditable(false);
        if (directorTxt != null) directorTxt.setEditable(false);

        // Inicializar el Spinner con un valor predeterminado
        if (cantidadSpinner != null) {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1); // Rango y valor inicial
            cantidadSpinner.setValueFactory(valueFactory);
        }
    }

    public void setPelicula(PeliculasCopia peliculaSeleccionada) {
        if (peliculaSeleccionada != null) {
            // Establecer los datos de la película en los campos de la vista
            if (nom_peli != null) nom_peli.setText(peliculaSeleccionada.getNombrePeli());
            if (generoTxt != null) generoTxt.setText(peliculaSeleccionada.getGenero());
            if (anhoTxt != null) anhoTxt.setText(String.valueOf(peliculaSeleccionada.getAnho()));
            if (directorTxt != null) directorTxt.setText(peliculaSeleccionada.getDirector());

            // Obtener el ID del usuario (suponiendo que tienes un método para obtener el usuario logueado)
            int usuarioId = HelloController.Sesion.getUsuarioId();

            // Obtener la copia específica para el usuario y película seleccionados
            Copias copia = copiasDAO.obtenerCopiaPorUsuarioYPelicula(usuarioId, peliculaSeleccionada.getId());

            // Establecer el estado y cantidad de la copia, si existe
            if (copia != null) {
                if (estadoChoice != null) estadoChoice.setValue(copia.getEstado());
                if (cantidadSpinner != null) cantidadSpinner.getValueFactory().setValue(copia.getCantidad());
            } else {
                // Configuración por defecto en caso de no encontrar una copia
                if (estadoChoice != null) estadoChoice.setValue("no disponible");
                if (cantidadSpinner != null) cantidadSpinner.getValueFactory().setValue(1);
            }

        }
    }
}
