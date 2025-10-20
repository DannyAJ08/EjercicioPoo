package logic;

public class CuentaAhorro extends Cuenta {
    private double tasaInteres; // en decimal, p.ej 0.03 = 3%

    public CuentaAhorro(String numero, double saldoInicial, Cliente titular, double tasaInteres) {
        super(numero, saldoInicial, titular);
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() { return tasaInteres; }
    public void setTasaInteres(double tasaInteres) { this.tasaInteres = tasaInteres; }

    /**
     * Retira solo si hay saldo suficiente.
     */
    @Override
    public boolean retirar(double monto) {
        if (monto <= 0) return false;
        if (saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    //interés=  salo += saldo * tasaInteres

    public double generarInteres() {
        double interes = saldo * tasaInteres;
        saldo += interes;
        return interes;
    }


    public String toString() {
        return super.toString().replace("}", "") +
                ", tasaInteres=" + tasaInteres +
                '}';
    }
}
