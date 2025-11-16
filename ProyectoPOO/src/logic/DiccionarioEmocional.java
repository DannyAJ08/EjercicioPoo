package logic;

import java.util.ArrayList;
import java.util.List;

public class DiccionarioEmocional {
    private final List<PalabraEmocional> listaPalabras;

    public DiccionarioEmocional() {
        this.listaPalabras = new ArrayList<>();
    }

    public boolean agregarPalabra(PalabraEmocional p) {
        if (buscarPalabra(p.getPalabra()) != null) return false;
        listaPalabras.add(p);
        return true;
    }

    public boolean eliminarPalabra(String palabra) {
        PalabraEmocional found = buscarPalabra(palabra);
        if (found == null) return false;
        listaPalabras.remove(found);
        return true;
    }

    public PalabraEmocional buscarPalabra(String palabra) {
        if (palabra == null) return null;
        for (PalabraEmocional p : listaPalabras) {
            if (p.getPalabra().equalsIgnoreCase(palabra)) return p;
        }
        return null;
    }

    public List<PalabraEmocional> listarPalabras() {
        return new ArrayList<>(listaPalabras);
    }

    public List<PalabraEmocional> getListaPalabras() {
        return listarPalabras();
    }
}
