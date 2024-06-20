package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Empleado;
import model.Producto;
import model.Ubicacion;
import utils.ConexionDB;

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public boolean registrarProducto(Producto producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexionDB.getConnection();
            String sql = "INSERT INTO productos (id_categoria, id_ubicacion, nombre_producto, stock, precio_individual) VALUES (?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, producto.getCategoria().getId());
            stmt.setInt(2, producto.getUbicacion().getId());
            stmt.setString(3, producto.getNombre());
            stmt.setInt(4, producto.getStock());
            stmt.setDouble(5, producto.getPrecio());
            stmt.executeUpdate();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConexionDB.closeConnection(conn);
        }
    }

    @Override
    public List<Producto> consultarProductos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Producto> productos = new ArrayList<>();

        try {
            conn = ConexionDB.getConnection();
            String sql = "SELECT p.id_producto, c.nombre_categoria AS categoria, u.direccion AS ubicacion, p.nombre_producto, p.stock, p.precio_individual "
                    + "FROM productos p "
                    + "JOIN categorias c ON p.id_categoria = c.id_categoria "
                    + "JOIN ubicacion u ON p.id_ubicacion = u.id_ubicacion";
            stmt = conn.prepareStatement(sql);
            //stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_producto");
                String categoria = rs.getString("categoria");
                String ubicacion = rs.getString("ubicacion");
                String nombre = rs.getString("nombre_producto");
                int stock = rs.getInt("stock");
                double precio = rs.getDouble("precio_individual");
                Producto producto = new Producto(id, nombre, stock, precio, new Categoria(categoria), new Ubicacion(ubicacion));
                productos.add(producto);
            }
            return productos;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConexionDB.closeConnection(conn);
        }  
    }

    @Override
    public boolean eliminarProducto(Producto producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexionDB.getConnection();
            String sql = "DELETE FROM productos WHERE id_producto=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, producto.getId());
            stmt.executeUpdate();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConexionDB.closeConnection(conn);
        }
    }

    @Override
    public boolean editarProducto(Producto producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ConexionDB.getConnection();
            String sql = "UPDATE productos SET id_categoria=?, id_ubicacion=?, nombre_producto=?, stock=?, precio_individual=? WHERE id_producto=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, producto.getCategoria().getId());
            stmt.setInt(2, producto.getUbicacion().getId());
            stmt.setString(3, producto.getNombre());
            stmt.setInt(4, producto.getStock());
            stmt.setDouble(5, producto.getPrecio());
            stmt.setInt(6, producto.getId());
            stmt.executeUpdate();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexionDB.closeConnection(conn);
        }
    }

    @Override
    public Producto consultarProductoPorId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Producto producto = null;

        try {
            conn = ConexionDB.getConnection();
            String sql = "SELECT * FROM productos WHERE id_producto=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //int id_producto = rs.getInt("id_producto");
                int idCategoria = rs.getInt("id_categoria");
                int idUbicacion = rs.getInt("id_ubicacion");
                String nombre = rs.getString("nombre_producto");
                int stock = rs.getInt("stock");
                double precio = rs.getDouble("precio_individual");
                producto = new Producto(id, nombre, stock, precio, new Categoria(idCategoria), new Ubicacion(idUbicacion));
            }
            return producto;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConexionDB.closeConnection(conn);
        }
    }
    
}
