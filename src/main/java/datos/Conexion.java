package datos;

import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/control_clientes?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "1234";

    private static BasicDataSource dataSource;

    public static DataSource getData() {

        /*correcion para evitar que se creen varios objetos de tipo connection 
          asi optimizaremos mucho mas el performance*/
        if (dataSource == null) {
            dataSource = new BasicDataSource();

            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASS);

            dataSource.setInitialSize(50);

        }

        return dataSource;

    }

    public static Connection getConexion() throws SQLException {

        return getData().getConnection();

    }

    public static void close(ResultSet rs) {

        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement st) {

        try {
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection c) {

        try {
            c.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

}
