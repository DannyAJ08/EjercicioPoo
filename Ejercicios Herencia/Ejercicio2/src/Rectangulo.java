public class Rectangulo extends Figura{

    private double base;
    private double altura;


    public Rectangulo(String nombre, double base, double altura) {
        super(nombre);
        this.base=base;
        this.altura=altura;
    }

    public double calcularPerimetro(){
        return 2*(base+altura);
    }


    public double calcularArea() {
        return base*altura;
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }
}
