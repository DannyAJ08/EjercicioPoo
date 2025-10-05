//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


                Cliente cliente = new Cliente("Danny Arias", "danny@gmail.com");
                Habitacion habitacion = new Habitacion(101, "Doble", 55.0);
                Reserva reserva = new Reserva(cliente, habitacion, "2025-10-10", "2025-10-15");

                System.out.println(reserva.mostrarDetalles());

    }

}