package model;

import java.time.LocalDate;

public class Reserva {
    private int id;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Reserva() {}

    public Reserva(int id, Cliente cliente, Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Reserva(Cliente cliente, Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        this(0, cliente, habitacion, fechaInicio, fechaFin);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Habitacion getHabitacion() { return habitacion; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", cliente=" + cliente.getNombre() +
                ", habitacion=" + habitacion.getNumero() +
                ", desde=" + fechaInicio + ", hasta=" + fechaFin + '}';
    }
}
