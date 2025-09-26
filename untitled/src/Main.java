import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    Comida comida = new Comida("Ensalada", "Sopa", "Espagetti", "Arroz con Pollo");
    Ingredientes ingredientes = new Ingredientes();

    System.out.println("=== Menú de Comidas ===");
    System.out.println("1. " + comida.getEnsalada());
    System.out.println("2. " + comida.getSopa());
    System.out.println("3. " + comida.getEspagetti());
    System.out.println("4. " + comida.getArrozPollo());
    System.out.print("Selecciona el número de la comida que quieres conocer: ");
    int opc = Integer.parseInt(in.readLine());

    String seleccion = "";
    switch (opc){
        case 1: seleccion = comida.getEnsalada(); break;
        case 2: seleccion = comida.getSopa(); break;
        case 3: seleccion = comida.getEspagetti(); break;
        case 4: seleccion = comida.getArrozPollo(); break;
        default:
            System.out.println("Opción inválida.");
    }
        System.out.println(ingredientes.getIngredientes(seleccion));



    }
}