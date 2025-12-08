package dao.impl;

import dao.ReservaDAO;
import model.Cliente;
import model.Habitacion;
import model.Reserva;
import utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAOImpl implements ReservaDAO {

    @Override
    public boolean guardar(Reserva r) throws Exception {
        String sql = "INSERT INTO reserva (cliente_id, habitacion_id, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, r.getCliente().getId());
            ps.setInt(2, r.getHabitacion().getId());
            ps.setDate(3, Date.valueOf(r.getFechaInicio()));
            ps.setDate(4, Date.valueOf(r.getFechaFin()));
            int affected = ps.executeUpdate();
            if (affected == 0) return false;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) r.setId(rs.getInt(1));
            }
            return true;
        }
    }

    @Override
    public List<Reserva> obtenerTodas() throws Exception {
        String sql = "SELECT r.id, r.fecha_inicio, r.fecha_fin, c.id as cid, c.nombre, c.correo, c.telefono, " +
                "h.id as hid, h.numero, h.piso, h.tipo, h.precio, h.disponible " +
                "FROM reserva r " +
                "JOIN cliente c ON r.cliente_id = c.id " +
                "JOIN habitacion h ON r.habitacion_id = h.id " +
                "ORDER BY r.fecha_inicio DESC";
        List<Reserva> lista = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente(rs.getInt("cid"), rs.getString("nombre"), rs.getString("correo"), rs.getString("telefono"));
                Habitacion h = new Habitacion(rs.getInt("hid"), rs.getInt("numero"), rs.getInt("piso"), rs.getString("tipo"), rs.getDouble("precio"), rs.getBoolean("disponible"));
                LocalDate inicio = rs.getDate("fecha_inicio").toLocalDate();
                LocalDate fin = rs.getDate("fecha_fin").toLocalDate();
                Reserva r = new Reserva(rs.getInt("id"), c, h, inicio, fin);
                lista.add(r);
            }
        }
        return lista;
    }
}
