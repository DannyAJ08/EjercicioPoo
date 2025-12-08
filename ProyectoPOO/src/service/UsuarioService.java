package service;

import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImpl;
import model.Usuario;
import java.util.List;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    public boolean registrarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()) {
            return false;
        }

        if (usuarioDAO.existeCorreo(usuario.getCorreo())) {
            System.out.println("Error: Ya existe un usuario con el correo " + usuario.getCorreo());
            return false;
        }

        return usuarioDAO.guardar(usuario);
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        return usuarioDAO.buscarPorCorreo(correo);
    }

    public boolean actualizarUsuario(Usuario usuario) {
        return usuarioDAO.actualizar(usuario);
    }

    public boolean eliminarUsuario(String correo) {
        return usuarioDAO.eliminar(correo);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarTodos();
    }

    public boolean validarCredenciales(String correo, String contrasenia) {
        Usuario usuario = usuarioDAO.buscarPorCorreo(correo);
        return usuario != null && usuario.getContrasenia().equals(contrasenia);
    }

    public List<Usuario> buscarUsuariosPorRol(String rol) {
        List<Usuario> todos = usuarioDAO.listarTodos();
        return todos.stream()
                .filter(u -> u.getRol().equalsIgnoreCase(rol))
                .toList();
    }
}