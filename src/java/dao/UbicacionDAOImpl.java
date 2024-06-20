package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Ubicacion;
import utils.ConexionDB;

public class UbicacionDAOImpl implements UbicacionDAO {

    @Override
    public List<Ubicacion> consultarUbicaciones() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Ubicacion> ubicaciones = new ArrayList<>();

        try {
            conn = ConexionDB.getConnection();
            String sql = "SELECT * FROM ubicacion";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_ubicacion");
                String direccion = rs.getString("direccion");
                Ubicacion ubicacion = new Ubicacion(id, direccion);
                ubicaciones.add(ubicacion);
            }
            return ubicaciones;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConexionDB.closeConnection(conn);
        }
    }
    
}
