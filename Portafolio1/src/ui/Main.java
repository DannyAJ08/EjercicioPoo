package ui;

import bl.ClienteBL;
import bl.HabitacionBL;
import bl.ReservaBL;
import bl.impl.ClienteBLImpl;
import bl.impl.HabitacionBLImpl;
import bl.impl.ReservaBLImpl;
import model.Cliente;
import model.Habitacion;
import model.Reserva;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClienteBL clienteBL = new ClienteBLImpl();
        HabitacionBL habitacionBL = new HabitacionBLImpl();
        ReservaBL reservaBL = new ReservaBLImpl();

        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- MENU HOTEL ---");
            System.out.println("1) Agregar cliente");
            System.out.println("2) Listar clientes");
            System.out.println("3) Agregar habitación");
            System.out.println("4) Listar habitaciones");
            System.out.println("5) Cambiar disponibilidad de habitación");
            System.out.println("6) Crear reserva");
            System.out.println("7) Listar reservas");
            System.out.println("0) Salir");
            System.out.print("Elija una opción: ");
            String opt = sc.nextLine().trim();
            try {
                switch (opt) {
                    case "1":
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Correo: ");
                        String correo = sc.nextLine();
                        System.out.print("Teléfono: ");
                        String telefono = sc.nextLine();
                        Cliente c = new Cliente(nombre, correo, telefono);
                        if (clienteBL.guardar(c)) {
                            System.out.println("Cliente guardado con id: " + c.getId());
                        } else {
                            System.out.println("No se pudo guardar el cliente.");
                        }
                        break;
                    case "2":
                        List<Cliente> clientes = clienteBL.obtenerTodos();
                        if (clientes.isEmpty()) System.out.println("No hay clientes.");
                        for (Cliente cli : clientes) System.out.println(cli);
                        break;
                    case "3":
                        System.out.print("Número de habitación: ");
                        int numero = Integer.parseInt(sc.nextLine());
                        System.out.print("Piso: ");
                        int piso = Integer.parseInt(sc.nextLine());
                        System.out.print("Tipo: ");
                        String tipo = sc.nextLine();
                        System.out.print("Precio: ");
                        double precio = Double.parseDouble(sc.nextLine());
                        Habitacion h = new Habitacion(numero, piso, tipo, precio);
                        if (habitacionBL.guardar(h)) {
                            System.out.println("Habitación guardada con id: " + h.getId());
                        } else {
                            System.out.println("No se pudo guardar la habitación.");
                        }
                        break;
                    case "4":
                        List<Habitacion> habs = habitacionBL.obtenerTodas();
                        if (habs.isEmpty()) System.out.println("No hay habitaciones.");
                        for (Habitacion habit : habs) System.out.println(habit);
                        break;
                    case "5":
                        System.out.print("Id de habitación a cambiar: ");
                        int hid = Integer.parseInt(sc.nextLine());
                        Habitacion habCambio = habitacionBL.obtenerPorId(hid);
                        if (habCambio == null) {
                            System.out.println("Habitación no encontrada.");
                        } else {
                            boolean nuevaDisp = !habCambio.isDisponible();
                            habitacionBL.cambiarDisponibilidad(hid, nuevaDisp);
                            System.out.println("Disponibilidad actualizada. Ahora disponible = " + nuevaDisp);
                        }
                        break;
                    case "6":
                        // Crear reserva: elegir cliente y habitación por id, pedir fechas
                        System.out.print("Id cliente: ");
                        int cid = Integer.parseInt(sc.nextLine());
                        Cliente cliente = clienteBL.obtenerPorId(cid);
                        if (cliente == null) {
                            System.out.println("Cliente no encontrado.");
                            break;
                        }
                        System.out.print("Id habitación: ");
                        int hid2 = Integer.parseInt(sc.nextLine());
                        Habitacion habitacion = habitacionBL.obtenerPorId(hid2);
                        if (habitacion == null) {
                            System.out.println("Habitación no encontrada.");
                            break;
                        }
                        if (!habitacion.isDisponible()) {
                            System.out.println("La habitación no está disponible.");
                            break;
                        }
                        System.out.print("Fecha inicio (YYYY-MM-DD): ");
                        String fi = sc.nextLine();
                        System.out.print("Fecha fin (YYYY-MM-DD): ");
                        String ff = sc.nextLine();
                        try {
                            LocalDate fechaInicio = LocalDate.parse(fi);
                            LocalDate fechaFin = LocalDate.parse(ff);
                            if (fechaFin.isBefore(fechaInicio)) {
                                System.out.println("Fecha fin anterior a inicio.");
                                break;
                            }
                            Reserva reserva = new Reserva(cliente, habitacion, fechaInicio, fechaFin);
                            if (reservaBL.guardar(reserva)) {
                                // al crear reserva, marcar habitación como no disponible
                                habitacionBL.cambiarDisponibilidad(habitacion.getId(), false);
                                System.out.println("Reserva registrada con id: " + reserva.getId());
                            } else {
                                System.out.println("No se pudo guardar la reserva.");
                            }
                        } catch (DateTimeParseException dtpe) {
                            System.out.println("Formato de fecha inválido.");
                        }
                        break;
                    case "7":
                        List<Reserva> reservas = reservaBL.obtenerTodas();
                        if (reservas.isEmpty()) System.out.println("No hay reservas.");
                        for (Reserva r : reservas) System.out.println(r);
                        break;
                    case "0":
                        salir = true;
                        System.out.println("Saliendo... ¡buena suerte con tu entrega!");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception ex) {
                System.out.println("Ocurrió un error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        sc.close();
    }
}
