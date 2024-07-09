package dao;

import java.util.List;
import model.Movimiento;

public interface MovimientoDAO {
    public List<Movimiento> consultarMovimientos();
    public boolean registrarMovimiento(Movimiento movimiento);
}
