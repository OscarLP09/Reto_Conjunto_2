package org.example.reto_conjunto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.reto_conjunto.dao.PeliculasDAO;
import org.example.reto_conjunto.models.Peliculas;
import org.example.reto_conjunto.models.PeliculasCopia;

import java.net.URL;
import java.util.ResourceBundle;

public class DetallesPeliculaController implements Initializable {

    @FXML
    private Label nom_peli;
    @FXML
    private Label generoTxt;
    @FXML
    private Label anhoTxt;
    @FXML
    private Label directorTxt;
    @FXML
    private TextArea descripcionTxt;
    @FXML
    private Spinner<Integer> cantidadSpinner;
    @FXML
    private ChoiceBox<String> estadoChoice;
    @FXML
    private Button backBtn;
    @FXML
    private Button guardarBtn;


    PeliculasCopia peliculaSeleccionada;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Agregar opciones a estadoChoice
        estadoChoice.getItems().addAll("disponible", "prestado", "en_mantenimiento", "no disponible");

        // Configurar el Spinner con un ValueFactory inicial
        cantidadSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
    }

    @FXML
    public void setBackBtn(ActionEvent event) {
        HelloApplication.loadFXML("peliculas-controller.fxml", "Peliculas");

    }
    @FXML
    public void setGuardarBtn(ActionEvent event) {
        HelloApplication.loadFXML("peliculas-controller.fxml", "Peliculas");
    }

    public void cargarPelicula(String nombrePeli) {
        // Usamos el DAO para obtener la película completa desde la base de datos
        PeliculasDAO peliculaDao = new PeliculasDAO();
        Peliculas peliculaCompleta = peliculaDao.obtenerPeliculaPorNombre(nombrePeli);

        // Verifica si la película fue encontrada
        if (peliculaCompleta != null) {
            System.out.println("Cargando detalles para la película: " + peliculaCompleta.getNombrePeli());

            // Ahora creamos un objeto PeliculasCopia
            PeliculasCopia peliculaCopia = new PeliculasCopia(
                    "Inception",
                    "Ciencia Ficción",
                    2010,
                    "Christopher Nolan",
                    "disponible",
                    1,
                    "DVD"
            );


            setPelicula(peliculaCopia);
        } else {
            System.out.println("Pelicula no encontrada.");
        }
    }

    public void setPelicula(PeliculasCopia peliculaSeleccionada) {
        if (peliculaSeleccionada != null) {
            this.peliculaSeleccionada = peliculaSeleccionada;

            System.out.println("Rellenando campos con la película: " + peliculaSeleccionada.getNombrePeli());

            nom_peli.setText("Inception");
            generoTxt.setText("Ciencia Ficción");
            anhoTxt.setText(String.valueOf(2010));
            directorTxt.setText("Christopher Nolan");
            descripcionTxt.setText("Un ladrón que roba secretos a través de sueños");
            estadoChoice.setValue(peliculaSeleccionada.getEstado());
            cantidadSpinner.getValueFactory().setValue(peliculaSeleccionada.getCantidad());
        } else {
            System.out.println("No se ha recibido ninguna película para rellenar los campos.");
        }
    }
}
