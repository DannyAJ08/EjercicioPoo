package service;

import dao.PalabraEmocionalDAO;
import dao.PalabraTecnicaDAO;
import dao.impl.PalabraEmocionalDAOImpl;
import dao.impl.PalabraTecnicaDAOImpl;
import model.PalabraEmocional;
import model.PalabraTecnica;
import java.util.List;

public class DiccionarioService {
    private PalabraEmocionalDAO palabraEmocionalDAO;
    private PalabraTecnicaDAO palabraTecnicaDAO;

    public DiccionarioService() {
        this.palabraEmocionalDAO = new PalabraEmocionalDAOImpl();
        this.palabraTecnicaDAO = new PalabraTecnicaDAOImpl();
    }

    // Métodos para Palabras Emocionales
    public boolean agregarPalabraEmocional(PalabraEmocional palabra) {
        if (palabra == null || palabra.getPalabra() == null || palabra.getPalabra().trim().isEmpty()) {
            return false;
        }

        return palabraEmocionalDAO.guardar(palabra);
    }

    public PalabraEmocional buscarPalabraEmocional(String palabra) {
        return palabraEmocionalDAO.buscarPorPalabra(palabra);
    }

    public boolean actualizarPalabraEmocional(PalabraEmocional palabra) {
        return palabraEmocionalDAO.actualizar(palabra);
    }

    public boolean eliminarPalabraEmocional(String palabra) {
        return palabraEmocionalDAO.eliminar(palabra);
    }

    public List<PalabraEmocional> listarPalabrasEmocionales() {
        return palabraEmocionalDAO.listarTodos();
    }

    public List<String> obtenerEmocionesUnicas() {
        return palabraEmocionalDAO.obtenerEmocionesUnicas();
    }

    // Métodos para Palabras Técnicas
    public boolean agregarPalabraTecnica(PalabraTecnica palabra) {
        if (palabra == null || palabra.getPalabra() == null || palabra.getPalabra().trim().isEmpty()) {
            return false;
        }

        return palabraTecnicaDAO.guardar(palabra);
    }

    public PalabraTecnica buscarPalabraTecnica(String palabra) {
        return palabraTecnicaDAO.buscarPorPalabra(palabra);
    }

    public boolean actualizarPalabraTecnica(PalabraTecnica palabra) {
        return palabraTecnicaDAO.actualizar(palabra);
    }

    public boolean eliminarPalabraTecnica(String palabra) {
        return palabraTecnicaDAO.eliminar(palabra);
    }

    public List<PalabraTecnica> listarPalabrasTecnicas() {
        return palabraTecnicaDAO.listarTodos();
    }

    public List<String> obtenerCategoriasUnicas() {
        return palabraTecnicaDAO.obtenerCategoriasUnicas();
    }
}