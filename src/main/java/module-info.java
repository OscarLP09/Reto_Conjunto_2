module org.example.reto_conjunto {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;
    requires java.naming;
    requires java.desktop;

    opens org.example.reto_conjunto.models to javafx.base, org.hibernate.orm.core;
    opens org.example.reto_conjunto to javafx.fxml;
    exports org.example.reto_conjunto;
}
