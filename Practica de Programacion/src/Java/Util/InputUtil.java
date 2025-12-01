package util;

import java.util.Scanner;

public class InputUtil {
    public static int leerEntero(Scanner sc, int min, int max) {
        while (true) {
            try {
                String line = sc.nextLine();
                int val = Integer.parseInt(line.trim());
                if (val < min || val > max) {
                    System.out.print("Valor inválido. Intente de nuevo: ");
                    continue;
                }
                return val;
            } catch (NumberFormatException ex) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }

    public static double leerDouble(Scanner sc) {
        while (true) {
            try {
                String line = sc.nextLine();
                double val = Double.parseDouble(line.trim());
                return val;
            } catch (NumberFormatException ex) {
                System.out.print("Entrada inválida. Ingrese un número decimal: ");
            }
        }
    }
}
