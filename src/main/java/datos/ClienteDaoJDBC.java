package datos;

import dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoJDBC {

    // Consultas SQL
    private static final String SQL_SELECT  = "SELECT idcliente, nombre, apellido, correo, telefono, saldo FROM cliente";
    private static final String SQL_SELECT_BY_ID = "SELECT idcliente, nombre, apellido, correo, telefono, saldo FROM cliente WHERE idcliente = ?";
    private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellido, correo, telefono, saldo) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE cliente SET nombre=?, apellido=?, correo=?, telefono=?, saldo=? WHERE idcliente=?";
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE idcliente=?";

    // Método para listar todos los clientes
    public List<Cliente> Listar(){
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stat = conn.prepareStatement(SQL_SELECT);
            rs = stat.executeQuery();

            while (rs.next()) {
                int idcliente = rs.getInt("idcliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");

                cliente = new Cliente(idcliente, nombre, apellido, correo, telefono, saldo);
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stat);
            Conexion.close(conn);
        }
        return clientes;
    }

    // Método para encontrar un cliente por su ID
    public Cliente encontrar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente clienteEncontrado = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, cliente.getIdcliente());
            rs = stmt.executeQuery();

            if (rs.next()) {
                int idCliente = rs.getInt("idcliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");

                clienteEncontrado = new Cliente(idCliente, nombre, apellido, correo, telefono, saldo);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return clienteEncontrado;
    }

    // Método para insertar un nuevo cliente
    public int insertar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, apellido, email, telefono, saldo) VALUES (?, ?, ?, ?, ?)";
        int registrosModificados = 0;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getCorreo());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());

            registrosModificados = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Captura y muestra el error
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn); // Cierra la conexión
        }

        return registrosModificados;
    }


    // Método para actualizar un cliente existente
    public int actualizar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getCorreo());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setInt(6, cliente.getIdcliente());

            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return registros;
    }

    // Método para eliminar un cliente
    public int eliminar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cliente.getIdcliente());

            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return registros;
    }
}


