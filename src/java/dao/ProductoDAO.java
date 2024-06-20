package dao;

import java.util.List;
import model.Producto;

public interface ProductoDAO {
    public boolean registrarProducto(Producto producto);
    public List<Producto> consultarProductos();
    public boolean eliminarProducto(Producto producto);
    public boolean editarProducto(Producto producto);
    public Producto consultarProductoPorId(int id);
}
