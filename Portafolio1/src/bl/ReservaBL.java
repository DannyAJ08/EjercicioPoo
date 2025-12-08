package bl;

import model.Reserva;
import java.util.List;

public interface ReservaBL {
    boolean guardar(Reserva r) throws Exception;
    List<Reserva> obtenerTodas() throws Exception;
}
