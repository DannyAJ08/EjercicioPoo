package Java.Model;

import java.time.LocalDateTime;

public class Transaccion {
    private int id;
    private int cuentaId;
    private String tipo;
    private double monto;
    private LocalDateTime fecha;
    private String descripcion;

    public Transaccion() {}

    public Transaccion(int id, int cuentaId, String tipo, double monto, LocalDateTime fecha, String descripcion) {
        this.id = id;
        this.cuentaId = cuentaId;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Transaccion(int cuentaId, String tipo, double monto, String descripcion) {
        this.cuentaId = cuentaId;
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
    }

    // getters y setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCuentaId() { return cuentaId; }
    public void setCuentaId(int cuentaId) { this.cuentaId = cuentaId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
