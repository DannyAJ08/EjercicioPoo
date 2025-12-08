package dao;

import model.PalabraTecnica;
import java.util.List;

public interface PalabraTecnicaDAO {
    boolean guardar(PalabraTecnica palabra);
    PalabraTecnica buscarPorPalabra(String palabra);
    boolean actualizar(PalabraTecnica palabra);
    boolean eliminar(String palabra);
    List<PalabraTecnica> listarTodos();
    List<String> obtenerCategoriasUnicas();
}