public class Habitacion {
    private int numero;
    private String tipo;
    private double precio;
    private boolean disponible;

    public Habitacion(int numero, String tipo, double precio) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = true;
    }

    // Getters públicos
    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void reservar() {
        disponible = false;
    }

    public void liberar() {
        disponible = true;
    }

    public String toString() {
        return "Habitación " + numero + " (" + tipo + ")";
    }
}
