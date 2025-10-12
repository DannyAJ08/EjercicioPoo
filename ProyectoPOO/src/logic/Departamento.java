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
}
