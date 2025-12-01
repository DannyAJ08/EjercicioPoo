package Java.Dao;

import Java.Model.Cuenta;
import java.util.List;
import java.util.Optional;

public interface CuentaDAO {
    int crearCuenta(Cuenta cuenta) throws Exception;
    Optional<Cuenta> obtenerPorNumero(String numeroCuenta) throws Exception;
    List<Cuenta> listarTodas() throws Exception;
    List<Cuenta> listarPorClienteId(int clienteId) throws Exception;
    boolean actualizarCuenta(Cuenta cuenta) throws Exception;
}
