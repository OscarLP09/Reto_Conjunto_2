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

    @FXML
    private TableView<PeliculasCopia> peliculasTable;
    @FXML
    private TableColumn<Object[], String> tituloColumn;
    @FXML
    private TableColumn<Object[], String> estadoColumn;
    @FXML
    private TableColumn<Object[], Integer> cantidadColumn;
    @FXML
    private TableColumn<Object[], String> soporteColumn;

    private PeliculasDAO peliculaDAO;

    public PeliculasController() {
        this.peliculaDAO = new PeliculasDAO();
    }

    @FXML
    private void initialize() {
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        soporteColumn.setCellValueFactory(new PropertyValueFactory<>("soporte"));

        cargarDatos();

        // Agregar evento de clic a las filas de la tabla
        peliculasTable.setRowFactory(tv -> {
            TableRow<PeliculasCopia> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    PeliculasCopia peliculaSeleccionada = row.getItem();
                    mostrarDetallesPelicula(peliculaSeleccionada);
                }
            });
            return row;
        });
    }

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

    private void mostrarDetallesPelicula(PeliculasCopia peliculaSeleccionada) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("detalles_pelicula.fxml"));
            Parent root = loader.load();

            // Accede al controlador de la nueva pantalla y pásale la película seleccionada
            DetallesPeliculaController detallesController = loader.getController();
            detallesController.setPelicula(peliculaSeleccionada);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Detalles de la Película");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
