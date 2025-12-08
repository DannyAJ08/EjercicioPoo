package dao;

import model.Cliente;
import java.util.List;

public interface ClienteDAO {
    boolean guardar(Cliente cliente) throws Exception;
    List<Cliente> obtenerTodos() throws Exception;
    Cliente obtenerPorId(int id) throws Exception;
}
