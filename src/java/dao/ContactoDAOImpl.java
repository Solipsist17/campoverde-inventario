package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Contacto;
import model.TipoContacto;
import utils.ConexionDB;

public class ContactoDAOImpl implements ContactoDAO {

    @Override
    public List<Contacto> consultarContactos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Contacto> contactos = new ArrayList<>();

        try {
            conn = ConexionDB.getConnection();
            String sql = "SELECT * FROM contactos";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_contacto");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                String correo = rs.getString("correo_electronico");
                String tipoContactoStr = rs.getString("tipo_contacto");
                TipoContacto tipoContacto = TipoContacto.valueOf(tipoContactoStr);
                
                Contacto contacto = new Contacto(id, nombre, descripcion, direccion, correo, tipoContacto);
                contactos.add(contacto);
            }
            return contactos;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
            //request.setAttribute("error", "Error al autenticar Usuario");
        } finally {
            ConexionDB.closeConnection(conn);
        }
    }
    
}
