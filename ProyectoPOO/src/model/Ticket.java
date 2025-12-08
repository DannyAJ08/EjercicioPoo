package model;

import java.util.Date;

public class Ticket {
    private int id;
    private String asunto;
    private String descripcion;
    private String estado;
    private Date fechaCreacion;
    private Usuario usuario;
    private Departamento departamento;

    public Ticket() {
        this.fechaCreacion = new Date();
    }

    public Ticket(String asunto, String descripcion, String estado,
                  Usuario usuario, Departamento departamento) {
        this();
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.usuario = usuario;
        this.departamento = departamento;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }

    @Override
    public String toString() {
        return String.format("Ticket #%d: %s\nEstado: %s | Usuario: %s\nDepartamento: %s\nFecha: %s",
                id, asunto, estado,
                usuario != null ? usuario.getCorreo() : "N/A",
                departamento != null ? departamento.getNombre() : "N/A",
                fechaCreacion);
    }
}