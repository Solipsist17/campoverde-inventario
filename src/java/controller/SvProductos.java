package controller;

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
import model.Categoria;
import model.Producto;
import model.Ubicacion;

/**
 *
 * @author Sebastian Vasquez
 */
@WebServlet(name = "SvProductos", urlPatterns = {"/SvProductos"})
public class SvProductos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvProductos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvProductos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // CONSULTAR PRODUCTO
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Producto> productos = new ArrayList<>();
        ProductoDAO dao = new ProductoDAOImpl();
        productos = dao.consultarProductos();
        
        HttpSession session = request.getSession();
        session.setAttribute("listaProductos", productos);
        
        System.out.println(productos);
                
        response.sendRedirect("consultar-productos.jsp");
    }

    // REGISTRAR PRODUCTO
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        int idUbicacion = Integer.parseInt(request.getParameter("ubicacion"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        double precio = Double.parseDouble(request.getParameter("precio"));
        
        Producto producto = new Producto(nombre, stock, precio, new Categoria(idCategoria), new Ubicacion(idUbicacion));
        ProductoDAO dao = new ProductoDAOImpl();
        
        if (dao.registrarProducto(producto)) {
            request.getSession().setAttribute("message", 1);
            System.out.println("producto registrado: " + producto);
        } else {
            request.getSession().setAttribute("message", 0);
        }
        
        response.sendRedirect("SvDatosProductos");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
