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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Contacto;
import model.Empleado;
import model.Movimiento;
import model.Producto;
import model.TipoMovimiento;

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
        
        HttpSession session = request.getSession();
        session.setAttribute("listaMovimientos", movimientos);
                
        response.sendRedirect("consultar-movimientos.jsp");
    }

    // REGISTRAR MOVIMIENTO
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        
        Movimiento movimiento = new Movimiento(new Contacto(idContacto), new Empleado(idEmpleado), new Producto(idProducto), precio, cantidad, fecha, tipo);
        MovimientoDAO dao = new MovimientoDAOImpl();
        
        if (dao.registrarMovimiento(movimiento)) {
            request.getSession().setAttribute("message", 1);
            System.out.println("movimiento registrado: " + movimiento.toString());
        } else {
            request.getSession().setAttribute("message", 0);
        }
        
        response.sendRedirect("SvDatosMovimientos");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
