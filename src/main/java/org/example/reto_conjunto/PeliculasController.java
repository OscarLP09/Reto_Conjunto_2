package org.example.reto_conjunto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.reto_conjunto.dao.PeliculasCopiaDAO;
import org.example.reto_conjunto.dao.PeliculasDAO;
import org.example.reto_conjunto.models.PeliculasCopia;

import java.util.List;

public class PeliculasController{

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




}
