package dao.impl;

import dao.PalabraTecnicaDAO;
import model.PalabraTecnica;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PalabraTecnicaDAOImpl implements PalabraTecnicaDAO {

    @Override
    public boolean guardar(PalabraTecnica palabra) {
        String sql = "INSERT INTO palabras_tecnicas (palabra, categoria) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, palabra.getPalabra());
            pstmt.setString(2, palabra.getCategoria());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar palabra técnica: " + e.getMessage());
            return false;
        }
    }

    @Override
    public PalabraTecnica buscarPorPalabra(String palabra) {
        String sql = "SELECT * FROM palabras_tecnicas WHERE palabra = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, palabra);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return crearPalabraDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar palabra técnica: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(PalabraTecnica palabra) {
        String sql = "UPDATE palabras_tecnicas SET categoria = ? WHERE palabra = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, palabra.getCategoria());
            pstmt.setString(2, palabra.getPalabra());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar palabra técnica: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(String palabra) {
        String sql = "DELETE FROM palabras_tecnicas WHERE palabra = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, palabra);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar palabra técnica: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<PalabraTecnica> listarTodos() {
        List<PalabraTecnica> palabras = new ArrayList<>();
        String sql = "SELECT * FROM palabras_tecnicas ORDER BY palabra";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                palabras.add(crearPalabraDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar palabras técnicas: " + e.getMessage());
        }
        return palabras;
    }

    @Override
    public List<String> obtenerCategoriasUnicas() {
        List<String> categorias = new ArrayList<>();
        String sql = "SELECT DISTINCT categoria FROM palabras_tecnicas ORDER BY categoria";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                categorias.add(rs.getString("categoria"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener categorías únicas: " + e.getMessage());
        }
        return categorias;
    }

    private PalabraTecnica crearPalabraDesdeResultSet(ResultSet rs) throws SQLException {
        PalabraTecnica palabra = new PalabraTecnica();
        palabra.setPalabra(rs.getString("palabra"));
        palabra.setCategoria(rs.getString("categoria"));
        return palabra;
    }
}