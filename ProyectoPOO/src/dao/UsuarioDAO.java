package dao;

import model.Usuario;
import java.util.List;

public interface UsuarioDAO {
    boolean guardar(Usuario usuario);
    Usuario buscarPorCorreo(String correo);
    boolean actualizar(Usuario usuario);
    boolean eliminar(String correo);
    List<Usuario> listarTodos();
    boolean existeCorreo(String correo);
}