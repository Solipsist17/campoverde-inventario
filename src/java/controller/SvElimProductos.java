package controller;

import dao.EmpleadoDAO;
import dao.EmpleadoDAOImpl;
import dao.ProductoDAO;
import dao.ProductoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Empleado;
import model.Producto;

/**
 *
 * @author Sebastian Vasquez
 */
@WebServlet(name = "SvElimProductos", urlPatterns = {"/SvElimProductos"})
public class SvElimProductos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvElimProductos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvElimProductos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Producto producto = new Producto(id);
        ProductoDAO dao = new ProductoDAOImpl();
        String mensaje = "";
        if (dao.eliminarProducto(producto)) {
            mensaje = "Producto eliminado exitosamente";
        } else {
            mensaje = "Error al eliminar el producto";
        }
        request.setAttribute("message", mensaje);
        
        response.sendRedirect("SvProductos");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
