package Java.Service;

import Java.Dao.*;
import Java.Dao.Impl.*;
import Java.Model.*;

import java.util.List;
import java.util.Optional;

public class BancoService {
    private AdministradorDAO administradorDAO = new AdministradorDAOImpl();
    private ClienteDAO clienteDAO = new ClienteDAOImpl();
    private CuentaDAO cuentaDAO = new CuentaDAOImpl();
    private TransaccionDAO transaccionDAO = new TransaccionDAOImpl();

    // Administrador
    public boolean existeAdministrador() throws Exception {
        return administradorDAO.existeAdministrador();
    }

    public int crearAdministrador(Administrador admin) throws Exception {
        return administradorDAO.crearAdministrador(admin);
    }

    public Optional<Administrador> loginAdministrador(String correo, String contrasenia) throws Exception {
        Optional<Administrador> opt = administradorDAO.buscarPorCorreo(correo);
        if (opt.isPresent() && opt.get().getContrasenia().equals(contrasenia)) {
            return opt;
        }
        return Optional.empty();
    }

    // Clientes
    public int crearCliente(Cliente c) throws Exception {
        return clienteDAO.crearCliente(c);
    }

    public Optional<Cliente> loginCliente(String correo, String contrasenia) throws Exception {
        Optional<Cliente> opt = clienteDAO.buscarPorCorreo(correo);
        if (opt.isPresent() && opt.get().getContrasenia().equals(contrasenia)) {
            return opt;
        }
        return Optional.empty();
    }

    public List<Cliente> listarClientes() throws Exception {
        return clienteDAO.listarClientes();
    }

    // Cuentas
    public int crearCuenta(Cuenta cuenta) throws Exception {
        if (cuenta instanceof CuentaAhorro) {
            if (cuenta.getSaldo() < 100.0) {
                throw new Exception("Cuenta de ahorro debe crearse con al menos $100.00");
            }
        } else if (cuenta instanceof CuentaCredito) {
            cuenta.setSaldo(0.0); // siempre creado con saldo 0
        } else if (cuenta instanceof CuentaDebito) {
            if (cuenta.getSaldo() < 0) {
                throw new Exception("Cuenta débito no puede crearse con saldo negativo");
            }
        }
        return cuentaDAO.crearCuenta(cuenta);
    }

    public Optional<Cuenta> obtenerCuentaPorNumero(String numero) throws Exception {
        return cuentaDAO.obtenerPorNumero(numero);
    }

    public List<Cuenta> listarTodasCuentas() throws Exception {
        return cuentaDAO.listarTodas();
    }

    public List<Cuenta> listarCuentasPorCliente(int clienteId) throws Exception {
        return cuentaDAO.listarPorClienteId(clienteId);
    }

    public boolean procesarRetiro(String numeroCuenta, double monto) throws Exception {
        Optional<Cuenta> opt = cuentaDAO.obtenerPorNumero(numeroCuenta);
        if (opt.isEmpty()) throw new Exception("Cuenta no encontrada");
        Cuenta cuenta = opt.get();
        boolean resultado = cuenta.retirar(monto);
        if (resultado) {
            cuentaDAO.actualizarCuenta(cuenta);
            Transaccion t = new Transaccion(cuenta.getId(), "RETIRO", monto, "Retiro/Pago");
            transaccionDAO.registrarTransaccion(t);
            return true;
        }
        return false;
    }

    public boolean procesarDeposito(String numeroCuenta, double monto) throws Exception {
        Optional<Cuenta> opt = cuentaDAO.obtenerPorNumero(numeroCuenta);
        if (opt.isEmpty()) throw new Exception("Cuenta no encontrada");
        Cuenta cuenta = opt.get();
        boolean resultado = cuenta.depositar(monto);
        if (resultado) {
            cuentaDAO.actualizarCuenta(cuenta);
            Transaccion t = new Transaccion(cuenta.getId(), "DEPOSITO", monto, "Depósito/Abono");
            transaccionDAO.registrarTransaccion(t);
            return true;
        }
        return false;
    }

    public void generarInteresesParaCuenta(String numero) throws Exception {
        Optional<Cuenta> opt = cuentaDAO.obtenerPorNumero(numero);
        if (opt.isEmpty()) throw new Exception("Cuenta no encontrada");
        Cuenta c = opt.get();
        if (c instanceof CuentaAhorro) {
            ((CuentaAhorro) c).generarInteres();
            cuentaDAO.actualizarCuenta(c);
            Transaccion t = new Transaccion(c.getId(), "INTERES", ((CuentaAhorro) c).getPorcentajeInteres(), "Generación de interés");
            transaccionDAO.registrarTransaccion(t);
        } else if (c instanceof CuentaDebito) {
            ((CuentaDebito) c).generarInteres();
            cuentaDAO.actualizarCuenta(c);
            Transaccion t = new Transaccion(c.getId(), "INTERES", ((CuentaDebito) c).getPorcentajeInteres(), "Generación de interés");
            transaccionDAO.registrarTransaccion(t);
        } else {
            throw new Exception("Cuenta no soporta intereses");
        }
    }

    public double saldoConsolidadoCliente(int clienteId) throws Exception {
        List<Cuenta> cuentas = cuentaDAO.listarPorClienteId(clienteId);
        double total = 0.0;
        for (Cuenta c : cuentas) total += c.getSaldo();
        return total;
    }
}
