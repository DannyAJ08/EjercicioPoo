package logic;

public class Ticket {
    private static int contador = 1;

    private int id;
    private String asunto;
    private String descripcion;
    private String estado;
    private Usuario usuario;
    private Departamento departamento;

    public Ticket(String asunto, String descripcion, String estado, Usuario usuario, Departamento departamento) {
        this.id = contador++;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.usuario = usuario;
        this.departamento = departamento;
    }

    public int getId() { return id; }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }

    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", asunto='" + asunto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", usuario=" + (usuario != null ? usuario.getCorreo() : "null") +
                ", departamento=" + (departamento != null ? departamento.getNombre() : "null") +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id;
    }

    public int hashCode() {
        return Integer.hashCode(id);
    }
}
