package dao.impl;

import dao.DepartamentoDAO;
import model.Departamento;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAOImpl implements DepartamentoDAO {

    @Override
    public boolean guardar(Departamento departamento) {
        String sql = "INSERT INTO departamentos (nombre, descripcion, contacto) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, departamento.getNombre());
            pstmt.setString(2, departamento.getDescripcion());
            pstmt.setString(3, departamento.getContacto());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar departamento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Departamento buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM departamentos WHERE nombre = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return crearDepartamentoDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar departamento: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(Departamento departamento) {
        String sql = "UPDATE departamentos SET descripcion = ?, contacto = ? WHERE nombre = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, departamento.getDescripcion());
            pstmt.setString(2, departamento.getContacto());
            pstmt.setString(3, departamento.getNombre());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar departamento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(String nombre) {
        String sql = "DELETE FROM departamentos WHERE nombre = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar departamento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Departamento> listarTodos() {
        List<Departamento> departamentos = new ArrayList<>();
        String sql = "SELECT * FROM departamentos ORDER BY nombre";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                departamentos.add(crearDepartamentoDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar departamentos: " + e.getMessage());
        }
        return departamentos;
    }

    @Override
    public boolean existeNombre(String nombre) {
        return buscarPorNombre(nombre) != null;
    }

    private Departamento crearDepartamentoDesdeResultSet(ResultSet rs) throws SQLException {
        Departamento departamento = new Departamento();
        departamento.setNombre(rs.getString("nombre"));
        departamento.setDescripcion(rs.getString("descripcion"));
        departamento.setContacto(rs.getString("contacto"));
        return departamento;
    }
}