package controller;

import dao.ContactoDAO;
import dao.ContactoDAOImpl;
import dao.EmpleadoDAO;
import dao.EmpleadoDAOImpl;
import dao.MovimientoDAO;
import dao.MovimientoDAOImpl;
import dao.ProductoDAO;
import dao.ProductoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Contacto;
import model.Empleado;
import model.Movimiento;
import model.Producto;

/**
 *
 * @author Sebastian Vasquez
 */
@WebServlet(name = "SvEditMovimientos", urlPatterns = {"/SvEditMovimientos"})
public class SvEditMovimientos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvEditMovimientos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvEditMovimientos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // CARGAR LOS DATOS A EDITAR 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // cargar los datos de contacto, empleado y producto
        List<Contacto> contactos = new ArrayList<>();
        ContactoDAO contactoDAO = new ContactoDAOImpl();
        contactos = contactoDAO.consultarContactos();
        
        List<Empleado> empleados = new ArrayList<>();
        EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();
        empleados = empleadoDAO.consultarUsuarios();
        
        List<Producto> productos = new ArrayList<>();
        ProductoDAO productoDAO = new ProductoDAOImpl();
        productos = productoDAO.consultarProductos();
        
        HttpSession session = request.getSession();
        session.setAttribute("listaContactos", contactos);
        session.setAttribute("listaEmpleados", empleados);
        session.setAttribute("listaProductos", productos);
        
        // traemos los datos
        int id = Integer.parseInt(request.getParameter("id")); // parámetro enviado por el FORM
        MovimientoDAO dao = new MovimientoDAOImpl();
        Movimiento movimiento = dao.consultarMovimientoPorId(id);
        
        if (movimiento == null) {
            request.setAttribute("message", "Error al cargar datos");
            request.getSession().setAttribute("message", 0);
            return;
        }
        
        // guardamos los datos en session y redirigimos
        //HttpSession session = request.getSession();
        session.setAttribute("movEditar", movimiento);
        System.out.println("El movimiento es: " + movimiento);
        response.sendRedirect("editar-movimiento.jsp");
    }

    // EDITAR
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
