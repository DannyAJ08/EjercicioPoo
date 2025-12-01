package Java.Dao.Impl;

import Java.Dao.ClienteDAO;
import Java.Model.Cliente;
import Java.Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public int crearCliente(Cliente cliente) throws Exception {
        String sql = "INSERT INTO usuarios (tipo, nombreCompleto, cedula, correoElectronico, profesion, direccion, sexo, contrasenia) VALUES ('CLIENTE', ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cliente.getNombreCompleto());
            ps.setString(2, cliente.getCedula());
            ps.setString(3, cliente.getCorreoElectronico());
            ps.setString(4, cliente.getProfesion());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getSexo());
            ps.setString(7, cliente.getContrasenia());
            int affected = ps.executeUpdate();
            if (affected == 0) return -1;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                    return cliente.getId();
                }
            }
            return -1;
        } catch (SQLException ex) {
            throw new Exception("Error al crear cliente: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<Cliente> buscarPorCorreo(String correo) throws Exception {
        String sql = "SELECT id, nombreCompleto, cedula, correoElectronico, profesion, direccion, sexo, contrasenia FROM usuarios WHERE correoElectronico = ? AND tipo = 'CLIENTE'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente c = new Cliente(
                            rs.getInt("id"),
                            rs.getString("nombreCompleto"),
                            rs.getString("cedula"),
                            rs.getString("correoElectronico"),
                            rs.getString("contrasenia"),
                            rs.getString("sexo"),
                            rs.getString("profesion"),
                            rs.getString("direccion")
                    );
                    return Optional.of(c);
                }
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new Exception("Error al buscar cliente por correo: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<Cliente> buscarPorId(int id) throws Exception {
        String sql = "SELECT id, nombreCompleto, cedula, correoElectronico, profesion, direccion, sexo, contrasenia FROM usuarios WHERE id = ? AND tipo = 'CLIENTE'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente c = new Cliente(
                            rs.getInt("id"),
                            rs.getString("nombreCompleto"),
                            rs.getString("cedula"),
                            rs.getString("correoElectronico"),
                            rs.getString("contrasenia"),
                            rs.getString("sexo"),
                            rs.getString("profesion"),
                            rs.getString("direccion")
                    );
                    return Optional.of(c);
                }
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new Exception("Error al buscar cliente por id: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Cliente> listarClientes() throws Exception {
        String sql = "SELECT id, nombreCompleto, cedula, correoElectronico, profesion, direccion, sexo FROM usuarios WHERE tipo = 'CLIENTE'";
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombreCompleto"),
                        rs.getString("cedula"),
                        rs.getString("correoElectronico"),
                        null,
                        rs.getString("sexo"),
                        rs.getString("profesion"),
                        rs.getString("direccion")
                );
                lista.add(c);
            }
            return lista;
        } catch (SQLException ex) {
            throw new Exception("Error al listar clientes: " + ex.getMessage(), ex);
        }
    }
}
