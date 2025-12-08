package service;

import model.PalabraEmocional;
import model.PalabraTecnica;
import utils.BagOfWords;
import java.util.*;

public class BoWService {
    private DiccionarioService diccionarioService;

    public BoWService() {
        this.diccionarioService = new DiccionarioService();
    }

    public Map<String, Integer> analizarEmociones(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return new HashMap<>();
        }

        List<PalabraEmocional> palabrasEmocionales = diccionarioService.listarPalabrasEmocionales();
        Map<String, Integer> emociones = new HashMap<>();

        String textoNormalizado = texto.toLowerCase();

        for (PalabraEmocional pe : palabrasEmocionales) {
            String palabra = pe.getPalabra().toLowerCase();
            String emocion = pe.getEmocion();

            int frecuencia = contarOcurrencias(textoNormalizado, palabra);
            if (frecuencia > 0) {
                emociones.put(emocion, emociones.getOrDefault(emocion, 0) + frecuencia);
            }
        }

        return emociones;
    }

    public Map<String, Integer> analizarCategoriasTecnicas(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return new HashMap<>();
        }

        List<PalabraTecnica> palabrasTecnicas = diccionarioService.listarPalabrasTecnicas();
        Map<String, Integer> categorias = new HashMap<>();

        String textoNormalizado = texto.toLowerCase();

        for (PalabraTecnica pt : palabrasTecnicas) {
            String palabra = pt.getPalabra().toLowerCase();
            String categoria = pt.getCategoria();

            int frecuencia = contarOcurrencias(textoNormalizado, palabra);
            if (frecuencia > 0) {
                categorias.put(categoria, categorias.getOrDefault(categoria, 0) + frecuencia);
            }
        }

        return categorias;
    }

    public List<String> obtenerEmocionesOrdenadas(String texto) {
        Map<String, Integer> emociones = analizarEmociones(texto);

        List<Map.Entry<String, Integer>> lista = new ArrayList<>(emociones.entrySet());
        lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        List<String> resultado = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : lista) {
            resultado.add(entry.getKey() + " (" + entry.getValue() + " ocurrencias)");
        }

        return resultado;
    }

    public List<String> obtenerCategoriasOrdenadas(String texto) {
        Map<String, Integer> categorias = analizarCategoriasTecnicas(texto);

        List<Map.Entry<String, Integer>> lista = new ArrayList<>(categorias.entrySet());
        lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        List<String> resultado = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : lista) {
            resultado.add(entry.getKey() + " (" + entry.getValue() + " ocurrencias)");
        }

        return resultado;
    }

    private int contarOcurrencias(String texto, String palabra) {
        int contador = 0;
        int indice = 0;

        while ((indice = texto.indexOf(palabra, indice)) != -1) {
            contador++;
            indice += palabra.length();
        }

        return contador;
    }
}