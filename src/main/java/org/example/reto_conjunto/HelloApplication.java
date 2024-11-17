/**
 * Clase principal de la aplicación JavaFX que inicia la interfaz gráfica.
 * Carga las vistas FXML y gestiona la navegación entre distintas ventanas.
 */
package org.example.reto_conjunto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    /**
     * Instancia de {@link Stage} que representa la ventana principal de la aplicación.
     * Se utiliza para cargar y mostrar diferentes vistas.
     */
    private static Stage ventana;

    /**
     * Método de inicio de la aplicación. Carga la vista inicial y muestra la ventana.
     *
     * @param stage el escenario principal de la aplicación.
     * @throws IOException si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.ventana = stage;
        loadFXML("hello-view.fxml", "Hola Mundo");
        stage.show();
    }

    /**
     * Método estático para cargar una vista FXML y establecerla en la ventana principal.
     *
     * @param view  el nombre del archivo FXML que se va a cargar.
     * @param title el título de la ventana.
     */
    public static void loadFXML(String view, String title) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(view));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 520, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ventana.setTitle(title);
        ventana.setScene(scene);
        ventana.setResizable(false);
    }

    /**
     * Método principal que lanza la aplicación.
     *
     * @param args los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}
