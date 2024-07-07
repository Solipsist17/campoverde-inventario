package controller;

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
import model.Movimiento;
import model.Producto;

@WebServlet(name = "SvMovimientos", urlPatterns = {"/SvMovimientos"})
public class SvMovimientos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvMovimientos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvMovimientos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // CONSULTAR MOVIMIENTOS
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Movimiento> movimientos = new ArrayList<>();
        MovimientoDAO dao = new MovimientoDAOImpl();
        movimientos = dao.consultarMovimientos();
        
        //System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
        //System.out.println(movimientos);
        
        HttpSession session = request.getSession();
        session.setAttribute("listaMovimientos", movimientos);
                
        response.sendRedirect("consultar-movimientos.jsp");
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
