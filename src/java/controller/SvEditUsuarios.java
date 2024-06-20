package controller;

import dao.EmpleadoDAO;
import dao.EmpleadoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.Empleado;

@WebServlet(name = "SvEditUsuarios", urlPatterns = {"/SvEditUsuarios"})
public class SvEditUsuarios extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvEditUsuarios</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvEditUsuarios at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // CARGAR LOS DATOS A EDITAR
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // traemos los datos
        int id = Integer.parseInt(request.getParameter("id"));
        EmpleadoDAO dao = new EmpleadoDAOImpl();
        Empleado empleado = dao.consultarUsuarioPorId(id);
        
        if (empleado == null) {
            request.setAttribute("message", "Error al cargar datos");
            return;
        }
        
        // guardamos los datos en session y redirigimos
        HttpSession session = request.getSession();
        session.setAttribute("usuEditar", empleado);
        System.out.println("El usuario es: " + empleado.getNombre());
        response.sendRedirect("editar-usuario.jsp");
    }

    // EDITAR 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        
        Empleado empleado = (Empleado) request.getSession().getAttribute("usuEditar");
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setCorreo(correo);
        empleado.setClave(clave);
        
        EmpleadoDAO dao = new EmpleadoDAOImpl();
        String mensaje = dao.editarUsuario(empleado);
        request.setAttribute("message", mensaje);
        
        response.sendRedirect("SvUsuarios");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
