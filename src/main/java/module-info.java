module org.example.reto_conjunto {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;
    requires java.naming;

    opens org.example.reto_conjunto.models to javafx.base, org.hibernate.orm.core; // Abre a javafx.base y Hibernate
    opens org.example.reto_conjunto to javafx.fxml; // Mantén este para FXML
    exports org.example.reto_conjunto;
}
