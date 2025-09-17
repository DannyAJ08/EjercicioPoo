//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

    Cliente cliente1 = new Cliente( "Danny", " Arias", " Jiménez", " Hombre", "Desamparados", 119680561 ){


    };

    Cliente cliente2 = new Cliente( " Antony", "Arias", "Jiménez", "Desamparados", 119680561){

    };

    Cliente cliente3 = new Cliente(){

    };

    Subscripcion sub1 = new Subscripcion("Plata", 9.96, "Mensual"){

    };

    Subscripcion sub2 = new Subscripcion("Oro", 27.89, "Trimestral"){

    };

    Subscripcion sub3 = new Subscripcion("Oro", 99.62, "Anual"){


    };

    cliente1.suscribirse(sub1);
    cliente2.suscribirse(sub2);
    cliente3.suscribirse(new Subscripcion("Diamante", 99.62, "Anual"));
    }
}