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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Contacto;
import model.Empleado;
import model.Movimiento;
import model.Producto;
import model.TipoMovimiento;

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
        
        // cargar contactos, empleados y productos
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
        session.setAttribute("movEditar", movimiento);
        
        System.out.println("El movimiento es: " + movimiento);
        response.sendRedirect("editar-movimiento.jsp");
    }

    // EDITAR
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // traemos los datos del jsp
        int idContacto = Integer.parseInt(request.getParameter("contacto"));
        int idEmpleado = Integer.parseInt(request.getParameter("empleado"));
        int idProducto = Integer.parseInt(request.getParameter("producto"));
        double precio = Double.parseDouble(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        
        String fechaStr = request.getParameter("fecha");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = new Date();
        try {
            fecha = dateFormat.parse(fechaStr);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());;
        }
        
        String tipoStr = request.getParameter("tipo").toUpperCase();
        TipoMovimiento tipo = TipoMovimiento.valueOf(tipoStr);
        
        // traemos los datos anteriores guardados en sesión
        Movimiento movimiento = (Movimiento) request.getSession().getAttribute("movEditar");
        movimiento.setContacto(new Contacto(idContacto));
        movimiento.setEmpleado(new Empleado(idEmpleado));
        movimiento.setProducto(new Producto(idProducto));
        movimiento.setPrecio(precio);
        movimiento.setCantidad(cantidad);
        movimiento.setFechaMovimiento(fecha);
        movimiento.setTipoMovimiento(tipo);
        
        MovimientoDAO dao = new MovimientoDAOImpl();
        if (dao.editarMovimiento(movimiento)) {
            request.getSession().setAttribute("message", 1);
            System.out.println("movimiento editado: " + movimiento);
        } else {
            request.getSession().setAttribute("message", 0);
        }
        
        response.sendRedirect("SvMovimientos");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
