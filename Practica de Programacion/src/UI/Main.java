package UI;

import logic.*;

public class Main {
    public static void main(String[] args) {
        //admin
        Administrador admin = new Administrador("A001", "Admin Principal", "admin123");
        System.out.println("Administrador creado: " + admin);

        // clientr

        Cliente cliente1 = new Cliente("C001", "María Pérez", "maria123");
        Cliente cliente2 = new Cliente("C002", "Juan López", "juan123");


        admin.agregarCliente(cliente1);
        admin.agregarCliente(cliente2);
        System.out.println("Clientes registrados: " + admin.listarClientes());


        CuentaAhorro ahorro1 = new CuentaAhorro("AH-001", 50000.00, cliente1, 0.02);
        CuentaDebito debito1 = new CuentaDebito("DB-001", 1500.00, cliente1, 200.00);
        CuentaCredito credito1 = new CuentaCredito("CR-001", 5000.00, cliente2, 0.0);


        cliente1.agregarCuenta(ahorro1);
        cliente1.agregarCuenta(debito1);
        cliente2.agregarCuenta(credito1);


        System.out.println("\n Estados iniciales ");
        for (Cliente c : admin.listarClientes()) {
            System.out.println(c);
            for (Cuenta cu : c.listarCuentas()) {
                System.out.println("  " + cu);
            }
        }


        System.out.println("\nOperaciones: Depósitos ");
        ahorro1.depositar(2500.00);
        System.out.println("Depósito en " + ahorro1.getNumero() + " -> nuevo saldo: " + ahorro1.getSaldo());

        debito1.depositar(500.00);
        System.out.println("Depósito en " + debito1.getNumero() + " -> nuevo saldo: " + debito1.getSaldo());


        System.out.println("\n Operaciones: Retiros ");
        boolean ex = ahorro1.retirar(1000.00);
        System.out.println("Retiro 1000 de " + ahorro1.getNumero() + " -> " + (ex ? "éxito" : "falló") + ", saldo: " + ahorro1.getSaldo());

        ex = debito1.retirar(2000.00); // usa descubierto
        System.out.println("Retiro 2000 de " + debito1.getNumero() + " -> " + (ex ? "éxito" : "falló") + ", saldo: " + debito1.getSaldo());


        System.out.println("\n Generar interés cuenta ahorro ");
        double interes = ahorro1.generarInteres();
        System.out.println("Interés generado: " + String.format("%.2f", interes) + ", saldo ahora: " + String.format("%.2f", ahorro1.getSaldo()));


        System.out.println("\n Operaciones: Crédito ");
        boolean uso = credito1.retirar(1200.00);
        System.out.println("Uso crédito 1200 en " + credito1.getNumero() + " -> " + (uso ? "éxito" : "rechazado") + ", deuda: " + credito1.getDeuda());

        boolean pago = credito1.abonar(300.00);
        System.out.println("Abono 300 en " + credito1.getNumero() + " -> " + (pago ? "procesado" : "falló") + ", deuda: " + credito1.getDeuda());


        System.out.println("\n Estado final ");
        for (Cliente c : admin.listarClientes()) {
            System.out.println(c);
            for (Cuenta cu : c.listarCuentas()) {
                System.out.println("  " + cu);
            }
        }

        
        System.out.println("\nBuscar cliente C002: " + admin.buscarCliente("C002"));
    }
}
