package bl.impl;

import bl.ClienteBL;
import dao.ClienteDAO;
import dao.impl.ClienteDAOImpl;
import model.Cliente;

import java.util.List;

public class ClienteBLImpl implements ClienteBL {
    private final ClienteDAO dao = new ClienteDAOImpl();

    @Override
    public boolean guardar(Cliente cliente) throws Exception {
        // Aquí podrías agregar reglas de negocio (ej: validar email)
        return dao.guardar(cliente);
    }

    @Override
    public List<Cliente> obtenerTodos() throws Exception {
        return dao.obtenerTodos();
    }

    @Override
    public Cliente obtenerPorId(int id) throws Exception {
        return dao.obtenerPorId(id);
    }
}
