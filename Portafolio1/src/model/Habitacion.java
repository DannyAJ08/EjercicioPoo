package model;

public class Habitacion {
    private int id;
    private int numero;
    private int piso;
    private String tipo;
    private double precio;
    private boolean disponible;

    public Habitacion() {}

    public Habitacion(int id, int numero, int piso, String tipo, double precio, boolean disponible) {
        this.id = id;
        this.numero = numero;
        this.piso = piso;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = disponible;
    }

    public Habitacion(int numero, int piso, String tipo, double precio) {
        this(0, numero, piso, tipo, precio, true);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public int getPiso() { return piso; }
    public void setPiso(int piso) { this.piso = piso; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return "Habitacion{" + "id=" + id + ", numero=" + numero + ", piso=" + piso +
                ", tipo='" + tipo + '\'' + ", precio=" + precio + ", disponible=" + disponible + '}';
    }
}
