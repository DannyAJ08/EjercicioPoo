package Java.App;

import Java.Model.*;
import Java.Service.BancoService;
import util.InputUtil;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final BancoService service = new BancoService();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;
        System.out.println("=== Bienvenido al sistema Bancario - Practica 2 ===");

        while (!salir) {
            try {
                System.out.println("\nMenu Principal:");
                System.out.println("1) Administrador");
                System.out.println("2) Cliente");
                System.out.println("3) Salir");
                System.out.print("Seleccione opción: ");
                int opcion = InputUtil.leerEntero(sc, 1, 3);

                switch (opcion) {
                    case 1:
                        menuAdministrador();
                        break;
                    case 2:
                        menuCliente();
                        break;
                    case 3:
                        salir = true;
                        System.out.println("Saliendo... buen día.");
                        break;
                }
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        }
        sc.close();
    }

    private static void menuAdministrador() {
        System.out.println("\n-- Login Administrador --");
        System.out.print("Correo: ");
        String correo = sc.nextLine().trim();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine().trim();
        try {
            Optional<Administrador> opt = service.loginAdministrador(correo, pass);
            if (opt.isEmpty()) {
                System.out.println("Login fallido. Revise credenciales o cree un administrador primero.");
                if (!service.existeAdministrador()) {
                    System.out.println("No existe administrador. ¿Desea crear uno ahora? (s/n)");
                    String resp = sc.nextLine();
                    if (resp.equalsIgnoreCase("s")) crearAdministradorInteractivo();
                }
                return;
            }
            System.out.println("Bienvenido administrador: " + opt.get().getNombreCompleto());
            boolean back = false;
            while (!back) {
                System.out.println("\nSubmenu Administrador:");
                System.out.println("1) Listar clientes");
                System.out.println("2) Listar todas las cuentas");
                System.out.println("3) Crear cliente");
                System.out.println("4) Crear cuenta para cliente");
                System.out.println("5) Volver");
                System.out.print("Opción: ");
                int op = InputUtil.leerEntero(sc, 1, 5);
                switch (op) {
                    case 1:
                        listarClientes();
                        break;
                    case 2:
                        listarCuentas();
                        break;
                    case 3:
                        crearClienteInteractivo();
                        break;
                    case 4:
                        crearCuentaInteractiva();
                        break;
                    case 5:
                        back = true;
                        break;
                }
            }
        } catch (Exception ex) {
            System.err.println("Error en admin: " + ex.getMessage());
        }
    }

    private static void menuCliente() {
        System.out.println("\n-- Login Cliente --");
        System.out.print("Correo: ");
        String correo = sc.nextLine().trim();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine().trim();
        try {
            Optional<Cliente> opt = service.loginCliente(correo, pass);
            if (opt.isEmpty()) {
                System.out.println("Login fallido. Revise credenciales.");
                return;
            }
            Cliente cliente = opt.get();
            System.out.println("Bienvenido: " + cliente.getNombreCompleto());
            boolean back = false;
            while (!back) {
                System.out.println("\nSubmenu Cliente:");
                System.out.println("1) Ver mis cuentas y saldo consolidado");
                System.out.println("2) Depositar / Abonar");
                System.out.println("3) Retirar / Pagar");
                System.out.println("4) Generar intereses en cuenta (si aplica)");
                System.out.println("5) Volver");
                System.out.print("Opción: ");
                int op = InputUtil.leerEntero(sc, 1, 5);
                switch (op) {
                    case 1:
                        verCuentasYSaldo(cliente.getId());
                        break;
                    case 2:
                        operarDeposito(cliente.getId());
                        break;
                    case 3:
                        operarRetiro(cliente.getId());
                        break;
                    case 4:
                        generarInteresCliente(cliente.getId());
                        break;
                    case 5:
                        back = true;
                        break;
                }
            }
        } catch (Exception ex) {
            System.err.println("Error en cliente: " + ex.getMessage());
        }
    }

    private static void crearAdministradorInteractivo() {
        try {
            System.out.println("== Crear Administrador ==");
            System.out.print("Nombre completo: ");
            String nombre = sc.nextLine();
            System.out.print("Cédula: ");
            String ced = sc.nextLine();
            System.out.print("Correo: ");
            String correo = sc.nextLine();
            System.out.print("Contraseña: ");
            String pass = sc.nextLine();
            Administrador admin = new Administrador(0, nombre, ced, correo, pass);
            int id = service.crearAdministrador(admin);
            if (id > 0) System.out.println("Administrador creado con id: " + id);
            else System.out.println("No se pudo crear administrador.");
        } catch (Exception ex) {
            System.err.println("Error creando admin: " + ex.getMessage());
        }
    }

    private static void crearClienteInteractivo() {
        try {
            System.out.println("== Crear Cliente ==");
            System.out.print("Nombre completo: ");
            String nombre = sc.nextLine();
            System.out.print("Cédula: ");
            String ced = sc.nextLine();
            System.out.print("Correo: ");
            String correo = sc.nextLine();
            System.out.print("Contraseña: ");
            String pass = sc.nextLine();
            System.out.print("Sexo: ");
            String sexo = sc.nextLine();
            System.out.print("Profesión: ");
            String prof = sc.nextLine();
            System.out.print("Dirección: ");
            String dir = sc.nextLine();
            Cliente c = new Cliente(0, nombre, ced, correo, pass, sexo, prof, dir);
            int id = service.crearCliente(c);
            if (id > 0) System.out.println("Cliente creado con id: " + id);
            else System.out.println("Error creando cliente.");
        } catch (Exception ex) {
            System.err.println("Error creando cliente: " + ex.getMessage());
        }
    }

    private static void crearCuentaInteractiva() {
        try {
            System.out.println("== Crear Cuenta para Cliente ==");
            System.out.print("Id Cliente: ");
            int idCliente = InputUtil.leerEntero(sc, 1, Integer.MAX_VALUE);
            System.out.println("Tipo cuenta: 1) AHORRO 2) DEBITO 3) CREDITO");
            int t = InputUtil.leerEntero(sc, 1, 3);
            System.out.print("Número de cuenta (único): ");
            String num = sc.nextLine();
            switch (t) {
                case 1:
                    System.out.print("Saldo inicial (>=100): ");
                    double sA = InputUtil.leerDouble(sc);
                    System.out.print("Porcentaje interés: ");
                    double pA = InputUtil.leerDouble(sc);
                    CuentaAhorro ca = new CuentaAhorro(0, idCliente, num, sA, true, pA);
                    service.crearCuenta(ca);
                    System.out.println("Cuenta de ahorro creada.");
                    break;
                case 2:
                    System.out.print("Saldo inicial (>=0): ");
                    double sD = InputUtil.leerDouble(sc);
                    System.out.print("Porcentaje interés: ");
                    double pD = InputUtil.leerDouble(sc);
                    CuentaDebito cd = new CuentaDebito(0, idCliente, num, sD, true, pD);
                    service.crearCuenta(cd);
                    System.out.println("Cuenta débito creada.");
                    break;
                case 3:
                    System.out.print("Límite crédito (positivo): ");
                    double lim = InputUtil.leerDouble(sc);
                    System.out.print("Tipo de crédito (ej. Cashback): ");
                    String tipo = sc.nextLine();
                    CuentaCredito cc = new CuentaCredito(0, idCliente, num, 0.0, true, lim, tipo);
                    service.crearCuenta(cc);
                    System.out.println("Cuenta crédito creada (saldo inicial 0).");
                    break;
            }
        } catch (Exception ex) {
            System.err.println("Error creando cuenta: " + ex.getMessage());
        }
    }

    private static void listarClientes() {
        try {
            List<Java.Model.Cliente> lista = service.listarClientes();
            System.out.println("=== Clientes registrados ===");
            for (Java.Model.Cliente c : lista) {
                System.out.println(c);
            }
        } catch (Exception ex) {
            System.err.println("Error listando clientes: " + ex.getMessage());
        }
    }

    private static void listarCuentas() {
        try {
            List<Java.Model.Cuenta> lista = service.listarTodasCuentas();
            System.out.println("=== Cuentas ===");
            for (Java.Model.Cuenta c : lista) {
                System.out.println(c);
            }
        } catch (Exception ex) {
            System.err.println("Error listando cuentas: " + ex.getMessage());
        }
    }

    private static void verCuentasYSaldo(int clienteId) {
        try {
            List<Java.Model.Cuenta> lista = service.listarCuentasPorCliente(clienteId);
            System.out.println("=== Mis cuentas ===");
            for (Java.Model.Cuenta c : lista) System.out.println(c);
            double total = service.saldoConsolidadoCliente(clienteId);
            System.out.printf("Saldo consolidado: $%.2f%n", total);
        } catch (Exception ex) {
            System.err.println("Error mostrando cuentas: " + ex.getMessage());
        }
    }

    private static void operarDeposito(int clienteId) {
        try {
            System.out.print("Número cuenta: ");
            String num = sc.nextLine();
            System.out.print("Monto a depositar/abonar: ");
            double monto = InputUtil.leerDouble(sc);
            boolean ok = service.procesarDeposito(num, monto);
            System.out.println(ok ? "Operación exitosa." : "No fue posible realizar el depósito.");
        } catch (Exception ex) {
            System.err.println("Error en depósito: " + ex.getMessage());
        }
    }

    private static void operarRetiro(int clienteId) {
        try {
            System.out.print("Número cuenta: ");
            String num = sc.nextLine();
            System.out.print("Monto a retirar/pagar: ");
            double monto = InputUtil.leerDouble(sc);
            boolean ok = service.procesarRetiro(num, monto);
            System.out.println(ok ? "Operación exitosa." : "No fue posible realizar el retiro/pago.");
        } catch (Exception ex) {
            System.err.println("Error en retiro: " + ex.getMessage());
        }
    }

    private static void generarInteresCliente(int clienteId) {
        try {
            System.out.print("Número cuenta (Ahorro/Debito): ");
            String num = sc.nextLine();
            service.generarInteresesParaCuenta(num);
            System.out.println("Interés generado y saldo actualizado.");
        } catch (Exception ex) {
            System.err.println("Error generando interés: " + ex.getMessage());
        }
    }
}
