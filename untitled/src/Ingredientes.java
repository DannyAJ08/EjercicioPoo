public class Ingredientes {

    public String getIngredientes(String comida) {
        switch (comida.toLowerCase()) {
            case "ensalada":
                return "Ingredientes de la ensalada: lechuga, tomate, pepino, zanahoria, aderezo.";
            case "sopa":
                return "Ingredientes de la sopa: agua, sal, pollo, verduras (zanahoria, papa, apio).";
            case "espagetti":
                return "Ingredientes del espagueti: pasta, salsa de tomate, carne molida, queso.";
            case "arroz con pollo":
                return "Ingredientes del arroz con pollo: arroz, pollo, especias, verduras.";
            default:
                return "Esa comida no está en el menú.";
        }
    }
}
