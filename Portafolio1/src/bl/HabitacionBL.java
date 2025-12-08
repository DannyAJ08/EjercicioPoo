package bl;

import model.Habitacion;
import java.util.List;

public interface HabitacionBL {
    boolean guardar(Habitacion h) throws Exception;
    List<Habitacion> obtenerTodas() throws Exception;
    Habitacion obtenerPorId(int id) throws Exception;
    boolean cambiarDisponibilidad(int id, boolean disponible) throws Exception;
}
