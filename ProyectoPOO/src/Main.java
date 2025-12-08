import ui.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("=== SISTEMA MESA DE AYUDA CENFOTEC ===");
            System.out.println("Iniciando aplicación...\n");

            MenuPrincipal menu = new MenuPrincipal();
            menu.iniciar();

        } catch (Exception e) {
            System.err.println("Error crítico: " + e.getMessage());
            e.printStackTrace();
        }
    }
}