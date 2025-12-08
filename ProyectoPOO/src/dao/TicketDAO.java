package dao;

import model.Ticket;
import java.util.List;

public interface TicketDAO {
    boolean guardar(Ticket ticket);
    Ticket buscarPorId(int id);
    boolean actualizar(Ticket ticket);
    boolean eliminar(int id);
    List<Ticket> listarTodos();
    List<Ticket> buscarPorEstado(String estado);
    List<Ticket> buscarPorUsuario(String correoUsuario);
}