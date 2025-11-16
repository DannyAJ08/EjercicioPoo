package logic;

public class PalabraTecnica {
    private String palabra;
    private String categoria;

    public PalabraTecnica(String palabra, String categoria) {
        this.palabra = palabra;
        this.categoria = categoria;
    }

    public String getPalabra() { return palabra; }
    public void setPalabra(String palabra) { this.palabra = palabra; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }


    public String toString() {
        return palabra + " -> " + categoria;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PalabraTecnica)) return false;
        PalabraTecnica that = (PalabraTecnica) o;
        return palabra != null && palabra.equalsIgnoreCase(that.palabra);
    }

    public int hashCode() {
        return palabra == null ? 0 : palabra.toLowerCase().hashCode();
    }
}
