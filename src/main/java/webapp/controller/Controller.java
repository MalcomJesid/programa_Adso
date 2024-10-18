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

        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
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
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;

        try {
            String saldoString = request.getParameter("saldo");
            if (saldoString != null && !"".equals(saldoString)) {
                saldo = Double.parseDouble(saldoString);
            }

            Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);
            int registrosModificados = new ClienteDaoJDBC().insertar(cliente);
            System.out.println("registrosModificados = " + registrosModificados);

            this.accionDefault(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Saldo debe ser un número válido.");
            request.getRequestDispatcher("clientes.jsp").forward(request, response);
        }
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idcliente"));
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

            Cliente cliente = new Cliente(idCliente, nombre, apellido, correo, telefono, saldo);
            int registrosModificados = new ClienteDaoJDBC().actualizar(cliente);
            System.out.println("registrosModificados = " + registrosModificados);

            this.accionDefault(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Saldo debe ser un número válido.");
            request.getRequestDispatcher("clientes.jsp").forward(request, response);
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

