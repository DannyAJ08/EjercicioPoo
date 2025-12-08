package dao.impl;

import dao.HabitacionDAO;
import model.Habitacion;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAOImpl implements HabitacionDAO {

    @Override
    public boolean guardar(Habitacion h) throws Exception {
        String sql = "INSERT INTO habitacion (numero, piso, tipo, precio, disponible) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, h.getNumero());
            ps.setInt(2, h.getPiso());
            ps.setString(3, h.getTipo());
            ps.setDouble(4, h.getPrecio());
            ps.setBoolean(5, h.isDisponible());
            int affected = ps.executeUpdate();
            if (affected == 0) return false;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) h.setId(rs.getInt(1));
            }
            return true;
        }
    }

    @Override
    public List<Habitacion> obtenerTodas() throws Exception {
        String sql = "SELECT id, numero, piso, tipo, precio, disponible FROM habitacion";
        List<Habitacion> lista = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Habitacion h = new Habitacion(
                        rs.getInt("id"),
                        rs.getInt("numero"),
                        rs.getInt("piso"),
                        rs.getString("tipo"),
                        rs.getDouble("precio"),
                        rs.getBoolean("disponible")
                );
                lista.add(h);
            }
        }
        return lista;
    }

    @Override
    public Habitacion obtenerPorId(int id) throws Exception {
        String sql = "SELECT id, numero, piso, tipo, precio, disponible FROM habitacion WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Habitacion(
                            rs.getInt("id"),
                            rs.getInt("numero"),
                            rs.getInt("piso"),
                            rs.getString("tipo"),
                            rs.getDouble("precio"),
                            rs.getBoolean("disponible")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public boolean cambiarDisponibilidad(int id, boolean disponible) throws Exception {
        String sql = "UPDATE habitacion SET disponible = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, disponible);
            ps.setInt(2, id);
            int r = ps.executeUpdate();
            return r > 0;
        }
    }
}
