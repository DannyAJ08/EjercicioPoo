package Java.Model;

public abstract class Cuenta {
    protected int id;
    protected int clienteId;  // FK al cliente (usuarios.id)
    protected String numeroCuenta;
    protected double saldo;
    protected boolean activa;

    public Cuenta() {}

    public Cuenta(int id, int clienteId, String numeroCuenta, double saldo, boolean activa) {
        this.id = id;
        this.clienteId = clienteId;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.activa = activa;
    }

    // operaciones comunes
    public abstract boolean retirar(double monto); // para retiros/pagos
    public abstract boolean depositar(double monto); // para depositos/abonos
    public abstract String getTipoCuenta();

    // getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }

    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", activa=" + activa +
                '}';
    }
}
