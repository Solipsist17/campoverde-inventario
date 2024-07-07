package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Contacto;
import model.Empleado;
import model.Movimiento;
import model.Producto;
import model.TipoMovimiento;
import utils.ConexionDB;

public class MovimientoDAOImpl implements MovimientoDAO{

    @Override
    public List<Movimiento> consultarMovimientos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Movimiento> movimientos = new ArrayList<>();

        try {
            conn = ConexionDB.getConnection();
            String sql = "SELECT m.id_movimiento, c.nombre AS contacto, CONCAT(e.nombre, ' ', e.apellido) AS empleado, p.nombre_producto AS producto, m.precio, m.cantidad, m.fecha_movimiento, m.tipo_movimiento "
                    + "FROM movimientos m "
                    + "JOIN contactos c ON m.id_contacto = c.id_contacto "
                    + "JOIN empleados e ON m.id_empleado = e.id_empleado "
                    + "JOIN productos p ON m.id_producto = p.id_producto;";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_movimiento");
                String contacto = rs.getString("contacto");
                String empleado = rs.getString("empleado");
                String producto = rs.getString("producto");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                Date fechaMovimiento = rs.getDate("fecha_movimiento");
                String tipoMovimientoStr = rs.getString("tipo_movimiento");
                TipoMovimiento tipoMovimiento = TipoMovimiento.valueOf(tipoMovimientoStr);
                
                Movimiento movimiento = new Movimiento(id, new Contacto(contacto), new Empleado(empleado), new Producto(producto), precio, cantidad, fechaMovimiento, tipoMovimiento);
                System.out.println(movimiento);
                movimientos.add(movimiento);
            }
            return movimientos;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConexionDB.closeConnection(conn);
        }  
    }
    
}
