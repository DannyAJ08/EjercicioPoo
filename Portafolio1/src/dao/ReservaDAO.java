package dao;

import model.Reserva;
import java.util.List;

public interface ReservaDAO {
    boolean guardar(Reserva r) throws Exception;
    List<Reserva> obtenerTodas() throws Exception;
}
