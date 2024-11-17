/**
 * Clase de utilidad para gestionar la configuración de Hibernate y la creación de la {@link SessionFactory}.
 * Proporciona un método estático para obtener la instancia de la fábrica de sesiones,
 * permitiendo la interacción con la base de datos.
 */
package org.example.reto_conjunto;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    /**
     * Instancia única de {@link SessionFactory} utilizada para la gestión de sesiones de Hibernate.
     */
    private static SessionFactory sessionFactory;

    // Bloque estático para inicializar la sesión de Hibernate al cargar la clase.
    static {
        sessionFactory = new Configuration()
                .configure() // Carga la configuración de Hibernate desde el archivo hibernate.cfg.xml por defecto.
                .buildSessionFactory();
    }

    /**
     * Devuelve la instancia de {@link SessionFactory} para manejar las sesiones de Hibernate.
     * Utilizado por los DAOs para interactuar con la base de datos.
     *
     * @return la instancia de {@link SessionFactory}.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
