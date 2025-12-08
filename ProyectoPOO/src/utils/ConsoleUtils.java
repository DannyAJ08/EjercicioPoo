package utils;

import java.util.Scanner;

public class ConsoleUtils {
    private static Scanner scanner = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printHeader(String title) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(" " + title);
        System.out.println("=".repeat(50));
    }

    public static void printSubHeader(String subtitle) {
        System.out.println("\n" + "-".repeat(40));
        System.out.println(" " + subtitle);
        System.out.println("-".repeat(40));
    }

    public static void pressEnterToContinue() {
        System.out.print("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    public static String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        }
    }

    public static boolean confirm(String message) {
        System.out.print(message + " (s/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("s") || response.equals("si") || response.equals("sí");
    }

    public static void printSuccess(String message) {
        System.out.println("✓ " + message);
    }

    public static void printError(String message) {
        System.out.println("✗ Error: " + message);
    }

    public static void printWarning(String message) {
        System.out.println("⚠ " + message);
    }
}