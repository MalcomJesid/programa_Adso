package webapp.controller;
import datos.ClienteDaoJDBC;
import dominio.Cliente;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarCliente(request, response);
                    break;
                case "eliminar":
                    this.eliminarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    protected void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = new ClienteDaoJDBC().Listar();
        System.out.println("clientes = " + clientes);

        HttpSession sesion = request.getSession();
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));

        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }

    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;
        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idcliente"));
        Cliente cliente = new ClienteDaoJDBC().encontrar(new Cliente(idCliente));
        request.setAttribute("cliente", cliente);

        String jspEditar = "/WEB-INF/paginas/clientes/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                case "modificar":
                    this.modificarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        double saldo = 0;

        try {
            String saldoString = request.getParameter("saldo");
            if (saldoString != null && !"".equals(saldoString)) {
                saldo = Double.parseDouble(saldoString);
            }

            Cliente cliente = new Cliente(nombre, apellido, correo, telefono, saldo);
            int registrosModificados = new ClienteDaoJDBC().insertar(cliente);
            System.out.println("registrosModificados = " + registrosModificados);

            this.accionDefault(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Saldo debe ser un número válido.");
            request.getRequestDispatcher("clientes.jsp").forward(request, response);
        }
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperar el ID del cliente desde el formulario
        String idClienteStr = request.getParameter("idCliente");
        int idCliente = 0;

        // Validar el ID del cliente
        if (idClienteStr != null && !idClienteStr.isEmpty()) {
            try {
                idCliente = Integer.parseInt(idClienteStr);
            } catch (NumberFormatException e) {
                System.out.println("Error: idCliente no es un número válido.");
                response.sendRedirect("error.jsp"); // Redirigir a una página de error
                return;
            }
        } else {
            System.out.println("Error: idCliente es nulo o vacío.");
            response.sendRedirect("error.jsp");
            return;
        }

        // Recuperar otros valores del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        double saldo = 0;

        // Validar y convertir el saldo
        String saldoStr = request.getParameter("saldo");
        if (saldoStr != null && !saldoStr.isEmpty()) {
            try {
                saldo = Double.parseDouble(saldoStr);
            } catch (NumberFormatException e) {
                System.out.println("Error: saldo no es un número válido.");
                response.sendRedirect("error.jsp"); // Redirigir a una página de error
                return;
            }
        }

        // Crear el objeto Cliente con los datos del formulario
        Cliente cliente = new Cliente(idCliente, nombre, apellido, telefono, correo, saldo);

        // Intentar actualizar el cliente en la base de datos
        try {
            int registrosModificados = new ClienteDaoJDBC().actualizar(cliente);
            System.out.println("Registros modificados = " + registrosModificados);

            if (registrosModificados > 0) {
                // Redirigir a la acción por defecto después de una modificación exitosa
                this.accionDefault(request, response);
            } else {
                System.out.println("No se encontró el cliente para modificar.");
                response.sendRedirect("error.jsp"); // Redirigir a una página de error si no se encontró el cliente
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar el cliente: " + e.getMessage());
            response.sendRedirect("error.jsp"); // Redirigir a una página de error
        }
    }





    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idcliente"));
        Cliente cliente = new Cliente(idCliente);
        int registrosModificados = new ClienteDaoJDBC().eliminar(cliente);
        System.out.println("registrosModificados = " + registrosModificados);

        this.accionDefault(request, response);
    }
}

