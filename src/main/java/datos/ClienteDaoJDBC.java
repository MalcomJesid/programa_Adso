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
    public List<Cliente> Listar() {
        List<Cliente> clientes = new ArrayList<>();

        // Usar try-with-resources para manejar la conexión, el statement y el ResultSet
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stat = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = stat.executeQuery()) {

            while (rs.next()) {
                int idcliente = rs.getInt("idcliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");

                Cliente cliente = new Cliente(idcliente, nombre, apellido, correo, telefono, saldo);
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return clientes;
    }

    // Método para encontrar un cliente por su ID
    public Cliente encontrar(Cliente cliente) {
        Cliente clienteEncontrado = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)) {

            stmt.setInt(1, cliente.getIdcliente());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idCliente = rs.getInt("idcliente");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String correo = rs.getString("correo");
                    String telefono = rs.getString("telefono");
                    double saldo = rs.getDouble("saldo");

                    clienteEncontrado = new Cliente(idCliente, nombre, apellido, correo, telefono, saldo);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return clienteEncontrado;
    }

    // Método para insertar un nuevo cliente
    public int insertar(Cliente cliente) {
        int registrosModificados = 0;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getCorreo());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());

            registrosModificados = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Captura y muestra el error
        }

        return registrosModificados;
    }

    // Método para actualizar un cliente existente
    public int actualizar(Cliente cliente) throws SQLException {
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getCorreo());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setInt(6, cliente.getIdcliente());
            return stmt.executeUpdate();
        }
    }



    // Método para eliminar un cliente
    public int eliminar(Cliente cliente) {
        int registros = 0;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)) {

            stmt.setInt(1, cliente.getIdcliente());

            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return registros;
    }
}