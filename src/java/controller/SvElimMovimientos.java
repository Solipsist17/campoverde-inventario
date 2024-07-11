package controller;

import dao.MovimientoDAO;
import dao.MovimientoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Movimiento;
import model.TipoMovimiento;

@WebServlet(name = "SvElimMovimientos", urlPatterns = {"/SvElimMovimientos"})
public class SvElimMovimientos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvElimMovimientos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvElimMovimientos at " + request.getContextPath() + "</h1>");
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
        Movimiento movimiento = new Movimiento(id);
        MovimientoDAO dao = new MovimientoDAOImpl();
        if (dao.eliminarMovimiento(movimiento)) {
            request.getSession().setAttribute("message", 1);
            System.out.println(movimiento);
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
