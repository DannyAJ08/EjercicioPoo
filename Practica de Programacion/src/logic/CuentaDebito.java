package logic;

public class CuentaDebito extends Cuenta {
    private double limiteDescubierto;

    public CuentaDebito(String numero, double saldoInicial, Cliente titular, double limiteDescubierto) {
        super(numero, saldoInicial, titular);
        this.limiteDescubierto = limiteDescubierto;
    }

    public double getLimiteDescubierto() { return limiteDescubierto; }
    public void setLimiteDescubierto(double limiteDescubierto) { this.limiteDescubierto = limiteDescubierto; }


    public boolean retirar(double monto) {
        if (monto <= 0) return false;
        if (saldo + limiteDescubierto >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }


    public String toString() {
        return super.toString().replace("}", "") +
                ", limiteDescubierto=" + limiteDescubierto +
                '}';
    }
}
