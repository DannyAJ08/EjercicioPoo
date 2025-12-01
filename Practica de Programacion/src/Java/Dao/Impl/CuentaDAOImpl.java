package Java.Dao.Impl;

import Java.Dao.CuentaDAO;
import Java.Model.*;
import Java.Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CuentaDAOImpl implements CuentaDAO {

    @Override
    public int crearCuenta(Cuenta cuenta) throws Exception {
        String sql = "INSERT INTO cuentas (cliente_id, tipoCuenta, numeroCuenta, saldo, activa, porcentajeInteres, limiteCredito, tipoCredito) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cuenta.getClienteId());
            ps.setString(2, cuenta.getTipoCuenta());
            ps.setString(3, cuenta.getNumeroCuenta());
            ps.setDouble(4, cuenta.getSaldo());
            ps.setBoolean(5, cuenta.isActiva());
            if (cuenta instanceof CuentaAhorro) {
                ps.setDouble(6, ((CuentaAhorro) cuenta).getPorcentajeInteres());
                ps.setNull(7, Types.DOUBLE);
                ps.setNull(8, Types.VARCHAR);
            } else if (cuenta instanceof CuentaDebito) {
                ps.setDouble(6, ((CuentaDebito) cuenta).getPorcentajeInteres());
                ps.setNull(7, Types.DOUBLE);
                ps.setNull(8, Types.VARCHAR);
            } else if (cuenta instanceof CuentaCredito) {
                ps.setNull(6, Types.DOUBLE);
                ps.setDouble(7, ((CuentaCredito) cuenta).getLimiteCredito());
                ps.setString(8, ((CuentaCredito) cuenta).getTipoCredito());
            } else {
                ps.setNull(6, Types.DOUBLE);
                ps.setNull(7, Types.DOUBLE);
                ps.setNull(8, Types.VARCHAR);
            }
            int affected = ps.executeUpdate();
            if (affected == 0) return -1;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cuenta.setId(rs.getInt(1));
                    return cuenta.getId();
                }
            }
            return -1;
        } catch (SQLException ex) {
            throw new Exception("Error al crear cuenta: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<Cuenta> obtenerPorNumero(String numeroCuenta) throws Exception {
        String sql = "SELECT * FROM cuentas WHERE numeroCuenta = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, numeroCuenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearCuenta(rs));
                }
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new Exception("Error al obtener cuenta por numero: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Cuenta> listarTodas() throws Exception {
        String sql = "SELECT * FROM cuentas";
        List<Cuenta> lista = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearCuenta(rs));
            }
            return lista;
        } catch (SQLException ex) {
            throw new Exception("Error al listar cuentas: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Cuenta> listarPorClienteId(int clienteId) throws Exception {
        String sql = "SELECT * FROM cuentas WHERE cliente_id = ?";
        List<Cuenta> lista = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearCuenta(rs));
                }
            }
            return lista;
        } catch (SQLException ex) {
            throw new Exception("Error al listar cuentas por cliente: " + ex.getMessage(), ex);
        }
    }

    @Override
    public boolean actualizarCuenta(Cuenta cuenta) throws Exception {
        String sql = "UPDATE cuentas SET saldo = ?, activa = ?, porcentajeInteres = ?, limiteCredito = ?, tipoCredito = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, cuenta.getSaldo());
            ps.setBoolean(2, cuenta.isActiva());
            if (cuenta instanceof CuentaAhorro) {
                ps.setDouble(3, ((CuentaAhorro) cuenta).getPorcentajeInteres());
                ps.setNull(4, Types.DOUBLE);
                ps.setNull(5, Types.VARCHAR);
            } else if (cuenta instanceof CuentaDebito) {
                ps.setDouble(3, ((CuentaDebito) cuenta).getPorcentajeInteres());
                ps.setNull(4, Types.DOUBLE);
                ps.setNull(5, Types.VARCHAR);
            } else if (cuenta instanceof CuentaCredito) {
                ps.setNull(3, Types.DOUBLE);
                ps.setDouble(4, ((CuentaCredito) cuenta).getLimiteCredito());
                ps.setString(5, ((CuentaCredito) cuenta).getTipoCredito());
            } else {
                ps.setNull(3, Types.DOUBLE);
                ps.setNull(4, Types.DOUBLE);
                ps.setNull(5, Types.VARCHAR);
            }
            ps.setInt(6, cuenta.getId());
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException ex) {
            throw new Exception("Error al actualizar cuenta: " + ex.getMessage(), ex);
        }
    }

    private Cuenta mapearCuenta(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int clienteId = rs.getInt("cliente_id");
        String tipo = rs.getString("tipoCuenta");
        String numero = rs.getString("numeroCuenta");
        double saldo = rs.getDouble("saldo");
        boolean activa = rs.getBoolean("activa");
        switch (tipo) {
            case "AHORRO":
                double interes = rs.getDouble("porcentajeInteres");
                return new CuentaAhorro(id, clienteId, numero, saldo, activa, interes);
            case "DEBITO":
                double interesD = rs.getDouble("porcentajeInteres");
                return new CuentaDebito(id, clienteId, numero, saldo, activa, interesD);
            case "CREDITO":
                double limite = rs.getDouble("limiteCredito");
                String tipoCredito = rs.getString("tipoCredito");
                return new CuentaCredito(id, clienteId, numero, saldo, activa, limite, tipoCredito);
            default:
                return null;
        }
    }
}
