package Java.Model;

public class Administrador extends Usuario {
    public Administrador() { super(); }
    public Administrador(int id, String nombreCompleto, String cedula, String correoElectronico, String contrasenia) {
        super(id, nombreCompleto, cedula, correoElectronico, contrasenia);
    }
}
