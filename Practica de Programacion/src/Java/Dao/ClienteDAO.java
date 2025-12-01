package Java.Dao;

import Java.Model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteDAO {
    int crearCliente(Cliente cliente) throws Exception;
    Optional<Cliente> buscarPorCorreo(String correo) throws Exception;
    Optional<Cliente> buscarPorId(int id) throws Exception;
    List<Cliente> listarClientes() throws Exception;
}
