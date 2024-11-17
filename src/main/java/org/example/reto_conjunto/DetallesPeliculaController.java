/**
 * Controlador para la vista de detalles de una película en la aplicación.
 * Proporciona métodos para mostrar y editar la información de una película seleccionada.
 * Implementa la interfaz {@link Initializable} para configurar los componentes de la interfaz al cargar la vista.
 */
package org.example.reto_conjunto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.reto_conjunto.dao.PeliculasDAO;
import org.example.reto_conjunto.models.Peliculas;
import org.example.reto_conjunto.models.PeliculasCopia;

import java.net.URL;
import java.util.ResourceBundle;

public class DetallesPeliculaController implements Initializable {

    /**
     * Etiqueta para mostrar el nombre de la película.
     */
    @FXML
    private Label nom_peli;

    /**
     * Etiqueta para mostrar el género de la película.
     */
    @FXML
    private Label generoTxt;

    /**
     * Etiqueta para mostrar el año de la película.
     */
    @FXML
    private Label anhoTxt;

    /**
     * Etiqueta para mostrar el director de la película.
     */
    @FXML
    private Label directorTxt;

    /**
     * Área de texto para mostrar y editar la descripción de la película.
     */
    @FXML
    private TextArea descripcionTxt;

    /**
     * Spinner para seleccionar la cantidad de copias de la película.
     */
    @FXML
    private Spinner<Integer> cantidadSpinner;

    /**
     * Selector de opciones para establecer el estado de la película.
     */
    @FXML
    private ChoiceBox<String> estadoChoice;

    /**
     * Botón para volver a la vista anterior.
     */
    @FXML
    private Button backBtn;

    /**
     * Botón para guardar los cambios realizados en la película.
     */
    @FXML
    private Button guardarBtn;

    /**
     * Objeto que representa la película seleccionada en la vista.
     */
    private PeliculasCopia peliculaSeleccionada;

    /**
     * Inicializa los elementos de la vista al cargar el controlador.
     * Configura el spinner de cantidad y las opciones del ChoiceBox de estado.
     *
     * @param url               la URL utilizada para resolver la ruta del archivo.
     * @param resourceBundle    el paquete de recursos para localizar elementos específicos.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        estadoChoice.getItems().addAll("disponible", "prestado", "en_mantenimiento", "no disponible");
        cantidadSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
    }

    /**
     * Maneja el evento de clic en el botón "Volver" y carga la vista de la lista de películas.
     *
     * @param event el evento de acción que desencadena el método.
     */
    @FXML
    public void setBackBtn(ActionEvent event) {
        HelloApplication.loadFXML("peliculas-controller.fxml", "Peliculas");
    }

    /**
     * Maneja el evento de clic en el botón "Guardar" y guarda los cambios realizados.
     *
     * @param event el evento de acción que desencadena el método.
     */
    @FXML
    public void setGuardarBtn(ActionEvent event) {
        HelloApplication.loadFXML("peliculas-controller.fxml", "Peliculas");
    }

    /**
     * Carga la información de la película a partir de su nombre y la muestra en la vista.
     *
     * @param nombrePeli el nombre de la película que se va a cargar.
     */
    public void cargarPelicula(String nombrePeli) {
        PeliculasDAO peliculaDao = new PeliculasDAO();
        Peliculas peliculaCompleta = peliculaDao.obtenerPeliculaPorNombre(nombrePeli);

        if (peliculaCompleta != null) {
            System.out.println("Cargando detalles para la película: " + peliculaCompleta.getNombrePeli());

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

    /**
     * Establece los detalles de la película seleccionada en los elementos de la vista.
     *
     * @param peliculaSeleccionada el objeto {@link PeliculasCopia} que contiene la información de la película.
     */
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
