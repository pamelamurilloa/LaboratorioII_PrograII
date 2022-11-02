package Data;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author pamelamurillo
 */
public class DBConexion {
    
    private Connection connection = null;

    public Connection conexion() {
        String url = "jdbc:postgresql://localhost:5432/laboratorioiifutbol";
        String password = "12pg";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, "postgres", password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
    
}
