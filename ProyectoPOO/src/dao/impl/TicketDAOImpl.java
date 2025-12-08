package dao.impl;

import dao.TicketDAO;
import dao.UsuarioDAO;
import dao.DepartamentoDAO;
import dao.impl.UsuarioDAOImpl;
import dao.impl.DepartamentoDAOImpl;
import model.Ticket;
import model.Usuario;
import model.Departamento;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    private DepartamentoDAO departamentoDAO = new DepartamentoDAOImpl();

    @Override
    public boolean guardar(Ticket ticket) {
        String sql = "INSERT INTO tickets (asunto, descripcion, estado, usuario_correo, departamento_nombre) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, ticket.getAsunto());
            pstmt.setString(2, ticket.getDescripcion());
            pstmt.setString(3, ticket.getEstado());
            pstmt.setString(4, ticket.getUsuario() != null ? ticket.getUsuario().getCorreo() : null);
            pstmt.setString(5, ticket.getDepartamento() != null ? ticket.getDepartamento().getNombre() : null);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        ticket.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar ticket: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Ticket buscarPorId(int id) {
        String sql = "SELECT * FROM tickets WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return crearTicketDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar ticket: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(Ticket ticket) {
        String sql = "UPDATE tickets SET asunto = ?, descripcion = ?, estado = ?, usuario_correo = ?, departamento_nombre = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ticket.getAsunto());
            pstmt.setString(2, ticket.getDescripcion());
            pstmt.setString(3, ticket.getEstado());
            pstmt.setString(4, ticket.getUsuario() != null ? ticket.getUsuario().getCorreo() : null);
            pstmt.setString(5, ticket.getDepartamento() != null ? ticket.getDepartamento().getNombre() : null);
            pstmt.setInt(6, ticket.getId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar ticket: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM tickets WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar ticket: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Ticket> listarTodos() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets ORDER BY fecha_creacion DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tickets.add(crearTicketDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar tickets: " + e.getMessage());
        }
        return tickets;
    }

    @Override
    public List<Ticket> buscarPorEstado(String estado) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE estado = ? ORDER BY fecha_creacion DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estado);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                tickets.add(crearTicketDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar tickets por estado: " + e.getMessage());
        }
        return tickets;
    }

    @Override
    public List<Ticket> buscarPorUsuario(String correoUsuario) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE usuario_correo = ? ORDER BY fecha_creacion DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correoUsuario);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                tickets.add(crearTicketDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar tickets por usuario: " + e.getMessage());
        }
        return tickets;
    }

    private Ticket crearTicketDesdeResultSet(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setAsunto(rs.getString("asunto"));
        ticket.setDescripcion(rs.getString("descripcion"));
        ticket.setEstado(rs.getString("estado"));
        ticket.setFechaCreacion(rs.getTimestamp("fecha_creacion"));

        // Obtener usuario y departamento
        String correoUsuario = rs.getString("usuario_correo");
        if (correoUsuario != null) {
            ticket.setUsuario(usuarioDAO.buscarPorCorreo(correoUsuario));
        }

        String nombreDepto = rs.getString("departamento_nombre");
        if (nombreDepto != null) {
            ticket.setDepartamento(departamentoDAO.buscarPorNombre(nombreDepto));
        }

        return ticket;
    }
}