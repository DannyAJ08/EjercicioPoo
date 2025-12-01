package Java.Dao.Impl;

import Java.Dao.AdministradorDAO;
import Java.Model.Administrador;
import Java.Util.DBConnection;

import java.sql.*;
import java.util.Optional;

public class AdministradorDAOImpl implements AdministradorDAO {

    @Override
    public boolean existeAdministrador() throws Exception {
        String sql = "SELECT COUNT(*) as total FROM usuarios WHERE tipo = 'ADMIN'";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
            return false;
        } catch (SQLException ex) {
            throw new Exception("Error al verificar administrador: " + ex.getMessage(), ex);
        }
    }

    @Override
    public int crearAdministrador(Administrador admin) throws Exception {
        String sql = "INSERT INTO usuarios (tipo, nombreCompleto, cedula, correoElectronico, contrasenia) VALUES ('ADMIN', ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, admin.getNombreCompleto());
            ps.setString(2, admin.getCedula());
            ps.setString(3, admin.getCorreoElectronico());
            ps.setString(4, admin.getContrasenia());
            int affected = ps.executeUpdate();
            if (affected == 0) return -1;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    admin.setId(rs.getInt(1));
                    return admin.getId();
                }
            }
            return -1;
        } catch (SQLException ex) {
            throw new Exception("Error al crear administrador: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<Administrador> buscarPorCorreo(String correo) throws Exception {
        String sql = "SELECT id, nombreCompleto, cedula, correoElectronico, contrasenia FROM usuarios WHERE correoElectronico = ? AND tipo = 'ADMIN'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Administrador a = new Administrador(
                            rs.getInt("id"),
                            rs.getString("nombreCompleto"),
                            rs.getString("cedula"),
                            rs.getString("correoElectronico"),
                            rs.getString("contrasenia")
                    );
                    return Optional.of(a);
                }
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new Exception("Error al buscar admin por correo: " + ex.getMessage(), ex);
        }
    }
}
