package UI;

import logic.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;


        List<Usuario> usuarios = new ArrayList<>();
        List<Departamento> departamentos = new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();
        DiccionarioEmocional dicEmocional = new DiccionarioEmocional();
        DiccionarioTecnico dicTecnico = new DiccionarioTecnico();

        while (!salir) {
            mostrarMenu();
            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    registrarUsuario(sc, usuarios);
                    break;
                case "2":
                    listarUsuarios(usuarios);
                    break;
                case "3":
                    registrarDepartamento(sc, departamentos);
                    break;
                case "4":
                    listarDepartamentos(departamentos);
                    break;
                case "5":
                    registrarTicket(sc, tickets, usuarios, departamentos);
                    break;
                case "6":
                    listarTickets(tickets);
                    break;
                case "7":
                    administrarDiccionarioEmocional(sc, dicEmocional);
                    break;
                case "8":
                    administrarDiccionarioTecnico(sc, dicTecnico);
                    break;
                case "9":
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
            System.out.println();
        }
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== MESA DE AYUDA UNIVERSITARIA ===");
        System.out.println("1) Registrar Usuario");
        System.out.println("2) Listar Usuarios");
        System.out.println("3) Registrar Departamento");
        System.out.println("4) Listar Departamentos");
        System.out.println("5) Registrar Ticket");
        System.out.println("6) Listar Tickets");
        System.out.println("7) Diccionario Emocional");
        System.out.println("8) Diccionario Técnico");
        System.out.println("9) Salir");
        System.out.print("Seleccione una opción: ");
    }


    private static void registrarUsuario(Scanner sc, List<Usuario> usuarios) {
        System.out.println("--- Registrar Usuario ---");
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Correo (único): ");
        String correo = sc.nextLine().trim();


        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                System.out.println("ERROR: Ya existe un usuario con ese correo.");
                return;
            }
        }

        System.out.print("Contraseña: ");
        String pass = sc.nextLine().trim();
        System.out.print("Teléfono: ");
        String tel = sc.nextLine().trim();
        System.out.print("Rol (Administrador/Estudiante/Funcionario): ");
        String rol = sc.nextLine().trim();

        usuarios.add(new Usuario(nombre, correo, pass, tel, rol));
        System.out.println("Usuario registrado correctamente.");
    }

    private static void listarUsuarios(List<Usuario> usuarios) {
        System.out.println("--- Lista de Usuarios ---");
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario u : usuarios) System.out.println(u);
        }
    }


    private static void registrarDepartamento(Scanner sc, List<Departamento> departamentos) {
        System.out.println("--- Registrar Departamento ---");
        System.out.print("Nombre (único): ");
        String nombre = sc.nextLine().trim();

        for (Departamento d : departamentos) {
            if (d.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("ERROR: Ya existe un departamento con ese nombre.");
                return;
            }
        }

        System.out.print("Descripción: ");
        String desc = sc.nextLine().trim();
        System.out.print("Contacto (opcional): ");
        String contacto = sc.nextLine().trim();

        departamentos.add(new Departamento(nombre, desc, contacto));
        System.out.println("Departamento registrado correctamente.");
    }

    private static void listarDepartamentos(List<Departamento> departamentos) {
        System.out.println("--- Lista de Departamentos ---");
        if (departamentos.isEmpty()) {
            System.out.println("No hay departamentos registrados.");
        } else {
            for (Departamento d : departamentos) System.out.println(d);
        }
    }

    private static void registrarTicket(Scanner sc, List<Ticket> tickets,
                                        List<Usuario> usuarios, List<Departamento> departamentos) {
        System.out.println("--- Registrar Ticket ---");
        System.out.print("Correo del usuario creador: ");
        String correo = sc.nextLine().trim();
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                usuario = u;
                break;
            }
        }
        if (usuario == null) {
            System.out.println("ERROR: No existe ese usuario.");
            return;
        }

        System.out.print("Nombre del departamento: ");
        String nombreDepto = sc.nextLine().trim();
        Departamento depto = null;
        for (Departamento d : departamentos) {
            if (d.getNombre().equalsIgnoreCase(nombreDepto)) {
                depto = d;
                break;
            }
        }
        if (depto == null) {
            System.out.println("ERROR: No existe ese departamento.");
            return;
        }

        System.out.print("Asunto: ");
        String asunto = sc.nextLine().trim();
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine().trim();
        System.out.print("Estado (Nuevo/En progreso/Resuelto): ");
        String estado = sc.nextLine().trim();

        Ticket t = new Ticket(asunto, descripcion, estado, usuario, depto);
        tickets.add(t);
        System.out.println("Ticket registrado correctamente (ID " + t.getId() + ").");
    }

    private static void listarTickets(List<Ticket> tickets) {
        System.out.println("--- Lista de Tickets ---");
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets registrados.");
        } else {
            for (Ticket t : tickets) System.out.println(t);
        }
    }


    private static void administrarDiccionarioEmocional(Scanner sc, DiccionarioEmocional dic) {
        System.out.println("--- Diccionario Emocional ---");
        System.out.println("1) Agregar palabra");
        System.out.println("2) Listar palabras");
        System.out.print("Opción: ");
        String op = sc.nextLine().trim();

        if (op.equals("1")) {
            System.out.print("Palabra: ");
            String palabra = sc.nextLine().trim();
            System.out.print("Emoción: ");
            String emocion = sc.nextLine().trim();
            boolean ok = dic.agregarPalabra(new PalabraEmocional(palabra, emocion));
            System.out.println(ok ? "Palabra agregada." : "Ya existe esa palabra.");
        } else if (op.equals("2")) {
            System.out.println("Lista de palabras emocionales:");
            if (dic.listarPalabras().isEmpty()) System.out.println("No hay palabras registradas.");
            else dic.listarPalabras().forEach(System.out::println);
        }
    }

    private static void administrarDiccionarioTecnico(Scanner sc, DiccionarioTecnico dic) {
        System.out.println("--- Diccionario Técnico ---");
        System.out.println("1) Agregar palabra");
        System.out.println("2) Listar palabras");
        System.out.print("Opción: ");
        String op = sc.nextLine().trim();

        if (op.equals("1")) {
            System.out.print("Palabra: ");
            String palabra = sc.nextLine().trim();
            System.out.print("Categoría: ");
            String categoria = sc.nextLine().trim();
            boolean ok = dic.agregarPalabra(new PalabraTecnica(palabra, categoria));
            System.out.println(ok ? "Palabra técnica agregada." : "Ya existe esa palabra.");
        } else if (op.equals("2")) {
            System.out.println("Lista de palabras técnicas:");
            if (dic.listarPalabras().isEmpty()) System.out.println("No hay palabras registradas.");
            else dic.listarPalabras().forEach(System.out::println);
        }
    }
}
