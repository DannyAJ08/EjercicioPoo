package bl.impl;

import bl.ReservaBL;
import dao.ReservaDAO;
import dao.impl.ReservaDAOImpl;
import model.Reserva;

import java.util.List;

public class ReservaBLImpl implements ReservaBL {
    private final ReservaDAO dao = new ReservaDAOImpl();

    @Override
    public boolean guardar(Reserva r) throws Exception {
        // Podrías validar que no haya solapamiento, etc. Por ahora guarda y espera que la habitación esté libre.
        return dao.guardar(r);
    }

    @Override
    public List<Reserva> obtenerTodas() throws Exception {
        return dao.obtenerTodas();
    }
}
