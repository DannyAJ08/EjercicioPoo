package dao.impl;

import dao.PalabraEmocionalDAO;
import model.PalabraEmocional;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PalabraEmocionalDAOImpl implements PalabraEmocionalDAO {

    @Override
    public boolean guardar(PalabraEmocional palabra) {
        String sql = "INSERT INTO palabras_emocionales (palabra, emocion) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, palabra.getPalabra());
            pstmt.setString(2, palabra.getEmocion());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar palabra emocional: " + e.getMessage());
            return false;
        }
    }

    @Override
    public PalabraEmocional buscarPorPalabra(String palabra) {
        String sql = "SELECT * FROM palabras_emocionales WHERE palabra = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, palabra);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return crearPalabraDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar palabra emocional: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(PalabraEmocional palabra) {
        String sql = "UPDATE palabras_emocionales SET emocion = ? WHERE palabra = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, palabra.getEmocion());
            pstmt.setString(2, palabra.getPalabra());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar palabra emocional: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(String palabra) {
        String sql = "DELETE FROM palabras_emocionales WHERE palabra = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, palabra);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar palabra emocional: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<PalabraEmocional> listarTodos() {
        List<PalabraEmocional> palabras = new ArrayList<>();
        String sql = "SELECT * FROM palabras_emocionales ORDER BY palabra";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                palabras.add(crearPalabraDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar palabras emocionales: " + e.getMessage());
        }
        return palabras;
    }

    @Override
    public List<String> obtenerEmocionesUnicas() {
        List<String> emociones = new ArrayList<>();
        String sql = "SELECT DISTINCT emocion FROM palabras_emocionales ORDER BY emocion";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                emociones.add(rs.getString("emocion"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener emociones únicas: " + e.getMessage());
        }
        return emociones;
    }

    private PalabraEmocional crearPalabraDesdeResultSet(ResultSet rs) throws SQLException {
        PalabraEmocional palabra = new PalabraEmocional();
        palabra.setPalabra(rs.getString("palabra"));
        palabra.setEmocion(rs.getString("emocion"));
        return palabra;
    }
}