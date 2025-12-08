package service;

import dao.TicketDAO;
import dao.impl.TicketDAOImpl;
import model.Ticket;
import model.Usuario;
import model.Departamento;
import java.util.List;

public class TicketService {
    private TicketDAO ticketDAO;

    public TicketService() {
        this.ticketDAO = new TicketDAOImpl();
    }

    public boolean registrarTicket(Ticket ticket) {
        if (ticket == null || ticket.getAsunto() == null || ticket.getAsunto().trim().isEmpty()) {
            return false;
        }

        return ticketDAO.guardar(ticket);
    }

    public Ticket buscarTicketPorId(int id) {
        return ticketDAO.buscarPorId(id);
    }

    public boolean actualizarTicket(Ticket ticket) {
        return ticketDAO.actualizar(ticket);
    }

    public boolean eliminarTicket(int id) {
        return ticketDAO.eliminar(id);
    }

    public List<Ticket> listarTickets() {
        return ticketDAO.listarTodos();
    }

    public List<Ticket> buscarTicketsPorEstado(String estado) {
        return ticketDAO.buscarPorEstado(estado);
    }

    public List<Ticket> buscarTicketsPorUsuario(String correoUsuario) {
        return ticketDAO.buscarPorUsuario(correoUsuario);
    }

    public boolean cambiarEstadoTicket(int id, String nuevoEstado) {
        Ticket ticket = ticketDAO.buscarPorId(id);
        if (ticket == null) {
            return false;
        }

        ticket.setEstado(nuevoEstado);
        return ticketDAO.actualizar(ticket);
    }
}