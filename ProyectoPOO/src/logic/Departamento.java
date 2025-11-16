package logic;


public class Departamento {
    private String nombre;
    private String descripcion;
    private String contacto;
    public Departamento(String nombre, String descripcion, String contacto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.contacto = contacto;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }

    public String toString() {
        return "Departamento{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento)) return false;
        Departamento that = (Departamento) o;
        return nombre != null && nombre.equalsIgnoreCase(that.nombre);
    }

    public int hashCode() {
        return nombre == null ? 0 : nombre.toLowerCase().hashCode();
    }
}