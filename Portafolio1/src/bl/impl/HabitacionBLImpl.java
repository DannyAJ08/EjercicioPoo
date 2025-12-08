package bl.impl;

import bl.HabitacionBL;
import dao.HabitacionDAO;
import dao.impl.HabitacionDAOImpl;
import model.Habitacion;

import java.util.List;

public class HabitacionBLImpl implements HabitacionBL {
    private final HabitacionDAO dao = new HabitacionDAOImpl();

    @Override
    public boolean guardar(Habitacion h) throws Exception {
        // Reglas negocio: ej: no duplicar numero de habitación podría implementarse
        return dao.guardar(h);
    }

    @Override
    public List<Habitacion> obtenerTodas() throws Exception {
        return dao.obtenerTodas();
    }

    @Override
    public Habitacion obtenerPorId(int id) throws Exception {
        return dao.obtenerPorId(id);
    }

    @Override
    public boolean cambiarDisponibilidad(int id, boolean disponible) throws Exception {
        return dao.cambiarDisponibilidad(id, disponible);
    }
}
