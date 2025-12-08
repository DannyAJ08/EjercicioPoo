package model;

public class PalabraEmocional {
    private String palabra;
    private String emocion;

    public PalabraEmocional() {}

    public PalabraEmocional(String palabra, String emocion) {
        this.palabra = palabra;
        this.emocion = emocion;
    }

    public String getPalabra() { return palabra; }
    public void setPalabra(String palabra) { this.palabra = palabra; }
    public String getEmocion() { return emocion; }
    public void setEmocion(String emocion) { this.emocion = emocion; }

    @Override
    public String toString() {
        return palabra + " → " + emocion;
    }
}