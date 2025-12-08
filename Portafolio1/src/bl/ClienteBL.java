package bl;

import model.Cliente;
import java.util.List;

public interface ClienteBL {
    boolean guardar(Cliente cliente) throws Exception;
    List<Cliente> obtenerTodos() throws Exception;
    Cliente obtenerPorId(int id) throws Exception;
}
