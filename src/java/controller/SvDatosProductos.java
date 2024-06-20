package controller;

import dao.CategoriaDAO;
import dao.CategoriaDAOImpl;
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
import model.Ubicacion;

/**
 *
 * @author Sebastian Vasquez
 */
@WebServlet(name = "SvDatosProductos", urlPatterns = {"/SvDatosProductos"})
public class SvDatosProductos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvDatosProductos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvDatosProductos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // CARGAR DATOS CATEGORIA Y UBICACION 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Categoria> categorias = new ArrayList<>();
        CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
        categorias = categoriaDAO.consultarCategorias();
        
        List<Ubicacion> ubicaciones = new ArrayList<>();
        UbicacionDAO ubicacionDAO = new UbicacionDAOImpl();
        ubicaciones = ubicacionDAO.consultarUbicaciones();
        
        HttpSession session = request.getSession();
        session.setAttribute("listaCategorias", categorias);
        session.setAttribute("listaUbicaciones", ubicaciones);
        
        System.out.println(categorias);
        System.out.println(ubicaciones);

        response.sendRedirect("registrar-producto.jsp");
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
