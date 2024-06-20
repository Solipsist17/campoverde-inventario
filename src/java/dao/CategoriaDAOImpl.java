package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import utils.ConexionDB;

public class CategoriaDAOImpl implements CategoriaDAO {

    @Override
    public List<Categoria> consultarCategorias() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Categoria> categorias = new ArrayList<>();

        try {
            conn = ConexionDB.getConnection();
            String sql = "SELECT * FROM categorias";
            stmt = conn.prepareStatement(sql);
            //stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_categoria");
                String nombre = rs.getString("nombre_categoria");
                String descripcion = rs.getString("descripcion");
                Categoria categoria = new Categoria(id, nombre, descripcion);
                categorias.add(categoria);
            }
            return categorias;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
            //request.setAttribute("error", "Error al autenticar Usuario");
        } finally {
            ConexionDB.closeConnection(conn);
        }
    }
    
}
