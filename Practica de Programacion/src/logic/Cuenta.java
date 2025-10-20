package logic;

import java.util.Objects;

public abstract class Cuenta {
    protected String numero;
    protected double saldo;
    protected Cliente titular;

    public Cuenta(String numero, double saldoInicial, Cliente titular) {
        this.numero = numero;
        this.saldo = saldoInicial;
        this.titular = titular;
    }

    public String getNumero() { return numero; }
    public double getSaldo() { return saldo; }
    public Cliente getTitular() { return titular; }

    /**
     * Deposita dinero en la cuenta (lógica básica).
     * @param monto monto > 0
     * @return true si se depositó
     */
    public boolean depositar(double monto) {
        if (monto <= 0) return false;
        saldo += monto;
        return true;
    }


    public abstract boolean retirar(double monto);


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cuenta)) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(numero, cuenta.numero);
    }


    public int hashCode() { return Objects.hash(numero); }


    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "numero='" + numero + '\'' +
                ", saldo=" + String.format("%.2f", saldo) +
                ", titular=" + (titular != null ? titular.getNombre() : "N/A") +
                '}';
    }
}
