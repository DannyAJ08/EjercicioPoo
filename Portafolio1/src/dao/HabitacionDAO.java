package dao;

import model.Habitacion;
import java.util.List;

public interface HabitacionDAO {
    boolean guardar(Habitacion h) throws Exception;
    List<Habitacion> obtenerTodas() throws Exception;
    Habitacion obtenerPorId(int id) throws Exception;
    boolean cambiarDisponibilidad(int id, boolean disponible) throws Exception;
}
