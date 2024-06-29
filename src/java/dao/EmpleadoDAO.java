package dao;

import java.util.List;
import model.Empleado;

public interface EmpleadoDAO {
    public String registrarUsuario(Empleado empleado);
    public String eliminarUsuario(Empleado empleado);
    public String editarUsuario(Empleado empleado);
    public List<Empleado> consultarUsuarios();
    public Empleado consultarUsuarioPorId(int id);
    public List<Empleado> consultarUsuarioPorCorreo(String correo);
}
