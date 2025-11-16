package logic;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

public class BagOfWords {

    private static final Set<String> STOPWORDS = new HashSet<>(Arrays.asList(
            "la","el","los","las","un","una","unos","unas","de","del","en","y","o","que","por","para","con","sin",
            "se","es","esta","esta","está","al","lo","me","mi","tu","su","le","les","nos","pero","muy","no","sí",
            "a","como","su","esta","ha","han","hay","fue","ser","son","cuando","donde","quien","porque","porqué"
    ));

    private static final Pattern PUNCT_PATTERN = Pattern.compile("\\p{Punct}");

    public static String normalize(String text) {
        if (text == null) return "";
        String lower = text.toLowerCase(Locale.ROOT);
        String noAccents = Normalizer.normalize(lower, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        String noPunct = PUNCT_PATTERN.matcher(noAccents).replaceAll(" ");
        return noPunct.replaceAll("\\s+", " ").trim();
    }

    public static List<String> tokenizeAndFilter(String text) {
        String norm = normalize(text);
        if (norm.isEmpty()) return Collections.emptyList();
        String[] toks = norm.split(" ");
        List<String> out = new ArrayList<>();
        for (String t : toks) {
            if (t.isBlank()) continue;
            if (STOPWORDS.contains(t)) continue;
            out.add(t);
        }
        return out;
    }

    public static Map<String, Integer> termFrequency(List<String> tokens) {
        Map<String, Integer> tf = new HashMap<>();
        for (String t : tokens) tf.put(t, tf.getOrDefault(t, 0) + 1);
        return tf;
    }

    public static AnalysisResult analyzeEmotion(String text, DiccionarioEmocional dicEmocional) {
        List<String> tokens = tokenizeAndFilter(text);
        Map<String, Integer> tf = termFrequency(tokens);

        Map<String, Integer> emocionPuntuaciones = new HashMap<>();
        Map<String, List<String>> emocionDetonantes = new HashMap<>();

        for (PalabraEmocional pe : dicEmocional.getListaPalabras()) {
            String palabra = normalize(pe.getPalabra());
            if (tf.containsKey(palabra)) {
                String emocion = pe.getEmocion();
                int count = tf.get(palabra);
                emocionPuntuaciones.put(emocion, emocionPuntuaciones.getOrDefault(emocion, 0) + count);

                emocionDetonantes.computeIfAbsent(emocion, k -> new ArrayList<>());
                for (int i = 0; i < count; i++) {
                    emocionDetonantes.get(emocion).add(palabra);
                }
            }
        }

        List<Map.Entry<String,Integer>> sorted = new ArrayList<>(emocionPuntuaciones.entrySet());
        sorted.sort((a,b) -> Integer.compare(b.getValue(), a.getValue()));

        List<String> emocionesOrdenadas = new ArrayList<>();
        for (Map.Entry<String,Integer> e : sorted) emocionesOrdenadas.add(e.getKey());

        return new AnalysisResult(emocionesOrdenadas, emocionDetonantes);
    }

    public static TechnicalResult analyzeTechnical(String text, DiccionarioTecnico dicTecnico) {
        List<String> tokens = tokenizeAndFilter(text);
        Map<String, Integer> tf = termFrequency(tokens);

        Map<String, Integer> categoriaPuntuaciones = new HashMap<>();
        Map<String, List<String>> categoriaDetonantes = new HashMap<>();

        for (PalabraTecnica pt : dicTecnico.getListaPalabras()) {
            String palabra = normalize(pt.getPalabra());
            if (tf.containsKey(palabra)) {
                String categoria = pt.getCategoria();
                int count = tf.get(palabra);
                categoriaPuntuaciones.put(categoria, categoriaPuntuaciones.getOrDefault(categoria, 0) + count);

                categoriaDetonantes.computeIfAbsent(categoria, k -> new ArrayList<>());
                for (int i = 0; i < count; i++) {
                    categoriaDetonantes.get(categoria).add(palabra);
                }
            }
        }

        List<Map.Entry<String,Integer>> sorted = new ArrayList<>(categoriaPuntuaciones.entrySet());
        sorted.sort((a,b) -> Integer.compare(b.getValue(), a.getValue()));

        List<String> categoriasOrdenadas = new ArrayList<>();
        for (Map.Entry<String,Integer> e : sorted) categoriasOrdenadas.add(e.getKey());

        return new TechnicalResult(categoriasOrdenadas, categoriaPuntuaciones, categoriaDetonantes);
    }

    public static class AnalysisResult {
        private final List<String> emocionesOrdenadas;
        private final Map<String, List<String>> emocionDetonantes;

        public AnalysisResult(List<String> emocionesOrdenadas, Map<String, List<String>> emocionDetonantes) {
            this.emocionesOrdenadas = emocionesOrdenadas;
            this.emocionDetonantes = emocionDetonantes;
        }

        public List<String> getEmocionesOrdenadas() { return emocionesOrdenadas; }
        public Map<String, List<String>> getEmocionDetonantes() { return emocionDetonantes; }
    }

    public static class TechnicalResult {
        private final List<String> categoriasOrdenadas;
        private final Map<String, Integer> categoriaPuntuaciones;
        private final Map<String, List<String>> categoriaDetonantes;

        public TechnicalResult(List<String> categoriasOrdenadas, Map<String, Integer> categoriaPuntuaciones, Map<String, List<String>> categoriaDetonantes) {
            this.categoriasOrdenadas = categoriasOrdenadas;
            this.categoriaPuntuaciones = categoriaPuntuaciones;
            this.categoriaDetonantes = categoriaDetonantes;
        }

        public List<String> getCategoriasOrdenadas() { return categoriasOrdenadas; }
        public Map<String, Integer> getCategoriaPuntuaciones() { return categoriaPuntuaciones; }
        public Map<String, List<String>> getCategoriaDetonantes() { return categoriaDetonantes; }
    }
}
