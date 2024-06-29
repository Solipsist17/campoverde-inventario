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
import java.util.ArrayList;
import java.util.List;
import model.Empleado;

/**
 *
 * @author Sebastian Vasquez
 */
@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");
        
        boolean isValido = false;
        isValido = validarLogin(email, clave);
        
        if (isValido) {
            HttpSession miSession = request.getSession(true); // creamos la sesión
            miSession.setAttribute("email", email);
            response.sendRedirect("index.jsp");
        } else {
            //response.sendRedirect("loginError.jsp");
            request.setAttribute("message", "Email o contraseña incorrecto");
            //response.sendRedirect("login.jsp");
            request.getRequestDispatcher("login.jsp").forward(request, response); // redirigir 
        }
    }
    
    private boolean validarLogin(String email, String clave) {
        boolean login = false;
        
        List<Empleado> listaEmpleados = new ArrayList<>();
        EmpleadoDAO dao = new EmpleadoDAOImpl();
        listaEmpleados = dao.consultarUsuarioPorCorreo(email);
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getClave().equals(clave)) {
                login = true;
            } else {
                login = false;
            }
        }
        
        return login;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
