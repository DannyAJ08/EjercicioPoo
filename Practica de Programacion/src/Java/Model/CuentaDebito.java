package Java.Model;

public class CuentaDebito extends Cuenta {
    private double porcentajeInteres;

    public CuentaDebito() { super(); }

    public CuentaDebito(int id, int clienteId, String numeroCuenta, double saldo, boolean activa, double porcentajeInteres) {
        super(id, clienteId, numeroCuenta, saldo, activa);
        this.porcentajeInteres = porcentajeInteres;
    }

    public double getPorcentajeInteres() { return porcentajeInteres; }
    public void setPorcentajeInteres(double porcentajeInteres) { this.porcentajeInteres = porcentajeInteres; }

    @Override
    public boolean retirar(double monto) {
        if (!activa || monto <= 0) return false;
        double nuevoSaldo = saldo - monto;
        if (nuevoSaldo >= 0.0) {
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

    public void generarInteres() {
        if (!activa) return;
        if (porcentajeInteres > 0) {
            double interes = saldo * (porcentajeInteres / 100.0);
            saldo += interes;
        }
    }

    @Override
    public String getTipoCuenta() { return "DEBITO"; }

    @Override
    public String toString() {
        return "CuentaDebito{" +
                "id=" + id +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", activa=" + activa +
                ", porcentajeInteres=" + porcentajeInteres +
                '}';
    }
}
