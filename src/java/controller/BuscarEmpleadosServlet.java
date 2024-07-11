package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ConexionDB;
import model.Empleado;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/BuscarEmpleadoServlet")
public class BuscarEmpleadosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Obtener el parámetro de búsqueda (ID o nombre del empleado)
        String searchTerm = request.getParameter("searchTerm");

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConexionDB.getConnection();
            String sql = "SELECT id_empleado, nombre, apellido FROM empleados " +
                         "WHERE id_empleado = ? OR nombre LIKE ? ORDER BY id_empleado";
            statement = connection.prepareStatement(sql);
            statement.setString(1, searchTerm); // ID del empleado
            statement.setString(2, "%" + searchTerm + "%"); // Nombre del empleado (búsqueda parcial)

            resultSet = statement.executeQuery();

            List<Empleado> empleados = new ArrayList<>(); // Lista para almacenar los empleados encontrados

            while (resultSet.next()) {
                int idEmpleado = resultSet.getInt("id_empleado");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");

                // Crear objeto Empleado y añadirlo a la lista
                Empleado empleado = new Empleado(idEmpleado, nombre, apellido);
                empleados.add(empleado);
            }

            // Convertir la lista de empleados a JSON
            Gson gson = new Gson();
            String jsonEmpleados = gson.toJson(empleados);

            // Enviar respuesta JSON
            out.print(jsonEmpleados);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            out.println("Error al buscar empleados: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                ConexionDB.closeConnection(connection);
            }
            if (out != null) {
                out.close();
            }
        }
    }
}



