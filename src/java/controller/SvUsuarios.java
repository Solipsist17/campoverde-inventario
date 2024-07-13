package controller;

import dao.EmpleadoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Empleado;
import dao.EmpleadoDAO;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SvUsuarios", urlPatterns = {"/SvUsuarios"})
public class SvUsuarios extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvUsuarios</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvUsuarios at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // CONSULTAR USUARIOS
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Empleado> empleados = new ArrayList<>();
        EmpleadoDAO dao = new EmpleadoDAOImpl();
        empleados = dao.consultarUsuarios();
        
        HttpSession session = request.getSession();
        session.setAttribute("listaUsuarios", empleados);
        
        System.out.println(empleados);
                
        //request.getRequestDispatcher("consultar-usuarios.jsp").forward(request, response);
        response.sendRedirect("consultar-usuarios.jsp");
    }

    // REGISTRAR USUARIOS
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        
        String mensaje = "";
                
        Empleado empleado = new Empleado(nombre, apellido, correo, clave);
        EmpleadoDAO dao = new EmpleadoDAOImpl();
        
        if (dao.registrarUsuario(empleado)) {
            request.getSession().setAttribute("message", 1);
            System.out.println("usuario registrado: " + empleado);
        } else {
            request.getSession().setAttribute("message", 0);
        }
                
        //request.getRequestDispatcher("registrar-usuario.jsp").forward(request, response); // redirigir 
        
        response.sendRedirect("registrar-usuario.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
