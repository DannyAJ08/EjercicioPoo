package logic;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente {
    private String id;
    private String nombre;
    private String password;
    private ArrayList<Cuenta> cuentas;

    public Cliente(String id, String nombre, String password) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.cuentas = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public void agregarCuenta(Cuenta c) {
        if (c != null && !cuentas.contains(c)) {
            cuentas.add(c);
        }
    }

    public Cuenta getCuenta(String numero) {
        for (Cuenta c : cuentas) {
            if (c.getNumero().equals(numero)) return c;
        }
        return null;
    }

    public ArrayList<Cuenta> listarCuentas() {
        return new ArrayList<>(cuentas);
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }


    public int hashCode() { return Objects.hash(id); }


    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cuentasCount=" + cuentas.size() +
                '}';
    }
}
