/**
 * Controlador para gestionar la vista de películas en la aplicación.
 * Se encarga de cargar, mostrar y manejar la interacción del usuario con la lista de películas y sus detalles.
 */
package org.example.reto_conjunto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.reto_conjunto.dao.PeliculasCopiaDAO;
import org.example.reto_conjunto.dao.PeliculasDAO;
import org.example.reto_conjunto.models.PeliculasCopia;

import java.io.IOException;
import java.util.List;

public class PeliculasController {

    /**
     * Tabla que muestra la lista de películas con sus detalles.
     */
    @FXML
    private TableView<PeliculasCopia> peliculasTable;

    /**
     * Columna de la tabla que muestra el título de la película.
     */
    @FXML
    private TableColumn<Object[], String> tituloColumn;

    /**
     * Columna de la tabla que muestra el estado de la película.
     */
    @FXML
    private TableColumn<Object[], String> estadoColumn;

    /**
     * Columna de la tabla que muestra la cantidad de copias de la película.
     */
    @FXML
    private TableColumn<Object[], Integer> cantidadColumn;

    /**
     * Columna de la tabla que muestra el tipo de soporte de la película.
     */
    @FXML
    private TableColumn<Object[], String> soporteColumn;

    /**
     * Objeto de acceso a datos para gestionar las películas.
     */
    private PeliculasDAO peliculaDAO;

    /**
     * Constructor de la clase. Inicializa la instancia de {@link PeliculasDAO}.
     */
    public PeliculasController() {
        this.peliculaDAO = new PeliculasDAO();
    }

    /**
     * Método de inicialización que se ejecuta al cargar la vista.
     * Configura las columnas de la tabla y carga los datos de las películas.
     */
    @FXML
    private void initialize() {
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        soporteColumn.setCellValueFactory(new PropertyValueFactory<>("soporte"));

        cargarDatos();

        peliculasTable.setRowFactory(tv -> {
            TableRow<PeliculasCopia> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    PeliculasCopia peliculaSeleccionada = row.getItem();
                    verDetallesPelicula(peliculaSeleccionada);
                }
            });
            return row;
        });
    }

    /**
     * Carga los datos de las películas y sus copias asociadas para el usuario actual.
     * Filtra las películas por el ID del usuario logueado y las muestra en la tabla.
     */
    private void cargarDatos() {
        int userId = HelloController.Sesion.getUsuarioId(); // Obtener el id del usuario logueado
        List<PeliculasCopia> listaPeliculas = PeliculasCopiaDAO.obtenerPeliculasYCopias(userId); // Filtrar las pelis y copias por el usuario logueado
        ObservableList<PeliculasCopia> observableList = FXCollections.observableArrayList(listaPeliculas);

        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("nombrePeli"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        soporteColumn.setCellValueFactory(new PropertyValueFactory<>("soporte"));

        peliculasTable.setItems(observableList);
    }

    /**
     * Muestra una ventana emergente con los detalles de la película seleccionada.
     *
     * @param peliculaSeleccionada la película seleccionada por el usuario.
     */
    @FXML
    private void verDetallesPelicula(PeliculasCopia peliculaSeleccionada) {
        if (peliculaSeleccionada != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("detalles_pelicula.fxml"));
                Parent detallesRoot = loader.load();

                DetallesPeliculaController detallesController = loader.getController();
                detallesController.setPelicula(peliculaSeleccionada);

                Stage detallesStage = new Stage();
                detallesStage.setScene(new Scene(detallesRoot));
                detallesStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
