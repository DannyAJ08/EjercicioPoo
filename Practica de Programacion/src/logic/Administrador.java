package logic;

import java.util.ArrayList;
import java.util.Objects;

public class Administrador {
    private String id;
    private String nombre;
    private String password;
    private ArrayList<Cliente> clientes;

    public Administrador(String id, String nombre, String password) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.clientes = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public void agregarCliente(Cliente c) {
        if (c != null && !clientes.contains(c)) {
            clientes.add(c);
        }
    }

    public boolean eliminarCliente(String id) {
        return clientes.removeIf(c -> c.getId().equals(id));
    }

    public Cliente buscarCliente(String id) {
        for (Cliente c : clientes) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }

    public ArrayList<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrador)) return false;
        Administrador that = (Administrador) o;
        return Objects.equals(id, that.id);
    }


    public int hashCode() { return Objects.hash(id); }


    public String toString() {
        return "Administrador{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", clientesCount=" + clientes.size() +
                '}';
    }
}
