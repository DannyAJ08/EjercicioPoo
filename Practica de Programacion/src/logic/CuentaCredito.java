package logic;

public class CuentaCredito extends Cuenta {
    private double limite;
    private double deuda;


    public CuentaCredito(String numero, double limite, Cliente titular, double deudaInicial) {
        super(numero, 0.0, titular);
        this.limite = limite;
        this.deuda = Math.max(0.0, deudaInicial);

    }

    public double getLimite() { return limite; }
    public double getDeuda() { return deuda; }


    public boolean retirar(double monto) {
        if (monto <= 0) return false;
        if (deuda + monto <= limite) {
            deuda += monto;
            return true;
        }
        return false;
    }


    public boolean abonar(double monto) {
        if (monto <= 0) return false;
        double pago = Math.min(monto, deuda);
        deuda -= pago;
        return true;
    }


    public String toString() {
        return "CuentaCredito{" +
                "numero='" + numero + '\'' +
                ", limite=" + String.format("%.2f", limite) +
                ", deuda=" + String.format("%.2f", deuda) +
                ", titular=" + (titular != null ? titular.getNombre() : "N/A") +
                '}';
    }
}
