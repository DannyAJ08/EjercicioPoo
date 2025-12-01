package Java.Dao;

import Java.Model.Transaccion;
import java.util.List;

public interface TransaccionDAO {
    int registrarTransaccion(Transaccion t) throws Exception;
    List<Transaccion> listarPorCuentaId(int cuentaId) throws Exception;
}
