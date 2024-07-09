package controller;

import dao.ContactoDAO;
import dao.ContactoDAOImpl;
import dao.EmpleadoDAO;
import dao.EmpleadoDAOImpl;
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
import model.Producto;

@WebServlet(name = "SvDatosMovimientos", urlPatterns = {"/SvDatosMovimientos"})
public class SvDatosMovimientos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvDatosMovimientos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvDatosMovimientos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // CARGAR DATOS CONTACTO, EMPLEADO Y PRODUCTO
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        
        System.out.println(contactos);
        System.out.println(empleados);
        System.out.println(productos);
        
        response.sendRedirect("registrar-movimiento.jsp");
    }

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
