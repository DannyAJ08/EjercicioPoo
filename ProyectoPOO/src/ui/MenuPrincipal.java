package ui;

import model.*;
import service.*;
import utils.ConsoleUtils;
import utils.DatabaseConnection;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner;
    private UsuarioService usuarioService;
    private DepartamentoService departamentoService;
    private TicketService ticketService;
    private DiccionarioService diccionarioService;
    private BoWService bowService;

    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
        this.usuarioService = new UsuarioService();
        this.departamentoService = new DepartamentoService();
        this.ticketService = new TicketService();
        this.diccionarioService = new DiccionarioService();
        this.bowService = new BoWService();
    }

    public void iniciar() {
        // Test de conexión a BD
        DatabaseConnection.testConnection();

        boolean salir = false;

        while (!salir) {
            ConsoleUtils.clearScreen();
            mostrarMenuPrincipal();

            String opcion = ConsoleUtils.readString("Seleccione una opción");

            switch (opcion) {
                case "1":
                    menuUsuarios();
                    break;
                case "2":
                    menuDepartamentos();
                    break;
                case "3":
                    menuTickets();
                    break;
                case "4":
                    menuDiccionarios();
                    break;
                case "5":
                    analizarTicket();
                    break;
                case "6":
                    ConsoleUtils.clearScreen();
                    ConsoleUtils.printHeader("INFORMACIÓN DEL SISTEMA");
                    mostrarInfoSistema();
                    ConsoleUtils.pressEnterToContinue();
                    break;
                case "0":
                    salir = confirmarSalida();
                    break;
                default:
                    ConsoleUtils.printError("Opción inválida. Intente nuevamente.");
                    ConsoleUtils.pressEnterToContinue();
            }
        }

        System.out.println("\n¡Gracias por usar el Sistema de Mesa de Ayuda CENFOTEC!");
        scanner.close();
    }

    private void mostrarMenuPrincipal() {
        ConsoleUtils.printHeader("SISTEMA MESA DE AYUDA CENFOTEC");
        System.out.println("1. Gestión de Usuarios");
        System.out.println("2. Gestión de Departamentos");
        System.out.println("3. Gestión de Tickets");
        System.out.println("4. Gestión de Diccionarios");
        System.out.println("5. Análisis de Tickets (Bag of Words)");
        System.out.println("6. Información del Sistema");
        System.out.println("0. Salir");
        System.out.println();
    }

    // ==================== MENÚ USUARIOS ====================
    private void menuUsuarios() {
        boolean volver = false;

        while (!volver) {
            ConsoleUtils.clearScreen();
            ConsoleUtils.printHeader("GESTIÓN DE USUARIOS");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Listar todos los usuarios");
            System.out.println("3. Buscar usuario por correo");
            System.out.println("4. Actualizar usuario");
            System.out.println("5. Eliminar usuario");
            System.out.println("6. Buscar usuarios por rol");
            System.out.println("0. Volver al menú principal");
            System.out.println();

            String opcion = ConsoleUtils.readString("Seleccione una opción");

            switch (opcion) {
                case "1":
                    registrarUsuario();
                    break;
                case "2":
                    listarUsuarios();
                    break;
                case "3":
                    buscarUsuarioPorCorreo();
                    break;
                case "4":
                    actualizarUsuario();
                    break;
                case "5":
                    eliminarUsuario();
                    break;
                case "6":
                    buscarUsuariosPorRol();
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    ConsoleUtils.printError("Opción inválida");
                    ConsoleUtils.pressEnterToContinue();
            }
        }
    }

    private void registrarUsuario() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("REGISTRAR NUEVO USUARIO");

        String nombre = ConsoleUtils.readString("Nombre completo");
        String correo = ConsoleUtils.readString("Correo electrónico");
        String contrasenia = ConsoleUtils.readString("Contraseña");
        String telefono = ConsoleUtils.readString("Teléfono (opcional)");
        String rol = ConsoleUtils.readString("Rol (Administrador/Estudiante/Funcionario)");

        Usuario usuario = new Usuario(nombre, correo, contrasenia, telefono, rol);

        if (usuarioService.registrarUsuario(usuario)) {
            ConsoleUtils.printSuccess("Usuario registrado exitosamente!");
        } else {
            ConsoleUtils.printError("No se pudo registrar el usuario. Verifique los datos.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void listarUsuarios() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("LISTA DE USUARIOS");

        List<Usuario> usuarios = usuarioService.listarUsuarios();

        if (usuarios.isEmpty()) {
            ConsoleUtils.printWarning("No hay usuarios registrados en el sistema.");
        } else {
            System.out.println("Total de usuarios: " + usuarios.size());
            System.out.println();

            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println((i + 1) + ". " + usuarios.get(i));
            }
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void buscarUsuarioPorCorreo() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("BUSCAR USUARIO POR CORREO");

        String correo = ConsoleUtils.readString("Ingrese el correo a buscar");
        Usuario usuario = usuarioService.buscarUsuarioPorCorreo(correo);

        if (usuario != null) {
            ConsoleUtils.printSuccess("Usuario encontrado:");
            System.out.println(usuario);
        } else {
            ConsoleUtils.printError("No se encontró ningún usuario con ese correo.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void actualizarUsuario() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("ACTUALIZAR USUARIO");

        String correo = ConsoleUtils.readString("Ingrese el correo del usuario a actualizar");
        Usuario usuario = usuarioService.buscarUsuarioPorCorreo(correo);

        if (usuario == null) {
            ConsoleUtils.printError("No se encontró ningún usuario con ese correo.");
            ConsoleUtils.pressEnterToContinue();
            return;
        }

        System.out.println("\nUsuario actual:");
        System.out.println(usuario);
        System.out.println();

        String nuevoNombre = ConsoleUtils.readString("Nuevo nombre completo (actual: " + usuario.getNombreCompleto() + ")");
        if (!nuevoNombre.isEmpty()) {
            usuario.setNombreCompleto(nuevoNombre);
        }

        String nuevaContrasenia = ConsoleUtils.readString("Nueva contraseña (dejar en blanco para mantener)");
        if (!nuevaContrasenia.isEmpty()) {
            usuario.setContrasenia(nuevaContrasenia);
        }

        String nuevoTelefono = ConsoleUtils.readString("Nuevo teléfono (actual: " + usuario.getTelefono() + ")");
        usuario.setTelefono(nuevoTelefono);

        String nuevoRol = ConsoleUtils.readString("Nuevo rol (actual: " + usuario.getRol() + ")");
        if (!nuevoRol.isEmpty()) {
            usuario.setRol(nuevoRol);
        }

        if (usuarioService.actualizarUsuario(usuario)) {
            ConsoleUtils.printSuccess("Usuario actualizado exitosamente!");
        } else {
            ConsoleUtils.printError("No se pudo actualizar el usuario.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void eliminarUsuario() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("ELIMINAR USUARIO");

        String correo = ConsoleUtils.readString("Ingrese el correo del usuario a eliminar");

        if (ConsoleUtils.confirm("¿Está seguro de eliminar al usuario " + correo + "?")) {
            if (usuarioService.eliminarUsuario(correo)) {
                ConsoleUtils.printSuccess("Usuario eliminado exitosamente!");
            } else {
                ConsoleUtils.printError("No se pudo eliminar el usuario. Verifique que exista.");
            }
        } else {
            ConsoleUtils.printWarning("Operación cancelada.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void buscarUsuariosPorRol() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("BUSCAR USUARIOS POR ROL");

        String rol = ConsoleUtils.readString("Ingrese el rol a buscar (Administrador/Estudiante/Funcionario)");
        List<Usuario> usuarios = usuarioService.buscarUsuariosPorRol(rol);

        if (usuarios.isEmpty()) {
            ConsoleUtils.printWarning("No hay usuarios con el rol: " + rol);
        } else {
            System.out.println("Usuarios encontrados (" + usuarios.size() + "):");
            System.out.println();

            for (Usuario usuario : usuarios) {
                System.out.println("• " + usuario);
            }
        }

        ConsoleUtils.pressEnterToContinue();
    }

    // ==================== MENÚ DEPARTAMENTOS ====================
    private void menuDepartamentos() {
        boolean volver = false;

        while (!volver) {
            ConsoleUtils.clearScreen();
            ConsoleUtils.printHeader("GESTIÓN DE DEPARTAMENTOS");
            System.out.println("1. Registrar nuevo departamento");
            System.out.println("2. Listar todos los departamentos");
            System.out.println("3. Buscar departamento por nombre");
            System.out.println("4. Actualizar departamento");
            System.out.println("5. Eliminar departamento");
            System.out.println("0. Volver al menú principal");
            System.out.println();

            String opcion = ConsoleUtils.readString("Seleccione una opción");

            switch (opcion) {
                case "1":
                    registrarDepartamento();
                    break;
                case "2":
                    listarDepartamentos();
                    break;
                case "3":
                    buscarDepartamentoPorNombre();
                    break;
                case "4":
                    actualizarDepartamento();
                    break;
                case "5":
                    eliminarDepartamento();
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    ConsoleUtils.printError("Opción inválida");
                    ConsoleUtils.pressEnterToContinue();
            }
        }
    }

    private void registrarDepartamento() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("REGISTRAR NUEVO DEPARTAMENTO");

        String nombre = ConsoleUtils.readString("Nombre del departamento");
        String descripcion = ConsoleUtils.readString("Descripción");
        String contacto = ConsoleUtils.readString("Correo de contacto");

        Departamento departamento = new Departamento(nombre, descripcion, contacto);

        if (departamentoService.registrarDepartamento(departamento)) {
            ConsoleUtils.printSuccess("Departamento registrado exitosamente!");
        } else {
            ConsoleUtils.printError("No se pudo registrar el departamento. Verifique los datos.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void listarDepartamentos() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("LISTA DE DEPARTAMENTOS");

        List<Departamento> departamentos = departamentoService.listarDepartamentos();

        if (departamentos.isEmpty()) {
            ConsoleUtils.printWarning("No hay departamentos registrados en el sistema.");
        } else {
            System.out.println("Total de departamentos: " + departamentos.size());
            System.out.println();

            for (int i = 0; i < departamentos.size(); i++) {
                System.out.println((i + 1) + ". " + departamentos.get(i));
                System.out.println();
            }
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void buscarDepartamentoPorNombre() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("BUSCAR DEPARTAMENTO POR NOMBRE");

        String nombre = ConsoleUtils.readString("Ingrese el nombre del departamento a buscar");
        Departamento departamento = departamentoService.buscarDepartamentoPorNombre(nombre);

        if (departamento != null) {
            ConsoleUtils.printSuccess("Departamento encontrado:");
            System.out.println(departamento);
        } else {
            ConsoleUtils.printError("No se encontró ningún departamento con ese nombre.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void actualizarDepartamento() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("ACTUALIZAR DEPARTAMENTO");

        String nombre = ConsoleUtils.readString("Ingrese el nombre del departamento a actualizar");
        Departamento departamento = departamentoService.buscarDepartamentoPorNombre(nombre);

        if (departamento == null) {
            ConsoleUtils.printError("No se encontró ningún departamento con ese nombre.");
            ConsoleUtils.pressEnterToContinue();
            return;
        }

        System.out.println("\nDepartamento actual:");
        System.out.println(departamento);
        System.out.println();

        String nuevaDescripcion = ConsoleUtils.readString("Nueva descripción (actual: " + departamento.getDescripcion() + ")");
        if (!nuevaDescripcion.isEmpty()) {
            departamento.setDescripcion(nuevaDescripcion);
        }

        String nuevoContacto = ConsoleUtils.readString("Nuevo contacto (actual: " + departamento.getContacto() + ")");
        if (!nuevoContacto.isEmpty()) {
            departamento.setContacto(nuevoContacto);
        }

        if (departamentoService.actualizarDepartamento(departamento)) {
            ConsoleUtils.printSuccess("Departamento actualizado exitosamente!");
        } else {
            ConsoleUtils.printError("No se pudo actualizar el departamento.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void eliminarDepartamento() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("ELIMINAR DEPARTAMENTO");

        String nombre = ConsoleUtils.readString("Ingrese el nombre del departamento a eliminar");

        if (ConsoleUtils.confirm("¿Está seguro de eliminar el departamento " + nombre + "?")) {
            if (departamentoService.eliminarDepartamento(nombre)) {
                ConsoleUtils.printSuccess("Departamento eliminado exitosamente!");
            } else {
                ConsoleUtils.printError("No se pudo eliminar el departamento. Verifique que exista.");
            }
        } else {
            ConsoleUtils.printWarning("Operación cancelada.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    // ==================== MENÚ TICKETS ====================
    private void menuTickets() {
        boolean volver = false;

        while (!volver) {
            ConsoleUtils.clearScreen();
            ConsoleUtils.printHeader("GESTIÓN DE TICKETS");
            System.out.println("1. Registrar nuevo ticket");
            System.out.println("2. Listar todos los tickets");
            System.out.println("3. Buscar ticket por ID");
            System.out.println("4. Buscar tickets por estado");
            System.out.println("5. Buscar tickets por usuario");
            System.out.println("6. Cambiar estado de ticket");
            System.out.println("7. Actualizar ticket");
            System.out.println("8. Eliminar ticket");
            System.out.println("0. Volver al menú principal");
            System.out.println();

            String opcion = ConsoleUtils.readString("Seleccione una opción");

            switch (opcion) {
                case "1":
                    registrarTicket();
                    break;
                case "2":
                    listarTickets();
                    break;
                case "3":
                    buscarTicketPorId();
                    break;
                case "4":
                    buscarTicketsPorEstado();
                    break;
                case "5":
                    buscarTicketsPorUsuario();
                    break;
                case "6":
                    cambiarEstadoTicket();
                    break;
                case "7":
                    actualizarTicket();
                    break;
                case "8":
                    eliminarTicket();
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    ConsoleUtils.printError("Opción inválida");
                    ConsoleUtils.pressEnterToContinue();
            }
        }
    }

    private void registrarTicket() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("REGISTRAR NUEVO TICKET");

        // Primero listar usuarios para referencia
        System.out.println("Usuarios disponibles:");
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        if (usuarios.isEmpty()) {
            ConsoleUtils.printError("No hay usuarios registrados. Debe registrar al menos un usuario primero.");
            ConsoleUtils.pressEnterToContinue();
            return;
        }

        for (Usuario u : usuarios) {
            System.out.println("• " + u.getCorreo() + " - " + u.getNombreCompleto());
        }
        System.out.println();

        // Listar departamentos
        System.out.println("Departamentos disponibles:");
        List<Departamento> departamentos = departamentoService.listarDepartamentos();
        if (departamentos.isEmpty()) {
            ConsoleUtils.printError("No hay departamentos registrados. Debe registrar al menos un departamento primero.");
            ConsoleUtils.pressEnterToContinue();
            return;
        }

        for (Departamento d : departamentos) {
            System.out.println("• " + d.getNombre());
        }
        System.out.println();

        String correoUsuario = ConsoleUtils.readString("Correo del usuario que reporta");
        Usuario usuario = usuarioService.buscarUsuarioPorCorreo(correoUsuario);

        if (usuario == null) {
            ConsoleUtils.printError("No se encontró el usuario con correo: " + correoUsuario);
            ConsoleUtils.pressEnterToContinue();
            return;
        }

        String nombreDepto = ConsoleUtils.readString("Nombre del departamento asignado");
        Departamento departamento = departamentoService.buscarDepartamentoPorNombre(nombreDepto);

        if (departamento == null) {
            ConsoleUtils.printError("No se encontró el departamento: " + nombreDepto);
            ConsoleUtils.pressEnterToContinue();
            return;
        }

        String asunto = ConsoleUtils.readString("Asunto del ticket");
        String descripcion = ConsoleUtils.readString("Descripción detallada");
        String estado = ConsoleUtils.readString("Estado inicial (Nuevo/En progreso/Resuelto)");

        Ticket ticket = new Ticket(asunto, descripcion, estado, usuario, departamento);

        if (ticketService.registrarTicket(ticket)) {
            ConsoleUtils.printSuccess("Ticket registrado exitosamente! ID: " + ticket.getId());
        } else {
            ConsoleUtils.printError("No se pudo registrar el ticket.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void listarTickets() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("LISTA DE TICKETS");

        List<Ticket> tickets = ticketService.listarTickets();

        if (tickets.isEmpty()) {
            ConsoleUtils.printWarning("No hay tickets registrados en el sistema.");
        } else {
            System.out.println("Total de tickets: " + tickets.size());
            System.out.println();

            for (Ticket ticket : tickets) {
                System.out.println(ticket);
                System.out.println();
            }
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void buscarTicketPorId() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("BUSCAR TICKET POR ID");

        int id = ConsoleUtils.readInt("Ingrese el ID del ticket a buscar");
        Ticket ticket = ticketService.buscarTicketPorId(id);

        if (ticket != null) {
            ConsoleUtils.printSuccess("Ticket encontrado:");
            System.out.println(ticket);
            System.out.println("\nDescripción completa:");
            System.out.println(ticket.getDescripcion());
        } else {
            ConsoleUtils.printError("No se encontró ningún ticket con ID: " + id);
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void buscarTicketsPorEstado() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("BUSCAR TICKETS POR ESTADO");

        System.out.println("Estados disponibles: Nuevo, En progreso, Resuelto");
        String estado = ConsoleUtils.readString("Ingrese el estado a buscar");

        List<Ticket> tickets = ticketService.buscarTicketsPorEstado(estado);

        if (tickets.isEmpty()) {
            ConsoleUtils.printWarning("No hay tickets con estado: " + estado);
        } else {
            System.out.println("\nTickets encontrados (" + tickets.size() + "):");
            System.out.println();

            for (Ticket ticket : tickets) {
                System.out.println(ticket);
                System.out.println();
            }
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void buscarTicketsPorUsuario() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("BUSCAR TICKETS POR USUARIO");

        String correo = ConsoleUtils.readString("Ingrese el correo del usuario");
        List<Ticket> tickets = ticketService.buscarTicketsPorUsuario(correo);

        if (tickets.isEmpty()) {
            ConsoleUtils.printWarning("No hay tickets para el usuario: " + correo);
        } else {
            System.out.println("\nTickets encontrados (" + tickets.size() + "):");
            System.out.println();

            for (Ticket ticket : tickets) {
                System.out.println(ticket);
                System.out.println();
            }
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void cambiarEstadoTicket() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("CAMBIAR ESTADO DE TICKET");

        int id = ConsoleUtils.readInt("Ingrese el ID del ticket");
        Ticket ticket = ticketService.buscarTicketPorId(id);

        if (ticket == null) {
            ConsoleUtils.printError("No se encontró el ticket con ID: " + id);
            ConsoleUtils.pressEnterToContinue();
            return;
        }

        System.out.println("\nTicket actual:");
        System.out.println(ticket);
        System.out.println("\nEstado actual: " + ticket.getEstado());

        System.out.println("\nEstados disponibles:");
        System.out.println("1. Nuevo");
        System.out.println("2. En progreso");
        System.out.println("3. Resuelto");

        int opcionEstado = ConsoleUtils.readInt("Seleccione el nuevo estado (1-3)");

        String nuevoEstado;
        switch (opcionEstado) {
            case 1:
                nuevoEstado = "Nuevo";
                break;
            case 2:
                nuevoEstado = "En progreso";
                break;
            case 3:
                nuevoEstado = "Resuelto";
                break;
            default:
                ConsoleUtils.printError("Opción inválida. Manteniendo estado actual.");
                ConsoleUtils.pressEnterToContinue();
                return;
        }

        if (ticketService.cambiarEstadoTicket(id, nuevoEstado)) {
            ConsoleUtils.printSuccess("Estado del ticket actualizado a: " + nuevoEstado);
        } else {
            ConsoleUtils.printError("No se pudo actualizar el estado del ticket.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void actualizarTicket() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("ACTUALIZAR TICKET");

        int id = ConsoleUtils.readInt("Ingrese el ID del ticket a actualizar");
        Ticket ticket = ticketService.buscarTicketPorId(id);

        if (ticket == null) {
            ConsoleUtils.printError("No se encontró el ticket con ID: " + id);
            ConsoleUtils.pressEnterToContinue();
            return;
        }

        System.out.println("\nTicket actual:");
        System.out.println(ticket);
        System.out.println();

        String nuevoAsunto = ConsoleUtils.readString("Nuevo asunto (actual: " + ticket.getAsunto() + ")");
        if (!nuevoAsunto.isEmpty()) {
            ticket.setAsunto(nuevoAsunto);
        }

        String nuevaDescripcion = ConsoleUtils.readString("Nueva descripción (dejar en blanco para mantener)");
        if (!nuevaDescripcion.isEmpty()) {
            ticket.setDescripcion(nuevaDescripcion);
        }

        String nuevoEstado = ConsoleUtils.readString("Nuevo estado (actual: " + ticket.getEstado() + ")");
        if (!nuevoEstado.isEmpty()) {
            ticket.setEstado(nuevoEstado);
        }

        // Actualizar usuario si es necesario
        if (ConsoleUtils.confirm("¿Desea cambiar el usuario asignado al ticket?")) {
            String nuevoCorreo = ConsoleUtils.readString("Nuevo correo del usuario");
            Usuario nuevoUsuario = usuarioService.buscarUsuarioPorCorreo(nuevoCorreo);
            if (nuevoUsuario != null) {
                ticket.setUsuario(nuevoUsuario);
            } else {
                ConsoleUtils.printWarning("Usuario no encontrado. Manteniendo usuario actual.");
            }
        }

        // Actualizar departamento si es necesario
        if (ConsoleUtils.confirm("¿Desea cambiar el departamento asignado al ticket?")) {
            String nuevoDepto = ConsoleUtils.readString("Nuevo nombre del departamento");
            Departamento nuevoDepartamento = departamentoService.buscarDepartamentoPorNombre(nuevoDepto);
            if (nuevoDepartamento != null) {
                ticket.setDepartamento(nuevoDepartamento);
            } else {
                ConsoleUtils.printWarning("Departamento no encontrado. Manteniendo departamento actual.");
            }
        }

        if (ticketService.actualizarTicket(ticket)) {
            ConsoleUtils.printSuccess("Ticket actualizado exitosamente!");
        } else {
            ConsoleUtils.printError("No se pudo actualizar el ticket.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void eliminarTicket() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("ELIMINAR TICKET");

        int id = ConsoleUtils.readInt("Ingrese el ID del ticket a eliminar");

        if (ConsoleUtils.confirm("¿Está seguro de eliminar el ticket #" + id + "?")) {
            if (ticketService.eliminarTicket(id)) {
                ConsoleUtils.printSuccess("Ticket eliminado exitosamente!");
            } else {
                ConsoleUtils.printError("No se pudo eliminar el ticket. Verifique que exista.");
            }
        } else {
            ConsoleUtils.printWarning("Operación cancelada.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    // ==================== MENÚ DICCIONARIOS ====================
    private void menuDiccionarios() {
        boolean volver = false;

        while (!volver) {
            ConsoleUtils.clearScreen();
            ConsoleUtils.printHeader("GESTIÓN DE DICCIONARIOS");
            System.out.println("1. Diccionario Emocional");
            System.out.println("2. Diccionario Técnico");
            System.out.println("0. Volver al menú principal");
            System.out.println();

            String opcion = ConsoleUtils.readString("Seleccione una opción");

            switch (opcion) {
                case "1":
                    menuDiccionarioEmocional();
                    break;
                case "2":
                    menuDiccionarioTecnico();
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    ConsoleUtils.printError("Opción inválida");
                    ConsoleUtils.pressEnterToContinue();
            }
        }
    }

    private void menuDiccionarioEmocional() {
        boolean volver = false;

        while (!volver) {
            ConsoleUtils.clearScreen();
            ConsoleUtils.printHeader("DICCIONARIO EMOCIONAL");
            System.out.println("1. Agregar palabra emocional");
            System.out.println("2. Listar palabras emocionales");
            System.out.println("3. Buscar palabra emocional");
            System.out.println("4. Actualizar palabra emocional");
            System.out.println("5. Eliminar palabra emocional");
            System.out.println("6. Ver emociones únicas");
            System.out.println("0. Volver");
            System.out.println();

            String opcion = ConsoleUtils.readString("Seleccione una opción");

            switch (opcion) {
                case "1":
                    agregarPalabraEmocional();
                    break;
                case "2":
                    listarPalabrasEmocionales();
                    break;
                case "3":
                    buscarPalabraEmocional();
                    break;
                case "4":
                    actualizarPalabraEmocional();
                    break;
                case "5":
                    eliminarPalabraEmocional();
                    break;
                case "6":
                    verEmocionesUnicas();
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    ConsoleUtils.printError("Opción inválida");
                    ConsoleUtils.pressEnterToContinue();
            }
        }
    }

    private void agregarPalabraEmocional() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("AGREGAR PALABRA EMOCIONAL");

        String palabra = ConsoleUtils.readString("Palabra (ej: 'enojado', 'feliz', 'triste')");
        String emocion = ConsoleUtils.readString("Emoción asociada (ej: 'Frustración', 'Alegría', 'Tristeza')");

        PalabraEmocional pe = new PalabraEmocional(palabra, emocion);

        if (diccionarioService.agregarPalabraEmocional(pe)) {
            ConsoleUtils.printSuccess("Palabra emocional agregada exitosamente!");
        } else {
            ConsoleUtils.printError("No se pudo agregar la palabra. Verifique que no exista ya.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void listarPalabrasEmocionales() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("LISTA DE PALABRAS EMOCIONALES");

        List<PalabraEmocional> palabras = diccionarioService.listarPalabrasEmocionales();

        if (palabras.isEmpty()) {
            ConsoleUtils.printWarning("No hay palabras en el diccionario emocional.");
        } else {
            System.out.println("Total de palabras: " + palabras.size());
            System.out.println();

            for (int i = 0; i < palabras.size(); i++) {
                System.out.println((i + 1) + ". " + palabras.get(i));
            }
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void buscarPalabraEmocional() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("BUSCAR PALABRA EMOCIONAL");

        String palabra = ConsoleUtils.readString("Ingrese la palabra a buscar");
        PalabraEmocional pe = diccionarioService.buscarPalabraEmocional(palabra);

        if (pe != null) {
            ConsoleUtils.printSuccess("Palabra encontrada:");
            System.out.println(pe);
        } else {
            ConsoleUtils.printError("No se encontró la palabra: " + palabra);
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void actualizarPalabraEmocional() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("ACTUALIZAR PALABRA EMOCIONAL");

        String palabra = ConsoleUtils.readString("Ingrese la palabra a actualizar");
        PalabraEmocional pe = diccionarioService.buscarPalabraEmocional(palabra);

        if (pe == null) {
            ConsoleUtils.printError("No se encontró la palabra: " + palabra);
            ConsoleUtils.pressEnterToContinue();
            return;
        }

        System.out.println("\nPalabra actual:");
        System.out.println(pe);
        System.out.println();

        String nuevaEmocion = ConsoleUtils.readString("Nueva emoción (actual: " + pe.getEmocion() + ")");
        if (!nuevaEmocion.isEmpty()) {
            pe.setEmocion(nuevaEmocion);
        }

        if (diccionarioService.actualizarPalabraEmocional(pe)) {
            ConsoleUtils.printSuccess("Palabra emocional actualizada exitosamente!");
        } else {
            ConsoleUtils.printError("No se pudo actualizar la palabra.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void eliminarPalabraEmocional() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("ELIMINAR PALABRA EMOCIONAL");

        String palabra = ConsoleUtils.readString("Ingrese la palabra a eliminar");

        if (ConsoleUtils.confirm("¿Está seguro de eliminar la palabra '" + palabra + "'?")) {
            if (diccionarioService.eliminarPalabraEmocional(palabra)) {
                ConsoleUtils.printSuccess("Palabra eliminada exitosamente!");
            } else {
                ConsoleUtils.printError("No se pudo eliminar la palabra. Verifique que exista.");
            }
        } else {
            ConsoleUtils.printWarning("Operación cancelada.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void verEmocionesUnicas() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("EMOCIONES ÚNICAS EN EL DICCIONARIO");

        List<String> emociones = diccionarioService.obtenerEmocionesUnicas();

        if (emociones.isEmpty()) {
            ConsoleUtils.printWarning("No hay emociones registradas.");
        } else {
            System.out.println("Emociones encontradas (" + emociones.size() + "):");
            System.out.println();

            for (String emocion : emociones) {
                System.out.println("• " + emocion);
            }
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void menuDiccionarioTecnico() {
        boolean volver = false;

        while (!volver) {
            ConsoleUtils.clearScreen();
            ConsoleUtils.printHeader("DICCIONARIO TÉCNICO");
            System.out.println("1. Agregar palabra técnica");
            System.out.println("2. Listar palabras técnicas");
            System.out.println("3. Buscar palabra técnica");
            System.out.println("4. Actualizar palabra técnica");
            System.out.println("5. Eliminar palabra técnica");
            System.out.println("6. Ver categorías únicas");
            System.out.println("0. Volver");
            System.out.println();

            String opcion = ConsoleUtils.readString("Seleccione una opción");

            switch (opcion) {
                case "1":
                    agregarPalabraTecnica();
                    break;
                case "2":
                    listarPalabrasTecnicas();
                    break;
                case "3":
                    buscarPalabraTecnica();
                    break;
                case "4":
                    actualizarPalabraTecnica();
                    break;
                case "5":
                    eliminarPalabraTecnica();
                    break;
                case "6":
                    verCategoriasUnicas();
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    ConsoleUtils.printError("Opción inválida");
                    ConsoleUtils.pressEnterToContinue();
            }
        }
    }

    private void agregarPalabraTecnica() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("AGREGAR PALABRA TÉCNICA");

        String palabra = ConsoleUtils.readString("Palabra técnica (ej: 'wifi', 'imprimir', 'contraseña')");
        String categoria = ConsoleUtils.readString("Categoría (ej: 'Redes', 'Impresoras', 'Sistema')");

        PalabraTecnica pt = new PalabraTecnica(palabra, categoria);

        if (diccionarioService.agregarPalabraTecnica(pt)) {
            ConsoleUtils.printSuccess("Palabra técnica agregada exitosamente!");
        } else {
            ConsoleUtils.printError("No se pudo agregar la palabra. Verifique que no exista ya.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void listarPalabrasTecnicas() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("LISTA DE PALABRAS TÉCNICAS");

        List<PalabraTecnica> palabras = diccionarioService.listarPalabrasTecnicas();

        if (palabras.isEmpty()) {
            ConsoleUtils.printWarning("No hay palabras en el diccionario técnico.");
        } else {
            System.out.println("Total de palabras: " + palabras.size());
            System.out.println();

            for (int i = 0; i < palabras.size(); i++) {
                System.out.println((i + 1) + ". " + palabras.get(i));
            }
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void buscarPalabraTecnica() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("BUSCAR PALABRA TÉCNICA");

        String palabra = ConsoleUtils.readString("Ingrese la palabra a buscar");
        PalabraTecnica pt = diccionarioService.buscarPalabraTecnica(palabra);

        if (pt != null) {
            ConsoleUtils.printSuccess("Palabra encontrada:");
            System.out.println(pt);
        } else {
            ConsoleUtils.printError("No se encontró la palabra: " + palabra);
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void actualizarPalabraTecnica() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("ACTUALIZAR PALABRA TÉCNICA");

        String palabra = ConsoleUtils.readString("Ingrese la palabra a actualizar");
        PalabraTecnica pt = diccionarioService.buscarPalabraTecnica(palabra);

        if (pt == null) {
            ConsoleUtils.printError("No se encontró la palabra: " + palabra);
            ConsoleUtils.pressEnterToContinue();
            return;
        }

        System.out.println("\nPalabra actual:");
        System.out.println(pt);
        System.out.println();

        String nuevaCategoria = ConsoleUtils.readString("Nueva categoría (actual: " + pt.getCategoria() + ")");
        if (!nuevaCategoria.isEmpty()) {
            pt.setCategoria(nuevaCategoria);
        }

        if (diccionarioService.actualizarPalabraTecnica(pt)) {
            ConsoleUtils.printSuccess("Palabra técnica actualizada exitosamente!");
        } else {
            ConsoleUtils.printError("No se pudo actualizar la palabra.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void eliminarPalabraTecnica() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("ELIMINAR PALABRA TÉCNICA");

        String palabra = ConsoleUtils.readString("Ingrese la palabra a eliminar");

        if (ConsoleUtils.confirm("¿Está seguro de eliminar la palabra '" + palabra + "'?")) {
            if (diccionarioService.eliminarPalabraTecnica(palabra)) {
                ConsoleUtils.printSuccess("Palabra eliminada exitosamente!");
            } else {
                ConsoleUtils.printError("No se pudo eliminar la palabra. Verifique que exista.");
            }
        } else {
            ConsoleUtils.printWarning("Operación cancelada.");
        }

        ConsoleUtils.pressEnterToContinue();
    }

    private void verCategoriasUnicas() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("CATEGORÍAS ÚNICAS EN EL DICCIONARIO");

        List<String> categorias = diccionarioService.obtenerCategoriasUnicas();

        if (categorias.isEmpty()) {
            ConsoleUtils.printWarning("No hay categorías registradas.");
        } else {
            System.out.println("Categorías encontradas (" + categorias.size() + "):");
            System.out.println();

            for (String categoria : categorias) {
                System.out.println("• " + categoria);
            }
        }

        ConsoleUtils.pressEnterToContinue();
    }

    // ==================== ANÁLISIS BAG OF WORDS ====================
    private void analizarTicket() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("ANÁLISIS DE TICKET - BAG OF WORDS");

        int id = ConsoleUtils.readInt("Ingrese el ID del ticket a analizar");
        Ticket ticket = ticketService.buscarTicketPorId(id);

        if (ticket == null) {
            ConsoleUtils.printError("No se encontró el ticket con ID: " + id);
            ConsoleUtils.pressEnterToContinue();
            return;
        }

        System.out.println("\n=== TICKET SELECCIONADO ===");
        System.out.println(ticket);
        System.out.println("\nDescripción:");
        System.out.println(ticket.getDescripcion());
        System.out.println();

        ConsoleUtils.printSubHeader("ANÁLISIS EMOCIONAL");
        List<String> emociones = bowService.obtenerEmocionesOrdenadas(ticket.getDescripcion());

        if (emociones.isEmpty()) {
            ConsoleUtils.printWarning("No se detectaron emociones en la descripción.");
        } else {
            System.out.println("Emociones detectadas (ordenadas por frecuencia):");
            for (String emocion : emociones) {
                System.out.println("• " + emocion);
            }
        }

        ConsoleUtils.printSubHeader("ANÁLISIS TÉCNICO");
        List<String> categorias = bowService.obtenerCategoriasOrdenadas(ticket.getDescripcion());

        if (categorias.isEmpty()) {
            ConsoleUtils.printWarning("No se detectaron categorías técnicas en la descripción.");
        } else {
            System.out.println("Categorías técnicas detectadas (ordenadas por frecuencia):");
            for (String categoria : categorias) {
                System.out.println("• " + categoria);
            }
        }

        ConsoleUtils.printSubHeader("RECOMENDACIÓN");
        if (!categorias.isEmpty()) {
            String categoriaPrincipal = categorias.get(0).split(" \\(")[0];
            System.out.println("Categoría principal sugerida: " + categoriaPrincipal);

            // Buscar departamentos que podrían atender esta categoría
            List<Departamento> deptos = departamentoService.listarDepartamentos();
            if (!deptos.isEmpty()) {
                System.out.println("\nDepartamentos disponibles para esta categoría:");
                for (Departamento d : deptos) {
                    if (d.getDescripcion().toLowerCase().contains(categoriaPrincipal.toLowerCase())) {
                        System.out.println("✓ " + d.getNombre() + " - " + d.getDescripcion());
                    }
                }
            }
        }

        System.out.println();
        ConsoleUtils.pressEnterToContinue();
    }

    // ==================== INFORMACIÓN DEL SISTEMA ====================
    private void mostrarInfoSistema() {
        System.out.println("Sistema de Mesa de Ayuda CENFOTEC");
        System.out.println("Versión: 1.0 Final");
        System.out.println("Arquitectura: MVC + DAO");
        System.out.println("Base de Datos: MySQL con SQLyog");
        System.out.println("\nEstadísticas actuales:");

        int numUsuarios = usuarioService.listarUsuarios().size();
        int numDepartamentos = departamentoService.listarDepartamentos().size();
        int numTickets = ticketService.listarTickets().size();
        int numPalabrasEmocionales = diccionarioService.listarPalabrasEmocionales().size();
        int numPalabrasTecnicas = diccionarioService.listarPalabrasTecnicas().size();

        System.out.println("• Usuarios registrados: " + numUsuarios);
        System.out.println("• Departamentos: " + numDepartamentos);
        System.out.println("• Tickets: " + numTickets);
        System.out.println("• Palabras emocionales: " + numPalabrasEmocionales);
        System.out.println("• Palabras técnicas: " + numPalabrasTecnicas);

        System.out.println("\nFuncionalidades implementadas:");
        System.out.println("✓ CRUD completo para todas las entidades");
        System.out.println("✓ Persistencia en base de datos MySQL");
        System.out.println("✓ Patrón DAO para acceso a datos");
        System.out.println("✓ Patrón MVC para separación de capas");
        System.out.println("✓ Análisis Bag of Words para tickets");
        System.out.println("✓ Validación de datos y manejo de errores");
        System.out.println("✓ Interfaz de consola amigable");
    }

    private boolean confirmarSalida() {
        return ConsoleUtils.confirm("¿Está seguro de que desea salir del sistema?");
    }
}