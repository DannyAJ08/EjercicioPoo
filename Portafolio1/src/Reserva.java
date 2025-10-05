public class Reserva {
    private Cliente cliente;
    private Habitacion habitacion;
    private String fechaInicio;
    private String fechaFin;

    public Reserva(Cliente cliente, Habitacion habitacion, String fechaInicio, String fechaFin) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String mostrarDetalles() {
        String detalles = "Reserva realizada por: " + cliente.getNombre() +
                "\nHabitación: " + habitacion.getNumero() +
                "\nTipo: " + habitacion.getTipo() +
                "\nDesde: " + fechaInicio + " hasta: " + fechaFin;

        return detalles;
    }
}
