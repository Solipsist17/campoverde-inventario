package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Empleado;
import utils.ConexionDB;

public class EmpleadoDAOImpl implements EmpleadoDAO {
    
    /*private Connection connection;
    
    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }*/
    
    @Override
    public String registrarUsuario(Empleado empleado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexionDB.getConnection();
            String sql = "INSERT INTO empleados (nombre, apellido, correo_electronico, clave) VALUES (?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getCorreo());
            stmt.setString(4, empleado.getClave());
            stmt.executeUpdate();
            //request.setAttribute("message", "Usuario registrado exitosamente");
            return "Usuario registrado exitosamente";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            //request.setAttribute("error", "Error al registrar Usuario");
            return "Error al registrar Usuario";
        } finally {
            ConexionDB.closeConnection(conn);
        }
    }

    @Override
    public String eliminarUsuario(Empleado empleado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexionDB.getConnection();
            String sql = "DELETE FROM empleados WHERE id_empleado=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, empleado.getId());
            stmt.executeUpdate();
            return "Usuario eliminado exitosamente";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return "Error al eliminar Usuario";
        } finally {
            ConexionDB.closeConnection(conn);
        }
    }

    @Override
    public List<Empleado> consultarUsuarios() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Empleado> empleados = new ArrayList<>();

        try {
            conn = ConexionDB.getConnection();
            String sql = "SELECT * FROM empleados";
            stmt = conn.prepareStatement(sql);
            //stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_empleado");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo_electronico");
                String clave = rs.getString("clave");
                Empleado empleado = new Empleado(id, nombre, apellido, correo, clave);
                empleados.add(empleado);
            }
            return empleados;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
            //request.setAttribute("error", "Error al autenticar Usuario");
        } finally {
            ConexionDB.closeConnection(conn);
        }
    }

    @Override
    public String editarUsuario(Empleado empleado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexionDB.getConnection();
            String sql = "UPDATE empleados SET nombre=?, apellido=?, correo_electronico=?, clave=? WHERE id_empleado=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getCorreo());
            stmt.setString(4, empleado.getClave());
            stmt.setInt(5, empleado.getId());
            stmt.executeUpdate();
            return "Usuario editado exitosamente";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return "Error al editar Usuario";
        } finally {
            ConexionDB.closeConnection(conn);
        }
    }

    @Override
    public Empleado consultarUsuarioPorId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Empleado empleado = null;

        try {
            conn = ConexionDB.getConnection();
            String sql = "SELECT * FROM empleados WHERE id_empleado=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //int id_empleado = rs.getInt("id_empleado");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo_electronico");
                String clave = rs.getString("clave");
                empleado = new Empleado(id, nombre, apellido, correo, clave);
            }
            return empleado;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
            //request.setAttribute("error", "Error al autenticar Usuario");
        } finally {
            ConexionDB.closeConnection(conn);
        }
    }
    
}
