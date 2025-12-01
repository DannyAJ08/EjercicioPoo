package Java.Model;

public class CuentaAhorro extends Cuenta {
    private double porcentajeInteres;

    public CuentaAhorro() { super(); }

    public CuentaAhorro(int id, int clienteId, String numeroCuenta, double saldo, boolean activa, double porcentajeInteres) {
        super(id, clienteId, numeroCuenta, saldo, activa);
        this.porcentajeInteres = porcentajeInteres;
    }

    public double getPorcentajeInteres() { return porcentajeInteres; }
    public void setPorcentajeInteres(double porcentajeInteres) { this.porcentajeInteres = porcentajeInteres; }

    @Override
    public boolean retirar(double monto) {
        if (!activa || monto <= 0) return false;
        double nuevoSaldo = saldo - monto;
        if (nuevoSaldo >= 100.0) {
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
    public String getTipoCuenta() { return "AHORRO"; }

    @Override
    public String toString() {
        return "CuentaAhorro{" +
                "id=" + id +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", activa=" + activa +
                ", porcentajeInteres=" + porcentajeInteres +
                '}';
    }
}
