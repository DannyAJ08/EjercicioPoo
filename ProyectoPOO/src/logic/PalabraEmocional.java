package logic;

public class PalabraEmocional {
    private String palabra;
    private String emocion;

    public PalabraEmocional(String palabra, String emocion) {
        this.palabra = palabra;
        this.emocion = emocion;
    }

    public String getPalabra() { return palabra; }
    public void setPalabra(String palabra) { this.palabra = palabra; }

    public String getEmocion() { return emocion; }
    public void setEmocion(String emocion) { this.emocion = emocion; }

    public String toString() {
        return palabra + " -> " + emocion;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PalabraEmocional)) return false;
        PalabraEmocional that = (PalabraEmocional) o;
        return palabra != null && palabra.equalsIgnoreCase(that.palabra);
    }

    public int hashCode() {
        return palabra == null ? 0 : palabra.toLowerCase().hashCode();
    }
}
