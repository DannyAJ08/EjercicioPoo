public class Circulo extends Figura{

    private float radio;



    public Circulo(String nombre, float radio) {
        super(nombre);

        this.radio=radio;
    }


    public double calcularArea() {
        return (radio*radio)* Math.PI;
    }


    public double calcularPerimetro() {
        return 2*Math.PI*radio;
    }

    public double getRadio(){
        return radio;
    }


}
