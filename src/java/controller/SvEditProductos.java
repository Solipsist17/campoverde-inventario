package controller;

import dao.CategoriaDAO;
import dao.CategoriaDAOImpl;
import dao.ProductoDAO;
import dao.ProductoDAOImpl;
import dao.UbicacionDAO;
import dao.UbicacionDAOImpl;
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

@WebServlet(name = "SvEditProductos", urlPatterns = {"/SvEditProductos"})
public class SvEditProductos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvEditProductos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvEditProductos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // CARGAR LOS DATOS A EDITAR
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // cargar categorias y ubicacion
        List<Categoria> categorias = new ArrayList<>();
        CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
        categorias = categoriaDAO.consultarCategorias();
        
        List<Ubicacion> ubicaciones = new ArrayList<>();
        UbicacionDAO ubicacionDAO = new UbicacionDAOImpl();
        ubicaciones = ubicacionDAO.consultarUbicaciones();
        
        HttpSession session = request.getSession();
        session.setAttribute("listaCategorias", categorias);
        session.setAttribute("listaUbicaciones", ubicaciones);
        
        
        // traemos los datos
        int id = Integer.parseInt(request.getParameter("id"));
        ProductoDAO dao = new ProductoDAOImpl();
        Producto producto = dao.consultarProductoPorId(id);
        
        if (producto == null) {
            request.setAttribute("message", "Error al cargar datos");
            return;
        }
        
        // guardamos los datos en session y redirigimos
        //HttpSession session = request.getSession();
        session.setAttribute("prodEditar", producto);
        System.out.println("El producto es: " + producto.getNombre());
        response.sendRedirect("editar-producto.jsp");
    }

    // EDITAR
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        int idUbicacion = Integer.parseInt(request.getParameter("ubicacion"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        double precio = Double.parseDouble(request.getParameter("precio"));
        
        Producto producto = (Producto) request.getSession().getAttribute("prodEditar");
        producto.setNombre(nombre);
        producto.setCategoria(new Categoria(idCategoria));
        producto.setUbicacion(new Ubicacion(idUbicacion));
        producto.setStock(stock);
        producto.setPrecio(precio);
        
        ProductoDAO dao = new ProductoDAOImpl();
        String mensaje = "";
        if (dao.editarProducto(producto)) {
            mensaje = "Producto editado exitosamente";
        } else {
            mensaje = "Error al editar el producto";
        }
        
        System.out.println("Nuevo producto: " + producto.getCategoria().getId());
             
        request.setAttribute("message", mensaje);
        
        response.sendRedirect("SvProductos");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
