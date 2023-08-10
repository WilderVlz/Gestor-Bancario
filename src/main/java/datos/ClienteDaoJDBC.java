package datos;

import dominio.Cliente;
import java.sql.*;
import java.util.*;
import java.util.List;
import servicio.ServicioCliente;
import static datos.Conexion.*;
import java.io.IOException;

public class ClienteDaoJDBC implements ServicioCliente {

    private static final String SQL_SELECT = "SELECT * FROM cliente";

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM WHERE id_cliente=?";

    private static final String SQL_INSERT = "INSERT INTO cliente (nombre,apellido,email,telefono,saldo) VALUES (?,?,?,?,?)";

    private static final String SQL_UPDATE = "UPDATE cliente SET nombre=?, apellido=?, email=?, telefono=?, saldo=? WHERE id_cliente=?";

    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente=?";

    @Override
    public List<Cliente> listar() {

        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            conexion = getConexion();
            consulta = conexion.prepareStatement(SQL_SELECT);
            resultado = consulta.executeQuery();

            while (resultado.next()) {

                cliente = new Cliente(
                        resultado.getInt(1),
                        resultado.getString(2),
                        resultado.getString(3),
                        resultado.getString(4),
                        resultado.getString(5),
                        resultado.getDouble(6));

                clientes.add(cliente);

                System.out.println(resultado.getInt(1)
                        + resultado.getString(2)
                        + resultado.getString(3)
                        + resultado.getString(4)
                        + resultado.getString(5)
                        + resultado.getDouble(6));

            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(resultado);
            close(consulta);
            close(conexion);
        }

        return clientes;

    }

    @Override
    public Cliente encontrar(Cliente idCliente) {

        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        Cliente cliente = null;

        try {
            conexion = getConexion();
            consulta = conexion.prepareStatement(SQL_SELECT_BY_ID);
            consulta.setInt(1, cliente.getIdCliente());
            resultado = consulta.executeQuery();

            if (resultado.next()) {

                cliente = new Cliente(
                        resultado.getString(2),
                        resultado.getString(3),
                        resultado.getString(4),
                        resultado.getString(5),
                        resultado.getDouble(6));

            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(resultado);
            close(consulta);
            close(conexion);
        }

        return cliente;

    }

    @Override
    public int insertar(Cliente cliente) {

        Connection conexion = null;
        PreparedStatement consulta = null;
        int contadorRegistros = 0;

        try {
            conexion = getConexion();
            consulta = conexion.prepareStatement(SQL_INSERT);
            consulta.setString(1, cliente.getNombre());
            consulta.setString(2, cliente.getApellido());
            consulta.setString(3, cliente.getEmail());
            consulta.setString(4, cliente.getTelefono());
            consulta.setDouble(5, cliente.getSaldo());

            contadorRegistros = consulta.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(consulta);
            close(conexion);
        }

        return contadorRegistros;

    }

    @Override
    public int actualizar(Cliente cliente) {

        Connection conexion = null;
        PreparedStatement consulta = null;
        int contadorRegistros = 0;

        try {
            conexion = getConexion();
            consulta = conexion.prepareStatement(SQL_UPDATE);
            consulta.setString(1, cliente.getNombre());
            consulta.setString(2, cliente.getApellido());
            consulta.setString(3, cliente.getEmail());
            consulta.setString(4, cliente.getTelefono());
            consulta.setDouble(5, cliente.getSaldo());
            consulta.setInt(6, cliente.getIdCliente());

            contadorRegistros = consulta.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(consulta);
            close(conexion);
        }

        return contadorRegistros;

    }

    @Override
    public int eliminar(Cliente cliente) {

        Connection conexion = null;
        PreparedStatement consulta = null;
        int contadorRegistros = 0;

        try {
            conexion = getConexion();
            consulta = conexion.prepareStatement(SQL_DELETE);
            consulta.setInt(1, cliente.getIdCliente());

            contadorRegistros = consulta.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(consulta);
            close(conexion);
        }

        return contadorRegistros;

    }

}
