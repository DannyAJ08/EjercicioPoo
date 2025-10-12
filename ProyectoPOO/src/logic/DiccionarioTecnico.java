package logic;

import java.util.ArrayList;
import java.util.List;

public class DiccionarioTecnico {
    private List<PalabraTecnica> listaPalabras;

    public DiccionarioTecnico() {
        this.listaPalabras = new ArrayList<>();
    }

    public boolean agregarPalabra(PalabraTecnica p) {
        if (buscarPalabra(p.getPalabra()) != null) return false;
        listaPalabras.add(p);
        return true;
    }

    public boolean eliminarPalabra(String palabra) {
        PalabraTecnica found = buscarPalabra(palabra);
        if (found == null) return false;
        listaPalabras.remove(found);
        return true;
    }

    public PalabraTecnica buscarPalabra(String palabra) {
        for (PalabraTecnica p : listaPalabras) {
            if (p.getPalabra().equalsIgnoreCase(palabra)) return p;
        }
        return null;
    }

    public List<PalabraTecnica> listarPalabras() {
        return new ArrayList<>(listaPalabras);
    }
}
