package dao.impl;

import dao.ClienteDAO;
import model.Cliente;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public boolean guardar(Cliente cliente) throws Exception {
        String sql = "INSERT INTO cliente (nombre, correo, telefono) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCorreo());
            ps.setString(3, cliente.getTelefono());
            int affected = ps.executeUpdate();
            if (affected == 0) return false;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) cliente.setId(rs.getInt(1));
            }
            return true;
        }
    }

    @Override
    public List<Cliente> obtenerTodos() throws Exception {
        String sql = "SELECT id, nombre, correo, telefono FROM cliente";
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );
                lista.add(c);
            }
        }
        return lista;
    }

    @Override
    public Cliente obtenerPorId(int id) throws Exception {
        String sql = "SELECT id, nombre, correo, telefono FROM cliente WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("telefono")
                    );
                }
            }
        }
        return null;
    }
}
