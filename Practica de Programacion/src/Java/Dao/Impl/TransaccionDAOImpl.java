package Java.Dao.Impl;

import Java.Dao.TransaccionDAO;
import Java.Model.Transaccion;
import Java.Util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDAOImpl implements TransaccionDAO {

    @Override
    public int registrarTransaccion(Transaccion t) throws Exception {
        String sql = "INSERT INTO transacciones (cuenta_id, tipo, monto, descripcion) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, t.getCuentaId());
            ps.setString(2, t.getTipo());
            ps.setDouble(3, t.getMonto());
            ps.setString(4, t.getDescripcion());
            int affected = ps.executeUpdate();
            if (affected == 0) return -1;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    t.setId(rs.getInt(1));
                    return t.getId();
                }
            }
            return -1;
        } catch (SQLException ex) {
            throw new Exception("Error al registrar transacción: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Transaccion> listarPorCuentaId(int cuentaId) throws Exception {
        String sql = "SELECT * FROM transacciones WHERE cuenta_id = ? ORDER BY fecha DESC";
        List<Transaccion> lista = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cuentaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transaccion t = new Transaccion();
                    t.setId(rs.getInt("id"));
                    t.setCuentaId(rs.getInt("cuenta_id"));
                    t.setTipo(rs.getString("tipo"));
                    t.setMonto(rs.getDouble("monto"));
                    Timestamp ts = rs.getTimestamp("fecha");
                    if (ts != null) t.setFecha(ts.toLocalDateTime());
                    t.setDescripcion(rs.getString("descripcion"));
                    lista.add(t);
                }
            }
            return lista;
        } catch (SQLException ex) {
            throw new Exception("Error al listar transacciones: " + ex.getMessage(), ex);
        }
    }
}
