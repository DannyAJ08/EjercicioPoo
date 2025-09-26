
public class Main {
    public static void main(String[] args) {

    Cliente cliente1 = new Cliente( "Danny", " Arias", " Jiménez", " Hombre", "Desamparados", "119680561" );


    Cliente cliente2 = new Cliente( " Antony", "Arias", "Jiménez", "Desamparados", "119680461");

    Cliente cliente3 = new Cliente();

    Subscripcion sub1 = new Subscripcion("Plata", 9.96, "Mensual");

    Subscripcion sub2 = new Subscripcion("Oro", 27.89, "Trimestral");

    Subscripcion sub3 = new Subscripcion("Oro", 99.62, "Anual");

    cliente1.suscribirse(sub1);
    cliente2.suscribirse(sub2);
    cliente3.suscribirse(new Subscripcion("Diamante", 99.62, "Anual"));

    System.out.println(cliente1);
    System.out.println(cliente2);

    System.out.println(cliente1.equals(cliente2));

    System.out.println(sub1);
        System.out.println(sub2);
    System.out.println(sub1.equals(sub2));

    }

}