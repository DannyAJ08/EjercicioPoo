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

        poblacionInicial(usuarios, departamentos, dicEmocional, dicTecnico);

        while (!salir) {
            mostrarMenuPrincipal();
            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1": gestionarUsuarios(sc, usuarios); break;
                case "2": gestionarDepartamentos(sc, departamentos); break;
                case "3": gestionarTickets(sc, usuarios, departamentos, tickets); break;
                case "4": gestionarDiccionarios(sc, dicEmocional, dicTecnico); break;
                case "5": realizarAnalisis(sc, tickets, dicEmocional, dicTecnico); break;
                case "6": System.out.println("Saliendo..."); salir = true; break;
                default: System.out.println("Opción inválida. Intente de nuevo.");
            }
            System.out.println();
        }

        sc.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("=== MESA DE AYUDA - AVANCE 2 ===");
        System.out.println("1) Gestión de Usuarios");
        System.out.println("2) Gestión de Departamentos");
        System.out.println("3) Gestión de Tickets");
        System.out.println("4) Gestión de Diccionarios");
        System.out.println("5) Análisis BoW (Ticket)");
        System.out.println("6) Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void gestionarUsuarios(Scanner sc, List<Usuario> usuarios) {
        boolean volver = false;
        while (!volver) {
            System.out.println("--- Gestión de Usuarios ---");
            System.out.println("1) Registrar Usuario");
            System.out.println("2) Listar Usuarios");
            System.out.println("3) Buscar Usuario por correo");
            System.out.println("4) Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();
            switch (op) {
                case "1": registrarUsuario(sc, usuarios); break;
                case "2": listarUsuarios(usuarios); break;
                case "3": buscarUsuario(sc, usuarios); break;
                case "4": volver = true; break;
                default: System.out.println("Opción inválida.");
            }
            System.out.println();
        }
    }

    private static void registrarUsuario(Scanner sc, List<Usuario> usuarios) {
        System.out.println("- Registrar Usuario -");
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Correo (único): ");
        String correo = sc.nextLine().trim();

        for (Usuario u : usuarios) if (u.getCorreo().equalsIgnoreCase(correo)) {
            System.out.println("ERROR: Ya existe un usuario con ese correo.");
            return;
        }

        System.out.print("Contraseña: ");
        String pass = sc.nextLine().trim();
        System.out.print("Teléfono (opcional): ");
        String tel = sc.nextLine().trim();
        System.out.print("Rol (Administrador/Estudiante/Funcionario): ");
        String rol = sc.nextLine().trim();

        Usuario u = new Usuario(nombre, correo, pass, tel, rol);
        usuarios.add(u);
        System.out.println("Usuario registrado correctamente.");
    }

    private static void listarUsuarios(List<Usuario> usuarios) {
        System.out.println("--- Lista de Usuarios ---");
        if (usuarios.isEmpty()) System.out.println("No hay usuarios registrados.");
        else usuarios.forEach(u -> System.out.println(u));
    }

    private static void buscarUsuario(Scanner sc, List<Usuario> usuarios) {
        System.out.print("Correo a buscar: ");
        String correo = sc.nextLine().trim();
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                System.out.println("Usuario encontrado: " + u);
                return;
            }
        }
        System.out.println("No se encontró usuario con ese correo.");
    }

    private static void gestionarDepartamentos(Scanner sc, List<Departamento> departamentos) {
        boolean volver = false;
        while (!volver) {
            System.out.println("--- Gestión de Departamentos ---");
            System.out.println("1) Registrar Departamento");
            System.out.println("2) Listar Departamentos");
            System.out.println("3) Buscar Departamento por nombre");
            System.out.println("4) Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();
            switch (op) {
                case "1": registrarDepartamento(sc, departamentos); break;
                case "2": listarDepartamentos(departamentos); break;
                case "3": buscarDepartamento(sc, departamentos); break;
                case "4": volver = true; break;
                default: System.out.println("Opción inválida.");
            }
            System.out.println();
        }
    }

    private static void registrarDepartamento(Scanner sc, List<Departamento> departamentos) {
        System.out.println("- Registrar Departamento -");
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
        System.out.println("- Lista de Departamentos -");
        if (departamentos.isEmpty()) System.out.println("No hay departamentos registrados.");
        else departamentos.forEach(d -> System.out.println(d));
    }

    private static void buscarDepartamento(Scanner sc, List<Departamento> departamentos) {
        System.out.print("Nombre a buscar: ");
        String nombre = sc.nextLine().trim();
        for (Departamento d : departamentos) {
            if (d.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Departamento encontrado: " + d);
                return;
            }
        }
        System.out.println("No se encontró departamento con ese nombre.");
    }

    private static void gestionarTickets(Scanner sc, List<Usuario> usuarios, List<Departamento> departamentos, List<Ticket> tickets) {
        boolean volver = false;
        while (!volver) {
            System.out.println("--- Gestión de Tickets ---");
            System.out.println("1) Registrar Ticket");
            System.out.println("2) Listar Tickets");
            System.out.println("3) Cambiar estado de Ticket");
            System.out.println("4) Buscar Ticket por ID");
            System.out.println("5) Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();
            switch (op) {
                case "1": registrarTicket(sc, usuarios, departamentos, tickets); break;
                case "2": listarTickets(tickets); break;
                case "3": cambiarEstadoTicket(sc, tickets); break;
                case "4": buscarTicketPorId(sc, tickets); break;
                case "5": volver = true; break;
                default: System.out.println("Opción inválida.");
            }
            System.out.println();
        }
    }

    private static void registrarTicket(Scanner sc, List<Usuario> usuarios, List<Departamento> departamentos, List<Ticket> tickets) {
        System.out.println("--- Registrar Ticket ---");
        System.out.print("Correo del usuario creador: ");
        String correo = sc.nextLine().trim();
        Usuario usuario = null;
        for (Usuario u : usuarios) if (u.getCorreo().equalsIgnoreCase(correo)) { usuario = u; break; }
        if (usuario == null) { System.out.println("ERROR: No existe un usuario con ese correo. Regístrelo primero."); return; }

        System.out.print("Nombre del departamento asociado: ");
        String nombreDepto = sc.nextLine().trim();
        Departamento depto = null;
        for (Departamento d : departamentos) if (d.getNombre().equalsIgnoreCase(nombreDepto)) { depto = d; break; }
        if (depto == null) { System.out.println("ERROR: No existe ese departamento. Regístrelo primero."); return; }

        System.out.print("Asunto: ");
        String asunto = sc.nextLine().trim();
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine().trim();
        System.out.print("Estado (Nuevo/En progreso/Resuelto): ");
        String estado = sc.nextLine().trim();

        Ticket t = new Ticket(asunto, descripcion, estado, usuario, depto);
        tickets.add(t);
        System.out.println("Ticket registrado (id=" + t.getId() + ").");
    }

    private static void listarTickets(List<Ticket> tickets) {
        System.out.println("- Lista de Tickets -");
        if (tickets.isEmpty()) System.out.println("No hay tickets registrados.");
        else tickets.forEach(t -> System.out.println(t));
    }

    private static void cambiarEstadoTicket(Scanner sc, List<Ticket> tickets) {
        System.out.print("ID del ticket a actualizar: ");
        String s = sc.nextLine().trim();
        try {
            int id = Integer.parseInt(s);
            for (Ticket t : tickets) {
                if (t.getId() == id) {
                    System.out.println("Estado actual: " + t.getEstado());
                    System.out.print("Nuevo estado (Nuevo/En progreso/Resuelto): ");
                    String nuevo = sc.nextLine().trim();
                    t.setEstado(nuevo);
                    System.out.println("Estado actualizado.");
                    return;
                }
            }
            System.out.println("No se encontró ticket con ese ID.");
        } catch (NumberFormatException ex) {
            System.out.println("ID inválido.");
        }
    }

    private static void buscarTicketPorId(Scanner sc, List<Ticket> tickets) {
        System.out.print("ID a buscar: ");
        String s = sc.nextLine().trim();
        try {
            int id = Integer.parseInt(s);
            for (Ticket t : tickets) {
                if (t.getId() == id) {
                    System.out.println("Ticket encontrado: " + t);
                    return;
                }
            }
            System.out.println("No se encontró ticket con ese ID.");
        } catch (NumberFormatException ex) {
            System.out.println("ID inválido.");
        }
    }

    private static void gestionarDiccionarios(Scanner sc, DiccionarioEmocional dicEmocional, DiccionarioTecnico dicTecnico) {
        boolean volver = false;
        while (!volver) {
            System.out.println("--- Gestión de Diccionarios ---");
            System.out.println("1) Diccionario Emocional");
            System.out.println("2) Diccionario Técnico");
            System.out.println("3) Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();
            switch (op) {
                case "1": menuDiccionarioEmocional(sc, dicEmocional); break;
                case "2": menuDiccionarioTecnico(sc, dicTecnico); break;
                case "3": volver = true; break;
                default: System.out.println("Opción inválida.");
            }
            System.out.println();
        }
    }

    private static void menuDiccionarioEmocional(Scanner sc, DiccionarioEmocional dic) {
        boolean volver = false;
        while (!volver) {
            System.out.println("--- Diccionario Emocional ---");
            System.out.println("1) Agregar palabra");
            System.out.println("2) Eliminar palabra");
            System.out.println("3) Listar palabras");
            System.out.println("4) Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();
            switch (op) {
                case "1":
                    System.out.print("Palabra (única): ");
                    String pal = sc.nextLine().trim();
                    System.out.print("Emoción asociada: ");
                    String emo = sc.nextLine().trim();
                    boolean ok = dic.agregarPalabra(new PalabraEmocional(pal, emo));
                    System.out.println(ok ? "Palabra agregada." : "ERROR: La palabra ya existe.");
                    break;
                case "2":
                    System.out.print("Palabra a eliminar: ");
                    String elim = sc.nextLine().trim();
                    boolean rem = dic.eliminarPalabra(elim);
                    System.out.println(rem ? "Palabra eliminada." : "No se encontró la palabra.");
                    break;
                case "3":
                    List<PalabraEmocional> listE = dic.listarPalabras();
                    if (listE.isEmpty()) System.out.println("No hay palabras registradas.");
                    else listE.forEach(System.out::println);
                    break;
                case "4": volver = true; break;
                default: System.out.println("Opción inválida.");
            }
            System.out.println();
        }
    }

    private static void menuDiccionarioTecnico(Scanner sc, DiccionarioTecnico dic) {
        boolean volver = false;
        while (!volver) {
            System.out.println("--- Diccionario Técnico ---");
            System.out.println("1) Agregar palabra técnica");
            System.out.println("2) Eliminar palabra técnica");
            System.out.println("3) Listar palabras técnicas");
            System.out.println("4) Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();
            switch (op) {
                case "1":
                    System.out.print("Palabra (única): ");
                    String pal = sc.nextLine().trim();
                    System.out.print("Categoría técnica: ");
                    String cat = sc.nextLine().trim();
                    boolean ok = dic.agregarPalabra(new PalabraTecnica(pal, cat));
                    System.out.println(ok ? "Palabra técnica agregada." : "ERROR: La palabra ya existe.");
                    break;
                case "2":
                    System.out.print("Palabra a eliminar: ");
                    String elim = sc.nextLine().trim();
                    boolean rem = dic.eliminarPalabra(elim);
                    System.out.println(rem ? "Palabra eliminada." : "No se encontró la palabra.");
                    break;
                case "3":
                    List<PalabraTecnica> listT = dic.listarPalabras();
                    if (listT.isEmpty()) System.out.println("No hay palabras registradas.");
                    else listT.forEach(System.out::println);
                    break;
                case "4": volver = true; break;
                default: System.out.println("Opción inválida.");
            }
            System.out.println();
        }
    }

    private static void realizarAnalisis(Scanner sc, List<Ticket> tickets, DiccionarioEmocional dicEmocional, DiccionarioTecnico dicTecnico) {
        System.out.println("--- Análisis BoW ---");
        System.out.print("ID del ticket a analizar: ");
        String s = sc.nextLine().trim();
        try {
            int id = Integer.parseInt(s);
            Ticket ticket = null;
            for (Ticket t : tickets) if (t.getId() == id) { ticket = t; break; }
            if (ticket == null) { System.out.println("No se encontró ticket con ese ID."); return; }

            System.out.println("Ticket seleccionado:");
            System.out.println(ticket);
            System.out.println();

            BagOfWords.AnalysisResult ar = BagOfWords.analyzeEmotion(ticket.getDescripcion(), dicEmocional);
            if (ar.getEmocionesOrdenadas().isEmpty()) {
                System.out.println("No se detectaron emociones con las palabras del diccionario emocional.");
            } else {
                System.out.println("Emociones detectadas (ordenadas por relevancia): " + ar.getEmocionesOrdenadas());
                System.out.println("Palabras detonantes por emoción:");
                ar.getEmocionDetonantes().forEach((emo, palabras) -> System.out.println(" - " + emo + ": " + palabras));
            }

            System.out.println();

            BagOfWords.TechnicalResult tr = BagOfWords.analyzeTechnical(ticket.getDescripcion(), dicTecnico);
            if (tr.getCategoriasOrdenadas().isEmpty()) {
                System.out.println("No se sugirió categoría técnica con las palabras del diccionario técnico.");
            } else {
                System.out.println("Categorías técnicas sugeridas (ordenadas por relevancia): " + tr.getCategoriasOrdenadas());
                System.out.println("Puntuaciones por categoría: " + tr.getCategoriaPuntuaciones());
                System.out.println("Palabras detonantes por categoría:");
                tr.getCategoriaDetonantes().forEach((cat, palabras) -> System.out.println(" - " + cat + ": " + palabras));
            }

        } catch (NumberFormatException ex) {
            System.out.println("ID inválido.");
        }
    }

    private static void poblacionInicial(List<Usuario> usuarios, List<Departamento> departamentos,
                                         DiccionarioEmocional dicEmocional, DiccionarioTecnico dicTecnico) {

        usuarios.add(new Usuario("Ana Pérez", "ana.perez@example.com", "pass1", "88881234", "Estudiante"));
        usuarios.add(new Usuario("Carlos López", "carlos.lopez@example.com", "pass2", "88884321", "Funcionario"));

        departamentos.add(new Departamento("Soporte TI", "Atiende problemas de hardware y software", "soporte@uni.edu"));
        departamentos.add(new Departamento("Impresiones", "Soporte de impresoras y consumibles", "impresoras@uni.edu"));

        dicEmocional.agregarPalabra(new PalabraEmocional("enojado", "Frustración"));
        dicEmocional.agregarPalabra(new PalabraEmocional("urgente", "Urgencia"));
        dicEmocional.agregarPalabra(new PalabraEmocional("feliz", "Positivo"));

        dicTecnico.agregarPalabra(new PalabraTecnica("imprimir", "Impresoras"));
        dicTecnico.agregarPalabra(new PalabraTecnica("papel", "Impresoras"));
        dicTecnico.agregarPalabra(new PalabraTecnica("wifi", "Redes"));
    }
}
