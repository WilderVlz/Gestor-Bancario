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

    public static DataSource getData() {

        BasicDataSource bd = new BasicDataSource();

        bd.setUrl(JDBC_URL);
        bd.setUsername(JDBC_USER);
        bd.setPassword(JDBC_PASS);
        
        bd.setInitialSize(50);

        return bd;

    }

    public static Connection getConexion() throws SQLException  {

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
