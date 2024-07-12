package dao;

import java.util.List;
import model.Movimiento;

public interface MovimientoDAO {
    public List<Movimiento> consultarMovimientos();
    public boolean registrarMovimiento(Movimiento movimiento);
    public boolean eliminarMovimiento(Movimiento movimiento);
    public Movimiento consultarMovimientoPorId(int id);
    public boolean editarMovimiento(Movimiento movimiento);
}
