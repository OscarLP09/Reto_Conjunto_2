package org.example.reto_conjunto;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.reto_conjunto.dao.PeliculasDAO;
import org.example.reto_conjunto.models.Copias;
import org.example.reto_conjunto.models.Peliculas;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PeliculasController{

    @FXML
    private TableView<Object[]> peliculasTable; // Usamos Object[] para almacenar ambos objetos
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
        List<Object[]> peliculasCopias = peliculaDAO.obtenerPeliculasYCopias();
        ObservableList<Object[]> observableList = FXCollections.observableArrayList(peliculasCopias);
        peliculasTable.setItems(observableList);
    }

}
