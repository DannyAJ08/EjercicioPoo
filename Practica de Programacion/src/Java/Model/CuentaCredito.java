package Java.Model;

public class CuentaCredito extends Cuenta {
    private double limiteCredito;
    private String tipoCredito;

    public CuentaCredito() { super(); }

    public CuentaCredito(int id, int clienteId, String numeroCuenta, double saldo, boolean activa,
                         double limiteCredito, String tipoCredito) {
        super(id, clienteId, numeroCuenta, saldo, activa);
        this.limiteCredito = limiteCredito;
        this.tipoCredito = tipoCredito;
    }

    public double getLimiteCredito() { return limiteCredito; }
    public void setLimiteCredito(double limiteCredito) { this.limiteCredito = limiteCredito; }
    public String getTipoCredito() { return tipoCredito; }
    public void setTipoCredito(String tipoCredito) { this.tipoCredito = tipoCredito; }

    @Override
    public boolean retirar(double monto) {
        if (!activa || monto <= 0) return false;
        double nuevoSaldo = saldo - monto;
        if (nuevoSaldo >= -limiteCredito) {
            saldo = nuevoSaldo;
            return true;
        }
        return false;
    }

    @Override
    public boolean depositar(double monto) {
        if (!activa || monto <= 0) return false;
        saldo += monto;
        return true;
    }

    @Override
    public String getTipoCuenta() { return "CREDITO"; }

    @Override
    public String toString() {
        return "CuentaCredito{" +
                "id=" + id +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", activa=" + activa +
                ", limiteCredito=" + limiteCredito +
                ", tipoCredito='" + tipoCredito + '\'' +
                '}';
    }
}
