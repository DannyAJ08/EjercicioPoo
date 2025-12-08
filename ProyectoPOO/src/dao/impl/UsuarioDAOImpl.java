package dao.impl;

import dao.UsuarioDAO;
import model.Usuario;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public boolean guardar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (correo, nombre_completo, contrasenia, telefono, rol) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getCorreo());
            pstmt.setString(2, usuario.getNombreCompleto());
            pstmt.setString(3, usuario.getContrasenia());
            pstmt.setString(4, usuario.getTelefono());
            pstmt.setString(5, usuario.getRol());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return crearUsuarioDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre_completo = ?, contrasenia = ?, telefono = ?, rol = ? WHERE correo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombreCompleto());
            pstmt.setString(2, usuario.getContrasenia());
            pstmt.setString(3, usuario.getTelefono());
            pstmt.setString(4, usuario.getRol());
            pstmt.setString(5, usuario.getCorreo());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(String correo) {
        String sql = "DELETE FROM usuarios WHERE correo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ORDER BY nombre_completo";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                usuarios.add(crearUsuarioDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    @Override
    public boolean existeCorreo(String correo) {
        return buscarPorCorreo(correo) != null;
    }

    private Usuario crearUsuarioDesdeResultSet(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(rs.getString("nombre_completo"));
        usuario.setCorreo(rs.getString("correo"));
        usuario.setContrasenia(rs.getString("contrasenia"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setRol(rs.getString("rol"));
        return usuario;
    }
}