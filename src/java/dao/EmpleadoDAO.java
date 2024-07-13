package dao;

import java.util.List;
import model.Empleado;

public interface EmpleadoDAO {
    public boolean registrarUsuario(Empleado empleado);
    public boolean eliminarUsuario(Empleado empleado);
    public boolean editarUsuario(Empleado empleado);
    public List<Empleado> consultarUsuarios();
    public Empleado consultarUsuarioPorId(int id);
    public List<Empleado> consultarUsuarioPorCorreo(String correo);
}
