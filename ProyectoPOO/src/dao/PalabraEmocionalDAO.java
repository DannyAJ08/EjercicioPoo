package dao;

import model.PalabraEmocional;
import java.util.List;

public interface PalabraEmocionalDAO {
    boolean guardar(PalabraEmocional palabra);
    PalabraEmocional buscarPorPalabra(String palabra);
    boolean actualizar(PalabraEmocional palabra);
    boolean eliminar(String palabra);
    List<PalabraEmocional> listarTodos();
    List<String> obtenerEmocionesUnicas();
}