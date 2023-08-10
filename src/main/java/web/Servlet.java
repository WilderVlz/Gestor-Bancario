package web;

import dominio.Cliente;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import datos.*;
import java.io.IOException;
import javax.servlet.ServletException;

@WebServlet("/ServletControlador")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //esto es brutal (refactorizamos el codigo)
        String accion = request.getParameter("accion");

        if (accion != null) {

            switch (accion) {

                case "editar":

                    this.editarCliente(request, response);
                    break;

                default:

                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }

    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cliente clienteAEditar = new ClienteDaoJDBC().encontrar(new Cliente(Integer.parseInt(request.getParameter("idCliente"))));

        request.setAttribute("clienteEditar", clienteAEditar);
        
        request.getRequestDispatcher("/WEB-INF/paginas/cliente/editarCliente.jsp").forward(request, response);
        
//        int registrosActualizados = new ClienteDaoJDBC().actualizar(clienteAEditar);

        this.accionDefault(request, response);
    }

    private double saldoTotal(List<Cliente> lista) {

        double totalSaldo = 0;

        for (Cliente cliente : lista) {

            totalSaldo += cliente.getSaldo();
        }

        return totalSaldo;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null) {

            switch (accion) {

                case "insertar":

                    this.insertarCliente(request, response);
                    break;

                default:

                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        double saldo = 0;
        String saldoRecibido = request.getParameter("saldo");
        if (saldoRecibido != null && !"".equals(saldoRecibido)) {

            saldo = Double.parseDouble(saldoRecibido);
        }

        Cliente nuevoCliente = new Cliente(
                request.getParameter("nombre"),
                request.getParameter("apellido"),
                request.getParameter("email"),
                request.getParameter("telefono"),
                saldo);

        int registrosModificados = new ClienteDaoJDBC().insertar(nuevoCliente);
        System.out.println("registrosModificados = " + registrosModificados);

        this.accionDefault(request, response);

    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Cliente> clientes = new ClienteDaoJDBC().listar();

        System.out.println("clientes = " + clientes);

        /*lo tuvimos que cambiar al scope de session ya que se hacia una doble peticion al servidor
        y se perdia la informacion compartida en el scope request*/
        HttpSession session = request.getSession();

        session.setAttribute("totalSaldo", saldoTotal(clientes));
        session.setAttribute("totalClientes", clientes.size());
        session.setAttribute("clientes", clientes);

        //necesitamos utilizar sendRedirect ya que forward no cambia la url si no el archivo de manera interna
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);
        //este metodo si notifica al navegador, por lo tanto tambien cambiará la url
        response.sendRedirect("clientes.jsp");

    }
}
